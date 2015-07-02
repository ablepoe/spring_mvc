<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<b>here shows the menu:</b>
<ul>
	<c:forEach items="${menuList}" var="menu">
	<li>
		${menu.menuId}
		-
		${menu.menuName}
	</li>
	</c:forEach>
</ul>
<h3>here shows the user authority</h3>
<input type="text" id="authority" />

<script type="text/javascript">
$(function(){
	var userAuth = new Object();
	$.ajax({
		type : "post",
		contentType : 'application/json;charset=UTF-8',
		url : "${pageContext.request.contextPath}/Main.do",
		data : JSON.stringify(userAuth),
		dataType : "json",
		success : function(data){
			//alert("suc");
			if(data.right){
				$("#authority").attr("disabled",false);
			}else{
				$("#authority").attr("disabled",true);
			}
		},
		error : function(){
			alert("error");
		}
	})
})

</script>
</body>
</html>