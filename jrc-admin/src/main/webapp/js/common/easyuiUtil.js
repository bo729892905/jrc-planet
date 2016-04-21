var EasyuiUtil={
    initDatagrid:function(id,columns,url) {
        $("#"+id).datagrid({
            url:"user/list",
            columns:columns,
            singleSelect:true,
            fit:true,
            fitColumns:true,
            rownumbers:true,
            pagination:true
        });
    }
}