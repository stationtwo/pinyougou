app.service('payLogService', function ($http) {
    //旧的查询方法(弃置)								   
    this.findPage = function (pageNum, pageSize) {
        return $http.get('../payLog/findPage.do?pageNum=' + pageNum + '&pageSize=' + pageSize)
    };

    //查询方法,读取列表数据绑定到表单中
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../payLog/findPage.do?pageNum=' + pageNum + "&pageSize=" + pageSize, searchEntity)
    };

    //删除方法
    this.removePayLogList = function (idList) {
        return $http.get('../payLog/removePayLog.do?idList=' + idList)
    };

    //根据id查询
    this.findOne = function (id) {
        return $http.get('../payLog/findOne.do?id=' + id)
    };

    //修改品牌
    this.updatePayLog = function (payLog) {
        return $http.post('../payLog/updatePayLog.do', payLog)
    };

    //新增品牌
    this.savePayLog = function (payLog) {
        return $http.post('../payLog/savePayLog.do', payLog)
    };   
});
