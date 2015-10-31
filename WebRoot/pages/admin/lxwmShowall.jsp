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

		<title>My JSP 'lxwmShowall.jsp' starting page</title>

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
		<form action="lxwmTianjia">
			<s:submit value="添加新的联系我们项目" align="left"></s:submit>
		</form>

		<div>
			<table border="1" align="center">
				<tr align="center">
					<td colspan="7" style="font-size: 22px; font-weight: bold">
						联系我们列表
					</td>
				</tr>
				<tr align="center" height="52">
					<td>
						标题
					</td>
					<td>
						预览
					</td>
					<td>
						发布时间
					</td>
					<td>
						最后更新人
					</td>
					<td>
						置顶
					</td>
					<td>
						编辑
					</td>
					<td>
						删除
					</td>
				</tr>
				<s:iterator value="#session.allLxwms" id="als">
					<tr align="center" height="49.6">
						<td width="165">
							<s:property value="#als.biaoti" />
						</td>
						<td width="150" align="center">
							<s:form action="lxwmYulan">
								<s:hidden name="id" value="%{#als.id}"></s:hidden>
								<s:submit value="预览"></s:submit>
							</s:form>
						</td>
						<td width="150">
							<s:property value="#als.fabushijian" />
						</td>
						<td width="120">
							<s:if test="#als.gengxinren ==null">
								<s:property value="#als.faburen" />
							</s:if>
							<s:else>
								<s:property value="#als.gengxinren" />
							</s:else>
						</td>
						<td width="80">
							<s:if test="#als.zhiding==0">
								<s:form action="lxwmZhiding">
									<s:hidden value="%{#als.id}" name="id"></s:hidden>
									<s:submit value="置顶"></s:submit>
								</s:form>
							</s:if>
							<s:else>
								<s:form action="lxwmQxZhiding">
									<s:hidden value="%{#als.id}" name="id"></s:hidden>
									<s:submit value="取消置顶"></s:submit>
								</s:form>
							</s:else>
						</td>
						<td width="80">
							<s:form action="lxwmBianji">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="编辑"></s:submit>
							</s:form>
						</td>
						<td width="80">
							<s:form action="lxwmShanchu">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="删除" onclick="return confirm('确认删除此条简介？')"></s:submit>
							</s:form>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</body>
</html>
