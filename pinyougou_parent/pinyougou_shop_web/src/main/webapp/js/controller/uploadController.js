app.controller('uploadController',function ($scope) {
        var vm = $scope.vm = {};
        vm.value = 0;
        vm.style = 'progress-bar-info';
        vm.showLabel = true;
        vm.striped = true;
});

//网页相关
/*
<div ng-class="{progress: true, 'progress-striped': vm.striped}">
    <div ng-class="['progress-bar', vm.style]" ng-style="{width: vm.value + '%'}">
        <div ng-if="vm.showLabel">{{vm.value}}%</div>
    </div>
</div>*/
