angular.module('app').controller('editDoctorController', function($scope, $http, $routeParams, $location, AlertService) {

    $scope.doctor = {};

    var id = $routeParams["id"];
    if (id !== undefined && id !== 'new') {
        var parseId = parseInt(id);
        if (isNaN(parseId)) {
            window.history.back();
        } else {
            $http.get('/doctors/' + parseId).then(
                function success(response) {
                    $scope.doctor = response.data;
                },
                function error(reason) {
                    window.history.back();
                });
        }
    }

    $scope.back = function () {
        window.history.back();
    };

    $scope.saveDoctor = function () {
        $http.post('/doctors/save', $scope.doctor).then(function success(response) {
            AlertService.add('success', 'Doctor successfully added');
            $location.url('/doctors')
        });
    };

});