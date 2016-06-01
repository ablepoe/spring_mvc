<!DOCTYPE html>
<html>
   <head>
      <title>秒杀列表</title>
      <%@include file="common/head.jsp" %>
      <%@include file="common/tag.jsp" %>
   </head>
   <body>
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
   				<td>
   					<fmt:formatDate value="${seckill.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
   				</td>
   				<td>
   					<fmt:formatDate value="${seckill.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
   				</td>
   				<td>
   					<fmt:formatDate value="${seckill.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
   				</td>
   				<td>
   					<a href="${seckill.seckillId }/detail" type="button" class="btn btn-info">信息按钮</a>
   				</td>
   			</tr>
		</c:forEach>
	  </tbody>
	</table>

      <%@include file="common/foot.jsp" %>
   </body>
</html>