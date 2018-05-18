<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
    <title>jQuery easy UI message使用方法的书写</title>
    <script type="text/javascript">
        $(function () {
            $.messager.show({
                title: "欢迎登陆",
                msg: "欢迎您的登陆",
                timeout: 5000
            });
       /* $.messager.alert("弹窗", "这是一个弹窗 只有一个确认", "info");
        $.messager.alert('Warning','The warning message');*/
        })




    </script>
</head>
<body>

</body>
</html>
