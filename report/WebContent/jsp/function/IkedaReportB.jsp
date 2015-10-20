<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/IkedaReportB.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>报表B</title>
</head>
<body>
<div align="left">
	<p/>
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;出库时间:&nbsp;&nbsp;</label>
	<input id="startDate" name="startDate" type="text" style="width:10%;" class="Wdate" 
		onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />  
	~
	<input id="endDate" name="endDate" type="text" style="width:10%;background-color:#C0C0C0;color:#FFFFFF " class="Wdate" readonly="readonly" />
	<p/>
	<label style="display:inline-block;width:2%"></label>
	<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="validate()">查询</a>
</div>	
<div id="IkedaReportBDiv" style="float:left;margin:auto;width:95%;height:85%" >
    <table class="easyui-datagrid" id="IkedaReportB" title="报表B" style="margin:auto;width:100%;height:100%" 
    data-options="fitColumns:true,singleSelect:true,rownumbers:false,remoteSort:true,multiSort:false">
        <thead>
	    	<tr>
	    		<th data-options="field:'send_date',width:'12%'">出库日期</th>
	    		<th data-options="field:'order_no',width:'12%'">订单数</th>
	    		<th data-options="field:'syslast',width:'12%'">销售数量</th>
	    		<th data-options="field:'split_amt',width:'12%'">销售金额</th>
	    		<th data-options="field:'split_dc',width:'12%'">折扣金额</th>
	    		<th data-options="field:'back_order_no',width:'12%'">退货订单数</th>
	    		<th data-options="field:'back_syslast',width:'10%'">退货数量</th>
	    		<th data-options="field:'back_amt',width:'10%'">退货金额</th>
	    		<th data-options="field:'back_dc_amt',width:'10%'">退货折扣金</th>
	    	</tr>
    	</thead>
    </table>
</div>


</body>
<script type="text/javascript">

$(function () {
	$('#startDate').val("20150930");
	var date = new Date().format("yyyyMMdd");
	$('#endDate').val(date);
});

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
	//get data
	getIkedaReportBData(startDate,endDate);
}

function getIkedaReportBData(startDate,endDate){
	showProgress();
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	$('#search').linkbutton('disable');
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getIkedaReportBData.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("IkedaReportB");
			}else if(data.status == "SUCCESS"){
				$("#IkedaReportB").datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			};
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(data){
			$('#search').linkbutton('enable');
			closeProgress();
			showMessageString("Error occured in method getIkedaReportBData!");
			
		}
	})
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