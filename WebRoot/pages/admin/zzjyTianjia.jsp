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

		<title>My JSP 'zzjyTianjia.jsp' starting page</title>

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
		<form action="zzjyAdd" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑图书信息
				</legend>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					<s:select
						list="#{'德育与教学文件':'德育与教学文件','素质教育':'素质教育','文化课':'文化课','计算机类':'计算机类','电子电气通信类':'电子电气通信类','机械机电类':'机械机电类','交通类':'交通类','建筑类':'建筑类','化工类':'化工类','旅游航空类':'旅游航空类','烹饪食品类':'烹饪食品类','财经类':'财经类','商贸类':'商贸类','文秘公关类':'文秘公关类','幼儿教育类':'幼儿教育类','服装类':'服装类','艺术与动漫类':'艺术与动漫类','服务类':'服务类','医药卫生类':'医药卫生类','司法服务类':'司法服务类','农林类':'农林类','体育专项类':'体育专项类','其它专业类':'其它专业类'}"
						name="fenlei" label="所属分类"></s:select>
					&nbsp;&nbsp;&nbsp;
					<s:select
						list="#{'无':'无','德育与教学文件':'德育与教学文件','素质教育':'素质教育','文化课':'文化课','计算机类':'计算机类','电子电气通信类':'电子电气通信类','机械机电类':'机械机电类','交通类':'交通类','建筑类':'建筑类','化工类':'化工类','旅游航空类':'旅游航空类','烹饪食品类':'烹饪食品类','财经类':'财经类','商贸类':'商贸类','文秘公关类':'文秘公关类','幼儿教育类':'幼儿教育类','服装类':'服装类','艺术与动漫类':'艺术与动漫类','服务类':'服务类','医药卫生类':'医药卫生类','司法服务类':'司法服务类','农林类':'农林类','体育专项类':'体育专项类','其它专业类':'其它专业类'}"
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
					<s:date name="" />
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
