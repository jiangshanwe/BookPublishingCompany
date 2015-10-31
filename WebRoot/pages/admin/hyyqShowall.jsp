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

		<title>My JSP 'hyyqShowall.jsp' starting page</title>

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
		<form action="hyyqTianjia">
			<s:submit value="添加新的会议邀请" align="left"></s:submit>
		</form>
		<form action="hyyqRePaixu">
			<s:submit value="全部重新排序(按照添加时间的先后)" align="left"></s:submit>
		</form>

		<div>
			<table border="1" align="center">
				<tr align="center">
					<td colspan="8" style="font-size: 22px; font-weight: bold">
						教学服务-会议邀请列表
					</td>
				</tr>
				<tr align="center" height="52">
					<td>
						序号
					</td>
					<td>
						标题
					</td>
					<td>
						会议回执
					</td>
					<td>
						最后更新人
					</td>
					<td>
						更新时间
					</td>
					<td>
						排序
					</td>
					<td>
						编辑
					</td>
					<td>
						删除
					</td>
				</tr>
				<s:iterator value="#session.allHyyqs" id="als" status="L">
					<tr align="center">
						<td>
							<s:property value="#als.paixu" />
						</td>
						<td>
							<s:url id="url" action="hyyqYulan">
								<s:param name="id" value="%{#als.id}"></s:param>
							</s:url>
							<s:a href="%{url}">
								<s:property value="#als.biaoti" />
							</s:a>
						</td>
						<td width="120" height="90">
							<a href='fujian/hyyq/<s:property value="%{#als.fujian}"/>'><s:property
									value="#als.fujian" /> </a>
						</td>
						<td width="120">
							<s:if test="#als.gengxinren==null">
								<s:property value="#als.tianjiaren" />
							</s:if>
							<s:else>
								<s:property value="#als.gengxinren" />
							</s:else>
						</td>
						<td width="120">
							<s:if test="#als.gengxinshijian==null">
								<s:property value="#als.tianjiashijian" />
							</s:if>
							<s:else>
								<s:property value="#als.gengxinshijian" />
							</s:else>
						</td>
						<td width="150">
							<s:form action="hyyqMoveUp">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="↑"></s:submit>
							</s:form>
							<s:form action="hyyqMoveDown">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="↓"></s:submit>
							</s:form>
						</td>
						<td width="120">
							<s:form action="hyyqBianji">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="编辑"></s:submit>
							</s:form>
						</td>
						<td width="60">
							<s:form action="hyyqShanchu">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="删除" onclick="return confirm('确认删除此条会议邀请？')"></s:submit>
							</s:form>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="12" height="26" align="center">
						<s:if test="page !=1">
							<a href="hyyqSyy">上一页</a>&nbsp;
						</s:if>
						<s:bean name="org.apache.struts2.util.Counter" var="i">
							<s:param name="first" value="1" />
							<s:param name="last" value="#session.pageCount" />
							<s:iterator>
								<s:if test="#i.current - 1 == page">
									<s:property value='#i.current - 1' />&nbsp;
								</s:if>
								<s:else>
									<a
										href="hyyqShowall?page=<s:property value='#i.current - 1' />">
										<s:property value="#i.current - 1" /> </a>&nbsp;
								</s:else>
							</s:iterator>
						</s:bean>
						<s:if test="#session.pageCount != page">
							<a href="hyyqXyy">下一页</a>
						</s:if>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
