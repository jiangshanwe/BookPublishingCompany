<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<META content=图书,教育,朗文图书,高等教育，高职高专 name=keywords>
		<META content=湖北朗文图书发行有限公司 name=description>
		<title>湖北朗文图书发行有限公司</title>
		<link href="<%=basePath%>/css/newcss.css" rel="stylesheet">
		<script src="<%=basePath%>/js/jquery.min.js"></script>
	</head>
	<body>
		<div class="header" id="top0">
			<div class="nav">
				<a href="index">
				<div class="header_logo"></div>
				</a>
				<ul>
					<li>
						<a href="index" class="top_bar">首页</a>
					</li>
					<li>
						<a href="fgdlgList" class="top_bar">产品中心</a>
					</li>
					<li>
						<a href="fjxfwList" class="top_bar">教学服务</a>
					</li>
					<li>
						<a href="fcbtd" class="top_bar">出版天地</a>
					</li>
					<li>
						<a href="fqyzxList" class="top_bar">企业资讯</a>
					</li>
					<li>
						<a href="flwjj" class="top_bar">公司简介</a>
					</li>
					<li>
						<a href="frczp" class="top_bar">人才招聘</a>
					</li>
					<li>
						<a href="flxwm" class="top_bar">联系我们</a>
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>