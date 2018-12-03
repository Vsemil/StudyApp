angular.module('app').controller('loginController', function($scope, $rootScope, $location, AUTH_EVENTS, AlertService) {
    $scope.credentials = {};

    $scope.login = function (credentials) {
        AuthService.login(credentials).then(function (data) {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $location.url($rootScope.redir);
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
            AlertService.add('danger', 'Invalid username or password');
        });
    };
});