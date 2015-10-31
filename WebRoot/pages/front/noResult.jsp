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

		<title>朗文图书 产品中心</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="<%=basePath%>/css/aiyike.css" rel="stylesheet">

	</head>

	<body>
		<s:include value="/pages/top.jsp"></s:include>
		<div id="central">
			<div class="central_left">
				<h3 class="ah3">
					搜索结果
				</h3>

				<div class="aleft_box">
					<ul>
					</ul>
				</div>
			</div>

			<div class="central_right">
				<div class="right_title">
					<p>
						当前位置：
					</p>

					<p>
						搜索结果
					</p>

					<p>

					</p>
					<form method="post" action="asearchBook">
							<input type="text" value="<s:property value='%{#session.key}'/>"
								name="key"
								style="border: 2px solid #CDB38B; height: 34px; width: 234px; font-size: 19px; padding-left: 12px; vertical-align: middle; margin-left: 206px;">
							&nbsp;&nbsp;
							<input type="submit" value="搜  索" class="searchLabel">
						</form>
				</div>
				<div id="right_messageBook">
				<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未找到符合条件的资料！
				</div>
			</div>
		</div>
		<s:include value="/pages/footer.jsp"></s:include>
	</body>
</html>
