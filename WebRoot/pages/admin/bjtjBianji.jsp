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

		<title>My JSP 'bjtjYulan.jsp' starting page</title>

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
		<form action="bjtjUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑推荐信息
				</legend>
				<s:hidden value="%{#session.bjtjBianji.id}" name="id"></s:hidden>
				<p>
					<s:textfield name="shuming" label="标题"
						value="%{#session.bjtjBianji.shuming}"></s:textfield>
				</p>
				<p>
					<s:textfield name="lianjie" label="链接"
						value="%{#session.bjtjBianji.lianjie}"></s:textfield>
				</p>
				<p>
					<input value="确认修改" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
