<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<META content=图书,教育,朗文图书,高等教育，高职高专 name=keywords>
		<META content=湖北朗文图书发行有限公司 name=description>
		<title>湖北朗文图书发行有限公司</title>
		<link href="<%=basePath%>/css/newcss.css" rel="stylesheet">
		<script src="<%=basePath%>/js/jquery.min.js"></script>
		<script src="<%=basePath%>/js/Change.js"></script>
		<script src="<%=basePath%>/js/product.js"></script>
	</head>
	<body>
		<jsp:include page="/pages/top.jsp"></jsp:include>
		<!--<iframe src="<%=basePath%>/pages/top.jsp" width="1349px" height="90px"
			frameborder="0" scrolling="no"></iframe>
		-->
		<div class="central">
			<div class="central_main">
				<div id="central-con" class="central-con">
					<s:iterator value="#session.frontBanner" id="fb" status="st">
						<s:if test="#st.first">
							<div class="mod" style="display: block;">
								<a href="<s:property value='%{#fb.lianjie}'/>" target="_blank;"><img
										class="img"
										src="fujian/banner/<s:property value='%{#fb.tupian}'/>">
								</a>
							</div>
						</s:if>
						<s:else>
							<div class="mod" style="display: none;">
								<a href="<s:property value='%{#fb.lianjie}'/>" target="_blank;"><img
										class="img"
										src="fujian/banner/<s:property value='%{#fb.tupian}'/>">
								</a>
							</div>
						</s:else>
					</s:iterator>
					<div id="central-tit" class="central-tit">
						<ul>
							<li class="select">
							</li>
							<li>
							</li>
							<li>
							</li>
							<li>
							</li>
							<li>
							</li>
						</ul>
					</div>
				</div>
				<div class="editor_choice">
					<div class="editor_title">
						编辑推荐
					</div>
					<div class="editor_book">
						<ul>
							<s:iterator value="#session.frontBjtj" id="fb" status="st"
								end="7" begin="0">
								<li>
									<a href="<s:property value='%{#fb.lianjie}'/>" target="_blank;">
										<s:property value="#st.index+1" />.&nbsp;<s:property
											value="#fb.shuming" /> </a>
								</li>
							</s:iterator>
						</ul>

					</div>

				</div>
			</div>
		</div>
		<!-- 搜索框 -->
		<div class="search">
			<h2 class="search-title">
				搜一下，看看你需要什么
			</h2>

			<form method="post" action="asearchBook">
				<div id="search_input">
					<input name="key" type="text" class="top_input"
						style="font-size: 19px; padding-left: 12px; vertical-align: middle;" />
					<input name="Submit" type="submit" class="top_search_botton"
						value="搜索" />
				</div>
			</form>
		</div>
		<div class="product">
			<h2>
				产品展示
			</h2>

			<div id="product_main" class="product_main">
				<div id="product_tit" class="product-tit">
					<ul id="tab">
						<li id="gdlg" onmouseover="ChangeDiv('gdlg')" class="select">
							<a href="fgdlgList">高等理工</a>
						</li>
						<li onmouseover="ChangeDiv('gdwk')" id="gdwk">
							<a href="fgdwkList">高等文科</a>
						</li>
						<li onmouseover="ChangeDiv('swny')" id="swny">
							<a href="fswnyList">生物农医</a>
						</li>
						<li onmouseover="ChangeDiv('gzjy')" id="gzjy">
							<a href="fgzjyList">高职教育</a>
						</li>
						<li onmouseover="ChangeDiv('zzjy')" id="zzjy">
							<a href="fzzjyList" />中职教育</a>
						</li>
						<li onmouseover="ChangeDiv('jsjy')" id="jsjy">
							<a href="fjsjyList" />教师教育</a>
						</li>
					</ul>

				</div>
				<div id="product-con" class="product-con">
					<div class="mod" style="display: block" id="tab_gdlg">
						<ul>
							<s:iterator value="#session.frontZsgdlg" id="fz" status="st"
								begin="0" end="7">
								<li>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank">
										<img src='fujian/zsgdlg/<s:property value="%{#fz.tupian}"/>'>
									</a>

									<p>
										<s:property value="#fz.chubanshe" />
									</p>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank"><p>
											<s:property value="#fz.shuming" />
										</p> </a>
								</li>
							</s:iterator>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_gdwk">
						<ul>
							<s:iterator value="#session.frontZsgdwk" id="fz" status="st"
								begin="0" end="7">
								<li>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank">
										<img src='fujian/zsgdwk/<s:property value="%{#fz.tupian}"/>'>
									</a>

									<p>
										<s:property value="#fz.chubanshe" />
									</p>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank"><p>
											<s:property value="#fz.shuming" />
										</p> </a>
								</li>
							</s:iterator>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_swny">
						<ul>
							<s:iterator value="#session.frontZsswny" id="fz" status="st"
								begin="0" end="7">
								<li>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank">
										<img src='fujian/zsswny/<s:property value="%{#fz.tupian}"/>'>
									</a>

									<p>
										<s:property value="#fz.chubanshe" />
									</p>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank"><p>
											<s:property value="#fz.shuming" />
										</p> </a>
								</li>
							</s:iterator>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_gzjy">
						<ul>
							<s:iterator value="#session.frontZsgzjy" id="fz" status="st"
								begin="0" end="7">
								<li>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank">
										<img src='fujian/zsgzjy/<s:property value="%{#fz.tupian}"/>'>
									</a>

									<p>
										<s:property value="#fz.chubanshe" />
									</p>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank"><p>
											<s:property value="#fz.shuming" />
										</p> </a>
								</li>
							</s:iterator>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_zzjy">
						<ul>
							<s:iterator value="#session.frontZszzjy" id="fz" status="st"
								begin="0" end="7">
								<li>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank">
										<img src='fujian/zszzjy/<s:property value="%{#fz.tupian}"/>'>
									</a>

									<p>
										<s:property value="#fz.chubanshe" />
									</p>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank"><p>
											<s:property value="#fz.shuming" />
										</p> </a>
								</li>
							</s:iterator>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_jsjy">
						<ul>
							<s:iterator value="#session.frontZsjsjy" id="fz" status="st"
								begin="0" end="7">
								<li>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank">
										<img src='fujian/zsjsjy/<s:property value="%{#fz.tupian}"/>'>
									</a>

									<p>
										<s:property value="#fz.chubanshe" />
									</p>
									<a href="<s:property value='%{#fz.lianjie}'/>" target="_blank"><p>
											<s:property value="#fz.shuming" />
										</p> </a>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="bottom">
			<ul>
				<li>
					<div class="style">
						<p class="bottom_title">
							关于我们
						</p>
						<a href="flwjj">关于朗文图书</a>
						<a href="flxwm">联系我们</a>
						<a href="frczp">人才招聘</a>
					</div>
				</li>
				<li>
					<div class="style">
						<p class="bottom_title">
							友情链接
						</p>
						<s:iterator value="#session.frontYqlj" id="fy" status="st"
							begin="0" end="3">
							<a href="<s:property value='%{#fy.lianjie}'/>" target="_blank">
								<s:property value="#fy.shuming" /> </a>
						</s:iterator>
					</div>
				</li>
				<li>
					<div class="style">
						<p class="bottom_title">
							服务热线
						</p>
						<a>027-87948888</a>
					</div>
				</li>
				<li>
					<div class="logo">
						<p class="bottom_title">
							关注我们
						</p>
						<a class="hsy_weibo" href="http://weibo.com/u/5111825239"
							target=“_blank”><p class="weibo">
								<img src="images/wbcode.png">
							</p> </a>
						<a class="hsy_weibo" target=“_blank”><p class="weixin">
								<img src="images/wxcode.png">
							</p> </a>
					</div>
				</li>
			</ul>
		</div>
		<s:include value="/pages/footer.jsp"></s:include>
	</body>
</html>