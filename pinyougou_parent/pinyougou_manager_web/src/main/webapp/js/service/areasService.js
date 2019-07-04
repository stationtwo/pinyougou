app.service('areasService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../areas/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../areas/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeAreasList = function (idList) {
        return $http.get('../areas/removeAreas.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../areas/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateAreas = function (areas) {
        return $http.post('../areas/updateAreas.do', areas)
    };

    //新增品牌
    this.saveAreas = function (areas) {
        return $http.post('../areas/saveAreas.do', areas)
    };   
});
