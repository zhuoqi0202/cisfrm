/**
 * Created by Dawn on 16/2/14.
 */
var requestUrl = {};
$(function () {
    var $table = $('#users_table'),
        $search = $('#users_search'),
        $dialog = $('#dialog'),
        $add = $('#add'),
        $edit = $('#edit'),
        $remove = $('#remove'),
        $cancel = $('#cancel'),
        $username = $('input[name="username"]'),
        $password = $('input[name="password"]'),
        $nickname = $('input[name="nickname"]'),
        $email = $('input[name="email"]');
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
                {field: 'email', title: '电子邮箱', width: 200}
            ]]
        },
        setting_search = {width: 400, prompt: '搜索'},
        setting_dialog = {
            modal: true,
            width: 300,
            height: 290,
            closed: true,
            buttons: '#buttons',
            onClose: clear
        };
    $table.datagrid(setting_datagrid);
    $search.searchbox(setting_search);
    $dialog.dialog(setting_dialog);
    $add.click(add);
    $edit.click(edit);
    $remove.click(remove);
    function add() {
        $dialog.dialog("open");
    }

    function edit() {
        var selected = getSelected();
        if (selected) {
            $username.val(selected.username);
            $nickname.val(selected.nickname);
            $email.val(selected.email);
            $dialog.dialog("open");
        } else {
            alert("请选择");
        }
    }

    function remove() {
        alert('remove');
    }

    function getSelected() {
        return $table.datagrid('getSelected');
    }

    function clear() {
        $('#form input').val('');
    }
});
