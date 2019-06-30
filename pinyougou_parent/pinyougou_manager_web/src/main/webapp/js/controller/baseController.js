app.controller('baseController', function ($scope) {
    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码
        $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    /**
     * 定义分页组件配置
     */
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();//重新加载
            $scope.selectIdList = [];
        }
    };


    /**
     * 定义id数组
     * @type {Array}
     */
    $scope.selectIdList = [];
//数组添加数据
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {//如果是被选中,则增加到数组
            $scope.selectIdList.push(id);
        } else {
            var idx = $scope.selectIdList.indexOf(id);
            $scope.selectIdList.splice(idx, 1);//删除
        }
    };
});


