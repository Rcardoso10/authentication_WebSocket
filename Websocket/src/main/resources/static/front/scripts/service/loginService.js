angular.module("app").service("loginService", function ($http, defaultUrl) {

    var urlBase = defaultUrl.getServerUrl() + '/api/auth';

    this.AccessToken = function (user) {

       /* var request = {
            url : urlBase,
            method : "POST",
            headers :{
                'Content-Type': "application/json"
            },
            params :{
                email : email,
                password : password
            }
        };*/
        //return $http.post('http://localhost:8086/api/auth', user);
        return $http.post('http://localhost:8086/login', user);
        //return $http(request);
    };

});