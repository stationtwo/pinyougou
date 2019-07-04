app.service('typeTemplateService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../typeTemplate/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../typeTemplate/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeTypeTemplateList = function (idList) {
        return $http.get('../typeTemplate/removeTypeTemplate.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../typeTemplate/findOne.do?id=' + id)
    };

    //修改
    this.updateTypeTemplate = function (typeTemplate) {
        return $http.post('../typeTemplate/updateTypeTemplate.do', typeTemplate)
    };

    //新增
    this.saveTypeTemplate = function (typeTemplate) {
        return $http.post('../typeTemplate/saveTypeTemplate.do', typeTemplate)
    };


});
