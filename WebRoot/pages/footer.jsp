<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div id="footer0">
			<div>
				<p>
					<br />
					地址：湖北省武汉市洪山区珞狮南路519号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 鄂ICP备 15003549号-3
					<br />
					版权所有&nbsp;&copy;&nbsp;&nbsp;湖北朗文书业有限公司 all rights reserved
					<br/><br/>
				</p>
			</div>
		</div>
	</body>
</html>