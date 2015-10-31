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

		<title>My JSP 'gdlgBianji.jsp' starting page</title>

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
		<form action="gdlgUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑图书信息
				</legend>
				<s:hidden name="id" value="%{#session.gdlgBianji.id}"></s:hidden>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					<s:select
						list="#{'数学类':'数学类','物理类':'物理类','化学,化工与制药类':'化学,化工与制药类','地学,地矿类':'地学,地矿类','环境类':'环境类','心理学类':'心理学类','计算机类':'计算机类','电子电气信息类':'电子电气信息类','力学类':'力学类','土建,水利类':'土建,水利类','机械,能源类':'机械,能源类','轻工,纺织,食品类':'轻工,纺织,食品类','材料类':'材料类'}"
						name="fenlei" label="所属分类" value="%{#session.gdlgBianji.fenlei}"></s:select>
					&nbsp;&nbsp;&nbsp;
					<s:select
						list="#{'无':'无','数学类':'数学类','物理类':'物理类','化学,化工与制药类':'化学,化工与制药类','地学,地矿类':'地学,地矿类','环境类':'环境类','心理学类':'心理学类','计算机类':'计算机类','电子电气信息类':'电子电气信息类','力学类':'力学类','土建,水利类':'土建,水利类','机械,能源类':'机械,能源类','轻工,纺织,食品类':'轻工,纺织,食品类','材料类':'材料类'}"
						name="fenlei2" label="添加分类" value="%{#session.gdlgBianji.fenlei2}"></s:select>
				</p>
				<p>
					图片1:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdlg/<s:property value="%{#session.gdlgBianji.tupian1}"/>'>
					图片2:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdlg/<s:property value="%{#session.gdlgBianji.tupian2}"/>'>
					图片3:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdlg/<s:property value="%{#session.gdlgBianji.tupian3}"/>'>
					图片4:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/gdlg/<s:property value="%{#session.gdlgBianji.tupian4}"/>'>
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
						value="%{#session.gdlgBianji.shuming}"></s:textfield>
				</p>
				<p>
					<s:textfield name="zuozhe" value="%{#session.gdlgBianji.zuozhe}"
						label="作者" cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshe"
						value="%{#session.gdlgBianji.chubanshe}" label="出版社"
						cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshijian"
						value="%{#session.gdlgBianji.chubanshijian}" label="出版时间"></s:textfield>
				</p>
				<p>
					<s:textfield name="isbn" label="ISBN" cssStyle="width:225px;"
						value="%{#session.gdlgBianji.isbn}"></s:textfield>
				</p>
				<p>
					<s:textfield name="banci" label="版次" value="1"
						value="%{#session.gdlgBianji.banci}"></s:textfield>
				</p>
				<p>
					<s:textfield name="baozhuang" label="包装"
						value="%{#session.gdlgBianji.baozhuang}"></s:textfield>
				</p>
				<p>
					<s:textfield name="kaiben" label="开本"
						value="%{#session.gdlgBianji.kaiben}"></s:textfield>
				</p>
				<p>
					<s:textfield name="yeshu" label="页数"
						value="%{#session.gdlgBianji.yeshu}"></s:textfield>
				</p>
				<p>
					<s:textfield name="dingjia" label="定价"
						value="%{#session.gdlgBianji.dingjia}"></s:textfield>
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
							<s:property value="#session.gdlgBianji.neirongjianjie" />
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
						<s:property value="#session.gdlgBianji.zuozhejianjie" />
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
						<s:property value="#session.gdlgBianji.mulu" />
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
							<s:property value="#session.gdlgBianji.jingcaishuzhai" />
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
						<s:property value="#session.gdlgBianji.qianyan" />
</textarea>
				</div>
				<p>
					首页展示：
					<s:radio name="zhanshi" list="%{#{'1':'是','0':'否'}}"
						value="%{#session.gdlgBianji.zhanshi}"></s:radio>
				</p>
				<p>
					<input value="确认修改" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
