<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>秒杀列表</title>
<%@include file="common/head.jsp"%>
<%@include file="common/tag.jsp"%>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
	<div class="panel-heading text-center">
			<h2>秒杀列表</h2>
		</div>
		<div class="panel-body">
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>name</th>
						<th>number</th>
						<th>startTime</th>
						<th>endTime</th>
						<th>createTime</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="seckill" items="${list }">
						<tr>
							<td>${seckill.seckillId }</td>
							<td>${seckill.name }</td>
							<td>${seckill.number }</td>
							<td><fmt:formatDate value="${seckill.startTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${seckill.endTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${seckill.createTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><a href="${seckill.seckillId }/detail" type="button"
								class="btn btn-info">Link</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	

	<%@include file="common/foot.jsp"%>
</body>
</html>