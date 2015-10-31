<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'yqljTianjia.jsp' starting page</title>
    
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
    <form action="yqljAdd" method="post">
			<fieldset>
				<legend>
					编辑友情链接信息
				</legend>
				<p>
					<s:textfield name="shuming" label="标题"></s:textfield>
				</p>
				<p>
					<s:textfield name="lianjie" label="链接"></s:textfield>
				</p>
				<p>
					<input value="确认添加" type="submit">
				</p>
			</fieldset>
		</form>
  </body>
</html>
