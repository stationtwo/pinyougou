app.controller('sellerController', function ($scope, $controller, sellerService) {//控制器
    //引入父类控制器
    $controller('baseController', {$scope: $scope});
    /**
     * 发送查询请求,定义findPage方法(已过时)
     */
    $scope.findPage = function (pageNum, pageSize) {
        sellerService.findPage(pageNum, pageSize).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 搜索 (升级版findPage)
	 * 读取列表数据绑定到表单中
     */
    $scope.search = function (pageNum, pageSize) {
        sellerService.search(pageNum, pageSize, $scope.searchEntity).success(function (data) {
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 定义新增方法
     */
    $scope.save = function () {
        var object = null;
        if ($scope.seller.id) {
            object = sellerService.updateSeller($scope.seller);//修改
        } else {
            object = sellerService.saveSeller($scope.seller);//增加
        }
        object.success(function (data) {
					  
            if (data.success) {
                location.href = 'shoplogin.html';
            } else {
                alert(data.message);
            }
        });
    };

    /**
     * 定义根据id查找
     */
    $scope.findOne = function (id) {
							   
        sellerService.findOne(id).success(function (data) {
            $scope.seller = data;
        })
    };
	
    /**
     * 删除方法
     * @param idList
     */
    $scope.removeSellerList = function (idList) {
        if (confirm("是否删除?")) {
            sellerService.removeSellerList(idList).success(function (data) {
                if (data.success) {
                    $scope.reloadList();
                } else {
                    alert(data.message);
                }
            });
        }
    };

    /**
     * 更改用户状态
     * @param id
     * @param status
     */
    $scope.update = function (id, status) {
        sellerService.update(id, status).success(function (data) {
            if (!data.success) {
                alert(data.message);
            } else {
                $scope.reloadList();
            }
        });
    };

});
