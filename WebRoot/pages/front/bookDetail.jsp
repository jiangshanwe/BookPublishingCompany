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

		<title>朗文 图书详细</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="http://misc.360buyimg.com/lib/skin/2013/base.css" />
		<link rel="stylesheet" type="text/css"
			href="http://misc.360buyimg.com/product/item/1.0.12/??/widget/common/common.css,/widget/sprite/sprite.css,/widget/main/main.css,/widget/contrast/contrast.css,/widget/combineShare/combineShare.css,/widget/itemInfo/itemInfo.css,/widget/extInfo/extInfo.css,/widget/promiseIcon/promiseIcon.css,/widget/popScore/popScore.css,/widget/preview/preview.css,/widget/fitting/fitting.css,/widget/ui-box/ui-box.css,/widget/ui-star/ui-star.css,/widget/ui-tag/ui-tag.css,/widget/detailContent/detailContent.css,/widget/comment/comment.css,/widget/commentsList/commentsList.css,/widget/ui-page/ui-page.css,/widget/consult/consult.css,/widget/discuss/discuss.css,/widget/yourFind/yourFind.css"
			source="widget" />
		<link rel="stylesheet" type="text/css" href="/css/aiyike.css">
		<script>
	var pageConfig = {
		compatible : true,
		searchType : 1,
		product : {
			name : '\u661f\u7403\u5927\u6218\uff08\u5957\u88c5\u5171\u0036\u518c\uff09',
			skuidkey : '4FEED31BA9E98C0265BF93A29481C657',
			href : 'http://book.jd.com/11704008.html',
			src : 'jfs/t1228/79/1050350878/322286/1249d825/556fe704N2227ef56.jpg',
			cat : [ 1713, 3263, 3394 ],
			brand : 0,
			luxury : false,
			pType : 3,
			venderId : 0,
			shopId : '0',
			specialAttrs : [],

			colorSize : {},
			warestatus : 1,
			isNew : true,

			desc : 'http://d.3.cn/desc/11704008'
		}
	};
