app.service('orderService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../order/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../order/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeOrderList = function (idList) {
        return $http.get('../order/removeOrder.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../order/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateOrder = function (order) {
        return $http.post('../order/updateOrder.do', order)
    };

    //新增品牌
    this.saveOrder = function (order) {
        return $http.post('../order/saveOrder.do', order)
    };   
});
