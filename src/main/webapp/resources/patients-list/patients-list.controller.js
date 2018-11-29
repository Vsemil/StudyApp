angular.module('app').controller('patientsListController', function($scope, $http, AlertService) {
    $scope.patients = [];
    $scope.indexOffset = 0;
    $scope.itemsByPage = 10;

    $scope.getPatients = function(tableState) {
        var pagination = tableState.pagination;
        var start = pagination.start || 0;
        var number = pagination.number || 10;
        var ret = {
            page: (start / number),
            size: number
        };
        $http.get('/patients/all', {params: ret}).then(function (response) {
            tableState.pagination.numberOfPages = response.data.totalPages;
            $scope.patients = response.data.content;
            $scope.indexOffset = ret.page * ret.size;
        });
    };

    $scope.remove = function (patient) {
        $scope.removePatient = patient;
    };

    $scope.delete = function () {
        $http.delete('/patients/' + $scope.removePatient.id).then(function (value) {
            var index = $scope.patients.indexOf($scope.removePatient);
            if (index !== -1) {
                $scope.patients.splice(index, 1);
            }
            AlertService.add('success', 'Patient successfully removed');
        }, function (reason) { 
            if (reason.data.status === 'CONFLICT') {
                AlertService.add('danger', 'It is not possible to remove this patient, as there is research with him')
            }
        })
    };

    $scope.show = function (patient) {
        if (patient.showFile === undefined) {
            patient.showFile = false;
        }
        patient.showFile = !patient.showFile
    };

    $scope.deleteFile = function (file, files) {
        $http.delete('/patients/deleteFile/' + file.id)
            .then(function (value) {
                AlertService.add('success', 'File ' + file.fileName + ' successfully removed');
                    var index =files.indexOf(file);
                    if (index !== -1) {
                        files.splice(index, 1);
                    }
                },
                function (error) {
                AlertService.add('danger', error.data.message)
            })
    }
});