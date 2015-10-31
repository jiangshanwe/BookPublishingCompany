<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<META content=图书,教育,朗文图书,高等教育，高职高专 name=keywords>
		<META content=湖北朗文图书发行有限公司 name=description>
		<title>湖北朗文图书发行有限公司</title>
		<link href="<%=basePath%>/css/newcss.css" rel="stylesheet">
		<script src="<%=basePath%>/js/jquery.min.js"></script>
		<script src="<%=basePath%>/js/Change.js"></script>
		<script src="<%=basePath%>/js/product.js"></script>
	</head>
	<body>
		<iframe src="<%=basePath%>/pages/top.jsp" width="1349px" height="90px"
			frameborder="0" scrolling="no"></iframe>
		<div class="central">
			<div class="central_main">
				<div id="central-con" class="central-con">
					<div class="mod" style="display: block">
						<a href="#"><img class="img"
								src="<%=basePath%>/images/banner/banner1.jpg"> </a>
					</div>
					<div class="mod" style="display: none">
						<a href="#"><img class="img"
								src="<%=basePath%>/images/banner/banner2.jpg"> </a>
					</div>
					<div class="mod" style="display: none">
						<a href="#"><img class="img"
								src="<%=basePath%>/images/banner/banner3.jpg"> </a>
					</div>
					<div class="mod" style="display: none">
						<a href="#"><img class="img"
								src="<%=basePath%>/images/banner/banner4.jpg"> </a>
					</div>
					<div class="mod" style="display: none">
						<a href="#"><img class="img"
								src="<%=basePath%>/images/banner/banner5.jpg"> </a>
					</div>
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
							<li>
								<a href="#">1. 我坦言我曾历尽沧桑</a>
							</li>
							<li>
								<a href="#">2. 目客:花朵与我(第001期)</a>
							</li>
							<li>
								<a href="#">3. 鱼羊野史·第3卷</a>
							</li>
							<li>
								<a href="#">4. 宝宝心理成长绘本</a>
							</li>
							<li>
								<a href="#">5. 【姜振宇推荐】清道夫</a>
							</li>
							<li>
								<a href="#">6. 宋家客厅：从钱锺书到张爱玲</a>
							</li>
							<li>
								<a href="#">7. 【屠岸倾情推荐】漂流记</a>
							</li>
							<li>
								<a href="#">8. 道路自信：中国为什么能</a>
							</li>
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

			<form id="form1" name="form1" method="post" action="">
				<div id="search_input">
					<input name="textfield" type="text" class="top_input" />
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
							<a href="#">高等理工</a>
						</li>
						<li onmouseover="ChangeDiv('gdwk')" id="gdwk">
							<a href="#">高等文科</a>
						</li>
						<li onmouseover="ChangeDiv('swny')" id="swny">
							<a href="#">生物农医</a>
						</li>
						<li onmouseover="ChangeDiv('gzjy')" id="gzjy">
							<a href="#">高职教育</a>
						</li>
						<li onmouseover="ChangeDiv('zzjy')" id="zzjy">
							<a href="#" />中职教育</a>
						</li>
						<li onmouseover="ChangeDiv('jsjy')" id="jsjy">
							<a href="#" />教师教育</a>
						</li>
					</ul>

				</div>
				<div id="product-con" class="product-con">
					<div class="mod" style="display: block" id="tab_gdlg">
						<ul>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>

							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gdsx.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_gdwk">
						<ul>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									1中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									1中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									1中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>

							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gxdl.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_swny">
						<ul>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>

							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/swny.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_gzjy">
						<ul>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>

							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/gzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_zzjy">
						<ul>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>

							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/zzjy.jpg"> </a>

								<p>
									中国人民出版社
								</p>
								<a href="#"><p>
										古汉语文学
									</p> </a>
							</li>
						</ul>
					</div>
					<div class="mod" style="display: none" id="tab_jsjy">
						<ul>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
							<li>
								<a href="#"> <img
										src="<%=basePath%>/images/bookpic/jsjy.jpg"> </a>

								<p>
									高等教育出版社
								</p>
								<a href="#">
									<p>
										Java编程思想
									</p> </a>
							</li>
						</ul>
					</div>
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
						<a href="#">关于朗文图书</a>
						<a href="#">联系我们</a>
						<a href="#">人才招聘</a>
					</div>
				</li>
				<li>
					<div class="style">
						<p class="bottom_title">
							友情链接
						</p>
						<a href="http://www.hep.com.cn/" target="_blank">高等教育出版社</a>
						<a href="http://www.crup.com.cn/" target="_blank">中国人民大学出版社</a>
						<a href="http://www.crup.com.cn/" target="_blank">中国人民大学出版社</a>
						<a href="http://www.crup.com.cn/" target="_blank">中国人民大学出版社</a>
					</div>
				</li>
				<li>
					<div class="style">
						<p class="bottom_title">
							服务热线
						</p>
						<a href="#">027-87948888</a>
					</div>
				</li>
				<li>
					<div class="logo">
						<p class="bottom_title">
							关注我们
						</p>
						<a class="hsy_weibo" href="http://weibo.com/u/5111825239"
							target=“_blank”><p class="weibo">
								<img src="images/wxcode.png">
							</p>
						</a>
						<a class="hsy_weibo"
							target=“_blank”><p class="weixin">
								<img src="images/wxcode.png">
							</p>
						</a>
					</div>
				</li>
			</ul>
		</div>
		<iframe frameborder="0" scrolling="no"
			src="<%=basePath%>/pages/footer.jsp" width="100%" height="15%"></iframe>
	</body>
</html>