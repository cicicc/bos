<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>ZTree的基于简单json数据构造ZTree</title>
    <%--引入easyUI的js和css之类的文件--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" >
        $(function () {
            //在页面加载成功以后 基于标准json数据构造出一颗json树(不常用)
            //1.定义一个ZTree设置的属性
            var setting = {
                data:{
                    simpleData:{
                        enable:true//启用简单格式的json数据描述节点数据
                    }
                }
            };//使用这个属性设置ztree的设置值
            //使用简单ZTree构造节点数据
            var zNodes = [
                {"id":"1","name":"系统管理1","pid":"1"},//最高节点的话就将pid设为不存在的值或者本身
                {"id":"2","name":"系统管理儿子1","pId":"1"},
                {"id":"3","name":"系统管理儿子2","pId":"1"},
                {"id":"4","name":"系统管理孙子1","pId":"2"},
                {"id":"5","name":"系统管理孙子2","pId":"2"},
            ];
            //调用ZTree的api动态生成一棵树
            $.fn.zTree.init($("#my_ztree"), setting, zNodes);
        });
    </script>
</head>
<body class="easyui-layout">
<%--使用div表示区域 --%>
<div title="BOS物流管理系统" data-options="region:'north'" style="height: 100px">北部区域</div>
<div style="width: 200px" title="系统菜单" data-options="region:'west'">
    <div class="easyui-accordion" data-options="fit:true">
        <div title="菜单1">
        <ul id="my_ztree" class="ztree">

        </ul>
        </div>
    </div>

</div>
<div  data-options="region:'center'">
    <%--制作tabs制作选项卡--%>
    <div class="easyui-tabs" id="my-tabs" data-options="fit:true">
        <div data-options="closable:true,iconCls:'icon-remove'" title="系统管理"></div>
    </div>
</div>
<div style="width: 100px" data-options="region:'east'">东部区域</div>
<div  data-options="region:'south'" style="height: 50px">南部区域</div>


</body>
