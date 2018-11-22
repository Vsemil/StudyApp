angular.module('app').controller('editRoomController', function($scope, $http, $routeParams, $location) {

    $scope.room = {};

    var id = $routeParams["id"];
    if (id !== undefined && id !== 'new') {
        var parseId = parseInt(id);
        if (isNaN(parseId)) {
            window.history.back();
        } else {
            $http.get('/rooms/' + parseId).then(
                function success(response) {
                    $scope.room = response.data;
                },
                function error(reason) {
                    window.history.back();
                });
        }
    }

    $scope.back = function () {
        window.history.back();
    };

    $scope.saveRoom = function () {
        $http.post('/rooms/save', $scope.room).then(function success(response) {
            $location.url('/rooms')
        });
    };

});