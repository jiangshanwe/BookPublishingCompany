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
					搜索结果
				</h3>

				<div class="aleft_box">
					<ul>
						<br />
						<li class="readNow">
							<a href="asearchBook">图书产品</a>
						</li>
						<s:if test="#session.kejianList.size()>0">
							<li>
								<a href="asearchKejian">课件下载</a>
							</li>
						</s:if>
						<s:if test="#session.xiazaiList.size()>0">
							<li>
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
						搜索结果 ->
					</p>

					<p>
						图书产品
					</p>
					<p style="float: right; padding-right: 12px;">
						<form method="post" action="asearchBook">
							<input type="text" value="<s:property value='%{#session.key}'/>"
								name="key"
								style="border: 2px solid #CDB38B; height: 34px; width: 234px; font-size: 19px; padding-left: 12px; vertical-align: middle; margin-left: 206px;">
							&nbsp;&nbsp;
							<input type="submit" value="搜  索" class="searchLabel">
						</form>
					</p>
				</div>
				<div id="right_messageBook">
					<ul>
						<s:iterator value="#session.bookList" id="bl" status="st">
							<s:if test="(#st.index+1)%4==1">
								<li class="right_messageBookFirst">
							</s:if>
							<s:else>
								<li>
							</s:else>
							<a
								href="asearchBookDetail?id=<s:property value='%{#bl.id}' />&type=<s:property value='%{#bl.type}'/>"
								target="_blank"> <img alt="暂无图书照片"
									src='bookPic/<s:property value="%{#bl.type}"/>/<s:property value="%{#bl.tupian1}"/>'>
							</a>
							<p>
								<a
									href="asearchBookDetail?id=<s:property value='%{#bl.id}'/>&type=<s:property value='%{#bl.type}'/>"
									target="_blank"><s:property value="#bl.shuming" /> </a>
								<br>
								<s:property value="#bl.zuozhe" />
								编著
							</p>
							</li>
						</s:iterator>
					</ul>
				</div>
				<div class="pagelist">
					<ul>
						<s:if test="#session.page!=1">
							<li>
								<a href="asearchBook?page=1">首页</a>
							</li>

							<li>
								<a href="asearchBookSyy">上页</a>
							</li>
						</s:if>
						<s:if test="#session.page< #session.pageCount">
							<li>
								<a href="asearchBookXyy">下页</a>
							</li>
							<li>
								<a
									href="asearchBook?page=<s:property value='%{#session.pageCount}'/>">尾页</a>
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
