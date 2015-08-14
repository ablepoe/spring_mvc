<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<title>Sample</title>
</head>
<body>

<div id="input" >
	<a href="#" class="easyui-linkbutton" onclick="exportData()" id="exportData" data-options="iconCls:'icon-save'">download</a>  
</div>
<form id="downloadFile" action="${pageContext.request.contextPath}/downloadFile.do" method="post" style="">
	<input id="dlFileName" name="dlFileName" type="hidden" value="" />
	<input id="dlSrcFile" name="dlSrcFile" type="hidden" value="" />
</form>

</body>
<script type="text/javascript">
function exportData(){
	console.warn("download");
	var username = "username";
	var password = "password";
	var obj = new Object();
	obj.username = username;
	obj.password = password;
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getExport.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			console.log(data);
			if(data.status == "SUCCESS"){
				//form submit();
				console.log(data.data.dlFileName);
				console.log(data.data.dlSrcFile);
				$('#dlFileName').val(data.data.dlFileName);
                $('#dlSrcFile').val(data.data.dlSrcFile);
                $('#downloadFile').submit();
			}else{
				showMessageObject(data);
			}
		},
		error : function(){
			showErrorMessage();
		}
	});
}
</script>
</html>