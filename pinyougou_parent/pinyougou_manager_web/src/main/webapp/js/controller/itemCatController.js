app.controller('itemCatController', function ($scope, $controller, itemCatService) {//控制器
    //引入父类控制器
    $controller('baseController', {$scope: $scope});
    /**
     * 发送查询请求,定义findPage方法(已过时)
     */
    $scope.findPage = function (pageNum, pageSize) {
        itemCatService.findPage(pageNum, pageSize).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
    /**
     * 搜索 (升级版findPage)
	 * 读取列表数据绑定到表单中
     */
    $scope.search = function (pageNum, pageSize) {
        itemCatService.search(pageNum, pageSize, $scope.searchEntity).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 定义新增方法
     */
    $scope.save = function () {
        var object = null;
        if ($scope.itemCat.id) {
            object = itemCatService.updateItemCat($scope.itemCat);//修改
        } else {
            object = itemCatService.saveItemCat($scope.itemCat);//增加
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
							   
        itemCatService.findOne(id).success(function (data) {
            $scope.itemCat = data;
        })
    };
	
    /**
     * 删除方法
     * @param idList
     */
    $scope.removeItemCatList = function (idList) {
        if (confirm("是否删除?")) {
            itemCatService.removeItemCatList(idList).success(function (data) {
                if (data.success) {
							   
                    $scope.reloadList();
                } else {
                    alert(data.message);
                }
            });
        }
    };

    $scope.listItemCat = function (parentId) {
        itemCatService.listItemCat(parentId).success(function (data) {
            $scope.rows = data;
        })
    };

    /**
     * 面包屑导航
     * @type {number}
     */
    $scope.grade=1;//默认是第一级
    $scope.setGrade = function (value) {
        $scope.grade=value;
    }

    /**
     * 读取导航列表
     *
     */
    $scope.selectList = function (p_itemCat) {
        if ($scope.grade == 1) {
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        }

        if ($scope.grade == 2) {
            $scope.entity_1 = p_itemCat;
            $scope.entity_2 = null;
        }

        if ($scope.grade == 3) {
            $scope.entity_2 = p_itemCat;
        }
        //查询下一级
        $scope.listItemCat(p_itemCat.id);
    }
});
