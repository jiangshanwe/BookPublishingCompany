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

		<title>朗文图书 教学服务</title>

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
					<a href="fjxfwList">教学服务</a>
				</h3>
				<div class="aleft_box">
					<ul>
						<br>
						<li>
							<a href="fyssq">样书申请</a>
						</li>
						<li class="readNow">
							<a href="fhyyqList">会议邀请</a>
						</li>
						<li>
							<a href="fkjxzList">课件下载</a>
						</li>
						<li>
							<a href="fxzzxList">下载中心</a>
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
						教学服务 ->
					</p>

					<p>
						会议邀请
					</p>
				</div>
				<div id="right_messageBox">
					<ul>
						<s:if test="#session.hyyqList.size==0">
							<p style="margin-left: 83px; margin-top: 46px;">
								暂无会议邀请
							</p>
						</s:if>
						<s:iterator value="#session.hyyqList" id="hl">
							<li>
								<a href="fhyyqDetail?id=<s:property value='%{#hl.id}' />"> <s:property
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
								<a href="fhyyqList?page=1">首页</a>
							</li>

							<li>
								<a href="fhyyqSyy">上页</a>
							</li>
						</s:if>
						<s:if test="#session.page< #session.pageCount">
							<li>
								<a href="fhyyqXyy">下页</a>
							</li>
							<li>
								<a
									href="fhyyqList?page=<s:property value='%{#session.pageCount}'/>">尾页</a>
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
