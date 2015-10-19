<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DHC 报表系统</title>
<jsp:include page="Include.jsp"/>
</head>
<body>

<div class="easyui-layout" data-options="footer:'#listFooter',fit:true,border:false" >
	<div data-options="region:'north'" style="width:100%;height:20%">
	<div id="selectName" class="easyui-panel" title="已分配角色名"  data-options="fit:true,border:false" >
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
	<div data-options="region:'east'" style="width:35%;height:15%">
			<div class="easyui-panel" title="已分配角色名"  data-options="fit:true,border:false" >
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
	<div data-options="region:'west'" style="width:35%;height:15%">
		<div class="easyui-panel" title="可分配角色名"  data-options="fit:true,border:false">
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
	<div data-options="region:'center'" style="width:15%;height:15%">
		 <table width="100%" style="margin : 5% 0 0 0">
		        <tr>
		        	<td style="vertical-align:middle;text-align:center">
		        		<a href="javascript:void(0)" id ="rightMoveButton" href="javascript:void(0)" class="easyui-linkbutton"  onclick="addRole();false;" style="width:15%">></a>
		       	 	</td>
		        </tr> 
		       	<tr>
		        	<td style="vertical-align:middle;text-align:center;margin:2% 0 0 0">
		        		<a href="javascript:void(0)"  id ="rightAllMoveButton" class="easyui-linkbutton"  onclick="addAllRole();false;" style="width:15%">>></a>
		        	</td>
		        </tr> 
		        <tr>
		        	<td style="vertical-align:middle;text-align:center;margin:2% 0 0 0">
		        		<a href="javascript:void(0)" id ="leftMoveButton" class="easyui-linkbutton"  onclick="removeAllRole();false;" style="width:15%"> &lt;&lt; </a>
		        	</td>
		        </tr> 
		        <tr>
		        	<td style="vertical-align:middle;text-align:center;margin:2% 0 0 0">
		        		<a href="javascript:void(0)" id="leftAllMoveButton" class="easyui-linkbutton"  onclick="removeRole();false;" style="width:15%"> &lt; </a>
		        	</td>
		        </tr> 
		 </table>
	</div>
	<div data-options="region:'south'" style="width:100%;height:20%">
		<div class="easyui-panel" title=""  data-options="fit:true,border:false,footer:'#listFooter'" >
			<table width="100%" style = "margin:1% 0 0 0">
				<tr>
				<td style="vertical-align:middle;text-align:center" >
					<a  href="javascript:void(0)" id ="HeightlevelButton"  class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="openRoleWindow();false;" style="width:15%">高级设置</a>
				</td>
				</tr>
			</table>
			</div>
	</div>
</div>

<!-- 编辑页面 -->
<div id="userDetail" class="easyui-window" title="用户功能分配" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true,closed:true,iconCls:'icon-save',top:($(window).height()*0.1),left:($(window).width()*0.1),width:'70%',height:'80%'" style="padding:1%, 1%;">
    <div style="padding:1% 1%;width:80%;height:10%">
        <table id="funcListData" class="easyui-datagrid"  width="100%" data-options="onClickRow:onClickRow, singleSelect:true" >
	       <thead>
            	<tr>
					<!-- <th data-options="field:'ck'"  checkbox="true"></th> -->
					
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
        <div style="padding:1% 1%;">
        	<a href="javascript:void(0)" id="sureButton" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="width:15%" onclick="updateGrid();false;">确定</a>
        	<a href="javascript:void(0)" id ="edtCancelButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="width:15%" onclick="closeDtlWin();false;">取消</a>
    	</div>
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
			showMessageString("Error occured!");
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
			if(data.status == "SUCCESS"){
				if(data.data == null){
					$('#roleData').datagrid('loadData', {"total":0,"rows":[]});
				}else{
					$('#roleData').datagrid('loadData', data.data);
				}
				enableBtn();
			}else{
				showMessageString("role not exist");
			}
		},
		error:function(data){
			showMessageString("Error occured!");
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
			if(data.status == "SUCCESS"){
				showMessageString("用户角色添加成功");
			}else{
				showMessageString("用户角色添加失败");
			}
			checkUser();
		},
		error:function(){
			showMessageString("Error occured!");
		}
	})
}

function addAllRole(){
	$('#roleData').datagrid('checkAll');
	addRole();
}

function removeRole(){
	var check = $('#distRoleList').datagrid('getChecked');
	var removeRoleList = new Array();
	$.each(check, function(index, items){
		removeRoleList.push(items.user_role_id);
   	});
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/user/deleteUserRole.do",
		data:JSON.stringify(removeRoleList),
		dataType:"json",
		contentType:"application/json;charset=UTF-8",
		success:function(data){
			if(data.status == "SUCCESS"){
				showMessageString("用户角色移除成功");
			}else{
				showMessageString("用户角色移除失败");
			}
			checkUser();
		},
		error:function(){
			showMessageString("Error occured!");
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
			$('#funcListData').datagrid('loadData', data.data);
		},
		error:function(){
			showMessageString("Error occured!");
		}
	})
	
}

var data = [{ "value": "W", "text": "编辑" }, { "value": "R", "text": "查询" }, { "value": "", "text": "无" }];
function authFormatter(row){
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
	if(!(changes.length == new Array().length) ){
		$.ajax({
			url:"${pageContext.request.contextPath}/user/saveUserFunc.do",
			type:"post",
			data:JSON.stringify(changes),
			dataType:"json",
			contentType:"application/json;charset=UTF-8",
			success:function(data){
				if(data.status == "SUCCESS"){
					showMessageString("用户自定义权限添加/删除成功!");	
				}else{
					showMessageString("用户自定义权限添加/删除失败!");
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