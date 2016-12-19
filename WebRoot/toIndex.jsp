<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="jstl-c" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>JSP + JavaBean + Servlet 分页技术实现</title>
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
		<!-- 这个页面的作用是使得第一次进入index.jsp页面可以查询出数据。
  	如果不用这个方法也可以。只是需要提供一个jsp页面，有个连接，比如”查询“
  	然后跳转到index.jsp页面。效果是一样的。
   -->
		<iframe src="<%=path%>/user?action=initial&pageType=first"
			width="100%" height="100%" scrolling="yes" frameborder="0"></iframe>
	</body>
</html>
