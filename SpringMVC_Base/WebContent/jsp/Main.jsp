<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>DHC 查询系统</title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/logo.jpg">
<jsp:include page="Include.jsp" />
<script type="text/javascript">

$(function(){
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/pageInit.do",
		data:"{}",
		dataType:"json",
		success : function(data){

		},
		error : function(){
			showErrorMessage();
		}
		
	})
})



var isCloseAlert = '0';
function addTab(funcId, title, href, icon){

    var tt = $('#mainTab');
    tt.tabs({
        onBeforeClose:function(title,index){
            var isClose = true;
            var obj;
            if (onBeforeCloseEvents[title]!=undefined) {
            	obj = onBeforeCloseEvents[title];
                if (obj.callBackFunc!=undefined) {
                    //回调
                    isClose = obj.callBackFunc();
                    //直接调用子页面
                    //document.getElementsByName(funcId)[0].contentWindow.isClosePage();
                }
            }
            return isClose;            
        }
    });
    if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab          
        tt.tabs('select', title);  
        //refreshTab({tabTitle:title,url:href});  
    } else {  
        if (href){  
            var content = '<iframe name="'+funcId+'" scrolling="no" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';  
        } else {  
            var content = '未实现';  
        }
        tt.tabs('add',{  
            title:title,  
            closable:true,  
            content:content
        });
    }  
}

var onBeforeCloseEvents = {};
function setOnBeforeCloseEvent(funcId, tabTitle, callBackFunc) {
	var obj = new Object();
	obj.funcId = funcId;
    obj.tabTitle = tabTitle;
    obj.callBackFunc = callBackFunc;
    onBeforeCloseEvents[tabTitle] = obj;
}

function closeMainFormTab(funcId, tabTitle) {
    $('#mainTab').tabs('close',tabTitle);
}

function changeSubMenu(menuId)
{
	<c:forEach items="${menuList}" var="menuItem">
	   $("#${menuItem.itemId}").hide();
	</c:forEach>
	$("#"+menuId).show();
}

function logout()
{
	confirmMessageObject(getJsMessage(JSCONFIRM_LOGOUT), function(r) {
		if(r) {
		    $('#main').submit();			
		}
    });
}

</script>
</head>
<body class="easyui-layout">
<form id="main" action="LogOut.do" method="post" style="padding:10px 20px 10px 40px;">
    <input id="selectedFstMenuId" type="hidden" name="selectedFstMenuId" value="${selectedFstMenuId}" />
	<div data-options="region:'north',border:false" style="height:60px;padding:0px 10px 0px 10px;background:#E8F1FF;">
		<table width="100%">
			<tr>
				<td width="5%" style="vertical-align:middle"><img src="${pageContext.request.contextPath}/images/logo.jpg" style="width:40px; height:20px;"/></td>
				<td width="" style="vertical-align:middle">
				    <c:forEach items="${menuList}" var="menuItem">
				        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true" style="font-weight:bold;" onclick="changeSubMenu('${menuItem.itemId}');">${menuItem.itemName}</a>
                    </c:forEach>
                    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true" style="font-weight:bold;" onclick="logout();false;">退出系统</a>             
				</td>
			</tr>
            <tr>
                <td></td>
                <td style="vertical-align:middle;text-align:left;font-weight:bold;">当前登录：<label>${user.userName}</label></td>
            </tr>
		</table>
	</div>
	<div id="subMenu" data-options="region:'west',split:true,title:'功能菜单'" style="width:180px;padding:0px;">
        <c:forEach items="${menuList}" var="fstMenuItem">
        <div id="${fstMenuItem.itemId}" class="easyui-accordion" data-options="fit:true,border:false" >
           <c:forEach items="${fstMenuItem.subMenuItem}" var="sndMenuItem">
               <div id="${sndMenuItem.itemId}" title="${sndMenuItem.itemName}" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:0px;">
                    <c:forEach items="${sndMenuItem.subMenuItem}" var="menuItem">
                        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,border:false" style="font-weight:bold;width:100%;text-align:left" onclick="addTab('${menuItem.itemId}', '${menuItem.itemName}', '${menuItem.realPath}');">${menuItem.itemName}</a>
                    </c:forEach>
               </div>
            </c:forEach>
        </div>
        </c:forEach>
	</div>
	<div data-options="region:'south',border:false" style="height:35px;padding:10px;text-align:center;background:#E8F1FF;color:black">Copyright(C) 2015 DHC Inc. All Right Reserved</div>
	<div data-options="region:'center',title:''">
		<div id= "mainTab"class="easyui-tabs" data-options="fit:true,border:false">
			<!-- <div id='newsList' title="最新信息" style="padding:10px">

				<div class="easyui-panel"  data-options="footer:'#listFooter',fit:true,border:1">
				    <table id="listData" class="easyui-datagrid" title="" style=""
				            data-options="">

				    </table>
				     <div id="listFooter" style="padding: 5px;">&nbsp;</div>
				</div>

			</div> -->
		</div>
	</div>
</form>
</body>
</html>