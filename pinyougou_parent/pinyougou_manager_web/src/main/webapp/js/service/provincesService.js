app.service('provincesService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../provinces/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../provinces/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeProvincesList = function (idList) {
        return $http.get('../provinces/removeProvinces.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../provinces/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateProvinces = function (provinces) {
        return $http.post('../provinces/updateProvinces.do', provinces)
    };

    //新增品牌
    this.saveProvinces = function (provinces) {
        return $http.post('../provinces/saveProvinces.do', provinces)
    };   
});
