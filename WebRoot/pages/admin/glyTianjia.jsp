<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'glyTianjia.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<form action="glyAdd" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑管理员信息
				</legend>
				<p>
					<s:textfield name="gonghao" label="工号"></s:textfield>
				</p>
				<p>
					<s:textfield name="xingming" label="姓名"></s:textfield>
				</p>
				<p>
					<s:password name="tmima" label="密码"></s:password>
				</p>
				<p>
					<s:password name="tcmima" label="确认密码"></s:password>
				</p>
				<p>
					<s:radio list="#{'0':'禁用','1':'启用'}" label="是否启用" value="1"
						name="status"></s:radio>
				</p>
				<p>
					<input value="确认添加" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
