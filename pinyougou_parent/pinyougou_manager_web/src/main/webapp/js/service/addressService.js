app.service('addressService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../address/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../address/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeAddressList = function (idList) {
        return $http.get('../address/removeAddress.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../address/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateAddress = function (address) {
        return $http.post('../address/updateAddress.do', address)
    };

    //新增品牌
    this.saveAddress = function (address) {
        return $http.post('../address/saveAddress.do', address)
    };   
});
