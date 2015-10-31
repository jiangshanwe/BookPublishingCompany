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

		<title>My JSP 'zsgzjyTianjia.jsp' starting page</title>

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
		<form action="zsgzjyAdd" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑展示信息
				</legend>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					上传展示图片:
					<input type="file" name="upload">
				</p>
				<p>
					<s:textfield name="shuming" label="图书名称"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshe" label="出版社"></s:textfield>
				</p>
				<p>
					<s:textfield name="lianjie" label="对应图书链接"></s:textfield>
				</p>
				<p>
					<input value="确认添加" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
