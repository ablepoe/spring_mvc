/**
 * 获取所有数据
 * @param basePath
 */
function search(basePath){
	console.log("search");
	var command = $("#command").val();
	var description = $("#description").val();
	var obj = new Object();
	obj.command = trim(command);
	obj.description = trim(description);
	$.ajax({
		contentType:'application/json;charset=UTF-8',
		type:"post",
		url:basePath+"search",
		data:JSON.stringify(obj),
		dataType:"json",
		success:function(data){
			console.log(data);
			console.log("success");
			$("#detail").html(render(data,basePath));
			renderPagination(data);
			renderInsertContent(data);
		},
		error:function(data){
			console.log("err");
			console.warn(data);
		}
	})
}

function renderPagination(data){
	$("#totalCounts").html(data.pagination.totalCounts);
	$("#currentPage").html(data.pagination.currentPage);
	$("#totalPages").html(data.pagination.totalPages);
}

/**
 * 在数据发生变化之后，重新渲染页面
 * @param data
 * @param basePath
 * @returns {String}
 */
function render(data,basePath){
	var tbody = "";
	var messageList = data.messageList;
	for (var i = 0; i < messageList.length; i++) {
		tbody += "<tr>";
		tbody += "<td><input type='checkbox'/></td>";
		tbody += "<td>"+messageList[i].id+"</td>";
		tbody += "<td>"+messageList[i].command+"</td>";
		tbody += "<td>"+messageList[i].description+"</td>";
		tbody += "<td><a href='javascript:void(0)' onclick=update('"+messageList[i].id+"','"+messageList[i].command+"','"+messageList[i].description+"')>修改</a>&nbsp;&nbsp;&nbsp;"
		tbody += "<a href='javascript:void(0)' onclick=removeOneCommand('"+basePath+"','"+messageList[i].id+"')>删除</a></td>";
		tbody += "</tr>";
	}
	return tbody;
}

/**
 * 开启新增页面
 */
function adds(){
	showBg("insertBg","insertBox");
}

function showBg(bg,box){
	$("#"+bg+"").css({
        display: "block", height: $(document).height()
    });
    var $box = $("."+box+"");
    $box.css({
        //设置弹出层距离左边的位置
        left: ($("body").width() - $box.width()) / 2 - 20 + "px",
        //设置弹出层距离上面的位置
        top: ($(window).height() - $box.height()) / 2 + $(window).scrollTop() + "px",
        display: "block"
    });
}

/**
 * 关闭弹出框
 */
function closeBgbox(){
	$("#insertBg,.insertBox").css("display", "none");
	$("#updateBg,.updateBox").css("display", "none");
}

/**
 * 删除单条记录
 * @param basePath
 * @param id
 */
function removeOneCommand(basePath,id){
	$.ajax({
		type:"post",
		url:basePath+"removeOneCommand",
		data:{"id":id},
		dataType:"json",
		success:function(data){
			console.log(data);
			console.log("suc");
			if(true == data.status){
				alert("移除数据成功");
				search(basePath);
			}else{
				alert("服务器发生错误，请稍后再试")
			}
		},
		error:function(data){
			console.log(data);
			console.log("err");
		}
	})
}

/**
 * 全选&反选
 */
function toggleAll(){
	var allSelected = $("#all").attr("checked");
	if("checked" == allSelected){
		// 全选
	    $("[name=checks]:checkbox").attr("checked", true);
	}else{
		// 全反选
	    $("[name=checks]:checkbox").removeAttr("checked");
	}
}

/**
 * 删除所有选中的记录
 */
function removeSelected(basePath){
	var selected = [];
	var selects = $("[name=checks]:checkbox");
	for (var i = 0; i < selects.length; i++) {
		if(selects[i].checked == true){
			selected.push(selects[i].value);
		}
	}
	console.log(selected);
	$.ajax({
		contentType:"application/json;charset=UTF-8",
		url:basePath+"removeSelected",
		type:"post",
		data:JSON.stringify(selected),
		dataType:"json",
		success:function(data){
			console.log(data);
			console.log("suc");
			if(true == data.status){
				alert("删除成功");
				search(basePath);
			}else{
				alert("服务器发生错误，请稍后再试");
			}
			
		},
		error:function(data){
			console.log(data);
			console.log("err");
		}
	})
}

/**
 * 开启修改框
 * @param id
 * @param command
 * @param description
 */
function update(id,command,description){
	showBg("updateBg","updateBox");
	$("#beforeChangeId").val(id);
	$("#beforeChangeCommand").val(command);
	$("#beforeChangeDescription").val(description);
	$("#targetCommand").val("");
	$("#targetDescription").val("");
}

/**
 * 提交数据更新
 * @param basePath
 */
function updateOneCommand(basePath){
	var params = dealUpdateParams();
	$.ajax({
		contentType:"application/json;charset=UTF-8",
		type:"post",
		url:basePath+"updateOneCommand",
		data:JSON.stringify(params),
		dataType:"json",
		success:function(data){
			console.log(data);
			console.log("suc");
			if(true == data.status){
				alert("更新成功");
				closeBgbox();
				search(basePath);
			}else{
				alert("服务器发生错误，请稍后再试")
			}
		},
		error:function(data){
			console.log(data);
			console.log("err");
		}
	})
}

/**
 * 获取输入参数
 * @returns {Array}
 */
function dealUpdateParams(){
	var id = $("#beforeChangeId").val();
	var beforeCommand = $("#beforeChangeCommand").val();
	var beforeDescription = $("#beforeChangeDescription").val();
	var targetCommand = $("#targetCommand").val();
	var targetDescription = $("#targetDescription").val();
	var params = []
	var obj = new Object();
	obj.id = id;
	obj.command = beforeCommand;
	obj.description = beforeDescription;
	params.push(obj);
	obj = new Object();
	obj.id = id;
	obj.command = targetCommand;
	obj.description = targetDescription;
	params.push(obj);
	return params;
}

/**
 * pagination page
 * @param basePath
 * @param pageNum
 */
function jumpPage(basePath,pageNum){
	var command = $("#command").val();
	var description = $("#description").val();
	var innerObj = new Object();
	innerObj.command = trim(command);
	innerObj.description = trim(description);
	var obj = new Object();
	obj.command = innerObj;
	obj.pageNum = pageNum;
	console.log(pageNum);
	$.ajax({
		contentType:"application/json;charset=UTF-8",
		url:basePath+"jumpPage",
		type:"post",
		data:JSON.stringify(obj),
		dataType:"json",
		success:function(data){
			console.log(data);
			console.log("suc");
			$("#detail").html(render(data,basePath));
			renderPagination(data);
		},
		error:function(data){
			console.log(data);
			console.log("err");
		}
	
	})
}

/**
 * first page
 * @param basePath
 */
function firstPage(basePath){
	jumpPage(basePath,1);
}

/**
 * pre page
 * @param basePath
 */
function prePage(basePath){
	var currentPage = $("#currentPage").html();
	jumpPage(basePath,parseInt(currentPage)-1);
}

/**
 * next page
 * @param basePath
 */
function nextPage(basePath){
	var currentPage = $("#currentPage").html();
	jumpPage(basePath,parseInt(currentPage)+1);
}

/**
 * last page
 * @param basePath
 */
function lastPage(basePath){
	var lastPage = $("#totalPages").html();
	jumpPage(basePath,lastPage);
}