</script>
		<script
			src="http://misc.360buyimg.com/??jdf/lib/jquery-1.6.4.js,jdf/1.0.0/unit/base/1.0.0/base.js"></script>

	</head>

	<body version="140120"
		class="cat-1-1713 cat-2-3263 cat-3-3394 item-11704008 JD JD-3"
		style="background-color: #F4F4F4;">
		<s:include value="/pages/top.jsp"></s:include>
		<div id="p-box" style="background-color: #F4F4F4;">
			<div class="w" style="background-color: #F4F4F4">
				<div id="product-intro" class="m-item-grid clearfix"
					style="background-color: #F4F4F4;">
					<div style="padding-top: 16px; padding-left: 16%; font-size: 17px;">
						<font style="text-decoration: underline">产品中心</font>->
						<font style="text-decoration: underline"><s:property value="#session.bookType"/> </font>->
						<font style="text-decoration: underline">图书明细</font>
					</div>
					<div id="preview" clstag="shangpin|keycount|product|mainpicarea_3"
						style="padding-top: 15px;">
						<div id="spec-n1" class="jqzoom"
							clstag="shangpin|keycount|product|mainpic_3"
							style="padding-left: 29%;">
							<img data-img="1" width="240" height="320"
								style="padding-top: 4%;"
								src='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian1}"/>'
								alt='<s:property value="%{#session.bookDetail.shuming}"/>' />
						</div>
						<div id="spec-list" clstag="shangpin|keycount|product|lunbotu_3">

							<div class="spec-items" style="background-color: #F4F4F4">
								<ul class="lh" style="padding-left: 32%; width: 260px;">
									<li>
										<img class='img-hover'
											alt='<s:property value="%{#session.bookDetail.shuming}"/>'
											src='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian1}"/>'
											data-url='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian1"/>'
											data-img='1' width='120' height='160'>
									</li>
									<li>
										<img
											alt='<s:property value="%{#session.bookDetail.shuming}"/>'
											src='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian2}"/>'
											data-url='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian2"/>'
											data-img='1' width='120' height='160'>
									</li>
									<li>
										<img
											alt='<s:property value="%{#session.bookDetail.shuming}"/>'
											src='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian3}"/>'
											data-url='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian3"/>'
											data-img='1' width='120' height='160'>
									</li>
									<li>
										<img
											alt='<s:property value="%{#session.bookDetail.shuming}"/>'
											src='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian4}"/>'
											data-url='bookPic/<s:property value="%{#session.bookDetail.type}"/>/<s:property value="%{#session.bookDetail.tupian4"/>'
											data-img='1' width='120' height='160'>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="m-item-inner"
						clstag="shangpin|keycount|product|zhushujuqu_3">
						<div id="itemInfo">
							<div id="name">
								<h1
									style="font-size: 21px; font-weight: normal; padding-left: 28%; padding-top: 20px; padding-bottom: 12px;">
									<s:property value="#session.bookDetail.shuming" />
								</h1>
							</div>
							<div id="summary"
								style="padding-top: 44px; padding-left: 22px; border: 2px solid #CDB38B; font-size: 16px; padding-bottom: 42px; margin-left: 19%;">
								<table>
									<tr>
										<td style="width: 60%;">
											著者:&nbsp;&nbsp;
											<s:property value="#session.bookDetail.zuozhe" />
										</td>
										<td style="padding-left: 18px;">
											出版社:&nbsp;&nbsp;
											<s:property value="#session.bookDetail.chubanshe" />
										</td>
									</tr>
									<tr style="height: 18px;"></tr>
									<tr>
										<td>
											ISBN:&nbsp;&nbsp;
											<s:property value="#session.bookDetail.isbn" />
										</td>
										<td style="padding-left: 18px;">
											版次:&nbsp;&nbsp;第&nbsp;
											<s:property value="#session.bookDetail.banci" />
											&nbsp;版
										</td>
									</tr>
									<tr style="height: 18px;"></tr>
									<tr>
										<td>
											包装:&nbsp;&nbsp;
											<s:property value="#session.bookDetail.baozhuang" />
										</td>
										<td style="padding-left: 18px;">
											开本:&nbsp;&nbsp;
											<s:property value="#session.bookDetail.kaiben" />
											&nbsp;开
										</td>
									</tr>
									<tr style="height: 18px;"></tr>
									<tr>
										<td>
											出版时间:&nbsp;&nbsp;<s:property value="#session.bookDetail.chubanshijian" />
										</td>
										<td style="padding-left: 18px;">
											页数:&nbsp;&nbsp;<s:property value="#session.bookDetail.yeshu" />
										</td>
									</tr>
									<tr style="height: 18px;"></tr>
									<tr>
										<td>
											定价:&nbsp;&nbsp;<s:property value="#session.bookDetail.dingjia" /> 元
										</td>
										<td></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="right"
			style="width: 70%; margin: 10px auto; text-align: left; padding-right: 15%">
			<div class="book-detail-item">
				<div class="item-mt">
					<h3>
						内容简介
					</h3>
				</div>
				<div class="item-mc">
					<div class="book-detail-content" style="line-height: 1.5">
						<s:property value="#session.bookDetail.neirongjianjie" escape="false" />
					</div>
				</div>
			</div>
			<div text="作者简介" class="book-detail-item">
				<div class="item-mt">
					<h3>
						作者简介
					</h3>
				</div>
				<div class="item-mc">
					<div class="book-detail-content" style="line-height: 1.5">
						<s:property value="#session.bookDetail.zuozhejianjie" escape="false" />
					</div>
				</div>
			</div>
			<div text="目录" class="book-detail-item">
				<div class="item-mt">
					<h3>
						目录
					</h3>
				</div>
				<div class="item-mc">
					<div class="book-detail-content" style="line-height: 1.5">
						<s:property value="#session.bookDetail.mulu" escape="false" />
					</div>
				</div>
			</div>
			<div text="精彩书摘" class="book-detail-item">
				<div class="item-mt">
					<h3>
						精彩书摘
					</h3>
				</div>
				<div class="item-mc">
					<div class="book-detail-content" style="line-height: 1.5">
						<s:property value="#session.bookDetail.jingcaishuzhai" escape="false" />
					</div>
				</div>
			</div>
			<div text="前言/序言" class="book-detail-item">
				<div class="item-mt">
					<h3>
						前言/序言
					</h3>
				</div>
				<div class="item-mc">
					<div class="book-detail-content" style="line-height: 1.5">
						<s:property value="#session.bookDetail.qianyan" escape="false" />
					</div>
				</div>
			</div>
		</div>


		<script>
    seajs.use('product/item/1.0.12/js/entrance', function (app) {
        app.init();
    });
    seajs.use('http://d.jd.com/hotwords/get?Position=mvd-05');
    function totouchbate() {
        var exp = new Date();
        exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);
        document.cookie = "pcm=2;expires=" + exp.toGMTString() + ";path=/;domain=jd.com";
        window.location.href = "http://m.jd.com/product/11704008.html";
    }
    if (window.showtouchurl) {
        $("#GLOBAL_FOOTER").after("<div class='ac' style='padding-bottom:30px;'>你的浏览器更适合浏览触屏版&nbsp;&nbsp;&nbsp;&nbsp;<a href='#none' style='text-decoration:underline;' onclick='totouchbate()'>京东触屏版</a></div>");
    } else {
        $("#GLOBAL_FOOTER").css("padding-bottom", "30px");
    }
</script>
<br><br>
		<iframe frameborder="0" scrolling="no"
			src="<%=basePath%>/pages/footer.jsp" width="100%" height="15%"></iframe>
	</body>
</html>
