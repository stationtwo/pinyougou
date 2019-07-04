app.service('freightTemplateService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../freightTemplate/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../freightTemplate/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeFreightTemplateList = function (idList) {
        return $http.get('../freightTemplate/removeFreightTemplate.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../freightTemplate/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateFreightTemplate = function (freightTemplate) {
        return $http.post('../freightTemplate/updateFreightTemplate.do', freightTemplate)
    };

    //新增品牌
    this.saveFreightTemplate = function (freightTemplate) {
        return $http.post('../freightTemplate/saveFreightTemplate.do', freightTemplate)
    };   
});
