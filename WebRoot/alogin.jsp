<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

		<title>朗文-后台管理登录</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/loginCss.css" />

	</head>

	<body>
		<div class="container">
			<section id="content">
			<form action="glyLogin" method="post">
				<h1>
					管理员登录
				</h1>
				<div>
					<input type="text" placeholder="Username" required="" id="gonghao" name="gonghao" />
				</div>
				<div>
					<input type="password" placeholder="Password" required=""
						id="mima" name="mima" />
				</div>
				<div>
					<input type="submit" value="Log in" />

				</div>
			</form>
			<div class="button">

			</div>
			</section>
		</div>
	</body>
	</body>
</html>
