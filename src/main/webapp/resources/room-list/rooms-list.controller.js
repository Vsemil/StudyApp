angular.module('app').controller('roomsListController', function($scope, $http, AlertService) {
    $scope.rooms = [];
    $scope.indexOffset = 0;
    $scope.itemsByPage = 10;

    $scope.getRooms = function(tableState) {
        var pagination = tableState.pagination;
        var start = pagination.start || 0;
        var number = pagination.number || 10;
        var ret = {
            page: (start / number),
            size: number
        };
        $http.get('/rooms/all', {params: ret}).then(function (response) {
            tableState.pagination.numberOfPages = response.data.totalPages;
            $scope.rooms = response.data.content;
            $scope.indexOffset = ret.page * ret.size;
        });
    };

    $scope.remove = function (room) {
        $scope.removeRoom = room;
    };

    $scope.delete = function () {
        $http.delete('/rooms/' + $scope.removeRoom.id).then(function (value) {
            var index = $scope.rooms.indexOf($scope.removeRoom);
            if (index !== -1) {
                $scope.rooms.splice(index, 1);
            }
            AlertService.add('success', 'Room successfully removed');
        })
    };
});