<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>测试easyUI layout代码书写</title>
    <%--引入easyUI的js和css之类的文件--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //页面加载完成后，为上面的按钮绑定事件
            $("#but1").click(function(){
                //判断“系统管理”选项卡是否存在
                var e = $("#my-tabs").tabs("exists","系统管理");
                if(e){
                    //已经存在，选中就可以
                    $("#my-tabs").tabs("select","系统管理");
                }else{
                    //调用tabs对象的add方法动态添加一个选项卡
                    $("#my-tabs").tabs("add",{
                        title:'系统管理',
                        iconCls:'icon-edit',
                        closable:true,
                        content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
                    });
                }
            });
        });
    </script>
</head>
<body class="easyui-layout">
<%--使用div表示区域 --%>
<div title="BOS物流管理系统" data-options="region:'north'" style="height: 100px">北部区域</div>
<div style="width: 200px" title="系统菜单" data-options="region:'west'">
    <div class="easyui-accordion" data-options="fit:true">
        <%--fit:true 表示填充父容器 自适应--%>
            <div title="系统管理" id="system-manage" >
            <a id="but1" class="easyui-linkbutton">添加一个选项卡</a>
        </div>
        <div title="角色管理"></div>
        <div title="用户管理"></div>


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
