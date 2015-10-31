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

		<title>My JSP 'jxfwjsShow.jsp' starting page</title>

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
				<s:property value="#session.jxfwjsShow.biaoti" />
			</h3>
			<p>
				<s:property value="#session.jxfwjsShow.neirong" escape="false" />
			</p>
			<p>
				最后更新时间:
				<s:property value="#session.jxfwjsShow.xiugaishijian" />
				<br>
				更新人:
				<s:property value="#session.jxfwjsShow.xiugairen" />
			</p>
		</div>
		<p>
			<s:form action="jxfwjsBianji">
				<s:submit value="重新编辑"></s:submit>
			</s:form>
		</p>
	</body>
</html>
