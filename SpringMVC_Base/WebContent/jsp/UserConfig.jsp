<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DHC 查询系统</title>
<jsp:include page="Include.jsp"/>
</head>
<body>

<div class="easyui-layout" data-options="footer:'#listFooter',fit:true,border:false" >
	<div data-options="region:'north'" style="height:60px">
	<div id="selectName" class="easyui-panel" title="已分配角色名"  data-options="fit:true,border:false,left:30" >
		<table >
			<tr>
				<td>
					<span>用户姓名:</span>
				</td>
				
				<td>
					<input type="text" id="userName" class="easyui-textbox" data-options="iconCls:'icon-search'" />
				</td>
				
				<td/>
				
				<td>
					<input type="button" onclick="checkUser()" id="search" value="Search" class="easyui-linkbutton" icon="icon-ok" />
				</td>
			</tr>
		</table>
	</div>
	</div>
	<div data-options="region:'east'" style="width:400px;height:200px">
			<div class="easyui-panel" title="已分配角色名"  data-options="fit:true,border:false,left:30" >
			<table id="distRoleList" class="easyui-datagrid" title="">
	        <thead>
	            <tr>
		          	<th data-options="field:'ck'"  checkbox="true"></th>
		            <th data-options="field:'user_role_name'" style="width:90%">已分配的角色名</th>
		            <th data-options="field:'user_role_id',hidden:true" >已分配的角色名ID</th>
		        </tr>
	        </thead>
	 	   </table>
	 	   </div>
	</div>
	<div data-options="region:'west'" style="width:400px;height:200px">
		<div class="easyui-panel" title="可分配角色名"  data-options="fit:true,border:false,left:30">
			<table id="roleData" class="easyui-datagrid" title="">
	        <thead>
	            <tr>
		            <th data-options="field:'ck'" checkbox="true"></th>
		            <th data-options="field:'user_role_name'" style="width:90%">可分配的角色名</th>
		            <th data-options="field:'user_role_id',hidden:true" >已分配的角色名ID</th>
	            </tr>
	        </thead>
	 	   </table>
	 	   </div>
	</div>
	<div data-options="region:'center'" style="width:200px;height:200px">
		 <table width="100%" style="margin : 80px 0 0 0">
		        <tr>
		        	<td style="vertical-align:middle;text-align:center">
		        		<a href="javascript:void(0)" id ="rightMoveButton" href="javascript:void(0)" class="easyui-linkbutton"  onclick="addRole();false;" style="width:80px">></a>
		       	 	</td>
		        </tr> 
		       	<tr>
		        	<td style="vertical-align:middle;text-align:center;margin:20px 0 0 0">
		        		<a href="javascript:void(0)"  id ="rightAllMoveButton" class="easyui-linkbutton"  onclick="addAllRole();false;" style="width:80px">>></a>
		        	</td>
		        </tr> 
		        <tr>
		        	<td style="vertical-align:middle;text-align:center;margin:20px 0 0 0">
		        		<a href="javascript:void(0)" id ="leftMoveButton" class="easyui-linkbutton"  onclick="removeAllRole();false;" style="width:80px"> &lt;&lt; </a>
		        	</td>
		        </tr> 
		        <tr>
		        	<td style="vertical-align:middle;text-align:center;margin:20px 0 0 0">
		        		<a href="javascript:void(0)" id="leftAllMoveButton" class="easyui-linkbutton"  onclick="removeRole();false;" style="width:80px"> &lt; </a>
		        	</td>
		        </tr> 
		 </table>
	</div>
	<div data-options="region:'south'" style="height:70px; ">
		<div class="easyui-panel" title=""  data-options="fit:true,border:false,footer:'#listFooter'" >
			<table width="100%" style = "margin:5px 0 0 0">
				<tr>
				<td style="vertical-align:middle;text-align:center" >
					<a  href="javascript:void(0)" id ="HeightlevelButton"  class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="openRoleWindow();false;" style="width:80px">高级设置</a>
				</td>
				</tr>
			</table>
			</div>
	</div>
</div>

<!-- 编辑页面 -->
<div id="userDetail" class="easyui-window" title="用户功能分配" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true,closed:true,iconCls:'icon-save',footer:'#detailFooter'" style="padding:5px, 5px;">
    <div style="padding:5px 5px;">
        <table id="funcListData" class="easyui-datagrid" width="95%"  data-options="onClickRow:onClickRow, singleSelect:true" >
	       <thead>
            	<tr>
					<th data-options="field:'ck'"  checkbox="true"></th>
					
					<th data-options="field:'func_Id',hidden:true" ></th>
					<th data-options="field:'func_name'" style="width:45%">功能名</th>
           			<th data-options="field:'authority', formatter:authFormatter, editor:{type:'combobox',options:{valueField: 'label',
           																			textField: 'value',
           																			data:[{
																						label: 'W',
																						value: '编辑'
																					},{
																						label: 'R',
																						value: '查询'
																					},{
																						label: '',
																						value: '无'
																					}]
																					} }" style="width:43%" >权限</th>
	           	</tr>
        	</thead>
        </table >
    </div>
    <div style="padding:5px 5px;">
        <a href="javascript:void(0)" id="sureButton" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:80px" onclick="updateGrid();false;">确定</a>
        <a href="javascript:void(0)" id ="edtCancelButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:80px" onclick="closeDtlWin();false;">取消</a>
    </div>
</div>

</body>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/pageInit.do",
		data:"{}",
		dataType:"json",
		success : function(data){

		},
		error : function(){
			showErrorMessage();
		}
	});
	disableBtn();
})

