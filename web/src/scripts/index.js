var requestAddress = {
    getCurrentUser: '/cisfrm/getCurrent',
    getNav: '../test/nav.json',
    logout: '/cisfrm/logout'
};
$(function () {
    setTimeout(function () {
        var $mainUser = $('#main_user');
        $mainUser.menubutton({
            menu: '#main_user_menu'
        });
        var getCurrentUser = $.get(requestAddress.getCurrentUser).promise();
        getCurrentUser.done(function (data) {
            $mainUser.find('i').text(' ' + data.data.nickname);
        });
        $('#logout').click(function () {
            var logout = $.get(requestAddress.logout).promise();
            logout.done(function (data) {
                if (data.status == 0) {
                    window.location.href = '/login.html';
                }
            });
        });
    }, 0);

    setTimeout(function () {
        var $mainNav = $('#main_nav'), $tabs = $('#tabs'), $tabsMenu = $('#tabsMenu'),
            setting = {
                callback: {
                    onClick: onTabClick
                }
            };
        var getNavData = $.get(requestAddress.getNav).promise();
        getNavData.done(function (data) {
            $.fn.zTree.init($mainNav, setting, data);
        });

        function onTabClick(event, treeId, treeNode, clickFlag) {
            open(treeNode.name, treeNode.path);
        }

        function open(text, url) {
            if ($tabs.tabs('exists', text)) {
                $tabs.tabs('select', text);
            } else {
                $tabs.tabs('add', {
                    title: text,
                    closable: true,
                    content: '<iframe src="' + url + '" frameBorder="0" border="0" style="width: 100%;height: 99.6% " />'
                });
            }
        }

        $tabs.tabs({
            fit:true,
            onContextMenu: function (e, title) {
                e.preventDefault();
                $tabsMenu.menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        });
        $tabsMenu.menu({
            onClick: function (item) {
                CloseTab(this, item.name);
            }
        });

        function CloseTab(menu, type) {
            var curTabTitle = $(menu).data("tabTitle");

            if (type === "close") {
                $tabs.tabs("close", curTabTitle);
                return;
            }

            var allTabs = $tabs.tabs("tabs");
            var closeTabsTitle = [];

            $.each(allTabs, function () {
                var opt = $(this).panel("options");
                if (opt.closable && opt.title != curTabTitle && type === "Other") {
                    closeTabsTitle.push(opt.title);
                } else if (opt.closable && type === "All") {
                    closeTabsTitle.push(opt.title);
                }
            });

            for (var i = 0; i < closeTabsTitle.length; i++) {
                $tabs.tabs("close", closeTabsTitle[i]);
            }
        }
    }, 0);

});