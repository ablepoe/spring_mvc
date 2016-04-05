/**
 * 添加单行
 */
function addLine(){
	//定义输入框
	var command_content = "<input type='text' name='command'/>";
	var command_id = "<input type='text' name='description'/>";
	//定义表单
	var data = "<tr><td>" + command_content + "</td><td>" + command_id + "</td></tr>";
	//填充表单
	var additionLine = $("#append").html();
	additionLine += data
	//渲染表单
	$("#append").html(additionLine);
}

/**
 * 提交添加数据
 */
function batchAdd(basePath){
	//获取输入参数数组
	var command_array = getArrayValues("command");
	var description_array = getArrayValues("description");
	var insertDatas = [];
	var obj;
	for (var i = 0; i < command_array.length; i++) {
		obj = new Object();
		obj.command = command_array[i];
		obj.description = description_array[i];
		insertDatas.push(obj);
	}
	//提交到后台进行处理
	addData(basePath,insertDatas);
}

function addData(basePath,insertDatas){
	$.ajax({
		contentType:'application/json;charset=UTF-8',
		url:basePath+"batchAdd",
		type:"post",
		data:JSON.stringify(insertDatas),
		dataType:"json",
		success:function(data){
			console.log(data);
			console.log("suc");
			if(true == data.status){
				renderInsertContent(data);
				alert("添加成功");
				var form = $("#fresh");
				form.submit();
			}else{
				alert("服务器发生异常，请重新尝试");
			}
			
		},
		error:function(data){
			console.log("err");
			console.warn(data);
		}
	})
}

/**
 * 获取输入参数
 * @param target
 * @returns {Array}
 */
function getArrayValues(target){
	var array = [];
	//获取输入参数
	var targets = $("input[name='"+target+"']");
	for (var int = 0; int < targets.length; int++) {
		array.push(targets[int].value);
	}
	return array;
}

/**
 * 渲染页面
 * @param data
 */
function renderInsertContent(data){
	var tbody = "";
	var messageList = data.allMessageList;
	for (var i = 0; i < messageList.length; i++) {
		tbody += "<tr>";
		tbody += "<td>"+messageList[i].id+"</td>";
		tbody += "<td>"+messageList[i].command+"</td>";
		tbody += "<td>"+messageList[i].description+"</td>";
		tbody += "</tr>";
	}
	tbody += "<tr>";
	tbody += "<td colspan='3'>";
	tbody += "<input type='button' onclick='addLine()' value='addLine'/>";
	tbody += "</td>";
	tbody += "</tr>";
	$("#insertExists").html(tbody);
}