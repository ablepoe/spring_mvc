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
				pathways:<input type="text" id="pathways" class="easyui-textbox" data-options="" value="71"/>
			</td>
		</tr>
		<tr>
			<td>
				date_from:<input type="text" id="date_from" class="easyui-datebox" data-options="" />
			</td>
			
			<td>
				date_to:<input type="text" id="date_to" class="easyui-datebox"  />
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
        data-options="fitColumns:true,singleSelect:true,rownumbers:true">   
    <thead>   
        <tr>   
            <th data-options="field:'goods_code',width:100">code</th>
            <th data-options="field:'goods_name',width:100">name</th>
            <th data-options="field:'memb_no',width:100,align:'right'">memb_no</th>
            <th data-options="field:'order_date',width:100,align:'right'">order_date</th>
            <th data-options="field:'order_media',width:100,align:'right'">order_media</th>
            <th data-options="field:'order_no',width:100,align:'right'">order_no</th>
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
	var pathways = $('#pathways').textbox('getValue');
	var obj = new Object();
	obj.date_from = date_from;
	obj.date_to = date_to;
	obj.pathways = pathways;
	console.log(obj);
	
 	$('#search').linkbutton('disable');
 	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/crmSample.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			console.log(data);
			$('#sample').datagrid('loadData',data.data); 
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
	obj.date_from = date_from;
	obj.date_to = date_to;
	obj.pathways = pathways;
	console.log(obj);
	
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/crmExport.do",
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

</script>
</html>