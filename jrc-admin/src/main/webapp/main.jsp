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
    <title>Complex Layout - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="libs/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="libs/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="libs/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="libs/easyui/jquery.easyui.min.js"></script>
    <style>
        html.panel-fit{overflow: auto}
        .console-topbar{
            position: relative;
            z-index: 100;
            clear: both;
            height: 50px;
            background: #09C;
            font-size: 12px;
        }
        .console-topbar .topbar-wrap, .console-topbar .topbar-logo, .console-topbar .topbar-home, .console-topbar .topbar-home-link, .console-topbar .topbar-nav, .console-topbar .topbar-info {
            height: 100%;
        }
        .console-topbar .topbar-head {
            background: #008fbf;
            height: 50px;
            position: relative;
            z-index: 3;
        }
        .console-topbar .topbar-left {
            float: left;
        }
        .console-topbar a {
            text-decoration: none;
        }
        .console-topbar .topbar-logo, .console-topbar .topbar-home {
            display: block;
            width: 50px;
            background: #0099cc;
            font-size: 18px;
            color: #FFF;
            text-align: center;
            line-height: 50px;
        }
        .console-topbar .topbar-logo {
            background: #0087b4;
        }
        .console-topbar .topbar-logo span, .console-topbar .topbar-home span {
            line-height: 50px;
        }
        .console-topbar .topbar-btn {
            color: #fff;
            font-size: 14px;
            line-height: 50px;
        }
        .console-topbar .topbar-home-link {
            padding: 0 20px;
            margin-right: 1px;
            background: #09c;
        }
        .console-topbar .topbar-home-link {
             padding: 0 20px;
             margin-right: 1px;
             background: #09c;
         }
    </style>
</head>
<body class="easyui-layout" style="min-width: 1200px;min-height: 800px">
    <div class="console-topbar" data-options="region:'north',border:false">
        <div class="topbar-wrap">
            <div class="topbar-head topbar-left">
                <a href="http://www.aliyun.com" target="_blank" class="topbar-logo topbar-left">
                    <span class="icon-logo1"></span>
                </a>
                <a href="http://home.console.aliyun.com" target="_self" class="topbar-home-link topbar-btn topbar-left">
                    <span class="ng-binding">管理控制台</span>
                </a>
            </div>
        </div>
    </div>
    <div data-options="region:'south'" style="height:50px;"></div>
    <div data-options="region:'east'" title="East" style="width:180px;">
        <ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:true"></ul>
    </div>
    <div data-options="region:'west'" title="导航菜单" style="width:100px;">
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="Title1" style="padding:10px;">
                content1
            </div>
            <div title="Title2" data-options="selected:true" style="padding:10px;">
                content2
            </div>
            <div title="Title3" style="padding:10px">
                content3
            </div>
        </div>
    </div>
    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
        <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
            <div title="About" data-options="href:'_content.html'" style="padding:10px"></div>
            <div title="DataGrid">
                <table class="easyui-datagrid"
                       data-options="url:'datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true,border:false">
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
        </div>
    </div>
</body>
</html>