function disableBtn(){
	$('#rightMoveButton').linkbutton('disable');
	$('#rightAllMoveButton').linkbutton('disable');
	$('#leftMoveButton').linkbutton('disable');
	$('#leftAllMoveButton').linkbutton('disable');
	$('#HeightlevelButton').linkbutton('disable');
}

function enableBtn(){
	$('#rightMoveButton').linkbutton('enable');
	$('#rightAllMoveButton').linkbutton('enable');
	$('#leftMoveButton').linkbutton('enable');
	$('#leftAllMoveButton').linkbutton('enable');
	$('#HeightlevelButton').linkbutton('enable');
}

function checkUser(){
	var user = new Object;
	user.userName = $("#userName").val();
	$.ajax({
		type:"post",
		contentType : 'application/json;charset=UTF-8',
		data:JSON.stringify(user),
		dataType:"json",
		url:"${pageContext.request.contextPath}/user/checkUserByName.do",
		success:function(data){
			//console.log(data);
			if(data.status == "SUCCESS"){
				$('#distRoleList').datagrid('loadData', data.data);
				getRoleList(user);
			}else{
				showMessageString("user not exist");
				disableBtn();
				$('#distRoleList').datagrid('loadData', {"total":0,"rows":[]});
				$('#roleData').datagrid('loadData', {"total":0,"rows":[]});
			}
		},
		error:function(data){
			//console.log(data);
			console.error("error exist");
		},
	});
	
}


function getRoleList(user){
	$.ajax({
		type:"post",
		contentType : 'application/json;charset=UTF-8',
		data:JSON.stringify(user),
		dataType:"json",
		url:"${pageContext.request.contextPath}/user/getUserRole.do",
		success:function(data){
			console.log(data);
			if(data.status == "SUCCESS"){
				if(data.data == null){
					$('#roleData').datagrid('loadData', {"total":0,"rows":[]});
				}else{
					$('#roleData').datagrid('loadData', data.data);
					enableBtn();
				}
			}else{
				showMessageString("role not exist");
			}
		},
		error:function(data){
			console.log(data);
			console.error("error exist");
		},
		
	}) 
}

function addRole(){
	var check = $('#roleData').datagrid('getChecked');
	var addRoleList = new Array();
	$.each(check, function(index, items){
		addRoleList.push(items.user_role_id);
   	});
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/user/addUserRole.do",
		data:JSON.stringify(addRoleList),
		dataType:"json",
		contentType:"application/json;charset=UTF-8",
		success:function(data){
			console.log(data);
			if(data.status == "SUCCESS"){
				console.warn("suc");	
			}else{
				console.error("fail");
			}
			checkUser();
		},
		error:function(){
			console.error("error");
		}
	})
}

function addAllRole(){
	$('#roleData').datagrid('checkAll');
	addRole();
}

function removeRole(){
	var check = $('#distRoleList').datagrid('getChecked');
	//console.log(check);
	var removeRoleList = new Array();
	$.each(check, function(index, items){
		removeRoleList.push(items.user_role_id);
   	});
	//console.log(removeRoleList);
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/user/deleteUserRole.do",
		data:JSON.stringify(removeRoleList),
		dataType:"json",
		contentType:"application/json;charset=UTF-8",
		success:function(data){
			console.log(data);
			if(data.status == "SUCCESS"){
				console.warn("suc");	
			}else{
				console.error("fail");
			}
			checkUser();
		},
		error:function(){
			console.error("error");
		}
	})
}

function removeAllRole(){
	$('#distRoleList').datagrid('checkAll');
	removeRole();
}

//***********点击高级设置 打开窗口 ********************
function openRoleWindow(){
	$('#userDetail').window('open');
	$.ajax({
		url:"${pageContext.request.contextPath}/user/getUserFunc.do",
		type:"post",
		data:"",
		dataType:"json",
		contentType:"application/json;charset=UTF-8",
		success:function(data){
			console.log(data);
			$('#funcListData').datagrid('loadData', data.data);
			console.warn("suc");
		},
		error:function(){
			console.error("error");
		}
	})
	
}

var data = [{ "value": "W", "text": "编辑" }, { "value": "R", "text": "查询" }, { "value": "", "text": "无" }];
function authFormatter(row){
	//console.log(row);
	if(row == ""){
		return "无";
	}
	if(row == "W"){
		return "编辑";
	}else if(row == "R"){
		return "查询";
	}
}

var row_id;
function onClickRow(){
	if(row_id != undefined){
		$('#funcListData').datagrid('endEdit',row_id);
	}
	var row = $('#funcListData').datagrid('getSelected');
	var index = $('#funcListData').datagrid('getRowIndex',row);
	row_id = index;
	$('#funcListData').datagrid('beginEdit',index);
}

function updateGrid(){
	if(row_id != undefined){
		$('#funcListData').datagrid('endEdit',row_id);
	}
	var changes = $('#funcListData').datagrid('getChanges','updated');
	//console.log(changes);
	if(!(changes.length == new Array().length) ){
		$.ajax({
			url:"${pageContext.request.contextPath}/user/saveUserFunc.do",
			type:"post",
			data:JSON.stringify(changes),
			dataType:"json",
			contentType:"application/json;charset=UTF-8",
			success:function(data){
				console.log(data);
				if(data.status == "SUCCESS"){
					showMessageString("user role saved!");	
				}else{
					showMessageString("save user role failed!");
				}
			},
			error:function(){
				showMessageString("save user role error!");
			}
		})
	}
	closeDtlWin();
}

function closeDtlWin(){
	row_id = undefined;
	$('#userDetail').window('close');
}
</script>
</html>