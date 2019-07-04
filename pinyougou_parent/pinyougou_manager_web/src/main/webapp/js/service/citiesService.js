app.service('citiesService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../cities/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../cities/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeCitiesList = function (idList) {
        return $http.get('../cities/removeCities.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../cities/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateCities = function (cities) {
        return $http.post('../cities/updateCities.do', cities)
    };

    //新增品牌
    this.saveCities = function (cities) {
        return $http.post('../cities/saveCities.do', cities)
    };   
});
