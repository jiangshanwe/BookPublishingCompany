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

		<title>My JSP 'descGly.jsp' starting page</title>
		<link href="<%=basePath%>/css/astyle.css" rel="stylesheet">

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<form action="glyXgmm">
			<fieldset>
				<legend>
					修改登录密码
				</legend>
				工号（登录账号）：
				<s:property value="#session.gly.gonghao" />
				<br><br>
				姓名：
				<s:property value="#session.gly.xingming" />
				<br><br>
				当前密码：
				<s:password name="mima" onblur=""></s:password>
				<br><br>
				修改密码：
				<s:password name="nmima"></s:password>
				<br><br>
				确认密码：
				<s:password name="cmima"></s:password>
				<br><br>
				<s:submit value="确认修改" align="left"></s:submit>
			</fieldset>
		</form>
	</body>
</html>
