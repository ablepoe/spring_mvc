<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../Include.jsp"></jsp:include>
<title>Sample</title>
</head>
<body>

<div id="input" >
	<table >
		<tr>
			<td>
				param1:  <input type="text" id="pathways" class="easyui-textbox" data-options="" value="71"/>
			</td>
		</tr>
		<tr>
			<td>
				param2:  <input type="text" id="date_from" class="easyui-datebox" data-options="" />
			</td>
			
			<td>
				param3:  <input type="text" id="date_to" class="easyui-datebox"  />
			</td>
			
			<td/>
			
			<td>
				<a href="#" class="easyui-linkbutton" onclick="getSample()" id="search" data-options="iconCls:'icon-search'">search</a>  
			</td>
			
			<td>
				<a href="#" class="easyui-linkbutton" onclick="exportData()" id="exportData" data-options="iconCls:'icon-save'">download</a>  
			</td>
		</tr>
	</table>
</div>
<form id="downloadFile" action="${pageContext.request.contextPath}/downloadFile.do" method="post" style="">
	<input id="dlFileName" name="dlFileName" type="hidden" value="" />
	<input id="dlSrcFile" name="dlSrcFile" type="hidden" value="" />
</form>
<table class="easyui-datagrid" title="Sampletable" id="sample"  style="margin:auto;width:1000px;height:550px" 
        data-options="fitColumns:true,singleSelect:true,rownumbers:false,pagination:true">   
    <thead>   
        <tr>   
            <th data-options="field:'row_num',width:100">ROW_NUM</th>
            <th data-options="field:'send_date',width:100">SEND_DATE</th>
            <th data-options="field:'order_no',width:100">ORDER_NO</th>
            <th data-options="field:'receiver',width:100,align:'right'">RECEIVER</th>
            <th data-options="field:'order_amt',width:100,align:'right'">ORDER_AMT</th>
            <th data-options="field:'pay_mode',width:100,align:'right'">PAY_MODE</th>
            <th data-options="field:'dely_gb',width:100,align:'right'">DELY_GB</th>
            <th data-options="field:'pre_price',width:100,align:'right'">pre_price</th>
            <th data-options="field:'pre_sale_price',width:100,align:'right'">pre_sale_price</th>
            <th data-options="field:'qty',width:100,align:'right'">qty</th>
            <th data-options="field:'real_sale_price',width:100,align:'right'">real_sale_price</th>  
            <th data-options="field:'sale_price',width:100,align:'right'">sale_price</th>    
        </tr>   
    </thead>   
</table>  



</body>
<script type="text/javascript">
$(function () {
    $("#date_from").datebox({
        required: "true",
        missingMessage: "必填项",
        formatter: function (date) {
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return y + (m < 10 ? ("0" + m) : m) + (d < 10 ? ("0" + d) : d) ;
        }
    });
    $("#date_to").datebox({
        required: "true",
        missingMessage: "必填项",
        formatter: function (date) {
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return y + (m < 10 ? ("0" + m) : m) + (d < 10 ? ("0" + d) : d) ;
        }
    });
    init();
    
  //设置分页控件 
    var p = $('#sample').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5,10,15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        total:0,
        onSelectPage:function (pageNumber, pageSize) {
        	console.log(pageNumber);
        	console.log(pageSize);
        	doPaging(pageNumber, pageSize);
        }
    });  
    
});

function init(){
	var curr_time = new Date();
    var y = curr_time.getFullYear();
    var m = curr_time.getMonth()+1;
    var d = curr_time.getDate();
	var strDate = y + "-" +(m < 10 ? ("0" + m) : m) + "-" +(d < 10 ? ("0" + d) : d) ;
	var strDate = "2015-06-01";
	var toDate = "2015-06-02";
    $("#date_from").datebox("setValue", strDate); 
    $("#date_to").datebox("setValue", toDate); 
}

function getSample(){
	var date_from = $('#date_from').datebox('getValue');
	var date_to = $('#date_to').datebox('getValue');
	
	var pageInfo = new Object();
	var p = $('#sample').datagrid('getPager');
	var opts = $('#sample').datagrid('options');
	pageInfo.pageNumber = opts.pageNumber;
	pageInfo.pageSize = opts.pageSize;
	
	var pathways = $('#pathways').textbox('getValue');
	
	var demoParam = new Object();
	demoParam.pathways = pathways;
	demoParam.pageInfo = pageInfo;
	
 	$('#search').linkbutton('disable');
 	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/demoSample.do",
		data:JSON.stringify(demoParam),
		dataType:"json",
		success : function(data){
			console.log(data);
			console.log(data.data);
			$('#sample').datagrid('loadData',data.data.data);
			p.pagination({
                "total":data.data.total,
                "pageSize":data.data.pageSize,
                "pageNumber":data.data.pageNumber,
            });
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(){
			closeProgress();
			console.error("error");
			//showErrorMessage();
			$('#search').linkbutton('enable');
			
		}
	});
}

function exportData(){
	console.warn("download");
	var date_from = $('#date_from').datebox('getValue');
	var date_to = $('#date_to').datebox('getValue');
	var pathways = $('#pathways').textbox('getValue');
	var obj = new Object();
	obj.pathways = pathways;
	//obj.date_from = date_from;
	//obj.date_to = date_to;
	//obj.pathways = pathways;
	//console.log(obj);
	
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/demoExport.do",
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
			showErrorMessage();
		}
	});
}

function doPaging(pageNumber, pageSize){
	var pageInfo = new Object();
	var p = $('#sample').datagrid('getPager');
	pageInfo.pageNumber = pageNumber;
	pageInfo.pageSize = pageSize;
	
	var pathways = $('#pathways').textbox('getValue');
	
	var demoParam = new Object();
	demoParam.pathways = pathways;
	demoParam.pageInfo = pageInfo;
	
 	$('#search').linkbutton('disable');
 	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/demoSample.do",
		data:JSON.stringify(demoParam),
		dataType:"json",
		success : function(data){
			console.log(data);
			console.log(data.data);
			$('#sample').datagrid('loadData',data.data.data);
			p.pagination({
                "total":data.data.total,
                "pageSize":data.data.pageSize,
                "pageNumber":data.data.pageNumber,
            });
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(){
			closeProgress();
			console.error("error");
			//showErrorMessage();
			$('#search').linkbutton('enable');
			
		}
	});
}
</script>
</html>