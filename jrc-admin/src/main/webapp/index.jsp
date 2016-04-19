<%--
  Created by IntelliJ IDEA.
  User: rxb
  Date: 2016/4/18
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理控制平台</title>
    <link rel="stylesheet" type="text/css" href="libs/easyui/themes/material/easyui.css">
    <link rel="stylesheet" type="text/css" href="libs/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/easyui-icon.css">
</head>
<body class="easyui-layout" style="min-width: 1100px;min-height: 650px">
<div class="console-topbar" data-options="region:'north',border:false">
    <div class="topbar-wrap">
        <div class="topbar-head topbar-left">
            <a href="http://www.aliyun.com" target="_blank" class="topbar-logo topbar-left">
                <span class="icon-logo1" style="font-size: 16px">台</span>
            </a>
            <a href="http://home.console.aliyun.com" target="_self" class="topbar-home-link topbar-btn topbar-left">
                <span class="ng-binding">管理控制台</span>
            </a>
        </div>
        <div class="topbar-info topbar-right">
            <!-- 搜索 -->
            <div class="topbar-btn topbar-btn-search topbar-left">
                <div>
                    <a href="#" class="topbar-btn" style="background-color: inherit">
                        <span class="icon-search"></span>
                        <span class="ng-binding">搜索</span>
                    </a>
                </div>
            </div>
            <!-- 站内信 -->
            <div class="topbar-notice topbar-btn topbar-left">
                <a href="#" class="topbar-btn-notice">
                    <span class="span-icon icon-bell-white"></span>
                    <span class="topbar-btn-notice-num">24</span>
                </a>
            </div>
            <!-- 用户 -->
            <div class="topbar-left topbar-user">
                <div class="topbar-info-item">
                    <a href="#" class="topbar-btn">
                        <span title="account.aliyunIdOrigin" class="ng-binding">zhan****@skyocean.com</span>
                        <span class="icon-arrow-down"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="console-sidebar" data-options="region:'west',border:false" title="导航菜单" style="width:180px;">
    <ul id="leftMenuTree" class="accordion-tree"></ul>
</div>
<div data-options="region:'center',border:false" style="padding:15px">
    <%--<div class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%">
        <div class="child-tree-sidebar" data-options="region:'west',border:false">
            <div class="child-tree-sidebar-top"></div>
        </div>
        <div data-options="region:'center',border:false">

        </div>
    </div>--%>
    <table class="easyui-datagrid"
           data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true">
        <thead>
        <tr>
            <th data-options="field:'itemid'" width="80">Item ID</th>
            <th data-options="field:'productid'" width="100">Product ID</th>
            <th data-options="field:'listprice',align:'right'" width="80">List Price</th>
            <th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
            <th data-options="field:'attr1'" width="150">Attribute</th>
            <th data-options="field:'status',align:'center'" width="50">Status</th>
        </tr>
        </thead>
    </table>
</div>
</body>
<script type="text/javascript" src="libs/jquery/jquery.min.js"></script>
<script type="text/javascript" src="libs/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="libs/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</html>