angular.module('app').controller('doctorListController', function($scope, $http) {
    $scope.doctors = [];
    $scope.indexOffset = 0;
    $scope.itemsByPage = 10;

    $scope.getDoctors = function(tableState) {
        var pagination = tableState.pagination;
        var start = pagination.start || 0;
        var number = pagination.number || 10;
        var ret = {
            page: (start / number),
            size: number
        };
        $http.get('/doctors/all', {params: ret}).then(function (response) {
            tableState.pagination.numberOfPages = response.data.totalPages;
            $scope.doctors = response.data.content;
            $scope.indexOffset = ret.page * ret.size;
        });
    };

    $scope.remove = function (doctor) {
        $scope.removeDoctor = doctor;
    };

    $scope.delete = function () {
        $http.delete('/doctors/' + $scope.removeDoctor.id).then(function (value) {
            var index = $scope.doctors.indexOf($scope.removeDoctor);
            if (index !== -1) {
                $scope.doctors.splice(index, 1);
            }
        })
    };

});