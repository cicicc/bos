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
  <%--  <script type="text/javascript" >
        $(function () {
            //在页面加载成功以后 基于标准json数据构造出一颗json树(不常用)

            //使用ajax构造节点数据
            var url = "${pageContext.request.contextPath}/json/menu.json";
            $.post(url, function (data) {
                //1.定义一个ZTree设置的属性
                var setting = {
                    data: {
                        simpleData: {
                            enable: true//启用简单格式的json数据描述节点数据
                        }
                    },
                    //设置回调函数
                    callback: {
                        //为节点绑定单击事件
                        onClick: function (event, treeId, treeNode) {
                            if (treeNode.page != undefined) {
                                //判断当前选项卡是否已经存在
                                var e = $("my-tabs").tabs("exists", treeNode.name);
                                alert(e);
                                if (e) {
                                    //如果选项卡存在的话 就定位到选项卡上面去
                                    $("my-tabs").tabs("select", treeNode.name);
                                }else{
                                    //不存在就动态添加选项卡
                                    $("$my-tabs").tabs("add",{
                                        title:treeNode.name,
                                        closable:true,
                                        content:'<iframe frameborder="0" width="100%" height="100%" src="http://www.baidu.com"></iframe>'
                                    });
                                }
                            }
                        }
                    }
                };//使用这个属性设置ztree的设置值
                //调用ZTree的api动态生成一棵树
                $.fn.zTree.init($("#my_ztree"), setting, data);
            });

        });
    </script>--%>

        <script type="text/javascript">
            $(function(){
                //页面加载完成后，执行这段代码----动态创建ztree
                var setting3 = {
                    data: {
                        simpleData: {
                            enable: true//使用简单json数据构造ztree节点
                        }
                    },
                    callback: {
                        //为ztree节点绑定单击事件
                        onClick: function(event, treeId, treeNode){
                            if(treeNode.page != undefined){
                                //判断选项卡是否已经存在
                                var e = $("#my-tabs").tabs("exists",treeNode.name);
                                if(e){
                                    //已经存在，选中
                                    $("#my-tabs").tabs("select",treeNode.name);
                                }else{
                                    //动态添加一个选项卡
                                    $("#my-tabs").tabs("add",{
                                        title:treeNode.name,
                                        closable:true,
                                        content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
                                    });
                                }
                            }
                        }
                    }
                };

                //发送ajax请求，获取json数据
                //jQuery提供 的ajax方法：ajax、post、get、load、getJSON、getScript
                var url = "${pageContext.request.contextPath}/json/menu.json";
                $.post(url,{},function(data){
                    //调用API初始化ztree
                    $.fn.zTree.init($("#my_ztree"), setting3, data);
                },'json');
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

