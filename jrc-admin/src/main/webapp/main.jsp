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
    </style>
</head>
<body class="easyui-layout" style="min-width: 1200px;min-height: 800px">
    <div data-options="region:'north'" style="height:50px"></div>
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