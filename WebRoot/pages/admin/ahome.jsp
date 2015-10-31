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
		<table>
			<tr>
				<td align="left" width="20%" height="100%" valign="top">
					<li>
						当前用户：
						<a href="<%=basePath%>/pages/admin/descGly.jsp"
							target="menuiframe"><s:property value="#session.gly.xingming" />
						</a>&nbsp;,&nbsp;
						<a href="glyLogout" style="color: red;">退出</a>
					</li>
					<li>
						<a href="bannerShowall" target="menuiframe">首页-Banner 管理</a>
					</li>
					<li>
						<a href="bjtjShowall" target="menuiframe">首页-编辑推荐 管理</a>
					</li>
					<li>
						<a href="zsgdlgShowall" target="menuiframe">首页-产品展示-高等理工</a>
					</li>
					<li>
						<a href="zsgdwkShowall" target="menuiframe">首页-产品展示-高等文科</a>
					</li>
					<li>
						<a href="zsswnyShowall" target="menuiframe">首页-产品展示-生物农医</a>
					</li>
					<li>
						<a href="zsgzjyShowall" target="menuiframe">首页-产品展示-高职教育</a>
					</li>
					<li>
						<a href="zszzjyShowall" target="menuiframe">首页-产品展示-中职教育</a>
					</li>
					<li>
						<a href="zsjsjyShowall" target="menuiframe">首页-产品展示-教师教育</a>
					</li>
					<li>
						<a href="yqljShowall" target="menuiframe">首页-友情链接</a>
					</li>
					<li>
						<a href="lwjjShowall" target="menuiframe">朗文简介 管理</a>
					</li>
					<li>
						<a href="gdlgShowall?page=1" target="menuiframe">图书-高等理工 管理</a>
					</li>
					<li>
						<a href="gdwkShowall?page=1" target="menuiframe">图书-高等文科 管理</a>
					</li>
					<li>
						<a href="swnyShowall?page=1" target="menuiframe">图书-生物农医 管理</a>
					</li>
					<li>
						<a href="gzjyShowall?page=1" target="menuiframe">图书-高职教育 管理</a>
					</li>
					<li>
						<a href="zzjyShowall?page=1" target="menuiframe">图书-中职教育 管理</a>
					</li>
					<li>
						<a href="jsjyShowall?page=1" target="menuiframe">图书-教师教育 管理</a>
					</li>
					<li>
						<a href="rczpShowall" target="menuiframe">人才招聘 管理</a>
					</li>
					<li>
						<a href="lxwmShowall" target="menuiframe">联系我们 管理</a>
					</li>
					<li>
						<a href="cbtdShowall" target="menuiframe">出版天地-介绍信息 管理</a>
					</li>
					<li>
						<a href="hydtShowall?page=1" target="menuiframe">出版天地-行业动态</a>
					</li>
					<li>
						<a href="yssqShow" target="menuiframe">教学服务-样书赠送</a>
					</li>
					<li>
						<a href="hyyqShowall?page=1" target="menuiframe">教学服务-会议邀请</a>
					</li>
					<li>
						<a href="kjxzShowall?page=1" target="menuiframe">教学服务-课件下载</a>
					</li>
					<li>
						<a href="xzzxShowall?page=1" target="menuiframe">教学服务-下载中心</a>
					</li>
					<li>
						<a href="qyxwShowall?page=1" target="menuiframe">企业资讯-企业新闻</a>
					</li>
					<li>
						<a href="gxxwShowall?page=1" target="menuiframe">企业资讯-高校新闻</a>
					</li>
					<li>
						<a href="glyShowall" target="menuiframe">系统管理员设置</a>
					</li>
				</td>
				<td align="left" width="100%" height="100%" valign="top">
					<iframe frameborder="0" scrolling="auto" name="menuiframe"
						width="115%" height="900"></iframe>
				</td>
			</tr>
		</table>
		<iframe frameborder="0" scrolling="no"
			src="<%=basePath%>/pages/footer.jsp" width="98%" height="10%"></iframe>
	</body>
</html>