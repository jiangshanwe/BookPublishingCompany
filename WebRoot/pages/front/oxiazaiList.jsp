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

		<title>朗文图书 搜索结果</title>

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
					文件搜索结果
				</h3>

				<div class="aleft_box">
					<ul>
						<br />
						<s:if test="#session.xiazaiList.size()>0">
							<li class="readNow">
								<a href="asearchXiazai">下载中心</a>
							</li>
						</s:if>
					</ul>
				</div>
			</div>

			<div class="central_right">
				<div class="right_title">
					<p>
						当前位置：
					</p>

					<p>
						文件搜索结果
					</p>
					<p style="float: right; padding-right: 12px;">
						<form method="post" action="osearchXiazai">
							<input type="text" value="<s:property value='%{#session.oxkey}'/>"
								name="oxkey"
								style="border: 2px solid #CDB38B; height: 34px; width: 234px; font-size: 19px; padding-left: 12px; vertical-align: middle; margin-left: 206px;">
							&nbsp;&nbsp;
							<input type="submit" value="搜索文件" class="searchLabel">
						</form>
					</p>
				</div>
				<div id="right_messageBox">
					<ul>
						<s:iterator value="#session.xiazaiList" id="bl" status="st">
							<li>
								<a href="fxzzxDetail?id=<s:property value='%{#bl.id}' />"
									target="_blank"> <s:property value="#bl.biaoti" /> </a>
								<p>
									<s:property value="#bl.tianjiashijian" />
								</p>
							</li>
						</s:iterator>
					</ul>
				</div>
				<div class="pagelist">
					<ul>
						<s:if test="#session.page!=1">
							<li>
								<a href="osearchXiazai?page=1">首页</a>
							</li>

							<li>
								<a href="osearchXiazaiSyy">上页</a>
							</li>
						</s:if>
						<s:if test="#session.page< #session.pageCount">
							<li>
								<a href="osearchXiazaiXyy">下页</a>
							</li>
							<li>
								<a
									href="osearchXiazai?page=<s:property value='%{#session.pageCount}'/>">尾页</a>
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
		</div>
		<s:include value="/pages/footer.jsp"></s:include>
	</body>
</html>
