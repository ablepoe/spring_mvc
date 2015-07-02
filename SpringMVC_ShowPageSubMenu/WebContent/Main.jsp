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
		<%-- <input type="button" value="${menu.menuId}" id="${menu.menuId}"> --%>
		<div id="${menu.menuId}"></div>
		-
		${menu.menuName}
		**
		<a href="javascript:void(0)" style="font-weight:bold;" 
		onclick="changeSubMenu('${menu.menuId}');">${menu.menuName}</a>
		--
		<c:forEach items="${menuItem.subMenu}" var="subMenuItem">
		${subMenuItem.menuId}
		-
		${subMenuItem.menuName})
		</c:forEach>
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
			console.log(data);
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


function changeSubMenu(menuId)
{
	var orgin_data;
	<c:forEach items="${menuList}" var="menuItem">
		if("${menuItem.menuId}" == menuId){
			console.log("${menuItem.menuId}");
			console.log("${menuItem.menuName}");
			<c:forEach items="${menuItem.subMenu}" var="subMenuItem">
				console.log("${subMenuItem.menuId}");
				console.log("${subMenuItem.menuName}");
				var insert = "<input type='button' id='${subMenuItem.menuName}' value='${subMenuItem.menuId}' />"
				document.getElementById(menuId).innerHTML = document.getElementById(menuId).innerHTML + insert;
				//document.write("<input type='button' name='${subMenuItem.menuId}' id='${subMenuItem.menuId}' value='${subMenuItem.menuId}' />");
			</c:forEach>
		}
	</c:forEach>
	$("#"+menuId).attr("disabled",true);
}
</script>
</body>
</html>