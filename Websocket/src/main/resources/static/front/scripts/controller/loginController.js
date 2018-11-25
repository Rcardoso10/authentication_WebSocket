angular.module('app').controller('loginController',
    function($scope, $rootScope, $http,$location, loginService, localStorage) {

        var hasAdmin = false;
        $scope.loginWeb = function () {

           /* if(!$scope.username){
                $scope.notificatios('danger', 'Username Obrigatorio!');
                return;
            } else if(!$scope.password){
                $scope.notificatios('danger', 'Senha Obrigatorio!');
                return;
            }*/
            loginService.AccessToken($scope.user).then(
                function (response) {
                    console.log(response.headers());
                    var user = response.headers();
                    localStorage.set('Authorization', user.authorization);
                    localStorage.set('username', $scope.user.email)
                    if(user.authorization != undefined){
                        $location.path('/chat');
                    } else {
                        $location.path('/logout');
                        $scope.notificatios('danger', 'Vocẽ não tem permissão para acessar esse módulo!!!');
                    }


                }, function (error) {
                    if(error.status === 401){
                        console.log("Usuario ou senha inválido!!")
                        $scope.notificatios('warning', 'Usuario ou senha inválido!!!');
                        $scope.username = undefined;
                        $scope.password = undefined;
                    }
                });
        };

        $scope.notificatios = function (type,message ) {
            $.notify({
                icon:"notifications",
                message:'<b>'+message+'</b>'
            }, {
                type:type,
                timer:3e3,
                placement:{
                    from:'top',
                    align:'right'
                }
            })
        };
    });

