<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/HighFrequencySamplingGoodsMemberAnalysisData.css" type="text/css" />
<jsp:include page="../Include.jsp"></jsp:include>
<title>抽样高频次商品会员分析</title>
</head>
<body>
<div align="left" id="data_table" style="width:100%;height:100%">
	<table id="detail_data" title="抽样高频次商品会员分析 单位:天" class="easyui-datagrid" style="width:100%;height:100%"    
        singleSelect="true" iconCls="icon-save">
        <thead>
			<tr>
				<th data-options="field:'goods_code'">
					商品ID
				</th>
				
	            <c:forEach begin="2" end="${repurchase_count }" var="index">
					<th data-options="field:'_${index }'">${index }购平均时长</th>
				</c:forEach>
				
				<th data-options="field:'average'">
					平均时长
				</th>
			</tr>
        </thead>
	</table>
</div>	

<form action="${pageContext.request.contextPath}/getHighFrequencyDuration.do" id="data_grid" method="post" > 
	<input id="startDate" name="startDate" type="hidden" />  
	<input id="endDate" name="endDate" type="hidden" />
	<input id="goods_code" name="goods_code" type="hidden" />
	<input id="re_count" name="re_count" type="hidden" />
</form>
</body>
<script type="text/javascript">
var startDate = undefined;
var endDate = undefined;
$(function () {
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

    startDate = $.getUrlParam('startDate');
    endDate = $.getUrlParam('endDate');
    goods_code = $.getUrlParam('goods_code');
    re_count = $.getUrlParam('re_count');
    var form = $("#data_grid");
	$("#startDate").val(startDate);
	$("#endDate").val(endDate);
	$("#goods_code").val(goods_code);
	$("#re_count").val(re_count);
	
	if(startDate != undefined && endDate != undefined){
		//generate table
		form.submit();
	}else{
		getData();
	}
});

function getData(){
	showProgress();
	$.ajax({
		type:"post",
		contentType:"application/json;charset=UTF-8",
		url:"${pageContext.request.contextPath}/getHighFrequencyDurationData.do",
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
			};
			closeProgress();
		},
		error : function(data){
			closeProgress();
			showMessageString("Error occured in method getData!");
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