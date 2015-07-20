<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<input type="button" value="!_!" onClick="article()"/>
</body>

<script type="text/javascript">

function article(){
	$.ajax({
		type:"post",
		//contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/findArticle.do",
		data:"",
		dataType:"json",
		success : function(data){
			console.log(data);
		},
		error : function(){
			showErrorMessage();
		}
	});
}

</script>
</html>