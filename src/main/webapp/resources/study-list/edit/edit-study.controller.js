angular.module('app').controller('editStudyController', function($scope, $http, $routeParams, $location, $filter, $q) {

    $scope.study = {};
    $scope.patients = [];

    var id = $routeParams["id"];
    if (id !== undefined && id !== 'new') {
        var parseId = parseInt(id);
        if (isNaN(parseId)) {
            window.history.back();
        } else {
            var promiseStudy = $http.get('/study/' + parseId);
            var promisePatients = $http.get('patients/allList');
            $q.all([promiseStudy, promisePatients]).then(
                function success(response) {
                    $scope.patients = response[1].data;
                    $scope.study = response[0].data;
                    if ($scope.study.plannedStartTime !== undefined && $scope.study.plannedStartTime !== null) {
                        var dateAndTimeS = $scope.study.plannedStartTime.split(' ');
                        var dateS = dateAndTimeS[0].split('.');
                        var timeS = dateAndTimeS[1].split(':');
                        $scope.study.pst = new Date(dateS[2], dateS[1] - 1, dateS[0], timeS[0], timeS[1], 0, 0);
                    }
                    if ($scope.study.estimatedEndTime !== undefined && $scope.study.estimatedEndTime !== null) {
                        var dateAndTimeE = $scope.study.estimatedEndTime.split(' ');
                        var dateE = dateAndTimeE[0].split('.');
                        var timeE = dateAndTimeE[1].split(':');
                        $scope.study.eet = new Date(dateE[2], dateE[1] - 1, dateE[0], timeE[0], timeE[1], 0, 0);
                    }
                },
                function error(reason) {
                    window.history.back();
                });
        }
    } else {
        $http.get('patients/allList').then(function (response) {
            $scope.patients = response.data;
        })
    }

    $scope.study.status = 'PLANNED';

    $scope.back = function () {
        window.history.back();
    };

    $scope.saveStudy = function () {
        $scope.study.plannedStartTime = $filter('date')($scope.study.pst, 'dd.MM.yyyy HH:mm');
        $scope.study.estimatedEndTime = $filter('date')($scope.study.eet, 'dd.MM.yyyy HH:mm');
        $http.post('/study/save', $scope.study).then(function success(response) {
            $location.url('/scheduling')
        });
    };

});