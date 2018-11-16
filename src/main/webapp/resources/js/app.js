angular.module('app', ['smart-table', 'ngRoute'])
    .controller('mainController', function($scope, $location) {
        $scope.location = $location
    });