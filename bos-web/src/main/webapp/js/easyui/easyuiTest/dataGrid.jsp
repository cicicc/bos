<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/19
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试easyUi datagrid代码的书写</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <!--方式一:=========将静态页面渲染成为表单形式的数据-->
    <table class="easyui-datagrid" >
        <thead >
            <tr>
                <th data-options="field:'id'">编号</th>
                <th data-options="field:'name'">姓名</th>
                <th data-options="field:'age'">年龄</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>001</td>
                <td>老陈</td>
                <td>20</td>
            </tr>
            <tr>
                <td>002</td>
                <td>小胖汪</td>
                <td>19</td>
            </tr>
        </tbody>
    </table>

    <!--方式二 发送ajax请求获取动态的json数据-->
    <table class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath}/json/dataGridJson.json'">
        <thead >
        <tr>
            <th data-options="field:'id'">编号</th>
            <th data-options="field:'name'">姓名</th>
            <th data-options="field:'age'">年龄</th>
        </tr>
        </thead>
    </table>
    <!--使用api动态创建datagrid-->
    <table id="useApiForDatagrid"></table>
    <script type="text/javascript">
        $("#useApiForDatagrid").datagrid(
            {
                //设置参数
                "columns":[
                    [
                        {"title":"id", "field":"id",checkbox:true},
                        {"title":"姓名", "field":"name"},
                        {"title":"年龄", "field":"age"}
                    ]
                ],
                url:"${pageContext.request.contextPath}/json/dataGridJson.json",
                singleSelect:true,
                //定义工具栏
                "toolbar":[
                    {"text":"增加","iconCls":"icon-add","handler":function () {
                            alert("add");
                        }}
                ],
                //显示分页条
                pagination:true,
                pageList:[1,10,20]
            }
        )
    </script>
</body>
</html>
