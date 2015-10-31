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

		<title>My JSP 'gxxwYulan.jsp' starting page</title>

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
		<form action="gxxwUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑高校新闻
				</legend>
				<s:hidden name="id" value="%{#session.gxxwBianji.id}"></s:hidden>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					<s:textfield name="biaoti" label="标题"
						value="%{#session.gxxwBianji.biaoti}"></s:textfield>
				</p>
				<p>
					<s:textfield name="ywlj" value="%{#session.gxxwBianji.ywlj}"
						label="原文链接(选填)"></s:textfield>
				</p>
				<div id="sample">
					<script type="text/javascript"
						src="http://js.nicedit.com/nicEdit-latest.js"></script>
					<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>
					<h4>
						新闻正文
					</h4>
					<textarea name="zhengwen" style="width: 100%; height: 380px;">
							<s:property value="#session.gxxwBianji.zhengwen" />
</textarea>
				</div>
				<p>
					<input value="确认修改" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
