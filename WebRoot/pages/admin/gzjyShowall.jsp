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

		<title>My JSP 'gzjyShowall.jsp' starting page</title>

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
		<form action="gzjyTianjia">
			<s:submit value="添加新的高职教育图书" align="left"></s:submit>
		</form>
		<form action="gzjyRePaixu">
			<s:submit value="全部重新排序(按照添加时间的先后)" align="left"></s:submit>
		</form>

		<div>
			<table border="1" align="center">
				<tr align="center">
					<td colspan="12" style="font-size: 22px; font-weight: bold">
						图书-高职教育列表
					</td>
				</tr>
				<tr align="center" height="52">
					<td>
						序号
					</td>
					<td>
						图书名称
					</td>
					<td>
						图片
					</td>
					<td>
						分类
					</td>
					<td>
						作者
					</td>
					<td>
						出版社
					</td>
					<td>
						添加时间
					</td>
					<td>
						添加人
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
				<s:iterator value="#session.allGzjys" id="als" status="L">
					<tr align="center" height="49.6">
						<td>
							<s:property value="#als.paixu" />
						</td>
						<td width="165">
							<s:url id="url" action="gzjyYulan">
								<s:param name="id" value="%{#als.id}"></s:param>
							</s:url>
							<s:a href="%{url}">
								<s:property value="#als.shuming" />
							</s:a>
						</td>
						<td width="120" height="90">
							<a href='bookPic/gzjy/<s:property value="%{#als.tupian1}"/>'
								target="_blank"><img width="90" height="120" alt="暂无图书照片"
									src='bookPic/gzjy/<s:property value="%{#als.tupian1}"/>'>
							</a>
						</td>
						<td width="120">
							<s:property value="#als.fenlei" />
							<br>
							<br>
							<s:property value="#als.fenlei2" />

						</td>
						<td width="120">
							<s:property value="#als.zuozhe" />
						</td>
						<td width="150">
							<s:property value="#als.chubanshe" />
						</td>
						<td width="120">
							<s:property value="#als.tianjiashijian" />
						</td>
						<td width="120">
							<s:property value="#als.tianjiaren" />
						</td>
						<td width="50">
							<s:form action="gzjyMoveUp">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="↑"></s:submit>
							</s:form>
							<s:form action="gzjyMoveDown">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="↓"></s:submit>
							</s:form>
						</td>
						<td width="80">
							<s:form action="gzjyBianji">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="编辑"></s:submit>
							</s:form>
						</td>
						<td width="60">
							<s:form action="gzjyShanchu">
								<s:hidden value="%{#als.id}" name="id"></s:hidden>
								<s:submit value="删除" onclick="return confirm('确认删除此本图书？')"></s:submit>
							</s:form>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="12" height="26" align="center">
						<s:if test="page !=1">
							<a href="gzjySyy">上一页</a>&nbsp;
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
										href="gzjyShowall?page=<s:property value='#i.current - 1' />">
										<s:property value="#i.current - 1" /> </a>&nbsp;
								</s:else>
							</s:iterator>
						</s:bean>
						<s:if test="#session.pageCount != page">
							<a href="gzjyXyy">下一页</a>
						</s:if>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
