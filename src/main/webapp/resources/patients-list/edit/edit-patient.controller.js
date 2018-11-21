angular.module('app').controller('editPatientController', function($scope, $http, $routeParams, $location, $filter) {

    $scope.patient = {};

    var id = $routeParams["id"];
    if (id !== undefined && id !== 'new') {
        var parseId = parseInt(id)
        if (isNaN(parseId)) {
            window.history.back();
        } else {
            $http.get('/patients/' + parseId).then(
                function success(response) {
                    $scope.patient = response.data;
                    if ($scope.patient.dayOfBirth !== undefined && $scope.patient.dayOfBirth !== null) {
                        var dateArr = $scope.patient.dayOfBirth.split('.');
                        $scope.patient.db = new Date(dateArr[2], dateArr[1] - 1, dateArr[0]);
                    }
                },
                function error(reason) {
                    window.history.back();
                });
        }
    }

    $scope.back = function () {
        window.history.back();
    };

    $scope.savePatient = function () {
        $scope.patient.dayOfBirth = $filter('date')($scope.patient.db, 'dd.MM.yyyy');
        $http.post('/patients/save', $scope.patient).then(function success(response) {
            $location.url('/patients')
        });
    };

});