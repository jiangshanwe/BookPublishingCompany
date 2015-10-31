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

		<title>My JSP 'bannerShowall.jsp' starting page</title>

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
		<form action="bannerTianjia">
			<s:submit value="添加新的Banner" align="left"></s:submit>
		</form>

		<div>
			<table border="1" align="center">
				<tr align="center">
					<td colspan="6" style="font-size: 22px; font-weight: bold">
						Banner图片
					</td>
				</tr>
				<tr align="center">
					<td>
						Banner预览
					</td>
					<td>
						发布时间
					</td>
					<td>
						发布人
					</td>
					<td>
						描述
					</td>
					<td>
						置顶
					</td>
					<td>
						删除
					</td>
				</tr>
				<s:iterator value="#session.allBanners" id="abs">
					<tr align="center">
						<td width="165.6" height="69.60">
							<a href='<s:property value="%{#abs.lianjie}"/>' target="_blank"><img
									width="165.6" height="69.60" alt="banner图片预览"
									src='fujian/banner/<s:property value="%{#abs.tupian}"/>'> </a>
						</td>
						<td width="160" height="69.60">
							<s:property value="#abs.fabushijian" />
						</td>
						<td width="100">
							<s:property value="#abs.faburen" />
						</td>
						<td style="WORD-WRAP: break-word" width="250" align="left"
							height="69.6">
							<s:property value="#abs.miaoshu" />
						</td>
						<td width="80">
							<s:if test="#abs.zhiding==0">
								<s:form action="bannerZhiding">
									<s:hidden value="%{#abs.id}" name="id"></s:hidden>
									<s:submit value="置顶"></s:submit>
								</s:form>
							</s:if>
							<s:else>
								<s:form action="bannerQxZhiding">
									<s:hidden value="%{#abs.id}" name="id"></s:hidden>
									<s:submit value="取消置顶"></s:submit>
								</s:form>
							</s:else>
						</td>
						<td width="80">
							<s:form action="bannerShanchu">
								<s:hidden value="%{#abs.id}" name="id"></s:hidden>
								<s:submit value="删除" onclick="return confirm('确认删除这张banner图片？')"></s:submit>
							</s:form>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</body>
</html>
