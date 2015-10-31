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

		<title>朗文图书 出版天地</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>/css/aiyike.css" rel="stylesheet">

	</head>

	<body>
		<s:include value="/pages/top.jsp"></s:include>
		<div id="central">
			<div class="central_left">
				<h3 class="ah3">
					出版天地
				</h3>
				<div class="aleft_box">
					<ul>
						<br>
						<s:iterator value="#session.cbtdList" id="ll" status="st">
							<li>
								<a href="fcbtd?id=<s:property value='%{#ll.id}'/>"> <s:property
										value="#ll.biaoti" /> </a>
							</li>
						</s:iterator>
						<li class="readNow">
							<a href="fhydtList">行业动态</a>
						</li>
					</ul>
				</div>
			</div>

			<div class="central_right">
				<div class="right_title">
					<p>
						当前位置：
					</p>

					<p>
						出版天地 ->
					</p>

					<p>
						行业动态
					</p>
				</div>
				<div id="right_messageBox">
					<ul>
					<s:if test="#session.hydtList.size==0">
							<p style="margin-left: 83px; margin-top: 46px;">
								暂无行业动态信息
							</p>
						</s:if>
						<s:iterator value="#session.hydtList" id="hl">
							<li>
								<a href="fhydtDetail?id=<s:property value='%{#hl.id}' />"> <s:property
										value="#hl.biaoti" /> </a>
								<p>
									<s:property value="#hl.tianjiashijian" />
								</p>
							</li>
						</s:iterator>
					</ul>
				</div>
				<div class="pagelist">
					<ul>
						<s:if test="#session.page!=1">
							<li>
								<a href="fhydtList?page=1">首页</a>
							</li>

							<li>
								<a href="fhydtSyy">上页</a>
							</li>
						</s:if>
						<s:if test="#session.page< #session.pageCount">
							<li>
								<a href="fhydtXyy">下页</a>
							</li>
							<li>
								<a
									href="fhydtList?page=<s:property value='%{#session.pageCount}'/>">尾页</a>
							</li>
						</s:if>
						<li class="pageinfo">
							第&nbsp;
							<s:property value="#session.page" />
							&nbsp;页
						</li>
						<li class="pageinfo">
							共&nbsp;
							<s:property value="#session.pageCount" />
							&nbsp;页
						</li>

					</ul>

				</div>
			</div>
			<a href="javascript:;" id="btn" title="回到顶部"></a>
		</div>

		<s:include value="/pages/footer.jsp"></s:include>
	</body>
</html>
