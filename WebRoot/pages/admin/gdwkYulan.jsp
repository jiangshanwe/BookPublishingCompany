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

		<title>My JSP 'gdwkYulan.jsp' starting page</title>

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
		<form action="gdwkUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					图书信息--预览
				</legend>
				<p>
					分类:
					<s:property value="#session.gdwkYulan.fenlei" />
					<s:if test="#session.gdwkYulan.fenlei != null">
					第二分类:<s:property value="#session.gdwkYulan.fenlei2" />
					</s:if>
				</p>
				<p>
					图片:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkYulan.tupian1}"/>'>
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkYulan.tupian2}"/>'>
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkYulan.tupian3}"/>'>
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkYulan.tupian4}"/>'>
				</p>
				<p>
					图书名称:
					<s:property value="#session.gdwkYulan.shuming" />
				</p>
				<p>
					作者:
					<s:property value="#session.gdwkYulan.zuozhe" />
				</p>
				<p>
					出版社:
					<s:property value="#session.gdwkYulan.chubanshe" />
				</p>
				<p>
					出版时间:
					<s:property value="#session.gdwkYulan.chubanshijian" />
				</p>
				<p>
					ISBN:
					<s:property value="#session.gdwkYulan.isbn" />
				</p>
				<p>
					版次:
					<s:property value="#session.gdwkYulan.banci" />
				</p>
				<p>
					包装:
					<s:property value="#session.gdwkYulan.baozhuang" />
				</p>
				<p>
					开本:
					<s:property value="#session.gdwkYulan.kaiben" />开
				</p>
				<p>
					页数:
					<s:property value="#session.gdwkYulan.yeshu" />
				</p>
				<p>
					定价:
					<s:property value="#session.gdwkYulan.dingjia" />元
				</p>
				<div style="line-height: 1.5">
					<br />
					内容简介：
					<br />
					<s:property value="#session.gdwkYulan.neirongjianjie"
						escape="false" />
				</div>
				<div style="line-height: 1.5">
					<br />
					作者简介：
					<br />
					<s:property value="#session.gdwkYulan.zuozhejianjie" escape="false" />
				</div>
				<div style="line-height: 1.5">
					<br />
					目录：
					<br />
					<s:property value="#session.gdwkYulan.mulu" escape="false" />
				</div>
				<div style="line-height: 1.5">
					<br />
					精彩书摘：
					<br />
					<s:property value="#session.gdwkYulan.jingcaishuzhai"
						escape="false" />
				</div>
				<div style="line-height: 1.5">
					<br />
					前言/序言：
					<br />
					<s:property value="#session.gdwkYulan.qianyan" escape="false" />
				</div>
			</fieldset>
		</form>
	</body>
</html>
