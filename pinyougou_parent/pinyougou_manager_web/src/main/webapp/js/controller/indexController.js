app.controller('indexController', function ($scope, loginService) {//控制器

    $scope.getLoginName = function () {
        loginService.getLoginName().success(function (data) {
            $scope.username = data.loginName;

        });
    };

});
