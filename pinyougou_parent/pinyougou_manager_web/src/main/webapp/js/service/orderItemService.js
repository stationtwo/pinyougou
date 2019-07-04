app.service('orderItemService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../orderItem/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../orderItem/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeOrderItemList = function (idList) {
        return $http.get('../orderItem/removeOrderItem.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../orderItem/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateOrderItem = function (orderItem) {
        return $http.post('../orderItem/updateOrderItem.do', orderItem)
    };

    //新增品牌
    this.saveOrderItem = function (orderItem) {
        return $http.post('../orderItem/saveOrderItem.do', orderItem)
    };   
});
