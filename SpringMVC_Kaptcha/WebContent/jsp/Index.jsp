<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<div class="chknumber">
	<label>验证码：        
	<input name="kaptcha" type="text" id="kaptcha" maxlength="4" class="chknumber_input" />             
	</label>
	<img src="${pageContext.request.contextPath}/captcha-image.do" width="55" height="20" id="kaptchaImage"  style="margin-bottom: -3px"/>
	<a href="#" onClick="chgImg()">chgImg</a>
	<input type="button" value="submit" onClick="checkCode()" />
</div>
</body>
<script type="text/javascript">    
 $(function(){
	chgImg();	 
 })
 
 function chgImg(){
	 $("#kaptchaImage").hide().attr('src', '${pageContext.request.contextPath}/captcha-image.do?' + Math.floor(Math.random()*100) ).fadeIn(); 
 }
 
 function checkCode(){
	 var code = $("#kaptcha").val();
	 $.ajax({
		 type:"post",
		 url:"${pageContext.request.contextPath}/login.do?",
		 data:{"input_code":code},
		 dataType:"json",
		 success:function(data){
			console.log(data);
			if(data.status){
				alert("suc!");
			}else{
				alert("fail!");
				chgImg();
			}
		 },
		 error:function(data){
			 alert("error");
		 }
	 })
 }
 
 function checkCode2(){
	 
 }
</script> 
</html>