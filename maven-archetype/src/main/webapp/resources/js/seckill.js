$(function(){
	$("#modal").modal('toggle');
})
function check(){
	$("#alertMsg").html('测试！！！');
}
//交互逻辑的js代码
var seckill = {
		//秒杀相关的url
		URL : {
			now : function(){
				return '/maven-archetype/seckill/time/now';
			},
			exposer : function(seckillId){
				return '/maven-archetype/seckill/'+seckillId+'/exposor';
			},
			execution : function(seckillId,md5){
				return '/maven-archetype/seckill/' + seckillId + "/" + md5 + "/execution";
			}
		},
		//处理秒杀逻辑
		handleSeckill : function(seckillId, node){
			node.hide().html("<button class='btn btn-primary btn-lg' id='killBtn'>秒秒秒</button>");
			$.post(seckill.URL.exposer(seckillId), {}, function(result){
				//执行交互流程
				if(result && result.status){
					var exposer = result.data;
					//秒杀启动状态
					if(exposer.exposed){
						//开启秒杀
						//获取md5
						var md5 = exposer.md5;
						//获取秒杀地址
						var killUrl = seckill.URL.execution(seckillId,md5);
						console.log(killUrl);
						$('#killBtn').one('click',function(){
							//执行秒杀
							//1、禁用按钮
							//2、发送请求
							$(this).addClass('disable');
							$.post(killUrl, {}, function(result){
								console.log(result);
								if(result && result.status){
									var killResult = result.data;
									var state = killResult.state;
									var stateInfo = killResult.stateInfo;
									//3、显示秒杀结果
									node.html('<span class="label label-success">'+stateInfo+'</span>');
								}
							});
						})
						node.show();
					}else{
						//未开启秒杀
						var now = exposer.now;
						var start = exposer.start;
						var end = exposer.end;
						seckill.countdown(seckillId,now,start,end);
					}
				}else{
					console.log(result);
				}
			});
			
		},
		//验证手机号
		validatePhone : function(phone){
			if(phone && phone.length == 11 && !isNaN(phone)){
				return true;
			}else {
				return false;
			}
			
		},
		//倒计时逻辑
		countdown : function(seckillId,nowTime,startTime,endTime){
			var countdownLabel = $('#countdown');
			var start = parseInt(startTime);
			var end = parseInt(endTime);
			if(nowTime > endTime){
				//秒杀结束
				countdownLabel.html(('秒杀结束'));
			}else if(nowTime < startTime){
				//秒杀未开始
				var date = new Date(parseInt(startTime));
				countdownLabel.countdown(date, function(event) {
					$(this).html(event.strftime('%w weeks %d days %H:%M:%S'));
				}).on('finish.countdown', function(){ //倒计时完成后回调事件
					//获取秒杀地址，控制显示逻辑
					seckill.handleSeckill(seckillId,countdownLabel);
				});	
			}else{
				//秒杀中
				countdownLabel.html(('秒杀中'));
				seckill.handleSeckill(seckillId,countdownLabel);
			}
		},
		//详情页逻辑
		detail : {
			//详情页初始化
			init : function(params) {
//				console.log(params);
				//手机验证和登陆，及时交互
				//在cookie中查找手机号
				var killPhone = $.cookie('kill_phone');
				var seckillId = params.seckillId;
				var startTime = params.startTime;
				var endTime = params.endTime;
//				console.log(seckillId);
//				console.log(startTime);
//				console.log(endTime);
				//验证手机号
				if(!seckill.validatePhone(killPhone)){
					//绑定phone，显示弹出框
					var killPhoneModal = $("#alertModal");
					killPhoneModal.modal({
						//弹出层属性配置
						show: true, //显示弹出层
						backdrop:"static", //禁止位置关闭
						keyboard: false, //关闭键盘时间
					})
					$('#phoneBtn').click(function(){
						var inputPhone = $('#killphone').val();
						console.log(inputPhone);
						if(seckill.validatePhone(inputPhone)){
							//phone写入cookie
							$.cookie('kill_phone', inputPhone, {expires: 7, path: '/maven-archetype/seckill'});
							console.log($.cookie('kill_phone'));
							//刷新页面
							window.location.reload();
						}else{
							$('#alertMsg').hide().html('手机号错误!').show(300);
						}
					});
				}else{
					//秒杀执行模块
					//计时交互
					$.get(seckill.URL.now(), {}, function (result){
						console.log(result);
						if(result && result.status){
							//计算倒计时
							var nowTime = result.data;
							seckill.countdown(seckillId,nowTime,startTime,endTime);
						}else{
							//日期不正确
						}
					});
					
					
				}
			}
		}
		
}