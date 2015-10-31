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

		<title>My JSP 'zszsgdwkTianjia.jsp' starting page</title>

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
		<form action="zsgdwkUpdate" method="post"
			enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑展示信息
				</legend>
				<s:hidden name="id" value="%{#session.zsgdwkBianji.id}"></s:hidden>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					图片:
					<img width="90" height="120" alt="暂无图书照片"
						src='fujian/zsgdwk/<s:property value="%{#session.zsgdwkBianji.tupian}"/>'>
					<br>
					重新上传图片:
					<input type="file" name="upload">
				</p>
				<p>
					<s:textfield name="shuming" label="图书名称"
						value="%{#session.zsgdwkBianji.shuming}"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshe"
						value="%{#session.zsgdwkBianji.chubanshe}" label="出版社"></s:textfield>
				</p>
				<p>
					<s:textfield name="lianjie"
						value="%{#session.zsgdwkBianji.lianjie}" label="图书对应链接"></s:textfield>
				</p>
				<p>
					<input value="确认修改" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
