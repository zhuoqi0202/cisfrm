/**
 * Created by Dawn on 16/3/4.
 */
$(function () {
    //datagrid class
    var Table = (function ($) {
        function _table(id, setting) {
            if (this instanceof Table) {
                this.id = $(id);
                this.id.datagrid(setting);
            } else {
                return new Table(id, setting);
            }
        }

        _table.prototype = {
            load: function (value) {
                var data = null;
                if (value) {
                    data = JSON.stringify(value);
                }
                this.id.datagrid('load', {data: data});
            },
            getSelected: function () {
                return this.id.datagrid('getSelected');
            }
        };
        return _table;
    })(jQuery);
    //dialog class
    var Dialog = (function ($) {
        function _dialog(id, setting) {
            if (this instanceof Dialog) {
                this.id = $(id);
                this.id.dialog(setting);
            } else {
                return new Dialog(id, setting);
            }
        }

        _dialog.prototype = {
            open: function () {
                this.id.dialog('open');
            },
            close: function () {
                this.id.dialog('close');
            }
        };
        return _dialog;
    })(jQuery);
    //propertygrid class
    var Grid = (function ($) {
        function _grid(id, setting) {
            if (this instanceof Grid) {
                this.id = $(id);
                this.id.propertygrid(setting);
            } else {
                return new Grid(id, setting);
            }
        }

        _grid.prototype = {
            addRow: function (row) {
                this.id.propertygrid('appendRow', row);
            },
            getData: function () {
                var result = {};
                var rows = this.id.propertygrid('getChanges');
                for (var i = 0; i < rows.length; i++) {
                    if (rows[i].trans) {
                        result[rows[i].key] = rows[i].trans(rows[i].value);
                    } else {
                        result[rows[i].key] = rows[i].value;
                    }
                }
                return result;
            },
            reset: function () {
                this.id.propertygrid('rejectChanges');
            }
        };
        return _grid;
    })(jQuery);
    var DialogModel = (function () {
        function _dialogModel() {
            if (this instanceof DialogModel) {
                this.$moduleId = $('#form input[name="moduleId"]');
                this.$areaCode = $('#form input[name="areaCode"]');
                this.$areaName = $('#form input[name="areaName"]');
                this.$singleName = $('#form input[name="singleName"]');
                this.$areaLevel = $('#form input[name="areaLevel"]');
                this.$validStatus = $('#form input[name="validStatus"]');
            } else {
                return new DialogModel();
            }
        }

        _dialogModel.prototype = {
            init: function () {
                this.$moduleId.val('');
                this.$areaCode.val('');
                this.$areaName.val('');
                this.$singleName.val('');
                this.$areaLevel.val('');
                this.$validStatus[0].checked = true;
            },
            getData: function () {
                var that = this;
                var result = {
                    moduleId: this.$moduleId.val(),
                    areaCode: this.$areaCode.val(),
                    areaName: this.$areaName.val(),
                    singleName: this.$singleName.val(),
                    areaLevel: this.$areaLevel.val(),
                    validStatus: (function () {
                        var data = '';
                        that.$validStatus.each(function () {
                            if (this.checked == true) {
                                data = this.value;
                                return false;
                            }
                        });
                        return data;
                    })()
                };
                return result;
            },
            setData: function (data) {
                this.$moduleId.val(data.moduleId);
                this.$areaCode.val(data.areaCode);
                this.$areaName.val(data.areaName);
                this.$singleName.val(data.singleName);
                this.$areaLevel.val(data.areaLevel);
                this.$validStatus.each(function (i) {
                    if (this.value == data.validStatus) {
                        this.checked = true;
                    }
                });
            }
        };
        return _dialogModel;
    })();
    var trans = function (data) {
        return function (value) {
            var i = 0, len = data.length;
            for (; i < len; i++) {
                if (data[i].codeCName === value) {
                    value = data[i].codeCode;
                    break;
                }
            }
            return value;
        }
    };
    (function ($) {
        var validStatusData = [
            {codeCode: '', codeCName: '所有'},
            {codeCode: '1', codeCName: '是'},
            {codeCode: '0', codeCName: '否'}
        ];
        var setting = {
            table: {
                url: '/cisfrm/manage/area',
                pageNumber: 1,
                pageSize: 30,
                fit: true,
                fitColumns: true,
                pageList: [10, 20, 30, 40, 50],
                rownumbers: true,
                pagination: true,
                idField: 'moduleId',
                singleSelect: true,
                method: 'get',
                toolbar: '#table_toolbar',
                columns: [[
                    {field: 'moduleId', checkbox: true},
                    {field: 'areaCode', title: '地区代码', width: 100},
                    {field: 'areaName', title: '地区名称', width: 100},
                    {field: 'singleName', title: '地区简称', width: 100},
                    {field: 'areaLevel', title: '地区层级', width: 100},
                    {
                        field: 'validStatus', title: '是否有效', width: 100, formatter: function (value) {
                        return value == 1 ? '是' : '否';
                    }
                    }
                ]]
            },
            grid: {
                data: {
                    total: 5,
                    rows: [
                        {key: 'areaCode', name: "地区代码", value: "", group: "condition", editor: "text"},
                        {key: 'areaName', name: "地区名称", value: "", group: "condition", editor: "text"},
                        {key: 'singleName', name: '地区简称', value: "", group: 'condition', editor: 'text'},
                        {key: 'areaLevel', name: '地区层级', value: "", group: 'condition', editor: 'text'},
                        {
                            key: 'validStatus', name: '是否有效', value: "所有", group: 'condition', editor: {
                            type: 'combobox',
                            options: {
                                valueField: 'codeCName',
                                textField: 'codeCName',
                                data: validStatusData
                            }
                        }, trans: trans(validStatusData)
                        }
                    ]
                },
                scrollbarSize: 0,
                fit: true,
                fitColumns: true,
                toolbar: '#grid_toolbar',
                columns: [[
                    {field: 'name', title: '属性名', width: 100, sortable: true},
                    {field: 'value', title: '属性值', width: 100, resizable: false}
                ]]
            },
            dialog: {
                modal: true,
                width: 380,
                height: 290,
                closed: true,
                buttons: '#buttons'
            },
            url: {
                remove: '/cisfrm/manage/area/',
                POST: '/cisfrm/manage/area/',
                PUT: '/cisfrm/manage/area/'
            }
        };
        var table = new Table('#table', setting.table);
        var dialog = new Dialog('#dialog', setting.dialog);
        var dialogModel = new DialogModel();
        var grid = new Grid('#grid', setting.grid);
        var $add = $('#add'), $edit = $('#edit'), $remove = $('#remove'), $cancel = $('#cancel'), $submit = $('#submit');
        //This variable is add or update event flag,update is PUT and add is POST
        var method = '';
        //cancel button event
        $cancel.click(function () {
            dialog.close();
        });
        //add button event
        $add.click(function () {
            method = 'POST';
            dialogModel.init();
            dialog.open();
        });
        //edit button event
        $edit.click(function () {
            method = 'PUT';
            dialogModel.init();
            var data = table.getSelected();
            if (data) {
                dialogModel.setData(data);
                dialog.open();
            } else {
                $.messager.alert('警告', '请选择一条纪录', 'error');
            }
        });
        //submit button event
        $submit.click(function () {
            function valid(data) {
                var text = '';
                if (!data.areaCode) {
                    text += '请输入地区代码,';
                }
                if (!data.areaName) {
                    text += '请输入地区名称,';
                }
                if (!data.singleName) {
                    text += '请输入地区简称,';
                }
                return text.substring(0, text.length - 1);
            }

            function dealWithMessage(message) {
                var text = '';
                var errors = message.errors;
                var i = 0, len = errors.length;
                for (; i < len; i++) {
                    var error = errors[i];
                    text += error.msg + ",";
                }
                return text.substring(0, text.length - 1);
            }

            var data = dialogModel.getData();
            var text = valid(data);
            if (text) {
                $.messager.alert('警告', text, 'error');
                return;
            }
            var request = (function (method) {
                return $.ajax({
                    url: setting.url[method],
                    method: method,
                    contentType: "application/json;",
                    dataType: 'json',
                    data: JSON.stringify(data)
                }).promise();
            })(method);
            request.done(function (data) {
                table.load();
                dialog.close();
            }).fail(function (val) {
                var data = val.responseJSON;
                var text = dealWithMessage(data);
                $.messager.alert('警告', text, 'error');
            });

        });
        $remove.click(function () {
            var data = table.getSelected();
            if (data) {
                $.messager.confirm('Confirm', '是否要删除地区 ' + data.areaName + '?', function (r) {
                    if (r) {
                        var request = $.ajax({
                            url: setting.url.remove + data.moduleId,
                            method: 'DELETE'
                        }).promise();
                        request.done(function (data) {
                            table.load();
                        });
                    }
                });
            } else {
                $.messager.alert('警告', '请选择一条纪录', 'error');
            }
        });
        //search button event
        $('#search').click(function () {
            var data = grid.getData();
            table.load(data);
        });
        //reset button event
        $('#reset').click(function () {
            grid.reset();
            table.load();
        });
    })(jQuery);
});