app.service('goodsService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../goods/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../goods/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeGoodsList = function (idList) {
        return $http.get('../goods/removeGoods.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../goods/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateGoods = function (goods) {
        return $http.post('../goods/updateGoods.do', goods)
    };

    //新增品牌
    this.save = function (goodsGroup) {
        return $http.post('../goods/saveGoods.do', goodsGroup)
    };   
});
