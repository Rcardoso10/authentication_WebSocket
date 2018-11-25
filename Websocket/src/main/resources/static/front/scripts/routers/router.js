angular.module("app").config(function ($routeProvider, $httpProvider) {

    $routeProvider.when("/login",{
        controller : "loginController",
        templateUrl : "front/views/telaInicial.html"
    })
        .when("/chat", {
            controller : "loginController",
            templateUrl : "front/views/chat.html",
            auth: true
        })
        .when('/logout', {
            template: ' ',
            controller: ['$scope',
                '$rootScope',
                '$http',
                '$location',
                'defaultUrl',
                'localStorage',
                function ($scope,
                          $rootScope,
                          $http,
                          $location,
                          defaultUrl,
                          localStorage) {
                    localStorage.remove('Authorization');
                    localStorage.remove('username');

                    //$http.delete(defaultUrl.getServerUrl() + '/tokens/revoke');
                    $rootScope.loggedIn = false;
                    $location.path('/login');
                }
            ],
            auth: true
        })

        .otherwise({redirectTo: "/login"});

    $httpProvider.interceptors.push('AuthenticationHttpInterceptor');

})
    .service('AuthenticationHttpInterceptor', ['localStorage', '$rootScope','$q', '$location',
        function(localStorage,
                 $rootScope,$q, $location) {
            this.request = function(config) {

                if(localStorage.get('Authorization')) {
                    config.headers.Authorization = localStorage.get('Authorization');
                    $rootScope.loggedIn = true;
                }

                responseError();
                return config;
            };

            let responseError = () => {
                let service = this;
                const EXCEPTION = 'io.jsonwebtoken.ExpiredJwtException';

                service.responseError = function(response) {
                    if (response.status === 401 || ( response.status === 500 && !!response.data ) ){

                        if(typeof response.data === 'string'){
                            response.data = JSON.parse(response.data);
                        }
                        if(response.data.exception === EXCEPTION){
                            $location.path('/logout');
                        } else if (response.status === 401 ){
                            $location.path('/logout');
                        }

                    }
                    return $q.reject(response);
                };
            }

            responseError();


        }]).run(function (localStorage, $rootScope, $location, $route) {
    $rootScope.$on('$locationChangeStart', function (e, next, current) {

        var nextPath = $location.path();
        var nextRoute = $route.routes[nextPath];
        if(!!nextRoute && !!nextRoute.auth && !localStorage.get('Authorization')) {
            e.preventDefault();
            $location.path('/login');
        } else if (!!localStorage.get('Authorization') && $location.path() ===  '/login'){
            $location.path('/chat');
        }
    });
});