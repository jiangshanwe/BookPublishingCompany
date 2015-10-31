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

		<title>My JSP 'zsgdlgShowall.jsp' starting page</title>

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
		<form action="zsgdlgTianjia">
			<s:submit value="添加新的产品展示-高等理工" align="left"></s:submit>
		</form>
		<form action="zsgdlgRePaixu">
			<s:submit value="全部重新排序(按照添加时间的先后)" align="left"></s:submit>
		</form>

		<div>
			<table border="1" align="center">
				<tr align="center">
					<td colspan="10" style="font-size: 22px; font-weight: bold">
						首页-产品展示-高等理工 列表(只展示前8条)
					</td>
				</tr>
				<tr align="center" height="52">
					<td>
						序号
					</td>
					<td>
						图片
					</td>
					<td>
						图书名称
					</td>
					<td>
						出版社
					</td>
					<td>
						添加人
					</td>
					<td>
						添加时间
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
				<s:iterator value="#session.allZsgdlgs" id="als" status="L">
					<tr align="center">
						<td>
							<s:property value="#als.paixu" />
						</td>
						<td>
							<a href='<s:property value="%{#als.lianjie}"/>'
								target="_blank"><img width="90" height="120" alt="暂无图书照片"
									src='fujian/zsgdlg/<s:property value="%{#als.tupian}"/>'>
							</a>
						</td>
						<td>
							<a href="<s:property value='%{#als.lianjie}'/>" target="_blank">
								<s:property value="#als.shuming" /> </a>
						</td>
						<td>
							<s:property value="#als.chubanshe" />
						</td>
						<td width="120">
							<s:property value="#als.tianjiaren" />
						</td>
						<td width="120">
							<s:property value="#als.tianjiashijian" />
						</td>
						<td width="150">
							<s:form action="zsgdlgMoveUp">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="↑"></s:submit>
							</s:form>
							<s:form action="zsgdlgMoveDown">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="↓"></s:submit>
							</s:form>
						</td>
						<td width="120">
							<s:form action="zsgdlgBianji">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="编辑"></s:submit>
							</s:form>
						</td>
						<td width="60">
							<s:form action="zsgdlgShanchu">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="删除" onclick="return confirm('确认删除此条展示图书？')"></s:submit>
							</s:form>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="9" height="26" align="center">
						<s:if test="page !=1">
							<a href="zsgdlgSyy">上一页</a>&nbsp;
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
										href="zsgdlgShowall?page=<s:property value='#i.current - 1' />">
										<s:property value="#i.current - 1" /> </a>&nbsp;
								</s:else>
							</s:iterator>
						</s:bean>
						<s:if test="#session.pageCount != page">
							<a href="zsgdlgXyy">下一页</a>
						</s:if>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
