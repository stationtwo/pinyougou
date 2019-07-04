app.service('contentCategoryService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../contentCategory/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../contentCategory/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeContentCategoryList = function (idList) {
        return $http.get('../contentCategory/removeContentCategory.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../contentCategory/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateContentCategory = function (contentCategory) {
        return $http.post('../contentCategory/updateContentCategory.do', contentCategory)
    };

    //新增品牌
    this.saveContentCategory = function (contentCategory) {
        return $http.post('../contentCategory/saveContentCategory.do', contentCategory)
    };   
});
