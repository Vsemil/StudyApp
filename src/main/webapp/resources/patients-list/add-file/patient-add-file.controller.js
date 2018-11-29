angular.module('app').controller('patientAddFileController', function($http, $scope, upload, $routeParams, $location, AlertService) {

    $scope.patient = {};

    var id = $routeParams["id"];
    if (id !== undefined) {
        var parseId = parseInt(id);
        if (isNaN(parseId)) {
            window.history.back();
        }
        $http.get('/patients/' + parseId).then(
            function success(response) {
                $scope.patient = response.data;
            },
            function error(reason) {
                window.history.back();
            });
    }

    $scope.back = function () {
        window.history.back();
    };

    $scope.saveFile = function () {
        upload({
            url: '/patients/addFiles',
            method: 'POST',
            data: {
                patientId: $scope.patient.id,
                files: angular.element(document.getElementById('myFile'))
            }
        }).then(
            function (response) {
                AlertService.add('success', 'Files successfully loaded');
                $location.url('/patients');
            },
            function (response) {
                AlertService.add('danger', response.data.message);
            }
        );
    };

});