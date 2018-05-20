<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}

	function doView(){
		alert("查看...");
	}

	function doDelete(){
		//进行批量删除的操作
        var selected = $("#grid").datagrid("getSelections");
        // alert(selected);
        if (selected.length == 0) {
            //如果没有被选中的数据的话
            $.messager.alert("提示信息", "请选择想要删除的信息", "info");
        }else{
            //弹出一个确认框
            $.messager.confirm("确认信息", "您是否要删除这些信息", function (confirmOrNot) {
                if (confirmOrNot) {
                    //如果点击确认删除的话
                    //获取用户选择的信息的id
                    var ids = "";
                    var array = new Array();
                    for (var i = 0;  i < selected.length;i++){
                        array.push(selected[i].id);
					}
                    ids = array.join(",");
                    //发送请求
                    location.href = "${pageContext.request.contextPath}/staffAction_deleteStaff.action?ids=" + ids;
                }
            });
		}

	}

	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];

	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});

		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [10,30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staffAction_pageQuery.action",//异步请求获取数据
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow,
				field:'deltag',
				title:"是否作废",
				width:120,
				align:'center',
				formatter:function (data,row,index) {
					if (data == "0") {
						return "正常使用";
					} else {
						return "已经作废";
					}
				}
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
        // 修改取派员窗口
        $('#editStaffWindow').window({
            title: '修改取派员',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
        });


	});


	function doDblClickRow(rowIndex, rowData){
	    //打开数据窗口
        $("#editStaffWindow").window("open");
        //回显数据
        $("#editStaffForm").form("load", rowData);
	}
</script>
	<script type="text/javascript" >
		$(function () {
			var reguler = /^1[3|5|6|7|8][0-9]{9}$/;
			//扩展校验规则 对手机号进行校验
			$.extend($.fn.validatebox.defaults.rules,{
			    telephone:{
			        validator:function (value,param) {
                        return reguler.test(value);
                    },
                    message: '信息输入有误'
				}
			});
        });
	</script>
    <script type="text/javascript" >
        $(function () {
            //为保存按钮绑定保存事件 返回值:jQueryclick([[data],fn])
            $("#save").click(function () {
				//首先进行表单的校验 如果检验出现问题就不进行下面的操作
               var e = $("#newStaffCreateForm").form("validate");
                //alert(e);
                if (e) {
                    $("#newStaffCreateForm").submit();
                }else{
                    $.messager.alert("提示信息","您输入的信息不符合规则 请重新进行输入","error")
				}
            });

        });
	</script>
	<script type="text/javascript" >
        $(function () {
            //为保存按钮绑定保存事件 返回值:jQueryclick([[data],fn])
            $("#edit").click(function () {
                //首先进行表单的校验 如果检验出现问题就不进行下面的操作
                var e = $("#editStaffForm").form("validate");
                //alert(e);
                if (e) {
                    $("#editStaffForm").submit();
                }else{
                    $.messager.alert("提示信息","您输入的信息不符合规则 请重新进行输入","error")
                }
            });

        });
	</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a  id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="newStaffCreateForm" action="${pageContext.request.contextPath}/staffAction_addStaff.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!--<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>-->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" data-options="validType:'telephone'"  name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>

	<div class="easyui-window" title="对收派员进行修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a  id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="editStaffForm" action="${pageContext.request.contextPath}/staffAction_editStaff.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!--<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>-->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" data-options="validType:'telephone'"  name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="haspda" value="1" />
							是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<input type="hidden" name="id" >
				</table>
			</form>
		</div>
	</div>
</body>
</html>