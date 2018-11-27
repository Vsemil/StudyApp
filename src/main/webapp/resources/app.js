angular.module('app', ['smart-table', 'ngRoute', 'ngCookies'])
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
    .controller('mainController', function($scope, $location, USER_ROLES, AuthService, Session) {
        $scope.location = $location;

        $scope.session = Session;
        $scope.userRoles = USER_ROLES;
        $scope.authService = AuthService;

        $scope.logout = function () {
            Session.destroy();
            $location.url('/');
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
                    //var encodedString = btoa(Session.username + ":" + Session.password);
                    //var encodedString = btoa("1:1");
                    config.headers.Authorization = 'Basic ' + Session.encodedString;
                }
                return config;
            }
        };
    }])
    .config(['$httpProvider', function($httpProvider) {
        $httpProvider.interceptors.push('AuthInterceptor');
    }])
    .factory('AuthService', function ($http, Session) {
        var authService = {};

        authService.login = function (credentials) {
            var headers = {'Content-Type': 'application/x-www-form-urlencoded'};
            var s = 'username='+ credentials.username + '&password=' + credentials.password;
            return $http.post('/login', s, {headers: headers})
                .then(function (res) {
                    Session.create(credentials.username, credentials.password, res.data, credentials.save);
                    return res.data;
                });
        };

        authService.isAuthenticated = function () {
            return !!Session.username;
        };

        authService.isAuthorized = function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                authorizedRoles = [authorizedRoles];
            }
            return (authService.isAuthenticated() && authorizedRoles.indexOf(Session.userRole) !== -1);
        };

        return authService;
    })
    .service('Session', ['$cookies', function($cookies) {

        this.createOfCookies = function () {
            if ($cookies.get('encodedString')) {
                this.encodedString = $cookies.get('encodedString');
                this.username = $cookies.get('username');
                this.userRole = $cookies.get('userRole');
            }
        };

        this.create = function (username, password, userRole, save) {
            this.username = username;
            this.encodedString = btoa(username + ":" + password);
            this.userRole = userRole;
            if (save) {
                $cookies.put('encodedString', this.encodedString);
                $cookies.put('username', this.username);
                $cookies.put('userRole', this.userRole);
            }
        };
        this.destroy = function () {
            this.username = null;
            this.encodedString = null;
            this.userRole = null;
            $cookies.remove('encodedString');
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
    .run(function ($rootScope, $location, AUTH_EVENTS, AuthService, Session) {
        Session.createOfCookies();
        $rootScope.$on('$routeChangeStart', function (event, next) {
            // var authorizedRoles = next.data.authorizedRoles;
            if (next.$$route.originalPath === '/login' || next.$$route.originalPath === '/'
            || next.$$route.originalPath === '') {
                if (next.$$route.originalPath !== '/login') {
                    $rootScope.redir = '/'
                }
            } else {
                if (!AuthService.isAuthenticated()) {
                    event.preventDefault();
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                    $rootScope.redir = next.$$route.originalPath;
                    $location.url('/login');
                } else {
                    $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                }
            }
        });
    });