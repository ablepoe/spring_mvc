<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>内容列表页面</title>
		<link href="<%=basePath %>css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath %>js/jquery-1.8.0.min.js"></script>
		<script src="<%=basePath %>js/insertContent.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<div>
			<input type="button" value="close" onclick="closeBgbox()"/>
			<table border="1" class="tab1">
				<thead>
					<tr>
						<td>id</td>
						<td>command</td>
						<td>description</td>
					</tr>
				</thead>
				<tbody id="insertExists">
					<c:forEach var="data" items="${allMessageList }">
						<tr>
							<td>${data.id }</td>
							<td>${data.command }</td>
							<td>${data.description }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3">
							<input type="button" onclick="addLine()" value="addLine"/>
						</td>
					</tr>
				</tbody>
			</table>
			<table border="2">
				<thead>
					<tr>
						<td>command</td>
						<td>description</td>
					</tr>
				</thead>
				<tbody id="append">
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"><input type="button" onclick="batchAdd('<%=basePath %>')" value="batchAdd"/></td>
					</tr>
				</tfoot>
			</table>
		</div>
		<form id="fresh" action="<%=basePath %>ajaxJump" method="post" />
	</body>
</html>