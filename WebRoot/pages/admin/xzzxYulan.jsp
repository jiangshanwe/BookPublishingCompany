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

		<title>My JSP 'xzzxYulan.jsp' starting page</title>

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
		<form action="xzzxUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					文件下载--预览
				</legend>
				<p>
					标题:
					<s:property value="#session.xzzxYulan.biaoti" />
				</p>
				<p>
					附件：&nbsp;&nbsp;
					<a href='fujian/xzzx/<s:property value="%{#session.xzzxYulan.fujian}"/>'><s:property
							value="#session.xzzxYulan.fujian" /> </a>
				</p>
				<p>
					添加人:
					<s:property value="#session.xzzxYulan.tianjiaren" />
				</p>
				<p>
					添加时间:
					<s:property value="#session.xzzxYulan.tianjiashijian" />
				</p>
				<div style="line-height: 1.5">
					<br />
					课件介绍：
					<br />
					<s:property value="#session.xzzxYulan.jieshao" escape="false" />
				</div>
			</fieldset>
		</form>
	</body>
</html>
