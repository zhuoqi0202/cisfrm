/**
 * Created by Dawn on 16/2/14.
 */
var requestUrl = {};
$(function () {
    var $table = $('#users_table'),
    //$search = $('#users_search'),
        $dialog = $('#dialog'),
        $add = $('#add'),
        $edit = $('#edit'),
        $remove = $('#remove'),
        $submit = $('#submit'),
        $cancel = $('#cancel'),
        $moduleId = $('input[name="moduleId"]'),
        $username = $('input[name="username"]'),
        $password = $('input[name="password"]'),
        $nickname = $('input[name="nickname"]'),
        $email = $('input[name="email"]'),
        $roles = $('input[name="roles"]'),
        flag = '';
    var setting_datagrid = {
            url: '/cisfrm/manage/user',
            fit: true,
            fitColumns: true,
            rownumbers: true,
            singleSelect: true,
            method: 'get',
            idField: 'moduleId',
            toolbar: '#toolbar',
            columns: [[
                {field: 'moduleId', checkbox: true},
                {field: 'username', title: '用户名', width: 100},
                {field: 'nickname', title: '姓名', width: 100},
                {
                    field: 'roles', title: '角色', width: 100,
                    formatter: function (value, rowData, rowIndex) {
                        var i = 0, len = value.length;
                        var text = '';
                        for (; i < len; i++) {
                            text += value[i].name + ",";
                        }
                        return text.substring(0, text.length - 1);
                    }
                },
                {field: 'email', title: '电子邮箱', width: 200}
            ]]
        },
        setting_search = {width: 400, prompt: '搜索'},
        setting_dialog = {
            modal: true,
            width: 380,
            height: 300,
            closed: true,
            buttons: '#buttons',
            onClose: clear
        },
        setting_combobox = {
            url: '/cisfrm/manage/role/getAll',
            method: 'get',
            required: true,
            multiple: true,
            valueField: 'moduleId',
            textField: 'name'
        };

    function closeDialog() {
        $dialog.dialog("close");
    }

    function add() {
        $roles.combobox('setValues', [1]);
        flag = 'add';
        $dialog.dialog("open");
    }

    function submit() {
        var text = valid();
        if (text) {
            $.messager.alert('警告', text, 'error');
            return;
        }
        var user = {
            username: $username.val(),
            nickname: $nickname.val(),
            password:$password.val(),
            email: $email.val(),
            roles: []
        };
        var moduleIds = $roles.combobox('getValues');
        var i = 0, len = moduleIds.length;
        for (; i < len; i++) {
            user.roles[i] = {moduleId: moduleIds[i]}
        }
        var requestSetting = {
            url: '/cisfrm/manage/user',
            contentType: "application/json;",
            dataType: 'json'
        };
        if (flag === 'edit') {
            requestSetting.method = 'PUT';
            user.moduleId = $moduleId.val();
        } else if (flag === 'add') {
            requestSetting.method = 'POST';
        }
        requestSetting.data = JSON.stringify(user);
        var requestAdd = $.ajax(requestSetting).promise();
        requestAdd.done(success);
        requestAdd.fail(fail);
    }

    function success(data) {
        refresh();
        closeDialog();
    }

    function fail(x) {
        var data = x.responseJSON;
        var text = dealWithMessage(data);
        $.messager.alert('警告', text, 'error');
    }

    function refresh() {
        $table.datagrid('load');
    }

    function dealWithMessage(message) {
        var errors = message.errors;
        var i = 0, len = errors.length;
        var text = '';
        for (; i < len; i++) {
            var error = errors[i];
            if (error.code === 40002) {
                if (!haveBlank(errors)) {
                    text += error.msg + ",";
                }
            } else {
                text += error.msg + ",";
            }
        }
        return text.substring(0, text.length - 1);
    }

    function haveBlank(errors) {
        var i = 0;
        len = errors.length;
        for (; i < len; i++) {
            if (errors.code === 40000) {
                return true;
            }
        }
        return false;
    }

    function valid() {
        var text = '';
        var username = $.trim($username.val());
        var nickname = $.trim($nickname.val());
        var roles = $roles.combobox('getValues');
        if (!username) {
            text += '请输入用户名,';
        }
        if (!nickname) {
            text += '请输入姓名,';
        }
        if (roles.length == 0) {
            text += '请选择角色,';
        }
        return text.substring(0, text.length - 1);
    }

    function edit() {
        var selected = getSelected();
        if (selected) {
            $moduleId.val(selected.moduleId);
            $username.val(selected.username);
            $nickname.val(selected.nickname);
            $email.val(selected.email);
            var roles = [];
            for (var i = 0; i < selected.roles.length; i++) {
                roles[i] = selected.roles[i].moduleId;
            }
            $roles.combobox('setValues', roles);
            $dialog.dialog("open");
            flag = 'edit';
        } else {
            $.messager.alert('警告', '请选择一条纪录', 'error');
        }
    }

    function remove() {
        var selected = getSelected();
        if (selected) {
            $.messager.confirm('Confirm', '是否要删除用户 ' + selected.username + '?', function (r) {
                if (r) {
                    var request = $.ajax({
                        url: '/cisfrm/manage/user/' + selected.moduleId,
                        method: 'DELETE'
                    }).promise();
                    request.done(function (data) {
                        refresh();
                    });
                }
            });
        } else {
            $.messager.alert('警告', '请选择一条纪录', 'error');
        }

    }

    function getSelected() {
        return $table.datagrid('getSelected');
    }

    function clear() {
        $('#form input[name!="roles"]').val('');
        $roles.combobox('setValues', []);
        flag = '';
    }

    $table.datagrid(setting_datagrid);
    //$search.searchbox(setting_search);
    $dialog.dialog(setting_dialog);
    $roles.combobox(setting_combobox);
    $add.click(add);
    $edit.click(edit);
    $remove.click(remove);
    $cancel.click(closeDialog);
    $submit.click(submit);
});
