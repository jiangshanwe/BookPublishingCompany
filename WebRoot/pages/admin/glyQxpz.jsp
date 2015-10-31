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

		<title>My JSP 'glyQxpz.jsp' starting page</title>

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
		<form action="glyUpdate" method="post">
			<fieldset>
				<legend>
					编辑管理员权限
				</legend>
				<s:hidden name="id" value="%{#session.glyQxpz.id}"></s:hidden>
				<p>
					工号:
					<s:property value="#session.glyQxpz.gonghao" />
				</p>
				<p>
					姓名:
					<s:property value="#session.glyQxpz.xingming" />
				</p>
				<s:checkboxlist list="#session.qxAll" listKey="id" listValue="qxm"
					name="qxList" label="选择授予的权限" value="#session.ql"></s:checkboxlist>
				<p>
					<input value="确认修改" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
