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

		<title>My JSP 'glyShowall.jsp' starting page</title>

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
		<form action="glyTianjia">
			<s:submit value="添加新的管理员" align="left"></s:submit>
		</form>

		<table border="1" align="center">
			<tr align="center">
				<td colspan="6" style="font-size: 22px; font-weight: bold">
					全部管理员列表
				</td>
			</tr>
			<tr align="center" style="font-size: 16px; font-weight: bold;">
				<td>
					登录工号
				</td>
				<td>
					姓名
				</td>
				<td>
					全部权限
				</td>
				<td>
					禁用
				</td>
				<td width="72">
					配置权限
				</td>
				<td>
					删除
				</td>
			</tr>
			<tr align="center">
				<td>
					<s:property value="#session.gly.gonghao" />
				</td>
				<td>
					<s:property value="#session.gly.xingming" />
				</td>
				<td>
					<s:iterator value="#session.gly.qxs" id="qx" status="u">
						<s:property value="#qx.qxm" />
						<s:if test="!#u.last">,</s:if>
					</s:iterator>
				</td>
				<td>
					******
				</td>
				<td>
					******
				</td>
				<td>
					******
				</td>
			</tr>
			<s:iterator value="#session.allGlys" id="gly">
				<tr align="center">
					<s:if test="#gly.id!=#session.gly.id && #gly.id!=1">
						<td>
							<s:property value="#gly.gonghao" />
						</td>
					</s:if>
					<s:if test="#gly.id!=#session.gly.id&& #gly.id!=1">
						<td>
							<s:property value="#gly.xingming" />
						</td>
					</s:if>
					<s:if test="#gly.id!=#session.gly.id&& #gly.id!=1">
						<td>
							<s:iterator value="#gly.qxs" id="qx" status="u">
								<s:property value="#qx.qxm" />
								<s:if test="!#u.last">,</s:if>
							</s:iterator>
						</td>
					</s:if>
					<s:if test="#gly.id!=#session.gly.id&& #gly.id!=1">
						<td>
							<form action="glyJinyong" method="post">
								<s:hidden name="id" value="%{#gly.id}"></s:hidden>
								<s:if test="#gly.status==1">
									<s:hidden name="status" value="0"></s:hidden>
									<input type="submit" value="点击禁用">
								</s:if>
								<s:elseif test="#gly.status==0">
									<s:hidden name="status" value="1"></s:hidden>
									<input type="submit" value="点击开启">
								</s:elseif>
							</form>
						</td>
					</s:if>
					<s:if test="#gly.id!=#session.gly.id&& #gly.id!=1">
						<td>
							<form action="glyQxpz" method="post">
								<s:hidden name="id" value="%{#gly.id}"></s:hidden>
								<input type="submit" value="配置">
							</form>
						</td>
					</s:if>
					<s:if test="#gly.id!=#session.gly.id&& #gly.id!=1">
						<td width="60">
							<s:form action="glyShanchu">
								<s:hidden value="%{#gly.id}" name="id"></s:hidden>
								<s:submit value="删除" onclick="return confirm('确认删除此管理员？')"></s:submit>
							</s:form>
						</td>
					</s:if>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
