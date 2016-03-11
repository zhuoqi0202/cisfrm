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
    //form model in dialog
    var DialogModel = (function ($) {
        function _dialogModel(params) {
            if (this instanceof DialogModel) {
                this.$cpyCode = $('#form input[name="cpyCode"]');
                this.$cpyName = $('#form input[name="cpyName"]');
                this.$cpytype1code = $('#form input[name="cpytype1code"]');
                this.$cpytype2code = $('#form input[name="cpytype2code"]');
                this.$cptltypecode = $('#form input[name="cptltypecode"]');
                this.$isVirtual = $('#form input[name="isVirtual"]');
                this.$validStatus = $('#form input[name="validStatus"]');
                this.$moduleId = $('#form input[name="moduleId"]');
                this.$cpytype1code.combobox(params.orgType);
                this.$cpytype2code.combobox(params.cpyType);
                this.$cptltypecode.combobox(params.capitalType);
            } else {
                return new DialogModel(params);
            }
        }

        _dialogModel.prototype = {
            init: function () {
                this.$moduleId.val('');
                this.$cpyCode.val('');
                this.$cpyName.val('');
                this.$cpytype1code.combobox('setValue', '');
                this.$cpytype2code.combobox('setValue', '');
                this.$cptltypecode.combobox('setValue', '');
                this.$isVirtual[1].checked = true;
                this.$validStatus[0].checked = true;
            },
            getData: function () {
                var that = this;
                var result = {
                    cpyCode: this.$cpyCode.val(),
                    cpyName: this.$cpyName.val(),
                    cpytype1code: this.$cpytype1code.combobox('getValue'),
                    cpytype1name: this.$cpytype1code.combobox('getText'),
                    cpytype2code: this.$cpytype2code.combobox('getValue'),
                    cpytype2name: this.$cpytype2code.combobox('getText'),
                    cptltypecode: this.$cptltypecode.combobox('getValue'),
                    cptltypename: this.$cptltypecode.combobox('getText'),
                    isVirtual: (function () {
                        var data = '';
                        that.$isVirtual.each(function (i) {
                            if (this.checked == true) {
                                data = this.value;
                                return false;
                            }
                        });
                        return data;
                    })(),
                    validStatus: (function () {
                        var data = '';
                        that.$validStatus.each(function (i) {
                            if (this.checked == true) {
                                data = this.value;
                                return false;
                            }
                        });
                        return data;
                    })()
                };
                if (this.$moduleId.val()) {
                    result.moduleId = this.$moduleId.val();
                }
                return result;
            },
            setData: function (data) {
                this.$moduleId.val(data.moduleId);
                this.$cpyCode.val(data.cpyCode);
                this.$cpyName.val(data.cpyName);
                this.$cpytype1code.combobox('setValue', data.cpytype1code);
                this.$cpytype2code.combobox('setValue', data.cpytype2code);
                this.$cptltypecode.combobox('setValue', data.cptltypecode);
                this.$isVirtual.each(function (i) {
                    if (this.value == data.isVirtual) {
                        this.checked = true;
                    }
                });
                this.$validStatus.each(function (i) {
                    if (this.value == data.validStatus) {
                        this.checked = true;
                    }
                });
            }
        };
        return _dialogModel;
    })(jQuery);
    //translate combobox value for propertygrid
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
        var setting = {
            table: {
                url: '/cisfrm/manage/company',
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
                    {field: 'cpyCode', title: '机构代码', width: 100},
                    {field: 'cpyName', title: '机构名称', width: 100},
                    {field: 'cpytype1name', title: '机构类别', width: 100},
                    {field: 'cpytype2name', title: '公司类别', width: 100},
                    {field: 'cptltypename', title: '资本结构', width: 100},
                    {
                        field: 'isVirtual', title: '是否虚拟', width: 100, formatter: function (value) {
                        return value == 1 ? '是' : '否';
                    }
                    },
                    {
                        field: 'validStatus', title: '是否有效', width: 100, formatter: function (value) {
                        return value == 1 ? '是' : '否';
                    }
                    }
                ]]
            },
            grid: {
                data: {total: 0, rows: []},
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
                height: 350,
                closed: true,
                buttons: '#buttons'
            },
            url: {
                remove: '/cisfrm/manage/company/',
                POST: '/cisfrm/manage/company/',
                PUT: '/cisfrm/manage/company/',
                orgType: '/cisfrm/manage/codetype/isValid/orgType',
                cpyType: '/cisfrm/manage/codetype/isValid/cpyType',
                capitalType: '/cisfrm/manage/codetype/isValid/capitalType'
            }
        };
        //init datagrid
        var table = new Table('#table', setting.table);
        //init dialog
        var dialog = new Dialog('#dialog', setting.dialog);
        //The dialog value model
        var dialogModel = null;
        //init propertygrid
        var grid = null;
        $.when($.get(setting.url.orgType), $.get(setting.url.cpyType), $.get(setting.url.capitalType))
            .done(function (orgValue, cpyValue, capitalValue) {
                dialogModel = new DialogModel({
                    orgType: {
                        data: orgValue[0],
                        valueField: 'codeCode',
                        textField: 'codeCName'
                    },
                    cpyType: {
                        data: cpyValue[0],
                        valueField: 'codeCode',
                        textField: 'codeCName'
                    },
                    capitalType: {
                        data: capitalValue[0],
                        valueField: 'codeCode',
                        textField: 'codeCName'
                    }
                });
            })
            .done(function (orgValue, cpyValue, capitalValue) {
                var orgData = $.extend(true, [], orgValue[0]);
                var cpyData = $.extend(true, [], cpyValue[0]);
                var capitalData = $.extend(true, [], capitalValue[0]);
                var isVirtualData = [
                    {codeCode: '', codeCName: '所有'},
                    {codeCode: '1', codeCName: '是'},
                    {codeCode: '0', codeCName: '否'}
                ];
                var validStatusData = [
                    {codeCode: '', codeCName: '所有'},
                    {codeCode: '1', codeCName: '是'},
                    {codeCode: '0', codeCName: '否'}
                ];
                orgData.unshift({codeCode: '', codeCName: '所有'});
                cpyData.unshift({codeCode: '', codeCName: '所有'});
                capitalData.unshift({codeCode: '', codeCName: '所有'});
                var data = [
                    {key: 'cpyCode', name: "机构代码", value: "", group: "condition", editor: "text"},
                    {key: 'cpyName', name: "机构名称", value: "", group: "condition", editor: "text"},
                    {
                        key: 'cpytype1code', name: '机构类别', value: '所有', group: "condition", editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'codeCName',
                            textField: 'codeCName',
                            data: orgData
                        }
                    },
                        trans: trans(orgData)
                    },
                    {
                        key: 'cpytype2code', name: '公司类别', value: '所有', group: "condition", editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'codeCName',
                            textField: 'codeCName',
                            data: cpyData
                        }
                    },
                        trans: trans(cpyData)
                    },
                    {
                        key: 'cptltypecode', name: '资本结构', value: '所有', group: "condition", editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'codeCName',
                            textField: 'codeCName',
                            data: capitalData
                        }
                    },
                        trans: trans(capitalData)
                    },
                    {
                        key: 'isVirtual', name: "是否虚拟", value: "所有", group: "condition", editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'codeCName',
                            textField: 'codeCName',
                            data: isVirtualData
                        }
                    },
                        trans: trans(isVirtualData)
                    },
                    {
                        key: 'validStatus', name: "是否有效", value: "所有", group: "condition", editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'codeCName',
                            textField: 'codeCName',
                            data: validStatusData
                        }
                    },
                        trans: trans(validStatusData)
                    }
                ];
                setting.grid.data.total = data.length;
                setting.grid.data.rows = data;
                grid = new Grid('#grid', setting.grid);
            });
        //init button document
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
                if (!data.cpyCode) {
                    text += '请输入机构代码,';
                }
                if (!data.cpyName) {
                    text += '请输入机构名称,';
                }
                if (!data.cpytype1code) {
                    text += '请选择机构类别,';
                }
                if (!data.cpytype2code) {
                    text += '请选择公司类别,';
                }
                if (!data.cptltypecode) {
                    text += '请选择资本结构,';
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
                $.messager.confirm('Confirm', '是否要删除机构 ' + data.cpyName + '?', function (r) {
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