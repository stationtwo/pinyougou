app.service('specificationOptionService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../specificationOption/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../specificationOption/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeSpecificationOptionList = function (idList) {
        return $http.get('../specificationOption/removeSpecificationOption.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../specificationOption/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateSpecificationOption = function (specificationOption) {
        return $http.post('../specificationOption/updateSpecificationOption.do', specificationOption)
    };

    //新增品牌
    this.saveSpecificationOption = function (specificationOption) {
        return $http.post('../specificationOption/saveSpecificationOption.do', specificationOption)
    };   
});
