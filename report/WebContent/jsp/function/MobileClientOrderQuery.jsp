<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/MobileClientOrderQuery.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>移动端订单查询</title>
</head>
<body>
<div align="left">
	<p/>
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;订单日期:&nbsp;&nbsp;</label>
	<input id="startDate" name="startDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />  
	~
	<input id="endDate" name="endDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />
	<p/>
	<label style="display:inline-block;width:2%"></label>
	<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="validate()">查询</a>
</div>	
<div id="mobileClientOrderQueryDiv" style="float:left;margin:auto;width:95%;height:85%" >
    <table class="easyui-datagrid" id="mobileClientOrder" title="移动端订单查询" style="margin:auto;width:100%;height:100%" data-options="fitColumns:true,singleSelect:true,rownumbers:true">
        <thead>
	    	<tr>
	    		<th data-options="field:'order_no',width:'20%'">订单号</th>
	    		<th data-options="field:'order_date',width:'20%'">订单生成时间</th>
	    		<th data-options="field:'send_date',width:'20%'">订单发货时间</th>
	    		<th data-options="field:'cust_no',width:'20%'">顾客号码</th>
	    		<th data-options="field:'quest_amt',width:'18%'">订单金额</th>
	    	</tr>
    	</thead>
    </table>
</div>

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
	//get mobileClientOrder data
	getMobileClientOrderData(startDate,endDate);
}

function getMobileClientOrderData(startDate,endDate){
	showProgress();
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	$('#search').linkbutton('disable');
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getMobileClientOrder.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("mobileClientOrder");
			}else if(data.status == "SUCCESS"){
				$("#mobileClientOrder").datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			};
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(data){
			$('#search').linkbutton('enable');
			closeProgress();
			showMessageString("Error occured in method getMobileClientOrderData!");
			
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