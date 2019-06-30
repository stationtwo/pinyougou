app.controller('brandController', function ($scope, $controller, brandService) {//控制器
    //引入父类控制器
    $controller('baseController', {$scope: $scope});
    /**
     * 发送查询请求,定义findPage方法(已过时)
     */
    $scope.findPage = function (pageNum, pageSize) {
        brandService.findPage(pageNum, pageSize).success(function (data) {
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
    /**
     * 搜索
     */
    $scope.search = function (pageNum, pageSize) {
        brandService.search(pageNum, pageSize, $scope.searchEntity).success(function (data) {
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
    /**
     * 定义新增方法
     */
    $scope.save = function () {
        var object = null;
        if ($scope.brand.id) {
            object = brandService.updateBrand($scope.brand)
        } else {
            object = brandService.saveBrand($scope.brand)
        }
        object.success(function (data) {
            if (data.success) {
                $scope.reloadList();
            } else {
                alert(data.message);
            }
        });
    };

    /**
     * 定义根据id查找方法
     */
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (data) {
            $scope.brand = data;
        })
    };
    /**
     * 删除方法
     * @param idList
     */
    $scope.removeBrandList = function (idList) {
        if (confirm("是否删除?")) {
            brandService.removeBrandList(idList).success(function (data) {
                if (data.success) {
                    $scope.reloadList();
                } else {
                    alert(data.message);
                }
            });
        }
    };

});