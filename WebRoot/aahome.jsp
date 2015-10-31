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

		<title>朗文图书-后台管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/demo.css">
		<script type="text/javascript" src="easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>

	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false"
			style="height: 60px; background: #B3DFDA; padding: 10px; font-size: 24px; font-weight: bold; font-family: 微软雅黑;"
			align="center">
			朗文图书发行有限公司-后台管理系统
		</div>
		<div data-options="region:'west',split:true,title:'所有菜单'"
			style="width: 200px; padding: 10px;">
			<li>
				当前用户：
				<a href="<%=basePath%>/pages/admin/descGly.jsp" target="menuiframe"><s:property
						value="#session.gly.xingming" /> </a>&nbsp;,&nbsp;
				<a href="glyLogout" style="color: red;">退出</a>
			</li>
			<div style="margin: 10px 0;"></div>
			<ul class="easyui-tree">
				<li data-options="state:'closed'">
					<span>首页管理</span>
					<ul>
						<li>
							<a href="bannerShowall" target="menuiframe">Banner</a>
						</li>
						<li>
							<a href="bjtjShowall" target="menuiframe">编辑推荐</a>
						</li>
						<li>
							<a href="yqljShowall" target="menuiframe">友情链接</a>
						</li>
						<li data-options="state:'closed'">
							<span>产品展示</span>
							<ul>
								<li>
									<a href="zsgdlgShowall" target="menuiframe">高等理工</a>
								</li>
								<li>
									<a href="zsgdwkShowall" target="menuiframe">高等文科</a>
								</li>
								<li>
									<a href="zsswnyShowall" target="menuiframe">生物农医</a>
								</li>
								<li>
									<a href="zsgzjyShowall" target="menuiframe">高职教育</a>
								</li>
								<li>
									<a href="zszzjyShowall" target="menuiframe">中职教育</a>
								</li>
								<li>
									<a href="zsjsjyShowall" target="menuiframe">教师教育</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li data-options="state:'closed'">
					<span>产品中心管理</span>
					<ul>
						<li>
							<a href="gdlgShowall?page=1" target="menuiframe">高等理工</a>
						</li>
						<li>
							<a href="gdwkShowall?page=1" target="menuiframe">高等文科</a>
						</li>
						<li>
							<a href="swnyShowall?page=1" target="menuiframe">生物农医</a>
						</li>
						<li>
							<a href="gzjyShowall?page=1" target="menuiframe">高职教育</a>
						</li>
						<li>
							<a href="zzjyShowall?page=1" target="menuiframe">中职教育</a>
						</li>
						<li>
							<a href="jsjyShowall?page=1" target="menuiframe">教师教育</a>
						</li>
					</ul>
				</li>
				
				<li data-options="state:'closed'">
					<span>教学服务管理</span>
					<ul>
						<li>
							<a href="jxfwjsShow" target="menuiframe">整体介绍</a>
						</li>
						<li>
							<a href="yssqShow" target="menuiframe">样书赠送</a>
						</li>
						<li>
							<a href="hyyqShowall?page=1" target="menuiframe">会议邀请</a>
						</li>
						<li>
							<a href="kjxzShowall?page=1" target="menuiframe">课件下载</a>
						</li>
						<li>
							<a href="xzzxShowall?page=1" target="menuiframe">下载中心</a>
						</li>
					</ul>
				</li>
				<li data-options="state:'closed'">
					<span>出版天地管理</span>
					<ul>
						<li>
							<a href="cbtdShowall" target="menuiframe">介绍信息</a>
						</li>
						<li>
							<a href="hydtShowall?page=1" target="menuiframe">行业动态</a>
						</li>
					</ul>
				</li>
				<li data-options="state:'closed'">
					<span>企业资讯管理</span>
					<ul>
						<li>
							<a href="qyxwShowall?page=1" target="menuiframe">企业新闻</a>
						</li>
						<li>
							<a href="gxxwShowall?page=1" target="menuiframe">高校新闻</a>
						</li>
					</ul>
				</li>
				<li data-options="state:'closed'">
					<span>公司简介管理</span>
					<ul>
						<li>
							<a href="lwjjShowall" target="menuiframe">公司简介</a>
						</li>
					</ul>
				</li>
				<li data-options="state:'closed'">
					<span>人才招聘管理</span>
					<ul>
						<li>
							<a href="rczpShowall" target="menuiframe">人才招聘</a>
						</li>
					</ul>
				</li>
				<li data-options="state:'closed'">
					<span>联系我们管理</span>
					<ul>
						<li>
							<a href="lxwmShowall" target="menuiframe">联系我们</a>
						</li>
					</ul>
				</li>
				<li data-options="state:'closed'">
					<span>系统管理员</span>
					<ul>
						<li>
							<a href="glyShowall" target="menuiframe">设置管理员</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<div data-options="region:'center',title:''">
			<iframe frameborder="0" scrolling="auto" name="menuiframe"
				style="width: 100%; height: 100%"></iframe>
		</div>
	</body>
</html>
