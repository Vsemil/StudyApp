angular.module('app', ['smart-table', 'ngRoute', 'ngCookies', 'lr.upload'])
    .config(function ($routeProvider) {
        $routeProvider.when('/',
            {
                templateUrl:'resources/home-page/home.html',
                controller:'homeController'
            })
            .when('/patients',
            {
                templateUrl:'resources/patients-list/patients-list.html',
                controller:'patientsListController'
            })
            .when('/patients/:id',
            {
                templateUrl:'resources/patients-list/edit/edit-patient.html',
                controller:'editPatientController'
            })
            .when('/patients/add-file/:id',
                {
                    templateUrl:'resources/patients-list/add-file/patient-add-file.html',
                    controller:'patientAddFileController'
                })
            .when('/doctors',
            {
                templateUrl:'resources/doctor-list/doctor-list.html',
                controller:'doctorListController'
            })
            .when('/doctors/:id',
            {
                templateUrl:'resources/doctor-list/edit/edit-doctor.html',
                controller:'editDoctorController'
            })
            .when('/scheduling',
            {
                templateUrl:'resources/study-list/study-list.html',
                controller:'studyListController'
            })
            .when('/study/:id',
            {
                templateUrl:'resources/study-list/edit/edit-study.html',
                controller:'editStudyController'
            })
            .when('/rooms',
            {
                templateUrl:'resources/room-list/rooms-list.html',
                controller:'roomsListController'
            })
            .when('/rooms/:id',
            {
                templateUrl:'resources/room-list/edit/edit-room.html',
                controller:'editRoomController'
            })
            .when('/login',
            {
                templateUrl:'resources/security/login.html',
                controller:'loginController'
            })
            .otherwise({redirectTo: '/'});
    })
    .controller('mainController', function($http, $scope, $location, USER_ROLES, Session) {
        $scope.location = $location;

        $scope.session = Session;
        $scope.userRoles = USER_ROLES;

        $scope.logout = function () {
            $http.post('/logout').then(function (value) {
                Session.destroy();
                $location.url('/');
            }, function (reason) {  })
        }

    })
    .factory('AlertService', ['$timeout', '$rootScope', function ($timeout, $rootScope) {
        var service = {};

        var alerts = [];

        $rootScope.alerts = alerts;

        service.closeAlertIdx = function (index) {
            return alerts.splice(index, 1);
        };

        service.closeAlert = function (alert) {
            return service.closeAlertIdx(alerts.indexOf(alert));
        };

        service.add = function(type, msg, timeout){
            var alert = {
                type: type,
                msg: msg,
                close: function () {
                    return service.closeAlert(this);
                }
            };
            if (type === 'success') {
                $timeout(function(){
                    alert.close();
                }, 3000);
            }
            if(timeout){
                $timeout(function(){
                    alert.close();
                }, timeout);
            }
            return alerts.push(alert);
        };

        service.clear = function () {
            alerts.length = 0;
        };

        service.get = function () {
            return alerts;
        };

        return service;
    }])
    .factory('AuthInterceptor', ['Session', function(Session) {
        return {
            // Send the Authorization header with each request
            'request': function(config) {
                if (Session.encodedString) {
                    config.headers = config.headers || {};
                    config.headers.Authorization = 'Basic ' + Session.encodedString;
                }
                return config;
            }
        };
    }])
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('AuthInterceptor');
    }])
    .service('Session', ['$cookies', function($cookies) {

        this.createOfCookies = function () {
            if ($cookies.get('XSRF-TOKEN')) {
                this.username = $cookies.get('username');
                this.userRole = $cookies.get('userRole');
            }
        };

        this.create = function (user) {
            this.username = user.username;
            this.userRole = user.roles;
            $cookies.put('username', this.username);
            $cookies.put('userRole', this.userRole);

        };
        this.destroy = function () {
            this.username = null;
            this.userRole = null;
            $cookies.remove('username');
            $cookies.remove('userRole');
        };
    }])
    .constant('AUTH_EVENTS', {
        loginSuccess: 'auth-login-success',
        loginFailed: 'auth-login-failed',
        logoutSuccess: 'auth-logout-success',
        sessionTimeout: 'auth-session-timeout',
        notAuthenticated: 'auth-not-authenticated',
        notAuthorized: 'auth-not-authorized'
    })
    .constant('USER_ROLES', {
        all: '*',
        admin: 'ADMIN',
        editor: 'USER'
    })
    .run(function ($rootScope, $location, AUTH_EVENTS, Session, $cookies, $http) {
        $http.get('/user').then(function (value) {
            if (value.data) {
                Session.create(value.data);
            } else {
                Session.destroy();
            }
        });
        $rootScope.$on('$routeChangeStart', function (event, next) {
            if (!(next.$$route.originalPath === '/' || next.$$route.originalPath === '')) {
                if (!Session.username) {
                    event.preventDefault();
                    $rootScope.redir = next.$$route.originalPath;
                    Session.destroy();
                    location.href = '/login'
                } else {
                    $http.get('/user').then(function (value) {
                        if (value.data) {
                            Session.create(value.data);
                        } else {
                            Session.destroy();
                            event.preventDefault();
                        }
                    })
                }
            }
        });
    });