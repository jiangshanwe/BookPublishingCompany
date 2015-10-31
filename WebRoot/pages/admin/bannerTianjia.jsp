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

		<title>My JSP 'bannerAdd.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">




	</head>

	<body>
		<form action="bannerAdd" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					设置Banner信息
				</legend>
				<s:hidden name="typelimit" value="1"></s:hidden>
				选择文件：
				<input type="file" name="upload">
				<br>
				<br>
				<s:textfield name="lianjie" label="链接地址"></s:textfield>
				<br>
				<br>
				置顶：<s:radio name="zhiding" list="%{#{'1':'是','0':'否'}}"></s:radio>
				<br>
				<br>
				<s:textarea name="miaoshu" label="Banner描述"></s:textarea>
				<br>
				<br>
				<input value="确认添加" type="submit">
			</fieldset>
		</form>
	</body>
</html>
