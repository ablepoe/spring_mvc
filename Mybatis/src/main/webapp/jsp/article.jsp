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
article page
<input type="button" value="article_!_!" onClick="index()"/>
<form action="index.do">
index page
<input type="submit">
</form>

<c:forEach items="${userArticle}" var="item">  
        ${item.id }--${item.title }--${item.content }<br />
</c:forEach>

</body>
</html>