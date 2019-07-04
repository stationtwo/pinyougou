app.service('seckillOrderService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../seckillOrder/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../seckillOrder/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeSeckillOrderList = function (idList) {
        return $http.get('../seckillOrder/removeSeckillOrder.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../seckillOrder/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateSeckillOrder = function (seckillOrder) {
        return $http.post('../seckillOrder/updateSeckillOrder.do', seckillOrder)
    };

    //新增品牌
    this.saveSeckillOrder = function (seckillOrder) {
        return $http.post('../seckillOrder/saveSeckillOrder.do', seckillOrder)
    };   
});
