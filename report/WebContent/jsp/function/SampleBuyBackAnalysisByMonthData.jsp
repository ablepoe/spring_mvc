<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/SampleBuyBackAnalysisByMonthData.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>索样逐月回购分析</title>
</head>
<body>
<div align="left" id="data_table" style="width:100%;height:100%">
	<table id="detail_data" title="索样逐月回购分析" class="easyui-datagrid" style="width:100%;height:100%"    
        singleSelect="true" iconCls="icon-save">
        <thead>
			<tr>
				<th rowspan="2" data-options="field:'sy_date'">
					索样年月
				</th>
				<th rowspan="2" data-options="field:'province'">
					省市
				</th>
				<th rowspan="2" data-options="field:'city'">
					城市
				</th>
				<th rowspan="2" data-options="field:'sy_count'">
					索样人数
				</th>
				<th colspan="3" >
					先购物，后索样
				</th>
				<c:forEach items="${dataList}" var="dataItem">
			    	<th colspan="3">${dataItem}</th>
	            </c:forEach>
			</tr>
	        <tr>
	            <th data-options="field:'firstCount'">首购人数</th>
	            <th data-options="field:'firstAmt'">首购金额</th>
	            <th data-options="field:'firstTurn'">转化率</th>
	            <c:forEach begin="1" end="${dataList.size() }" var="index">
					<th data-options="field:'_${index }firstCount'">首购人数</th>
		            <th data-options="field:'_${index }firstAmt'">首购金额</th>
		            <th data-options="field:'_${index }fristTurn'">转化率</th>
				</c:forEach>
	        </tr>
        </thead>
	</table>
</div>	

<form action="${pageContext.request.contextPath}/getMonthDuration.do" id="data_grid" method="post" > 
	<input id="startMonth" name="startMonth" type="hidden" />  
	<input id="endMonth" name="endMonth" type="hidden" />
	<input id="pathWay" name="pathWay" type="hidden" />
	<input id="provinceValue" name="provinceValue" type="hidden" />
	<input id="city" name="city" type="hidden" />
</form>

</body>
<script type="text/javascript">
var startMonth = undefined;
var endMonth = undefined;
$(function () {
	var dataList = "${dataList}";
    //方法二：
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) 
            	return decodeURI(r[2]); 
            return null;
        }
    })(jQuery);

    startMonth = $.getUrlParam('startMonth');
    endMonth = $.getUrlParam('endMonth');
    pathWay = $.getUrlParam('pathWay');
    provinceValue = $.getUrlParam('provinceValue');
    city = $.getUrlParam('city');
    
    
    var form = $("#data_grid");
	$("#startMonth").val(startMonth);
	$("#endMonth").val(endMonth);
	$("#pathWay").val(pathWay);
	$("#provinceValue").val(provinceValue);
	$("#city").val(city);
	if(startMonth != undefined && endMonth != undefined){
		//generate table
		form.submit();	
	}else {
		//fillin table
		getData();
	}
	
});

function getData(){
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getSampleBuyBackAnalysisByMonth.do",
		data:"",
		dataType:"json",
		success : function(data){
			if(data.data.length == 0){
				//show scrollbar even no data
				defaultHaveScroll("detail_data");
			}else if(data.status == "SUCCESS"){
				$("#detail_data").datagrid('loadData',data.data);
			}else{
				showMessageObject(data);
			}
			closeProgress();
		},
		error : function(data){
			closeProgress();
			showMessageString("Error occured in mehtod getData!");
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