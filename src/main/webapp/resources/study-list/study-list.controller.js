angular.module('app').controller('studyListController', function($scope, $http) {
    $scope.study = [];
    $scope.indexOffset = 0;
    $scope.itemsByPage = 10;

    $scope.getStudy = function(tableState) {
        var pagination = tableState.pagination;
        var start = pagination.start || 0;
        var number = pagination.number || 10;
        var ret = {
            page: (start / number),
            size: number
        };
        $http.get('/study/all', {params: ret}).then(function (response) {
            tableState.pagination.numberOfPages = response.data.totalPages;
            $scope.study = response.data.content;
            $scope.indexOffset = ret.page * ret.size;
        });
    };

    $scope.remove = function (study) {
        $scope.removeStudy = study;
    };

    $scope.delete = function () {
        $http.delete('/study/' + $scope.removeStudy.id).then(function (value) {
            var index = $scope.study.indexOf($scope.removeStudy);
            if (index !== -1) {
                $scope.study.splice(index, 1);
            }
        })
    };

    $scope.selectStatus = function (study) {
        $http.put('/study/setStatus', study).then(function (value) {

        })
    }
});