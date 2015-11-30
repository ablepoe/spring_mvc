<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/IkedaReportC.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>报表C</title>
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
<div id="IkedaReportCDiv" style="float:left;margin:auto;width:100%;height:85%" >
    <table class="easyui-datagrid" id="IkedaReportC" title="报表C" style="margin:auto;width:100%;height:100%" 
    data-options="fitColumns:true,singleSelect:true,rownumbers:true,remoteSort:true,multiSort:false">
        <thead>
	    	<tr>
	    		<th data-options="field:'order_no',width:'12%'">订单号(前8位是日期)</th>
	    		<th data-options="field:'goods_code',width:'9%'">商品号</th>
	    		<th data-options="field:'syslast',width:'5%'">数量</th>
	    		<th data-options="field:'syslast_amt',width:'7%'">单价</th>
	    		<th data-options="field:'goods_amt',width:'11%'">订单金额(含化妆品)</th>
	    		<th data-options="field:'dc_amt',width:'9%'">订单折扣金额</th>
	    		<th data-options="field:'split_dc',width:'11%'">商品折扣金额(分摊)</th>
	    		<th data-options="field:'split_amt',width:'9%'">商品实际金额</th>
	    		<th data-options="field:'send_date',width:'8%'">出库日期</th>
	    		<th data-options="field:'cust_name',width:'6%'">客户名称</th>
	    		<th data-options="field:'birthday',width:'7%'">生日</th>
	    		<th data-options="field:'city_name',width:'6%'">省</th>
	    		<th data-options="field:'address',width:'40%'">地址</th>
	    		<th data-options="field:'cust_level_id',width:'8%',formatter:function(value,row,index){return memberLevel(value);}">会员级别</th>
	    		<th data-options="field:'memb_no',width:'8%'">会员号</th>
	    		<th data-options="field:'tel',width:'8%'">电话</th>
	    		<th data-options="field:'back_amt',width:'8%'">退货数量</th>
	    		<th data-options="field:'back_dc_amt',width:'8%'">退货金额 </th>
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
	console.log("validate");
	//get data
	getIkedaReportCData(startDate,endDate);
}

function getIkedaReportCData(startDate,endDate){
	showProgress();
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	console.log(obj);
	$('#search').linkbutton('disable');
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getIkedaReportCData.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			console.log(data.data);
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("IkedaReportC");
			}else if(data.status == "SUCCESS"){
				$("#IkedaReportC").datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			};
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(data){
			$('#search').linkbutton('enable');
			closeProgress();
			showMessageString("Error occured in method getIkedaReportCData!");
			
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

function memberLevel(value){
	console.log(value);
	switch(value){
	case 1000:return '普通会员';
		break;
	case 2000:return '高级会员';
		break;
	case 3000:return 'vip会员';
		break;
	case 4000:return '超级vip会员';
		break;
	default:return'未知等级会员';
	break;
	}
}

</script>
</html>