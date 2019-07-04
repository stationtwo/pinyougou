app.controller('typeTemplateController', function ($scope, $controller, typeTemplateService,brandService,specificationService) {//控制器
    //引入父类控制器
    $controller('baseController', {$scope: $scope});
    /**
     * 发送查询请求,定义findPage方法(已过时)
     */
    $scope.findPage = function (pageNum, pageSize) {
        typeTemplateService.findPage(pageNum, pageSize).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 搜索 (升级版findPage)
	 * 读取列表数据绑定到表单中
     */
    $scope.search = function (pageNum, pageSize) {
        typeTemplateService.search(pageNum, pageSize, $scope.searchEntity).success(function (data) {
					  
            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };
	
    /**
     * 定义新增方法
     */
    $scope.save = function () {
        var object = null;
        if ($scope.typeTemplate.id) {
            object = typeTemplateService.updateTypeTemplate($scope.typeTemplate);//修改
        } else {
            object = typeTemplateService.saveTypeTemplate($scope.typeTemplate);//增加
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
							   
        typeTemplateService.findOne(id).success(function (data) {
            $scope.typeTemplate = data;
            $scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds); //将字符串转换成属性
            $scope.typeTemplate.specIds = JSON.parse($scope.typeTemplate.specIds); //将字符串转换成属性
            $scope.typeTemplate.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems); //将字符串转换成属性

        })
    };
	
    /**
     * 删除
     * @param selectIdList
     */
    $scope.removeTypeTemplateList = function (selectIdList) {
        if (confirm("是否删除?")) {
            typeTemplateService.removeTypeTemplateList(selectIdList).success(function (data) {
                if (data.success) {
							   
                    $scope.reloadList();
                } else {
                    alert(data.message);
                }
            });
        }
    };

    //查询品牌列表
    $scope.selectOptionList = function () {
        brandService.selectOptionList().success(function (data) {
            $scope.brandList = {data:data}
        })
    };

    //查询规格列表
    $scope.selectSpecList = function(){
        specificationService.selectSpecList().success(function (data) {
            $scope.specList = {data:data}
        })
    };

    //定义一个init方法 初始化两个列表
    $scope.initTwo = function(){
        $scope.selectSpecList();
        $scope.selectOptionList();
    };
    //品牌列表
    $scope.brandList = {data:[{"id":1,"text":'华为'},{"id":2,"text":'联想'},{"id":3,"text":'小米'}]};

    //规格列表
    $scope.specList = {data:[{"id":1,"text":'华为'},{"id":2,"text":'联想'},{"id":3,"text":'小米'}]};//select2 下拉框的数据定义方式

    //新增扩展属性行
    $scope.addTableRow = function () {
        $scope.typeTemplate.customAttributeItems.push({});
    };

    $scope.removeTableRow=function (index) {
        $scope.typeTemplate.customAttributeItems.splice(index, 1);
    };
});
