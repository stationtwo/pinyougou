app.controller('specificationController', function ($scope, $controller, specificationService) {//控制器
    //引入父类控制器
    $controller('baseController', {$scope: $scope});
    /**
     * 发送查询请求,定义findPage方法(已过时)
     */
    $scope.findPage = function (pageNum, pageSize) {
        specificationService.findPage(pageNum, pageSize).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 搜索 (升级版findPage)
	 * 读取列表数据绑定到表单中
     */
    $scope.search = function (pageNum, pageSize) {
        specificationService.search(pageNum, pageSize, $scope.searchEntity).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 定义新增方法
     */
    $scope.save = function () {
        var object = null;
        if ($scope.specification.tbSpecification.id) {
            object = specificationService.updateSpecification($scope.specification);//修改
        } else {
            object = specificationService.saveSpecification($scope.specification);//增加
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
        specificationService.findOne(id).success(function (data) {
            $scope.specification = data;

        })
    };
	
    /**
     * 删除方法
     * @param idList
     */
    $scope.removeSpecificationList = function (idList) {
        if (confirm("是否删除?")) {
            specificationService.removeSpecificationList(idList).success(function (data) {
                if (data.success) {
							   
                    $scope.reloadList();
                } else {
                    alert(data.message);
                }
            });
        }
    };

    $scope.specification={optionList:[]};
    /**
     * 新增选项行
     */
    $scope.addTableRow = function () {
        $scope.specification.optionList.push({})
    };

    /**
     * 删除选项行
     */
    $scope.removeTableRow = function (index) {
        $scope.specification.optionList.splice(index, 1);
    };

});
