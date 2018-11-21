angular.module('app', ['smart-table', 'ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider.when('/',
            {
                templateUrl:'resources/home-page/home.html',
                controller:'homeController'
            });
        $routeProvider.when('/patients',
            {
                templateUrl:'resources/patients-list/patients-list.html',
                controller:'patientsListController'
            });

        $routeProvider.when('/patients/:id',
            {
                templateUrl:'resources/patients-list/edit/edit-patient.html',
                controller:'editPatientController'
            });
        $routeProvider.when('/doctors',
            {
                templateUrl:'resources/doctor-list/doctor-list.html',
                controller:'doctorListController'
            });
        $routeProvider.when('/doctors/:id',
            {
                templateUrl:'resources/doctor-list/edit/edit-doctor.html',
                controller:'editDoctorController'
            });
        $routeProvider.when('/scheduling',
            {
                templateUrl:'resources/study-list/study-list.html',
                controller:'studyListController'
            });
        $routeProvider.when('/study/:id',
            {
                templateUrl:'resources/study-list/edit/edit-study.html',
                controller:'editStudyController'
            });
        $routeProvider.when('/rooms',
            {
                templateUrl:'resources/room-list/rooms-list.html',
                controller:'roomsListController'
            });
        $routeProvider.when('/rooms/:id',
            {
                templateUrl:'resources/room-list/edit/edit-room.html',
                controller:'editRoomController'
            });
        $routeProvider.otherwise({redirectTo: '/'});
    })
    .controller('mainController', function($scope, $location) {
        $scope.location = $location
    });