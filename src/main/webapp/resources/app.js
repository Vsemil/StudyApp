angular.module('app', ['smart-table', 'ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider.when('/',
            {
                templateUrl:'resources/home-page/home.html',
                controller:'homeController'
            });
        $routeProvider.when('/list',
            {
                templateUrl:'resources/patients-list/patients-list.html',
                controller:'patientsListController'
            });
        $routeProvider.when('/doctors',
            {
                templateUrl:'resources/doctor-list/doctor-list.html',
                controller:'doctorListController'
            });
        $routeProvider.otherwise({redirectTo: '/'});
    })
    .controller('mainController', function($scope, $location) {
        $scope.location = $location
    });