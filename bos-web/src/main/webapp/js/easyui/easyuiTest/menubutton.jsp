<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/18
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>menu button 的使用</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>
    <!--<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>-->
    <div id="mm">
        <!--使用子div制作选项-->
        <div onclick="alert(111)" data-options="iconCls:'icon-edit'">修改密码</div>
        <div>你猜</div>
        <div class="menu-sep"></div>
    </div>

</body>
</html>
