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

		<title>My JSP 'yssqShow.jsp' starting page</title>

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
				<s:property value="#session.yssqShow.biaoti" />
			</h3>
			<p>
				<s:property value="#session.yssqShow.neirong" escape="false" />
			</p>
			<p>
				附件:&nbsp;&nbsp;
				<a
					href='fujian/yssq/<s:property value="%{#session.yssqShow.fujian}"/>'><s:property
						value="#session.yssqShow.fujian" /> </a>
			</p>
			<p>
				最后更新时间:
				<s:property value="#session.yssqShow.xiugaishijian" />
				<br>
				更新人:
				<s:property value="#session.yssqShow.xiugairen" />
			</p>
		</div>
		<p>
			<s:form action="yssqBianji">
				<s:submit value="重新编辑"></s:submit>
			</s:form>
		</p>
	</body>
</html>
