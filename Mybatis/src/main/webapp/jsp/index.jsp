<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" >
</script>
</head>
<body>
user page
<form action="findUser.do">
<input type="button" value="user_!_!" />
<input type="submit">
</form>

<form action="findArticle.do">
article page
<input type="button" value="article_!_!" />
<input type="submit">
</form>

<form action="ajaxArticle.do">
article data
<input type="button" value="article_!_!" />
<input type="submit">
</form>

</body>
</html>