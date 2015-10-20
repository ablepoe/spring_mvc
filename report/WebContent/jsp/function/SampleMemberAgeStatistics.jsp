<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ShoppingAnalyze.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>索样会员年龄统计</title>
</head>
<body>
<div align="left">
	<p/>
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;索样日期:&nbsp;&nbsp;</label>
	<input id="startDate" name="startDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />  
	~
	<input id="endDate" name="endDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />
	<label style="display:inline-block;width:2%"></label>
	<label style="font-size:120%">通路选择:&nbsp;&nbsp;</label>
	<select id="pathWay" class="easyui-combobox" style="width:10%">
		<option value="0">全通路</option>
		<option value="1">通信</option>
		<option value="2">全店铺</option>
		<option value="3">直营店</option>
		<option value="4">特约店</option>
	</select>
	<p/>
	<label style="display:inline-block;width:2%"></label>
	<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="validate()">查询</a>

</div>	
<div id="sampleMemberAgeStatisticsDiv" style="float:left;margin:auto;width:80%;height:85%" >
    <table class="easyui-datagrid" id="sampleMemberAgeStatistics" title="索样会员年龄统计" style="margin:auto;width:100%;height:100%" data-options="fitColumns:true,singleSelect:true,rownumbers:false">
        <thead>
	    	<tr>
	    		<th data-options="field:'age',width:'5%'">年龄</th>
	    		<th data-options="field:'below20',width:'13%'">20岁以下</th>
	    		<th data-options="field:'between2130',width:'13%'">21~30岁</th>
	    		<th data-options="field:'between3140',width:'13%'">31~40岁</th>
	    		<th data-options="field:'between4150',width:'13%'">41~50岁</th>
	    		<th data-options="field:'over51',width:'13%'">51岁以上</th>
	    		<th data-options="field:'unknow',width:'14%'">不明</th>
	    		<th data-options="field:'total',width:'15%'">合计</th>
	    	</tr>
    	</thead>
    </table>
</div>


</body>
<script type="text/javascript">


function validate(){
 	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var pathWay = $('#pathWay').combobox('getValue');
	if(startDate == ""){
		showMessageString("起始日期不能为空");
		return;
	}
	if(endDate == ""){
		showMessageString("终止日期不能为空");
		return;
	}
	getSampleMemberAgeStatistics(startDate,endDate,pathWay)
}

function getSampleMemberAgeStatistics(startDate,endDate,pathWay){
	showProgress();
	var obj = new Object();
	obj.startDate = startDate;
	obj.endDate = endDate;
	obj.pathWay = pathWay;
	$('#search').linkbutton('disable');
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getSampleMemberAgeStatisticsByDateReturn.do",
		data:JSON.stringify(obj),
		dataType:"json",
		success : function(data){
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("sampleMemberAgeStatistics");
			}else if(data.status == "SUCCESS"){
				$("#sampleMemberAgeStatistics").datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			};
			$('#search').linkbutton('enable');
			closeProgress();
		},
		error : function(data){
			$('#search').linkbutton('enable');
			closeProgress();
			showMessageString("Error occured in method getSampleMemberAgeStatistics!");
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