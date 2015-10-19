<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ShoppingAnalyze.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>商品首购&2购分析</title>
</head>
<body>
<div align="left">
	<p/>
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;分析时间段:&nbsp;&nbsp;</label>
	<input id="startDate" name="startDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />  
	~
	<input id="endDate" name="endDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />
	<p/>
	<label style="display:inline-block;width:2%"></label>
	<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="validate()">查询</a>
<!-- 	<label style="font-size:15px;width:10px;">&nbsp;</label>
	<a id="exportData" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="exportData()">导出首次购物</a>
	<label style="font-size:15px;width:10px;">&nbsp;</label>
	<a id="exportData2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="exportData2()">导出2次购物</a> -->
</div>	
<div id="firstShoppingAnalyzeDiv" style="float:left;margin:auto;width:20%;height:85%" >
    <table class="easyui-datagrid" id="firstShoppingAnalyze" title="商品首购分析" style="margin:auto;width:100%;height:100%" data-options="fitColumns:true,singleSelect:true,rownumbers:true">
        <thead>
	    	<tr>
	    		<th data-options="field:'goods_code',width:'40%'">商品ID</th>
	    		<th data-options="field:'order_qty',width:'40%'">购物数量</th>
	    	</tr>
    	</thead>
    </table>
</div>
<div style="float:left;width:2%">
	<label style="font-size:120%;width:2%;">&nbsp;</label>
</div>
<div id="secondShoppingAnalyzeDiv" style="float:left;margin:auto;width:20%;height:85%">
	<table class="easyui-datagrid" id="secondShoppingAnalyze" title="商品2购分析" style="margin:auto;width:100%;height:100%" 
        data-options="fitColumns:true,singleSelect:true,rownumbers:true">
        <thead>
	    	<tr>
	    		<th data-options="field:'goods_code',width:'40%'">商品ID</th>
	    		<th data-options="field:'order_qty',width:'40%'">购物数量</th>
	    	</tr>
    	</thead>
    </table>
</div>

<form id="downloadFile" action="${pageContext.request.contextPath}/downloadFile.do" method="post" style="">
	<input id="dlFileName" name="dlFileName" type="hidden" value="" />
	<input id="dlSrcFile" name="dlSrcFile" type="hidden" value="" />
</form>

</body>
<script type="text/javascript">


function validate(){
 	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if(startDate == ""){
		showMessageString("起始日期不能为空");
		return;
	}
	if(endDate == ""){
		showMessageString("终止日期不能为空");
		return;
	}
	showProgress();
	//get first data
	getFirstData(startDate,endDate);
}

function getFirstData(startDate,endDate){
	showProgress();
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	$('#search').linkbutton('disable');
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getFirstShoppingAnalyze.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("firstShoppingAnalyze");
			}else if(data.status == "SUCCESS"){
				$("#firstShoppingAnalyze").datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			};
			$('#search').linkbutton('enable');
			//get second data
			getSecondData(startDate,endDate);
		},
		error : function(data){
			$('#search').linkbutton('enable');
			//closeProgress();
			showMessageString("Error occured in method getFirstData!");
			
		}
	})
}

function getSecondData(startDate,endDate){
	showProgress();
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	$('#search').linkbutton('disable');
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getSecondShoppingAnalyze.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("secondShoppingAnalyze");
			}else if(data.status == "SUCCESS"){
				$("#secondShoppingAnalyze").datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			};
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(data){
			$('#search').linkbutton('enable');
			closeProgress();
			showMessageString("Error occured in mehtod getSecondData!");
		}
	})
}

function exportData(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if(startDate == ""){
		showMessageString("起始日期不能为空");
		return;
	}
	if(endDate == ""){
		showMessageString("终止日期不能为空");
		return;
	}
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getFirstShoppingAnalyzeExport.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
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
			closeProgress();
		},
		error : function(){
			showMessageString("Error occured in mehtod exportData!");
		}
	});
}

function exportData2(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if(startDate == ""){
		showMessageString("起始日期不能为空");
		return;
	}
	if(endDate == ""){
		showMessageString("终止日期不能为空");
		return;
	}
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getSecondShoppingAnalyzeExport.do",
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
			closeProgress();
		},
		error : function(){
			showMessageString("Error occured in mehtod exportData2");
		}
	});
}

//easyUI scrollbar
function defaultHaveScroll(gridid){  
    var opts=$('#'+gridid).datagrid('options');  
    var text='{';  
    for(var i=0;i<opts.columns.length;i++){  
       var inner_len=opts.columns[i].length;  
       for(var j=0;j<inner_len;j++){  
           if((typeof opts.columns[i][j].field)=='undefined')break;  
            text+="'"+opts.columns[i][j].field+"':''";  
            if(j!=inner_len-1){
                text+=",";  
            }  
       }  
    }  
    text+="}";  
    text=eval("("+text+")");  
    var data={"total":1,"rows":[text]};  
    $('#'+gridid).datagrid('loadData',data);  
   $("tr[datagrid-row-index='0']").css({"visibility":"hidden"});  
}

</script>
</html>