<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin Page</h1>
	<p>管理员页面</p>
	<a href="${pageContext.request.contextPath}/login">退出登录</a>
	<form action="j_spring_security_logout">
		<input type="submit">
	</form>
</body>
</html>
