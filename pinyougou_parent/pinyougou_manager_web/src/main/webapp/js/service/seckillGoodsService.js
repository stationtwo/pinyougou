app.service('seckillGoodsService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../seckillGoods/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../seckillGoods/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeSeckillGoodsList = function (idList) {
        return $http.get('../seckillGoods/removeSeckillGoods.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../seckillGoods/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateSeckillGoods = function (seckillGoods) {
        return $http.post('../seckillGoods/updateSeckillGoods.do', seckillGoods)
    };

    //新增品牌
    this.saveSeckillGoods = function (seckillGoods) {
        return $http.post('../seckillGoods/saveSeckillGoods.do', seckillGoods)
    };   
});
