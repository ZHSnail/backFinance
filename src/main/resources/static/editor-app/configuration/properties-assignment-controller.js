/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/*
 * Assignment
 */
var KisBpmAssignmentCtrl = ['$scope', '$modal', function ($scope, $modal) {

    // Config for the modal window
    var opts = {
        template: 'editor-app/configuration/properties/assignment-popup.html?version=' + Date.now(),
        scope: $scope
    };

    // Open the dialog
    $modal(opts);
}];

var KisBpmAssignmentPopupCtrl = ['$scope', '$http', function ($scope, $http) {

    //参数初始化
    $scope.gridData = [];//表格数据
    $scope.gridDataName = 'gridData';
    $scope.selectTitle = '选择审批人';
    $scope.columnData = [];//表格列数据
    $scope.columnDataName = 'columnData';
    $scope.selectType = 0;//0-代理人，1-候选人，2-候选组
    $scope.totalServerItems = 0;//表格总条数
    //分页初始化
    $scope.pagingOptions = {
        pageSizes: [10],//page 行数可选值
        pageSize: 10, //每页行数
        currentPage: 1, //当前显示页数
    };
    // Put json representing assignment on scope
    if ($scope.property.value !== undefined && $scope.property.value !== null
        && $scope.property.value.assignment !== undefined
        && $scope.property.value.assignment !== null) {
        $scope.assignment = $scope.property.value.assignment;
    } else {
        $scope.assignment = {};
    }
    if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0) {
        $scope.assignment.candidateUsers = [{value: ''}];
    }

    // Click handler for + button after enum value
    var userValueIndex = 1;
    $scope.addCandidateUserValue = function (index) {
        $scope.assignment.candidateUsers.splice(index + 1, 0, {value: 'value ' + userValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateUserValue = function (index) {
        $scope.assignment.candidateUsers.splice(index, 1);
    };

    if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0) {
        $scope.assignment.candidateGroups = [{value: ''}];
    }

    var groupValueIndex = 1;
    $scope.addCandidateGroupValue = function (index) {
        $scope.assignment.candidateGroups.splice(index + 1, 0, {value: 'value ' + groupValueIndex++});
    };

    // Click handler for - button after enum value
    $scope.removeCandidateGroupValue = function (index) {
        $scope.assignment.candidateGroups.splice(index, 1);
    };

    $scope.save = function () {

        $scope.property.value = {};
        handleAssignmentInput($scope);
        $scope.property.value.assignment = $scope.assignment;

        $scope.updatePropertyInModel($scope.property);
        $scope.close();
    };

    // Close button handler
    $scope.close = function () {
        handleAssignmentInput($scope);
        $scope.property.mode = 'read';
        $scope.$hide();
    };

    var handleAssignmentInput = function ($scope) {
        if ($scope.assignment.candidateUsers) {
            var emptyUsers = true;
            var toRemoveIndexes = [];
            for (var i = 0; i < $scope.assignment.candidateUsers.length; i++) {
                if ($scope.assignment.candidateUsers[i].value != '') {
                    emptyUsers = false;
                } else {
                    toRemoveIndexes[toRemoveIndexes.length] = i;
                }
            }

            for (var i = 0; i < toRemoveIndexes.length; i++) {
                $scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
            }

            if (emptyUsers) {
                $scope.assignment.candidateUsers = undefined;
            }
        }

        if ($scope.assignment.candidateGroups) {
            var emptyGroups = true;
            var toRemoveIndexes = [];
            for (var i = 0; i < $scope.assignment.candidateGroups.length; i++) {
                if ($scope.assignment.candidateGroups[i].value != '') {
                    emptyGroups = false;
                } else {
                    toRemoveIndexes[toRemoveIndexes.length] = i;
                }
            }

            for (var i = 0; i < toRemoveIndexes.length; i++) {
                $scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
            }

            if (emptyGroups) {
                $scope.assignment.candidateGroups = undefined;
            }
        }
    };
    //数据监视
    $scope.dataWatch = function () {
        //分页数据监视
        $scope.$watch('pagingOptions', function (newValue, oldValue) {
            $scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
        }, true);

        //当切换类型时，初始化当前页
        $scope.$watch('selectType', function (newValue, oldValue) {
            if (newValue != oldValue) {
                $scope.pagingOptions.currentPage = 1;
                $scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            }
        }, true);
    };
    //异步请求表格数据
    $scope.getPagedDataAsync = function (pageNum, pageSize) {
        var url;
        if ($scope.selectType == 2) {
            url = '/system/roleList';
            $scope.columnData = $scope.groupColumns;
        } else {
            // url = '/system/userList';
            // $scope.columnData = $scope.userColumns;
            url = '/system/roleList';
            $scope.columnData = $scope.groupColumns;
        }
        $http({
            method: 'GET',
            url: ACTIVITI.CONFIG.contextRoot + url,
            params: {
                'pageNum': pageNum,
                'pageSize': pageSize,
            },
        }).then(function successCallback(response) {
            $scope.gridData = response.data.obj.list;
            $scope.totalServerItems = response.data.obj.total;
        }, function errorCallback(response) {
            // 请求失败执行代码
            $scope.gridData = [];
            $scope.totalServerItems = 0;
        });
    }
    //表格属性配置
    $scope.gridOptions = {
        data: $scope.gridDataName,
        multiSelect: false,//不可多选
        enablePaging: true,
        pagingOptions: $scope.pagingOptions,
        totalServerItems: 'totalServerItems',
        i18n:'zh-cn',
        showFooter: true,
        showSelectionCheckbox: false,
        columnDefs : $scope.columnDataName,
        beforeSelectionChange: function (event) {
            var data = event.entity.id;

            if($scope.selectType == 0){//选代理人
                event.entity.checked = !event.selected;
                $scope.assignment.assignee = data;
            }else if($scope.selectType == 1){//候选人
                var obj = {value: data};
                if(!array_contain($scope.assignment.candidateUsers, obj.value)){
                    $scope.assignment.candidateUsers.push(obj);
                }
            }else if($scope.selectType == 2){//候选组
                var obj = {value: event.entity.id};
                if(!array_contain($scope.assignment.candidateGroups, obj.value)){
                    $scope.assignment.candidateGroups.push(obj);
                }
            }
            return true;
        }
    };

    //选择用户时表头
    $scope.userColumns = [
        {
            field: 'id',
            type: 'number',
            displayName: '用户Id',
            minWidth: 100,
            width: '50%'
        },
        {
            field: 'userName',
            displayName: '用户名',
            minWidth: 100,
            width: '50%'
        },
    ];
    //选择用户组时表头
    $scope.groupColumns = [
        {
            field: 'id',
            displayName: '角色Id',
            minWidth: 100,
            width: '50%'
        },
        {
            field: 'roleName',
            displayName: '角色名称',
            minWidth: 100,
            width: '50%'
        },
    ];
    //代理人(审批人)
    $scope.selectAssignee = function () {
        $scope.selectType = 0;
        $scope.selectTitle = '选择审批人';
        $scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize)
    };

    //候选人
    $scope.selectCandidate = function () {
        $scope.selectType = 1;
        $scope.selectTitle = '选择审批角色';
		$scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize)
	};

    //候选组
    $scope.selectGroup = function () {
        $scope.selectType = 2;
        $scope.selectTitle = '选择审批角色';
		$scope.getPagedDataAsync($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize)
	};

    //声明----如果有此 contains 直接用最好
    function array_contain(array, obj) {
        for (var i = 0; i < array.length; i++) {
            if (array[i].value == obj)//如果要求数据类型也一致，这里可使用恒等号===
                return true;
        }
        return false;
    }

    /*
        // Put json representing assignment on scope
        if ($scope.property.value !== undefined && $scope.property.value !== null
            && $scope.property.value.assignment !== undefined
            && $scope.property.value.assignment !== null)
        {
            $scope.assignment = $scope.property.value.assignment;
        } else {
            $scope.assignment = {};
        }
        if ($scope.assignment.candidateUsers == undefined || $scope.assignment.candidateUsers.length == 0)
        {
            $scope.assignment.candidateUsers = [{value: ''}];
        }

        // Click handler for + button after enum value
        var userValueIndex = 1;
        $scope.addCandidateUserValue = function(index) {
            $scope.assignment.candidateUsers.splice(index + 1, 0, {value: 'value ' + userValueIndex++});
        };

        // Click handler for - button after enum value
        $scope.removeCandidateUserValue = function(index) {
            $scope.assignment.candidateUsers.splice(index, 1);
        };

        if ($scope.assignment.candidateGroups == undefined || $scope.assignment.candidateGroups.length == 0)
        {
            $scope.assignment.candidateGroups = [{value: ''}];
        }

        var groupValueIndex = 1;
        $scope.addCandidateGroupValue = function(index) {
            $scope.assignment.candidateGroups.splice(index + 1, 0, {value: 'value ' + groupValueIndex++});
        };

        // Click handler for - button after enum value
        $scope.removeCandidateGroupValue = function(index) {
            $scope.assignment.candidateGroups.splice(index, 1);
        };

        $scope.save = function() {

            $scope.property.value = {};
            handleAssignmentInput($scope);
            $scope.property.value.assignment = $scope.assignment;

            $scope.updatePropertyInModel($scope.property);
            $scope.close();
        };

        // Close button handler
        $scope.close = function() {
            handleAssignmentInput($scope);
            $scope.property.mode = 'read';
            $scope.$hide();
        };

        var handleAssignmentInput = function($scope) {
            if ($scope.assignment.candidateUsers)
            {
                var emptyUsers = true;
                var toRemoveIndexes = [];
                for (var i = 0; i < $scope.assignment.candidateUsers.length; i++)
                {
                    if ($scope.assignment.candidateUsers[i].value != '')
                    {
                        emptyUsers = false;
                    }
                    else
                    {
                        toRemoveIndexes[toRemoveIndexes.length] = i;
                    }
                }

                for (var i = 0; i < toRemoveIndexes.length; i++)
                {
                    $scope.assignment.candidateUsers.splice(toRemoveIndexes[i], 1);
                }

                if (emptyUsers)
                {
                    $scope.assignment.candidateUsers = undefined;
                }
            }

            if ($scope.assignment.candidateGroups)
            {
                var emptyGroups = true;
                var toRemoveIndexes = [];
                for (var i = 0; i < $scope.assignment.candidateGroups.length; i++)
                {
                    if ($scope.assignment.candidateGroups[i].value != '')
                    {
                        emptyGroups = false;
                    }
                    else
                    {
                        toRemoveIndexes[toRemoveIndexes.length] = i;
                    }
                }

                for (var i = 0; i < toRemoveIndexes.length; i++)
                {
                    $scope.assignment.candidateGroups.splice(toRemoveIndexes[i], 1);
                }

                if (emptyGroups)
                {
                    $scope.assignment.candidateGroups = undefined;
                }
            }
        };*/
}];