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

		<title>My JSP 'gdwkBianji.jsp' starting page</title>

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
		<form action="gdwkUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑图书信息
				</legend>
				<s:hidden name="id" value="%{#session.gdwkBianji.id}"></s:hidden>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					<s:select
						list="#{'马克思主义理论类':'马克思主义理论类','中文类':'中文类','历史类':'历史类','哲学类':'哲学类','政治学类':'政治学类','社会学类':'社会学类','法律类':'法律类','经济类':'经济类','管理类':'管理类','新闻传播类':'新闻传播类','艺术类':'艺术类','教育类':'教育类','体育类':'体育类','外语类':'外语类'}"
						name="fenlei" label="所属分类" value="%{#session.gdwkBianji.fenlei}"></s:select>
					&nbsp;&nbsp;&nbsp;
					<s:select
						list="#{'无':'无','马克思主义理论类':'马克思主义理论类','中文类':'中文类','历史类':'历史类','哲学类':'哲学类','政治学类':'政治学类','社会学类':'社会学类','法律类':'法律类','经济类':'经济类','管理类':'管理类','新闻传播类':'新闻传播类','艺术类':'艺术类','教育类':'教育类','体育类':'体育类','外语类':'外语类'}"
						name="fenlei2" label="添加分类" value="%{#session.gdwkBianji.fenlei2}"></s:select>
				</p>
				<p>
					图片1:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkBianji.tupian1}"/>'>
					图片2:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkBianji.tupian2}"/>'>
					图片3:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkBianji.tupian3}"/>'>
					图片4:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdwk/<s:property value="%{#session.gdwkBianji.tupian4}"/>'>
					<br>
					重新上传图片1:
					<input type="file" name="upload">
					重新上传图片2:
					<input type="file" name="upload">
					<br>
					重新上传图片3:
					<input type="file" name="upload">
					重新上传图片4:
					<input type="file" name="upload">
				</p>
				<p>
					<s:textfield name="shuming" label="图书名称" cssStyle="width:225px;"
						value="%{#session.gdwkBianji.shuming}"></s:textfield>
				</p>
				<p>
					<s:textfield name="zuozhe" value="%{#session.gdwkBianji.zuozhe}"
						label="作者" cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshe"
						value="%{#session.gdwkBianji.chubanshe}" label="出版社"
						cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshijian"
						value="%{#session.gdwkBianji.chubanshijian}" label="出版时间"></s:textfield>
				</p>
				<p>
					<s:textfield name="isbn" label="ISBN" cssStyle="width:225px;"
						value="%{#session.gdwkBianji.isbn}"></s:textfield>
				</p>
				<p>
					<s:textfield name="banci" label="版次" value="1"
						value="%{#session.gdwkBianji.banci}"></s:textfield>
				</p>
				<p>
					<s:textfield name="baozhuang" label="包装"
						value="%{#session.gdwkBianji.baozhuang}"></s:textfield>
				</p>
				<p>
					<s:textfield name="kaiben" label="开本"
						value="%{#session.gdwkBianji.kaiben}"></s:textfield>
				</p>
				<p>
					<s:textfield name="yeshu" label="页数"
						value="%{#session.gdwkBianji.yeshu}"></s:textfield>
				</p>
				<p>
					<s:textfield name="dingjia" label="定价"
						value="%{#session.gdwkBianji.dingjia}"></s:textfield>
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
							<s:property value="#session.gdwkBianji.neirongjianjie" />
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
						<s:property value="#session.gdwkBianji.zuozhejianjie" />
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
						<s:property value="#session.gdwkBianji.mulu" />
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
							<s:property value="#session.gdwkBianji.jingcaishuzhai" />
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
						<s:property value="#session.gdwkBianji.qianyan" />
</textarea>
				</div>
				<p>
					首页展示：
					<s:radio name="zhanshi" list="%{#{'1':'是','0':'否'}}"
						value="%{#session.gdwkBianji.zhanshi}"></s:radio>
				</p>
				<p>
					<input value="确认修改" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
