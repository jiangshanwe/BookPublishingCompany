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

		<title>My JSP 'hyyqYulan.jsp' starting page</title>

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
		<div style="line-height: 1.5">
			<h3 align="center">
				<s:property value="#session.hyyqYulan.biaoti" />
			</h3>
			<p>
				<s:property value="#session.hyyqYulan.neirong" escape="false" />
			</p>
			<p>
				附件:&nbsp;&nbsp;
				<a
					href='fujian/hyyq/<s:property value="%{#session.hyyqYulan.fujian}"/>'><s:property
						value="#session.hyyqYulan.fujian" /> </a>
			</p>
			<p>
				添加时间:
				<s:property value="#session.hyyqYulan.tianjiashijian" />
				<br>
				添加人:
				<s:property value="#session.hyyqYulan.tianjiaren" />
			</p>
		</div>
	</body>
</html>
