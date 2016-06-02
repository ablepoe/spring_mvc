<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>秒杀详情页</title>
      <%@include file="common/head.jsp" %>
      <%@include file="common/tag.jsp" %>

   </head>
   <body>
   <div class="container">
    <div class="panel panel-default text-center">
    	<div class="panel-heading">
    		<h2>${seckill.name }</h2>
    	</div>
    	<div class="panel-body">
    		<span class="glyphicon glyphicon-time"><label id="countdown" class="label label-danger">danger</label></span>
    	</div>
    </div>
    
   <div id="alertModal" class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body">
	        <div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<input type="text" name="killphone" id="killphone" placeholder="填手机号" class="form-control">
				</div>	        
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <label id="alertMsg" class="label label-danger"></label>
	        <button type="button" class="btn btn-primary" id="phoneBtn">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
   </div>
   </body>
<%@include file="common/foot.jsp" %>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="//cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="<%=basePath%>/resources/js/seckill.js"></script>
<script type="text/javascript">
	$(function(){
		seckill.detail.init({
			seckillId : "${seckill.seckillId }",
			startTime : "${seckill.startTime.time}",
			endTime : "${seckill.endTime.time}"
		})
	})
</script>
</html>