app.controller('freightTemplateController', function ($scope, $controller, freightTemplateService) {//控制器
    //引入父类控制器
    $controller('baseController', {$scope: $scope});
    /**
     * 发送查询请求,定义findPage方法(已过时)
     */
    $scope.findPage = function (pageNum, pageSize) {
        freightTemplateService.findPage(pageNum, pageSize).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 搜索 (升级版findPage)
	 * 读取列表数据绑定到表单中
     */
    $scope.search = function (pageNum, pageSize) {
        freightTemplateService.search(pageNum, pageSize, $scope.searchEntity).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 定义新增方法
     */
    $scope.save = function () {
        var object = null;
        if ($scope.freightTemplate.id) {
            object = freightTemplateService.updateFreightTemplate($scope.freightTemplate);//修改
        } else {
            object = freightTemplateService.saveFreightTemplate($scope.freightTemplate);//增加
        }
        object.success(function (data) {
					  
            if (data.success) {
					//重新查询
                $scope.reloadList();//重新加载
            } else {
                alert(data.message);
            }
        });
    };

    /**
     * 定义根据id查找
     */
    $scope.findOne = function (id) {
							   
        freightTemplateService.findOne(id).success(function (data) {
            $scope.freightTemplate = data;
        })
    };
	
    /**
     * 删除方法
     * @param idList
     */
    $scope.removeFreightTemplateList = function (idList) {
        if (confirm("是否删除?")) {
            freightTemplateService.removeFreightTemplateList(idList).success(function (data) {
                if (data.success) {
							   
                    $scope.reloadList();
                } else {
                    alert(data.message);
                }
            });
        }
    };

});
