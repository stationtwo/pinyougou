app.service('goodsDescService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../goodsDesc/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../goodsDesc/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeGoodsDescList = function (idList) {
        return $http.get('../goodsDesc/removeGoodsDesc.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../goodsDesc/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateGoodsDesc = function (goodsDesc) {
        return $http.post('../goodsDesc/updateGoodsDesc.do', goodsDesc)
    };

    //新增品牌
    this.saveGoodsDesc = function (goodsDesc) {
        return $http.post('../goodsDesc/saveGoodsDesc.do', goodsDesc)
    };   
});
