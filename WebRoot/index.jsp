<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="jstl-c" prefix="c" %>
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
		<center>
			<h2>
				JSP + JavaBean + Servlet 分页技术实现
			</h2>
			<hr>
			<br>
			<!-- 数据显示列表 -->
			<table width="80%">
				<tr  align="center">
					<td>
						序号
					</td>
					<td>
						学号
					</td>
					<td>
						姓名
					</td>
					<td>
						年龄
					</td>
				</tr>
				<%
					int row = 1;
				 %>
				<c:forEach var="user" items="${users}">
				<tr  align="center">
					<td>
						<%= row++%>
					</td>
					<td>
						<c:out value="${user.stu_num}"></c:out>
					</td>
					<td>
						<c:out value="${user.username}"></c:out>
					</td>
					<td>
						<c:out value="${user.age}"></c:out>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<!-- 分页表单 -->			
			<table cellSpacing=0 cellPadding=0 border="0" width="80%">	
					<tr><td  height="25"></td></tr>		
					<tr>
						<td align="right">
							第<c:out value="${pageBean.currentPage}"></c:out>页/共<c:out value="${pageBean.totalPages}"></c:out>页
							&nbsp;&nbsp;
							每页<c:out value="${pageBean.maxSize}"></c:out>行/共<c:out value="${pageBean.totalRows}"></c:out>行
							&nbsp;&nbsp;
							<c:choose>
							<c:when test="${pageBean.currentPage==1}">				
								首页&nbsp;上页&nbsp;
							</c:when>		
							<c:otherwise>
								<a href="<%=path %>/user?action=page&pageType=first">首页</a>&nbsp;<a href="<%=path %>/user?action=page&pageType=previous">上页</a>&nbsp;
							</c:otherwise>	
							</c:choose>		
							<c:choose>				
							<c:when test="${pageBean.currentPage==pageBean.totalPages}">
								下页&nbsp;尾页&nbsp;
							</c:when>	
							<c:otherwise>
								<a href="<%=path %>/user?action=page&pageType=next">下页</a>&nbsp;<a href="<%=path %>/user?action=page&pageType=last">尾页</a>&nbsp;
							</c:otherwise>	
							</c:choose>									
							&nbsp;&nbsp;&nbsp;&nbsp;	
						</td>
					</tr>
			</table>			
		</center>
	</body>
</html>
