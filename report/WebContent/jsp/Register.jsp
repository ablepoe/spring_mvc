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
	
	var form = $('#register');
	if(form.form('validate') == true) {
		var obj = new Object();
		obj.userName = $('#userName').val();
		obj.password = $('#password').val();
		obj.code = $('#kaptcha').val();
        showProgress();
		jQuery.ajax( {
            type : 'POST',     
            contentType : 'application/json;charset=UTF-8',    
            url : ' ${pageContext.request.contextPath}/user/registerUser.do',     
            data : JSON.stringify(obj),     
            dataType : 'json',
            success : function(data) {     
           	   if(data.status == "SUCCESS") {
           			registerFormSubmit();
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
	$('#register').form('clear');
}

function registerFormSubmit() {
    //$('#login').form('submit');为Ajax异步提交
	$('#register').submit();
}
function chgImg(){
	 $("#kaptchaImage").hide().attr('src', '${pageContext.request.contextPath}/captcha-image.do?' + Math.floor(Math.random()*100) ).fadeIn(); 
}
function index(){
	$('#index').submit();
}
</script>
</head>
<body >
<div id="content" >
	<table id="loginTable" style="margin:auto;width:30%"> 
		<tr>
			<td style="vertical-align:middle">
				<div class="easyui-panel" title="DHC 报表系统" style="width:100%;">
					<div style="padding:1% 5% 5% 15%">
					    <form id="register" action="${pageContext.request.contextPath}/Register.do" method="post" style="padding:1% 5% 5% 15%;">
				               <input id="errorMsg" name="errorMsg" type="hidden" value="${errorMsg}" />
						    	<p><span class="span_must">注册:用户名</span>&nbsp;<input id="userName" class="easyui-textbox" type="text" name="userName" data-options="required:true,iconCls:'icon-man'" style="width:50%" value=""></p>
				              	<p><span class="span_must">注册:密码</span>&nbsp;&nbsp;&nbsp;&nbsp;<input id="password" class="easyui-textbox" type="password" name="password" data-options="required:true,iconCls:'icon-lock'" style="width:50%" value=""></p>
				              	<p><span class="span_must">注册:验证码</span>&nbsp;<input name="kaptcha" id="kaptcha" type="text" id="kaptcha" maxlength="4" class="easyui-textbox" data-options="required:true" style="width:50%" value=""></p>
								<img src="${pageContext.request.contextPath}/captcha-image.do" width="75" height="20" id="kaptchaImage"  style="margin-bottom: -3px"/>
								<a href="#" onClick="chgImg()">看不清,换一张</a>
						    <div style="text-align:center;width:100%">
								<a id="btnLogin" href="javascript:void(0)" style="width:40%" class="easyui-linkbutton" icon="icon-ok" onclick="submitForm();">注册</a>
								<a id="btnClear" href="javascript:void(0)" style="width:40%" class="easyui-linkbutton" icon="icon-reload" onclick="clearForm();">重置</a>
					        </div>
					        <div style="text-align:center;width:100%">
								<a id="btnBack" href="javascript:void(0)" style="width:50%" class="easyui-linkbutton" icon="icon-redo" onclick="index();">回到首页</a>
					        </div>
					    </form>
					</div>
				</div>
			</td>
		</tr>
	</table>			
</div>
<div>
	<form id="index" action="IndexPage.do" method="post">
		
	</form>
</div>
</body>
</html>