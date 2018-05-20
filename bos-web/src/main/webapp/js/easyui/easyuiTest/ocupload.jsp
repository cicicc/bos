<%@ page contentType="text/html;charset=UTF-8"  %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn1").upload({
                name: 'regionFile',
                action: "${pageContext.request.contextPath}/regionAction_fileUpload.action"
            });
        });
    </script>
    <title>一键上传功能代码的书写</title>
</head>
<body>

<input id="btn1" value="upload" type="button">
</body>
</html>
