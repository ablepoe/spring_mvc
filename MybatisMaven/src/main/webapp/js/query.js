function search(basePath){
	console.log("search");
	var command = $("#command").val();
	var description = $("#description").val();
	var obj = new Object();
	obj.command = command;
	obj.description = description;
	$.ajax({
		contentType:'application/json;charset=UTF-8',
		type:"post",
		url:basePath+"search",
		data:JSON.stringify(obj),
		dataType:"json",
		success:function(data){
			console.log("success");
			$("#detail").html(render(data,basePath));
		},
		error:function(data){
			console.log("err");
			console.warn(data);
		}
	})
}

function render(data,basePath){
	var tbody = "";
	var messageList = data.messageList;
	for (var i = 0; i < messageList.length; i++) {
		tbody += "<tr>";
		tbody += "<td><input type='checkbox'/></td>";
		tbody += "<td>"+messageList[i].id+"</td>";
		tbody += "<td>"+messageList[i].command+"</td>";
		tbody += "<td>"+messageList[i].description+"</td>";
		tbody += "<td><a href='#'>修改</a>&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick=removeOne('"+basePath+"')>删除</a></td>";
		tbody += "</tr>";
	}
	return tbody;
}

function removeOne(basePath){
	console.log(basePath);
}