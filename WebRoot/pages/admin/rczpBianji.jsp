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

		<title>My JSP 'rczpBianji.jsp' starting page</title>

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
		<form action="rczpUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				&nbsp;
				<legend>
					更新人才招聘信息
				</legend>
				<br>
				<s:hidden value="%{#session.rczpBianji.id}" name="id"></s:hidden>
				<s:textfield name="biaoti" label="标题"
					value="%{#session.rczpBianji.biaoti}"></s:textfield>
				<br>
				<div id="sample">
					<script type="text/javascript"
						src="http://js.nicedit.com/nicEdit-latest.js"></script>
					<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>
					<h4>
						详细内容：
					</h4>
					<textarea name="xiangxi" style="width: 100%; height: 380px;">
					<s:property value="#session.rczpBianji.xiangxi" />
</textarea>
				</div>
				<br>
				置顶：
				<s:radio name="zhiding" list="%{#{'1':'是','0':'否'}}"></s:radio>
				<br>
				<input value="确认修改" type="submit">
			</fieldset>
		</form>
	</body>
</html>
