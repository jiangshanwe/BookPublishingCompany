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

		<title>朗文图书 产品中心</title>

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
					产品中心
				</h3>

				<div class="aleft_box">
					<ul>
						<br />
						<li class="readNow">
							<a href="fgdlgList">高等理工</a>
						</li>
						<li>
							<a href="fgdwkList">高等文科</a>
						</li>
						<li>
							<a href="fswnyList">生物农医</a>
						</li>
						<li>
							<a href="fgzjyList">高职教育</a>
						</li>
						<li>
							<a href="fzzjyList">中职教育</a>
						</li>
						<li>
							<a href="fjsjyList">教师教育</a>
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
						产品中心 ->
					</p>

					<p>
						高等理工
					</p>
					<p style="float: right; padding-right: 12px;">
						<form method="post" action="osearchBook">
							<input type="text"
								value="<s:property value='%{#session.obkey}'/>" name="obkey"
								style="border: 2px solid #CDB38B; height: 34px; width: 234px; font-size: 19px; padding-left: 12px; vertical-align: middle; margin-left: 206px;">
							&nbsp;&nbsp;
							<input type="submit" value="搜索图书" class="searchLabel">
						</form>
					</p>
				</div>
				<div id="right_messageBook">
					<ul>
						<s:if test="#session.gdlgList.size==0">
							<p style="margin-left: 83px; margin-top: 46px;">
								暂无图书信息
							</p>
						</s:if>
						<s:iterator value="#session.gdlgList" id="bl" status="st">
							<s:if test="(#st.index+1)%4==1">
								<li class="right_messageBookFirst">
							</s:if>
							<s:else>
								<li>
							</s:else>
							<a href="fgdlgDetail?id=<s:property value='%{#bl.id}' />"
								target="_blank"> <img alt="暂无图书照片"
									src='bookPic/gdlg/<s:property value="%{#bl.tupian1}"/>'>
							</a>
							<p>
								<a href="fgdlgDetail?id=<s:property value='%{#bl.id}' />"
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
								<a href="fgdlgList?page=1">首页</a>
							</li>

							<li>
								<a href="fgdlgSyy">上页</a>
							</li>
						</s:if>
						<s:if test="#session.page< #session.pageCount">
							<li>
								<a href="fgdlgXyy">下页</a>
							</li>
							<li>
								<a
									href="fgdlgList?page=<s:property value='%{#session.pageCount}'/>">尾页</a>
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
