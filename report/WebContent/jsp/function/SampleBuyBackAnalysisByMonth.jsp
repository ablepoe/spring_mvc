<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../Include.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/SampleBuyBackAnalysisByMonth.css" type="text/css" />
<title>索样逐月回购分析</title>
</head>
<body>
<div align="left">
	<p/>
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;索样年月:&nbsp;&nbsp;</label>
	<input id="startMonth" name="startMonth" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endMonth\')}',dateFmt:'yyyyMM', isShowToday:false, isShowClear:true});" />  
	~
	<input id="endMonth" name="endMonth" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'startMonth\',{M:11});}', minDate:'#F{$dp.$D(\'startMonth\')}',dateFmt:'yyyyMM', isShowToday:false, isShowClear:true});" />
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
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;省市:&nbsp;&nbsp;</label>
	<select id="province" class="easyui-combobox" style="width:10%">
	</select>
	<label style="display:inline-block;width:18%"></label>
	<label style="font-size:120%">城市:&nbsp;&nbsp;</label>
	<input id="city" class="easyui-textbox" style="width:10%"/>
	<label style="display:inline-block;width:5%"></label>
	<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="validate()">查询</a>
	<label style="display:inline-block;width:1%"></label>
	<a id="correspondingCity" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="openWindow()" >查看店铺对应城市</a>
<!-- 	<label style="display:inline-block;width:10px"></label>
	<a id="exportData" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="exportData()">导出数据</a> -->
</div>	

<div id="dataFrame" class="dataFrame">
	<iframe id="showDataFrame" style="width:100%;height:100%" frameborder="0" src="" scrolling="yes"></iframe>
</div>

<div id="mappedCity" class="easyui-window" title="店铺城市对应关系" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true,closed:true,iconCls:'icon-save',top:($(window).height()*0.1),left:($(window).width()*0.1),width:'80%',height:'85%'" style="padding:1%, 1%;">   
	<label style="font-size:120%">店铺类型 1:直营店 2:特约店</label>
	<a id="mappedCitySearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:15%" onclick="checkMappedCity()">刷新</a>
    <table class="easyui-datagrid" id="shopMappedCity"  style="margin:auto;width:95%;height:90%" 
        data-options="fitColumns:true,singleSelect:true">
        <thead>
	    	<tr>
	    		<th data-options="field:'dp_code'">店铺代码</th>
	    		<th data-options="field:'dp_name'">店铺名称</th>
	    		<th data-options="field:'dp_prov'">所在省</th>
	    		<th data-options="field:'dp_city'">所在市</th>
	    		<th data-options="field:'dp_diff'">店铺类型</th>
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
function refreshFrame(startMonth,endMonth,pathWay,provinceText,city,provinceValue) {
	var target = "SampleBuyBackAnalysisByMonthData.jsp?startMonth="+startMonth+"&endMonth="+endMonth+"&pathWay="+pathWay+"&provinceValue="+provinceValue+"&city="+city;
	$('#showDataFrame').attr('src', encodeURI(target));
}

$(function () {
	initProvince();
	$('#mappedCity').window('close');  // close a window  
});

function initProvince(){
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/getProvince.do",
		data:"",
		dataType:"json",
		success : function(data){
			if(data.status == "SUCCESS"){
				var msg = new Array();
				$.each(data.data,function(i,item){
					msg.push({ "text": item, "value": i });
				}); 
				$("#province").combobox('loadData', msg);
			}else{
				showMessageObject(data);
			}
		},
		error : function(){
			closeProgress();
			showMessageString("Error occured in method initProvince!");
		}
	});
}

function openWindow(){
	$('#mappedCity').window('open');
	$('#shopMappedCity').datagrid('enableFilter');  // 启用过滤
}

function checkMappedCity(){
	showProgress();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/getShopMappedCity.do",
		data:"",
		dataType:"json",
		success : function(data){
			if(data.status == "SUCCESS"){
				$('#shopMappedCity').datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			}
			closeProgress();
		},
		error : function(){
			closeProgress();
			showMessageString("Error occured in method checkMappedCity!");
		}
	});
}

function validate(){
 	var startMonth = $('#startMonth').val();
	var endMonth = $('#endMonth').val();
	var pathWay = $('#pathWay').combobox('getValue');
	var provinceValue = $('#province').combobox('getValue');
	if(provinceValue == ""){
		provinceValue = 0;
	}
	var provinceText = $('#province').combobox('getText');
	var city = $('#city').textbox('getValue');
	
	if(startMonth == ""){
		showMessageString("起始日期不能为空");
		return;
	}
	if(endMonth == ""){
		showMessageString("终止日期不能为空");
		return;
	}
	
	refreshFrame(startMonth,endMonth,pathWay,provinceText,city,provinceValue);

}

function exportData(){
	var startMonth = $('#startMonth').val();
	var endMonth = $('#endMonth').val();
	var pathWay = $('#pathWay').combobox('getValue');
	var provinceValue = $('#province').combobox('getValue');
	if(provinceValue == ""){
		provinceValue = 0;
	}
	var provinceText = $('#province').combobox('getText');
	var city = $('#city').textbox('getValue');
	
	if(startMonth == ""){
		showMessageString("起始日期不能为空");
		return;
	}
	if(endMonth == ""){
		showMessageString("终止日期不能为空");
		return;
	}
	var obj = new Object();
	obj.startMonth = startMonth;
	obj.endMonth = endMonth;
	obj.pathWay = pathWay;
	obj.province = provinceValue;
	obj.city = city;
	
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getSampleBuyBackAnalysisByMonthExport.do",
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
			showMessageString("Error occured in method exportData!");
		}
	});
}
</script>
</html>