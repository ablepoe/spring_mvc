<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>内容列表页面</title>
		<link href="<%=basePath %>css/all.css" rel="stylesheet" type="text/css" />
	</head>
	<body style="background: #e1e9eb;">
		<div>
			<span>${messageTotalCounts }</span>
			<span>${messageList.size() }</span>
		</div>
		<form action="" id="mainForm" method="post">
			<div class="right">
				<div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
				<div class="rightCont">
					<p class="g_title fix">内容列表 <a class="btn03" href="#">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="#">删 除</a></p>
					<table class="tab1">
						<tbody>
							<tr>
								<td width="90" align="right">指令：</td>
								<td>
									<input type="text" class="allInput" id="command" value=""/>
								</td>
								<td width="90" align="right">描述：</td>
								<td>
									<input type="text" class="allInput" id="description" value=""/>
								</td>
	                            <td width="85" align="right"><input type="button" class="tabSub" value="查 询" onclick="search('<%=basePath %>')"/></td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<thead>
								<tr>
									<th><input type="checkbox" id="all" onclick="#"/></th>
								    <th>序号</th>
								    <th>指令</th>
								    <th>描述</th>
								    <th>处理</th>
								</tr>
							</thead>
							<tbody id="detail">
								<c:forEach var="message" items="${messageList }" varStatus="i">
									<tr>
										<td>
											<input type="checkbox"/>
										</td>
										<td>
											${message.id }
										</td>
										<td>
											${message.command }
										</td>
										<td>
											${message.description }
										</td>
										<td>
											<a href="#">修改</a>&nbsp;&nbsp;&nbsp;
											<a href="javascript:void(0)" onclick="removeOne('<%=basePath %>')" >删除</a>
											<input type="hidden" name="id" value="${message.id}"></input>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class='page fix'>
							共 <b>4</b> 条
							<a href='###' class='first'>首页</a>
							<a href='###' class='pre'>上一页</a>
							当前第<span>1/1</span>页
							<a href='###' class='next'>下一页</a>
							<a href='###' class='last'>末页</a>
							跳至&nbsp;<input type='text' value='1' class='allInput w28' />&nbsp;页&nbsp;
							<a href='###' class='go'>GO</a>
						</div>
					</div>
				</div>
			</div>
	    </form>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/query.js"></script>
	</body>
</html>