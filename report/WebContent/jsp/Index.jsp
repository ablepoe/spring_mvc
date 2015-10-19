<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DHC 报表系统</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/logo.jpg">
<jsp:include page="Include.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	if ($('#errorMsg').val() != undefined && $('#errorMsg').val() != '') {
		showMessageString($('#errorMsg').val());
	}
    //设置用户ID焦点
    setTextBoxFocus('userName');
    
    $('#userName').textbox('textbox').keypress(function (e) {
        if (e.keyCode == 13) {
        	setTextBoxFocus('password');
            $('#password').textbox('textbox').keypress(function (e) {
                if (e.keyCode == 13) {
                	submitForm();
                }
            });

        }
    });
    
    $('#password').textbox('textbox').keypress(function (e) {
        if (e.keyCode == 13) {
        	submitForm();
        }
    });
    
    $('#kaptcha').textbox('textbox').keypress(function (e) {
        if (e.keyCode == 13) {
        	submitForm();
        }
    });
});

function submitForm() {
	
	var form = $('#login');
	if(form.form('validate') == true) {
		var obj = new Object();
		obj.userName = $('#userName').val();
		obj.password = $('#password').val();
		obj.code = $('#kaptcha').val();
        showProgress();
		jQuery.ajax( {
            type : 'POST',     
            contentType : 'application/json;charset=UTF-8',    
            url : ' ${pageContext.request.contextPath}/user/checkUser.do',     
            data : JSON.stringify(obj),     
            dataType : 'json',
            success : function(data) {     
           	   if(data.status == "SUCCESS") {
               		loginFormSubmit();
           	   } else {
		       		closeProgress();
		       		showMessageObject(data);
		       		chgImg();
           	   }
            },     
            error : function(data) {
                closeProgress();
            	showErrorMessage();
            }     
        });
	}
}

function clearForm() {
	$('#login').form('clear');
}

function loginFormSubmit() {
    //$('#login').form('submit');为Ajax异步提交
	$('#login').submit();
}

function chgImg(){
	 $("#kaptchaImage").hide().attr('src', '${pageContext.request.contextPath}/captcha-image.do?' + Math.floor(Math.random()*100) ).fadeIn(); 
}

function register(){
	$('#register').submit();
}
</script>
</head>
<body >
<div id="content" >
	<table id="loginTable" style="margin:auto;width:30%"> 
		<tr>
			<td style="vertical-align:middle;">
				<div class="easyui-panel" title="DHC 报表系统" style="width:100%;">
					<div style="padding:1% 5% 5% 15%">
					    <form id="login" action="Login.do" method="post" style="padding:1% 5% 5% 15%;">
				               <input id="errorMsg" name="errorMsg" type="hidden" value="${errorMsg}" />
						    	<p><span class="span_must">用户名</span>&nbsp;<input id="userName" class="easyui-textbox" type="text" name="userName" data-options="required:true,iconCls:'icon-man'" style="width:50%" value=""></p>
				              	<p><span class="span_must">密码</span>&nbsp;&nbsp;&nbsp;&nbsp;<input id="password" class="easyui-textbox" type="password" name="password" data-options="required:true,iconCls:'icon-lock'" style="width:50%" value=""></p>
				              	<p><span class="span_must">验证码</span>&nbsp;<input name="kaptcha" id="kaptcha" type="text" id="kaptcha" maxlength="4" class="easyui-textbox" data-options="required:true" style="width:50%" value=""></p>
								<img src="${pageContext.request.contextPath}/captcha-image.do" width="75" height="20" id="kaptchaImage"  style="margin-bottom: -3px"/>
								<a href="#" onClick="chgImg()">看不清,换一张</a>
						    <div style="text-align:center;width:100%">
								<a id="btnLogin" href="javascript:void(0)" style="width:30%" class="easyui-linkbutton" icon="icon-ok" onclick="submitForm();">登陆</a>
								<a id="btnClear" href="javascript:void(0)" style="width:30%" class="easyui-linkbutton" icon="icon-reload" onclick="clearForm();">重置</a>
								<a id="btnLogin" href="javascript:void(0)" style="width:30%" class="easyui-linkbutton" icon="icon-redo" onclick="register();">注册</a>
					        </div>
					        <div style="text-align:center;width:100%">
					        <p><strong style="color: red">请使用非IE内核的浏览器进行操作!推荐Chrome或Firefox</strong></p>
					        </div>
					    </form>
					</div>
				</div>
			</td>
		</tr>
	</table>			
</div>
<div>
	<form id="register" action="RegisterPage.do" method="post">
	</form>
</div>
</body>
</html>