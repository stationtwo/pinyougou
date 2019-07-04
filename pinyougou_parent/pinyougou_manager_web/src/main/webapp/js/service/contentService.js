app.service('contentService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../content/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../content/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeContentList = function (idList) {
        return $http.get('../content/removeContent.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../content/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateContent = function (content) {
        return $http.post('../content/updateContent.do', content)
    };

    //新增品牌
    this.saveContent = function (content) {
        return $http.post('../content/saveContent.do', content)
    };   
});
