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

		<title>My JSP 'zzjyBianji.jsp' starting page</title>

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
		<form action="zzjyUpdate" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>
					编辑图书信息
				</legend>
				<s:hidden name="id" value="%{#session.zzjyBianji.id}"></s:hidden>
				<s:hidden name="typelimit" value="1"></s:hidden>
				<p>
					<s:select
						list="#{'德育与教学文件':'德育与教学文件','素质教育':'素质教育','文化课':'文化课','计算机类':'计算机类','电子电气通信类':'电子电气通信类','机械机电类':'机械机电类','交通类':'交通类','建筑类':'建筑类','化工类':'化工类','旅游航空类':'旅游航空类','烹饪食品类':'烹饪食品类','财经类':'财经类','商贸类':'商贸类','文秘公关类':'文秘公关类','幼儿教育类':'幼儿教育类','服装类':'服装类','艺术与动漫类':'艺术与动漫类','服务类':'服务类','医药卫生类':'医药卫生类','司法服务类':'司法服务类','农林类':'农林类','体育专项类':'体育专项类','其它专业类':'其它专业类'}"
						name="fenlei" label="所属分类" value="%{#session.zzjyBianji.fenlei}"></s:select>
					&nbsp;&nbsp;&nbsp;
					<s:select
						list="#{'无':'无','德育与教学文件':'德育与教学文件','素质教育':'素质教育','文化课':'文化课','计算机类':'计算机类','电子电气通信类':'电子电气通信类','机械机电类':'机械机电类','交通类':'交通类','建筑类':'建筑类','化工类':'化工类','旅游航空类':'旅游航空类','烹饪食品类':'烹饪食品类','财经类':'财经类','商贸类':'商贸类','文秘公关类':'文秘公关类','幼儿教育类':'幼儿教育类','服装类':'服装类','艺术与动漫类':'艺术与动漫类','服务类':'服务类','医药卫生类':'医药卫生类','司法服务类':'司法服务类','农林类':'农林类','体育专项类':'体育专项类','其它专业类':'其它专业类'}"
						name="fenlei2" label="添加分类" value="%{#session.zzjyBianji.fenlei2}"></s:select>
				</p>
				<p>
					图片1:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/zzjy/<s:property value="%{#session.zzjyBianji.tupian1}"/>'>
					图片2:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/zzjy/<s:property value="%{#session.zzjyBianji.tupian2}"/>'>
					图片3:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/zzjy/<s:property value="%{#session.zzjyBianji.tupian3}"/>'>
					图片4:
					<img width="90" height="120" alt="暂无图书照片"
						src='bookPic/zzjy/<s:property value="%{#session.zzjyBianji.tupian4}"/>'>
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
						value="%{#session.zzjyBianji.shuming}"></s:textfield>
				</p>
				<p>
					<s:textfield name="zuozhe" value="%{#session.zzjyBianji.zuozhe}"
						label="作者" cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshe"
						value="%{#session.zzjyBianji.chubanshe}" label="出版社"
						cssStyle="width:225px;"></s:textfield>
				</p>
				<p>
					<s:textfield name="chubanshijian"
						value="%{#session.zzjyBianji.chubanshijian}" label="出版时间"></s:textfield>
				</p>
				<p>
					<s:textfield name="isbn" label="ISBN" cssStyle="width:225px;"
						value="%{#session.zzjyBianji.isbn}"></s:textfield>
				</p>
				<p>
					<s:textfield name="banci" label="版次" value="1"
						value="%{#session.zzjyBianji.banci}"></s:textfield>
				</p>
				<p>
					<s:textfield name="baozhuang" label="包装"
						value="%{#session.zzjyBianji.baozhuang}"></s:textfield>
				</p>
				<p>
					<s:textfield name="kaiben" label="开本"
						value="%{#session.zzjyBianji.kaiben}"></s:textfield>
				</p>
				<p>
					<s:textfield name="yeshu" label="页数"
						value="%{#session.zzjyBianji.yeshu}"></s:textfield>
				</p>
				<p>
					<s:textfield name="dingjia" label="定价"
						value="%{#session.zzjyBianji.dingjia}"></s:textfield>
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
							<s:property value="#session.zzjyBianji.neirongjianjie" />
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
						<s:property value="#session.zzjyBianji.zuozhejianjie" />
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
						<s:property value="#session.zzjyBianji.mulu" />
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
							<s:property value="#session.zzjyBianji.jingcaishuzhai" />
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
						<s:property value="#session.zzjyBianji.qianyan" />
</textarea>
				</div>
				<p>
					首页展示：
					<s:radio name="zhanshi" list="%{#{'1':'是','0':'否'}}"
						value="%{#session.zzjyBianji.zhanshi}"></s:radio>
				</p>
				<p>
					<input value="确认修改" type="submit">
				</p>
			</fieldset>
		</form>
	</body>
</html>
