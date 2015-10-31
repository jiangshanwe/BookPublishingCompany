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

		<title>My JSP 'kjxzYulan.jsp' starting page</title>

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
		<form action="kjxzUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					课件下载--预览
				</legend>
				<p>
					标题:
					<s:property value="#session.kjxzYulan.biaoti" />
				</p>
				<p>
					图片:
					<img width="165.6" height="69.60" alt="暂无图书照片"
						src='fujian/kjxz/<s:property value="%{#session.kjxzYulan.tupian}"/>'>
				</p>
				<p>
					分享链接:
					<a href="<s:property value='%{#session.kjxzYulan.bdfx}'/>"
						target="_blank"> <s:property value="#session.kjxzYulan.bdfx" />
					</a>
				</p>
				<p>
					分享密码:
					<s:if test="#session.kjxzYulan.fxmm == ''">
						无密码
					</s:if>
					<s:else>
						<s:property value="#session.kjxzYulan.fxmm" />
					</s:else>
				</p>
				<s:if test="#session.kjxzYulan.dyts != ''">
					对应的图书链接:
					<a href="<s:property value='%{#session.kjxzYulan.dyts}'/>"
						target="_blank"> <s:property value="#session.kjxzYulan.dyts" />
					</a>
				</s:if>
				<p>
					添加人:
					<s:property value="#session.kjxzYulan.tianjiaren" />
				</p>
				<p>
					添加时间:
					<s:property value="#session.kjxzYulan.tianjiashijian" />
				</p>
				<div style="line-height: 1.5">
					<br />
					课件介绍：
					<br />
					<s:property value="#session.kjxzYulan.jieshao" escape="false" />
				</div>
			</fieldset>
		</form>
	</body>
</html>
