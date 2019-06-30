app.service('brandService', function ($http) {
    //旧的查询方法
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../brand/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../brand/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeBrandList = function (idList) {
        return $http.get('../brand/removeBrand.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../brand/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateBrand = function (brand) {
        return $http.post('../brand/updateBrand.do', brand)
    };

    //新增品牌
    this.saveBrand = function (brand) {
        return $http.post('../brand/saveBrand.do', brand)
    };

});