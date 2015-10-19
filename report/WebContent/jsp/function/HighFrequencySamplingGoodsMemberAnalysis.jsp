<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.dhc.entity.TempInnerObject"  %>
<%@page import="com.dhc.entity.TempObject"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../Include.jsp"></jsp:include>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/HighFrequencySamplingGoodsMemberAnalysis.css" type="text/css" />
<title>抽样高频次商品会员分析</title>
</head>
<body>
<div align="left">
	<p/>
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;分析时间段:&nbsp;&nbsp;</label>
	<input id="startDate" name="startDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />  
	~
	<input id="endDate" name="endDate" type="text" style="width:10%;" class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyyMMdd', isShowToday:false, isShowClear:true});" />
	<label style="display:inline-block;width:2%"></label>
	<label style="font-size:120%">商品ID:&nbsp;&nbsp;</label>
	<input id="goods_code" name="goods_code" class="easyui-textbox" style="width:8%"/>
	<p/>
	<label style="font-size:120%">&nbsp;&nbsp;&nbsp;&nbsp;回购次数:&nbsp;&nbsp;</label>
	<input id="re_count" name="re_count" class="easyui-numberbox" style="width:8%" data-options="min:2"/>
	<label style="display:inline-block;width:4%"></label>
	<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="validate()">查询</a>
</div>	

<div id="dataFrame" class="dataFrame">
	<iframe id="showDataFrame" style="width:100%;height:100%"  frameborder="0" src=""  scrolling="yes"></iframe>
</div>
	

</body>
<script type="text/javascript">

function validate(){
 	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var goods_code = $('#goods_code').val();
	var re_count = $('#re_count').val();
	
	if(startDate == ""){
		showMessageString("起始日期不能为空");
		return;
	}
	if(endDate == ""){
		showMessageString("终止日期不能为空");
		return;
	}
	if(goods_code == ""){
		showMessageString("商品ID不能为空");
		return;
	}
	if(re_count == ""){
		showMessageString("回购次数");
		return;
	}
	refreshFrame(startDate,endDate,goods_code,re_count);
}

function refreshFrame(startDate,endDate,goods_code,re_count) {
	var target = "HighFrequencySamplingGoodsMemberAnalysisData.jsp?startDate="+startDate+"&endDate="+endDate+"&goods_code="+goods_code+"&re_count="+re_count;
	$('#showDataFrame').attr('src', encodeURI(target));
}

</script>
</html>