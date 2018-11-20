angular.module('app').controller('doctorListController', function($scope, $http) {
    $scope.doctors = [];
    $http.get('/doctors/all').then(function(response) {
        $scope.doctors = response.data.content;
    });
});