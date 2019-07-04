app.service('userService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../user/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../user/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removeUserList = function (idList) {
        return $http.get('../user/removeUser.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../user/findOne.do?id=' + id)
    };

    //修改品牌
    this.updateUser = function (user) {
        return $http.post('../user/updateUser.do', user)
    };

    //新增品牌
    this.saveUser = function (user) {
        return $http.post('../user/saveUser.do', user)
    };   
});
