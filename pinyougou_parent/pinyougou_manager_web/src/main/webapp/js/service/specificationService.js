app.service('specificationService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../specification/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../specification/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeSpecificationList = function (idList) {
        return $http.get('../specification/removeSpecification.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../specification/findOne.do?id=' + id)
    };

    //修改
    this.updateSpecification = function (specification) {
        return $http.post('../specification/updateSpecification.do', specification)
    };

    //新增
    this.saveSpecification = function (specification) {
        return $http.post('../specification/saveSpecification.do', specification)
    };

    //查询所有规格
    this.selectSpecList = function () {
        return $http.post('../specification/selectSpecList.do')
    };
});
