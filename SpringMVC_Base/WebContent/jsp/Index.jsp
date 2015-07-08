<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DHC 查询系统</title>
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
});

function submitForm() {
	
	var form = $('#login');
	if(form.form('validate') == true) {
		var obj = new Object();
		obj.userName = $('#userName').val();
		obj.password = $('#password').val();
        showProgress();
		jQuery.ajax( {
            type : 'POST',     
            contentType : 'application/json;charset=UTF-8',    
            url : ' ${pageContext.request.contextPath}/user/checkUser.do',     
            data : JSON.stringify(obj),     
            dataType : 'json',
            success : function(data) {     
            	console.log(data);
               if (data) {
            	   console.log(data.status);
            	   if(data.status == "SUCCESS") {
                        loginFormSubmit();
            	   } else {
                       closeProgress();
                   	   showMessageObject(data);
            	   }
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
</script>
</head>
<body >
<div id="content" >
	<table id="loginTable" style="margin:auto;"> 
		<tr>
			<td style="vertical-align:middle">
				<div class="easyui-panel" title="DHC 查询系统" style="width:400px;">
					<div style="padding:10px 60px 20px 60px">
					    <form id="login" action="Login.do" method="post" style="padding:10px 20px 10px 40px;">
				               <input id="errorMsg" name="errorMsg" type="hidden" value="${errorMsg}" />
						    <p><span class="span_must">用户名</span>&nbsp;<input id="userName" class="easyui-textbox" type="text" name="userName" data-options="required:true,iconCls:'icon-man'" style="width:150px" value=""></p>
				               <p><span class="span_must">密码</span>&nbsp;&nbsp;&nbsp;&nbsp;<input id="password" class="easyui-textbox" type="password" name="password" data-options="required:true,iconCls:'icon-lock'" style="width:150px" value=""></p>
						    <div style="padding:5px;text-align:center;">
								<a id="btnLogin" href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok" onclick="submitForm();">登陆</a>
								<a id="btnClear" href="javascript:void(0)" class="easyui-linkbutton" icon="icon-reload" onclick="clearForm();">重置</a>
					        </div>
					    </form>
					</div>
				</div>
			</td>
		</tr>
	</table>			
</div>
</body>
</html>