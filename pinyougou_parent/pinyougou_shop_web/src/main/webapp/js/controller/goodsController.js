app.controller('goodsController', function ($scope, $controller,
        goodsService,uploadService ,itemCatService,typeTemplateService) {//控制器
    //引入父类控制器
    $controller('baseController', {$scope: $scope});
    $controller('uploadController',{$scope: $scope});
    /**
     * 发送查询请求,定义findPage方法(已过时)
     */
    $scope.findPage = function (pageNum, pageSize) {
        goodsService.findPage(pageNum, pageSize).success(function (data) {

            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };

    /**
     * 搜索 (升级版findPage)
     * 读取列表数据绑定到表单中
     */
    $scope.search = function (pageNum, pageSize) {
        goodsService.search(pageNum, pageSize, $scope.searchEntity).success(function (data) {

            $scope.rows = data.rows;
            $scope.paginationConf.totalItems = data.total;//更新总记录数
        })
    };

    /**
     * 新增
     */
    $scope.save = function (goodsGroup) {
        $scope.goodsGroup.goodsDesc.introduction = editor.html();

        goodsService.save(goodsGroup).success(function (data) {
            alert(data.message);
            if (data.success) {
                $scope.typeTemplate={};
                $scope.goodsGroup.goodsDesc.customAttributeItems ={};
                $scope.goodsGroup = {};
                editor.html('');//清空富文本编辑器
            }
        });
    };

    /**
     * 定义根据id查找
     */
    $scope.findOne = function (id) {

        goodsService.findOne(id).success(function (data) {
            $scope.goods = data;
        })
    };

    /**
     * 删除方法
     * @param idList
     */
    $scope.removeGoodsList = function (idList) {
        if (confirm("是否删除?")) {
            goodsService.removeGoodsList(idList).success(function (data) {
                if (data.success) {

                    $scope.reloadList();
                } else {
                    alert(data.message);
                }
            });
        }
    };

    $scope.uploadFile = function () {
        uploadService.uploadFile().success(function (data) {
            if (data.success) {
                //如果上传成功,获得地址
                $scope.imageEntity.url = data.message;
            } else {
                alert(data.message)
            }
        }).error(function () {
            alert("上传发生错误")
        })
    };

    /**
     * 定义页面实体
     * @type {{}}
     */
    $scope.goodsGroup = {goods:{},goodsDesc:{itemImages:[]}};

    /**
     * 添加图片列表
     */
    $scope.addImageEntity = function () {
        $scope.goodsGroup.goodsDesc.itemImages.push($scope.imageEntity);
    };

    /**
     * 删除列表图片
     */
    $scope.removeImage= function (iidex) {
        $scope.goodsGroup.goodsDesc.itemImages.splice(iidex,1);
    };

    /**
     * 加载一级分类下拉框
     */
    $scope.listItemCat1= function () {
        itemCatService.listItemCat(0).success(function (data) {
            $scope.itemCat1List = data;
        });
    };

    /**
     * 读取二级分类  这里监听一级分类 $watch
     */
    $scope.$watch('goodsGroup.goods.category1Id',function (newValue,oldValue) {
        if (newValue === undefined) {
            return;
        }
        itemCatService.listItemCat(newValue).success(function (data) {
            $scope.itemCat2List = data;
        });
        $scope.itemCat3List = [];
        $scope.goodsGroup.goods.typeTemplateId = undefined;
    });

    /**
     * 读取三级分类
     */
    $scope.$watch('goodsGroup.goods.category2Id',function (newValue,oldValue) {
        if (newValue === undefined) {
            return;
        }
        itemCatService.listItemCat(newValue).success(function (data) {
            $scope.itemCat3List = data;
        });
        $scope.goodsGroup.goods.typeTemplateId = undefined;
    });

    /**
     * 获取模板id
     */
    $scope.$watch('goodsGroup.goods.category3Id',function (newValue, oldValue) {
        if (newValue === undefined) {
            return;
        }
        itemCatService.findOne(newValue).success(function (data) {
            $scope.goodsGroup.goods.typeTemplateId = data.typeId;
        })
    });

    /**
     * 获取模板对象
     */
    $scope.$watch('goodsGroup.goods.typeTemplateId',function (newValue, oldValue) {
        if (newValue === undefined) {
            $scope.typeTemplate={};
            $scope.goodsGroup.goodsDesc.customAttributeItems ={};
            return;
        }
        typeTemplateService.findOne(newValue).success(function (data) {
            $scope.typeTemplate=data;
            //品牌列表
            $scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);
            //扩展属性列表
            $scope.goodsGroup.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
        });

        //查询规格列表
        typeTemplateService.listSpec(newValue).success(function (data) {
            $scope.specList = data;
        })
    });

    /**
     *展示规格选项列表
     */
    $scope.goodsGroup={ goodsDesc:{itemImages:[],specificationItems:[]} };
    $scope.updateSpecAttribute=function($event,name,value){
        var object= $scope.searchObjectByKey(
            $scope.goodsGroup.goodsDesc.specificationItems ,'attributeName', name);
        if(object!=null){
            if($event.target.checked ){
                object.attributeValue.push(value);
            }else{//取消勾选
                object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
//如果选项都取消了，将此条记录移除
                if(object.attributeValue.length===0){
                    $scope.goodsGroup.goodsDesc.specificationItems.splice(
                        $scope.goodsGroup.goodsDesc.specificationItems.indexOf(object),1);
                }
            }
        }else{
            $scope.goodsGroup.goodsDesc.specificationItems.push(
                {"attributeName":name,"attributeValue":[value]});
        }
    };

    /**
     * 生成sku列表(深克隆)
     */
    //创建 SKU 列表
    $scope.createItemList=function(){
        $scope.goodsGroup.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0' }];//初始
        var items = $scope.goodsGroup.goodsDesc.specificationItems;
        for(var i=0;i< items.length;i++){
            $scope.goodsGroup.itemList =
            addColumn( $scope.goodsGroup.itemList,items[i].attributeName,items[i].attributeValue );
        }
    };
//添加列值
    addColumn=function(list,columnName,conlumnValues){
        var newList=[];//新的集合
        for(var i=0;i<list.length;i++){
            var oldRow= list[i];
            for(var j=0;j<conlumnValues.length;j++){
                var newRow= JSON.parse( JSON.stringify( oldRow ) );//深克隆
                newRow.spec[columnName]=conlumnValues[j];
                newList.push(newRow);
            }
        }
        return newList;
    };

});
