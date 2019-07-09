app.service('itemCatService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../itemCat/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../itemCat/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeItemCatList = function (idList) {
        return $http.get('../itemCat/removeItemCat.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../itemCat/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateItemCat = function (itemCat) {
        return $http.post('../itemCat/updateItemCat.do', itemCat)
    };

    //新增品牌
    this.saveItemCat = function (itemCat) {
        return $http.post('../itemCat/saveItemCat.do', itemCat)
    };

    //根据上级id列表展示
    this.listItemCat = function (parentId) {
        return $http.get('../itemCat/listItemCat.do?parentId='+parentId);
    }
});
