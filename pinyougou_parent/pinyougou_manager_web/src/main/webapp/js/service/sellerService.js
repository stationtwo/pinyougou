app.service('sellerService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../seller/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../seller/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeSellerList = function (idList) {
        return $http.get('../seller/removeSeller.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../seller/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateSeller = function (seller) {
        return $http.post('../seller/updateSeller.do', seller)
    };

    //新增品牌
    this.saveSeller = function (seller) {
        return $http.post('../seller/saveSeller.do', seller)
    };

    //更改用户状态
    this.update = function (id, status) {
        return $http.get('../seller/update.do?sellerId='+id+'&status='+status);
    };
});
