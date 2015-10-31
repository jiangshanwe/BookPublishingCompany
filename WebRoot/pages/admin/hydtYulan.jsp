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

		<title>My JSP 'hydtYulan.jsp' starting page</title>

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
		<form action="hydtUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					行业动态--预览
				</legend>
				<p>
					标题:
					<s:property value="#session.hydtYulan.biaoti" />
				</p>
				<p>
					原文链接：&nbsp;&nbsp;
					<s:if test="#session.hydtYulan.ywlj == ''">
					 无
					</s:if>
					<s:else>
						<a href='<s:property value="%{#session.hydtYulan.ywlj}"/>' target="_blank"><s:property
								value="#session.hydtYulan.ywlj" /> </a>
					</s:else>
				</p>
				<p>
					添加人:
					<s:property value="#session.hydtYulan.tianjiaren" />
				</p>
				<p>
					添加时间:
					<s:property value="#session.hydtYulan.tianjiashijian" />
				</p>
				<div style="line-height: 1.5">
					<br />
					新闻正文：
					<br />
					<s:property value="#session.hydtYulan.zhengwen" escape="false" />
				</div>
			</fieldset>
		</form>
	</body>
</html>
