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

		<title>朗文图书 人才招聘</title>

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
					人才招聘
				</h3>
				<div class="aleft_box">
					<ul>
						<br>
						<s:iterator value="#session.rczpList" id="ll" status="st">
							<s:if test="#ll.id == #session.lid">
								<li class="readNow">
							</s:if>
							<s:else>
								<li>
							</s:else>
							<a href="frczp?id=<s:property value='%{#ll.id}'/>"> <s:property
									value="#ll.biaoti" /> </a>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<div class="central_right">
				<div id="right_messageBox">

					<div class="news_title">
						<p>
							<s:property value="#session.rczp.biaoti" />
						</p>
					</div>
					<div class="Article_area">
						<p>
							<s:property value="#session.rczp.xiangxi" escape="false" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<s:include value="/pages/footer.jsp"></s:include>
	</body>
</html>
