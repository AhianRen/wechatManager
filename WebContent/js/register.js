$(function(){//页面加载完毕后运行
	//请求servlet,获取用户信息
	$.ajax({
		url:COMMON_URL+"/user/getuserinfo",
		type:"get",
		dataType:"json",
		success:function(data){
			$("#headimg").attr("src",data.headimgurl);
			$("#nickname").html(data.nickname);
		},
		error:function(){
			alert("网络异常，稍后再试!");
		}
	});
	
	$("#sendSMS-A").click(function(){
		if($("#mobile").val() == null || $("#mobile").val() == "") {
			alert("请输入正确的手机号");
		}
		var classes = $("#sendSMS-A").prop("class");
		if(!classes.indexOf("weui_btn_disabled")) {
			$.ajax({
				type:"post",
				url:COMMON_URL+"/user/sendSMS",
				data: {
					"mobile": $("#mobile").val()
				},
				dataType: "json",
				success: function(data) {
					if(data.respCode == "0") {
						$("#sendSMS-A").addClass("weui_btn_disabled");
						$("#sendSMS-A").text("60s");
						var time = 60;
						var timer = setInterval(function() {
							time--;
							if(time == 0) {
								$("#sendSMS-A").removeClass("weui_btn_disabled");
								$("#sendSMS-A").html("获取验证码");
								clearInterval(timer);
							} else {
								$("#sendSMS-A").text(time + "s");
							}
	
						}, 1000);
					}
				},
				error: function() {
					alert("网络异常，稍后再试");
				}
			});
		}
	});
});