angular.module("app").factory("defaultUrl",function($location){

    var _getServerUrl = function () {
        return $location.protocol() + "://" + $location.host()
            + ":" + $location.port() + "";

    };

    return {
        getServerUrl: _getServerUrl

    };
});