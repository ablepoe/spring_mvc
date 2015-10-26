<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/IkedaReportA.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>报表A</title>
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
	<a href="#" class="easyui-linkbutton" onclick="exportData()" id="exportData" data-options="iconCls:'icon-save'">下载</a>  
</div>	
<div id="IkedaReportADiv" style="float:left;margin:auto;width:95%;height:85%" >
    <table class="easyui-datagrid" id="IkedaReportA" title="报表A" style="margin:auto;width:100%;height:100%" 
    data-options="fitColumns:true,singleSelect:true,rownumbers:false,remoteSort:true,multiSort:false,sortName:'goods_code',sortOrder:'asc'">
        <thead>
	    	<tr>
	    		<th data-options="field:'goods_code',width:'9%',sortable:true">品号</th>
	    		<th data-options="field:'goods_name',width:'18%',sortable:true">品名</th>
	    		<th data-options="field:'middle_syslast',width:'10%',sortable:true">期间中销售数量</th>
	    		<th data-options="field:'middle_split_amt',width:'14%',sortable:true">期间中销售金额</th>
	    		<th data-options="field:'total_syslast',width:'10%',sortable:true">累计销售数量</th>
	    		<th data-options="field:'total_split_amt',width:'14%',sortable:true">累计销售金额</th>
	    		<th data-options="field:'total_turnrate',width:'9%',formatter: function(value){ value = value+'%';return value;},sortable:true">累计消化率</th>
	    		<th data-options="field:'stock',width:'9%',sortable:true">当前库存</th>
	    		<th data-options="field:'goods_turn_duration',width:'9%',sortable:true">商品回转日数</th>
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

$(function () {
	var date = new Date().format("yyyyMMdd");
	$('#endDate').val(date);
	$('#exportData').linkbutton('disable');
	
	$('#IkedaReportA').datagrid({
		 onSortColumn: function (sort, order) {
             getIkedaReportAData($('#startDate').val(),$('#endDate').val());
         }
	})
	
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
	//get mobileClientOrder data
	getIkedaReportAData(startDate,endDate);
}

function getIkedaReportAData(startDate,endDate){
	showProgress();
	var sortName = $('#IkedaReportA').datagrid('options').sortName;
	var sortOrder = $('#IkedaReportA').datagrid('options').sortOrder;
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	obj.sortName = sortName;
	obj.sortOrder = sortOrder;
	$('#search').linkbutton('disable');
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getIkedaReportAData.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("IkedaReportA");
			}else if(data.status == "SUCCESS"){
				$("#IkedaReportA").datagrid('loadData',data.data);
				$('#exportData').linkbutton('enable');
			}else{
				showMessageObject(data);
			};
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(data){
			$('#search').linkbutton('enable');
			closeProgress();
			showMessageString("Error occured in method getIkedaReportAData!");
			
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

function exportData(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var sortName = $('#IkedaReportA').datagrid('options').sortName;
	var sortOrder = $('#IkedaReportA').datagrid('options').sortOrder;
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	obj.sortName = sortName;
	obj.sortOrder = sortOrder;
	//return;
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getIkedaReportAExport.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			if(data.status == "SUCCESS"){
				//form submit();
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