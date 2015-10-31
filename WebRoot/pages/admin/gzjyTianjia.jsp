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

		<title>My JSP 'gzjyTianjia.jsp' starting page</title>

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
		<form action="gzjyAdd" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑图书信息
				</legend>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					<s:select
						list="#{'高职通用课程':'高职通用课程','农林牧渔大类':'农林牧渔大类','交通运输大类':'交通运输大类','生化与药品大类':'生化与药品大类','资源开发与测绘大类':'资源开发与测绘类','材料与能源大类':'材料与能源大类','土建大类':'土建大类','水利大类':'水利大类','制造大类':'制造大类','电子信息大类':'电子信息大类','计算机大类':'计算机大类','环保,气象与安全大类':'环保,气象与安全大类','轻纺食品大类':'轻纺食品大类','财经大类':'财经大类','医药卫生大类':'医药卫生大类','旅游大类':'旅游大类','公共事业大类':'公共事业大类','文化教育大类':'文化教育大类','外语类':'外语类','艺术设计传媒大类':'艺术设计传媒大类','公安大类':'公安大类','法律大类':'法律大类','其它专业大类':'其它专业大类'}"
						name="fenlei" label="所属分类"></s:select>
					&nbsp;&nbsp;&nbsp;
					<s:select
						list="#{'无':'无','高职通用课程':'高职通用课程','农林牧渔大类':'农林牧渔大类','交通运输大类':'交通运输大类','生化与药品大类':'生化与药品大类','资源开发与测绘大类':'资源开发与测绘类','材料与能源大类':'材料与能源大类','土建大类':'土建大类','水利大类':'水利大类','制造大类':'制造大类','电子信息大类':'电子信息大类','计算机大类':'计算机大类','环保,气象与安全大类':'环保,气象与安全大类','轻纺食品大类':'轻纺食品大类','财经大类':'财经大类','医药卫生大类':'医药卫生大类','旅游大类':'旅游大类','公共事业大类':'公共事业大类','文化教育大类':'文化教育大类','外语类':'外语类','艺术设计传媒大类':'艺术设计传媒大类','公安大类':'公安大类','法律大类':'法律大类','其它专业大类':'其它专业大类'}"
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
