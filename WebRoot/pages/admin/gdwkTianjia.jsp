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

		<title>My JSP 'gdwkTianjia.jsp' starting page</title>

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
		<form action="gdwkAdd" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑图书信息
				</legend>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					<s:select
						list="#{'马克思主义理论类':'马克思主义理论类','中文类':'中文类','历史类':'历史类','哲学类':'哲学类','政治学类':'政治学类','社会学类':'社会学类','法律类':'法律类','经济类':'经济类','管理类':'管理类','新闻传播类':'新闻传播类','艺术类':'艺术类','教育类':'教育类','体育类':'体育类','外语类':'外语类'}"
						name="fenlei" label="所属分类"></s:select>
					&nbsp;&nbsp;&nbsp;
					<s:select
						list="#{'无':'无','马克思主义理论类':'马克思主义理论类','中文类':'中文类','历史类':'历史类','哲学类':'哲学类','政治学类':'政治学类','社会学类':'社会学类','法律类':'法律类','经济类':'经济类','管理类':'管理类','新闻传播类':'新闻传播类','艺术类':'艺术类','教育类':'教育类','体育类':'体育类','外语类':'外语类'}"
						name="fenlei2" label="添加分类"></s:select>
				</p>
				<p>
					上传图片1:
					<input type="file" name="upload">
					上传图片2:
					<input type="file" name="upload">
					<br>
					上传图片3:
					<input type="file" name="upload">
					上传图片4:
					<input type="file" name="upload">
				</p>
				<p>
					<s:textfield name="shuming" label="图书名称" cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="zuozhe" label="作者" cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshe" label="出版社" cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:date name=""/>
					<s:textfield name="chubanshijian" label="出版时间"></s:textfield>
				</p>
				<p>
					<s:textfield name="isbn" label="ISBN" cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="banci" label="版次" value="1"></s:textfield>
				</p>
				<p>
					<s:textfield name="baozhuang" label="包装"></s:textfield>
				</p>
				<p>
					<s:textfield name="kaiben" label="开本"></s:textfield>
				</p>
				<p>
					<s:textfield name="yeshu" label="页数"></s:textfield>
				</p>
				<p>
					<s:textfield name="dingjia" label="定价"></s:textfield>
				</p>
				<div id="sample">
					<script type="text/javascript"
						src="http://js.nicedit.com/nicEdit-latest.js"></script>
					<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>
					<h4>
						内容简介
					</h4>
					<textarea name="neirongjianjie" style="width: 100%; height: 380px;">
</textarea>
				</div>
				<div id="sample">
					<script type="text/javascript"
						src="http://js.nicedit.com/nicEdit-latest.js"></script>
					<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>
					<h4>
						作者简介
					</h4>
					<textarea name="zuozhejianjie" style="width: 100%; height: 380px;">
</textarea>
				</div>
				<div id="sample">
					<script type="text/javascript"
						src="http://js.nicedit.com/nicEdit-latest.js"></script>
					<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>
					<h4>
						目录
					</h4>
					<textarea name="mulu" style="width: 100%; height: 380px;">
</textarea>
				</div>
				<div id="sample">
					<script type="text/javascript"
						src="http://js.nicedit.com/nicEdit-latest.js"></script>
					<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>
					<h4>
						精彩书摘
					</h4>
					<textarea name="jingcaishuzhai" style="width: 100%; height: 380px;">
</textarea>
				</div>
				<div id="sample">
					<script type="text/javascript"
						src="http://js.nicedit.com/nicEdit-latest.js"></script>
					<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
</script>
					<h4>
						前言/序言
					</h4>
					<textarea name="qianyan" style="width: 100%; height: 380px;">
</textarea>
				</div>
				<p>
					<input value="确认添加" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
