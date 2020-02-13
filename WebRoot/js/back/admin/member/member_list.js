$(function(){
	$("#selectAll").on("click",function(){
		checkboxSelectAll('user.userid',this.checked) ;
	}) ;
	$("#lockBtn").on("click",function(){	// 绑定用户锁定操作
		var ids = "" ;
		var elename = "member.mid" ;
		var url = "pages/back/admin/member/MemberActionBack!lock.action?p=p" ;
		$("input[id='"+elename+"']:checked").each(function() {
			ids += $(this).val() + "|" ;
		}) ;
		if (ids == "") {
			$("#alertDiv").attr("class","alert alert-danger") ;
	        $("#alertText").text("您还未选择任何数据，请确认您的操作！") ;
			$("#alertDiv").fadeIn(1000,function(){
	            $("#alertDiv").fadeOut(3000) ;
	        }) ;
		} else {
			if (window.confirm("您确定要继续执行此操作吗？")) {
				$.post(url,{"ids":ids},function(data){
					operateAlert(data.trim() == "true","用户锁定成功！","用户锁定失败！") ;
				},"text") ;
			}
		}
		// operateChecked("确定要锁定这些用户吗？",,'pages/jsp/admin/member/MemberActionBack!lock.action?p=p') ;
	}) ;
	$("a[id*=userBtn-]").each(function(){
		// 拆分id数据
		var mid = this.id.substring(this.id.indexOf("-") + 1) ;
		console.log("用户ID：" + mid) ;
		$(this).on("click",function(){
			// Ajax异步读取用户信息
			// 将异步加载信息填充到模态窗口的组件之中
			$("#userMid").text(mid) ;
			$("#userid").val(mid) ;
			$.post("pages/back/admin/member/MemberActionBack!show.action",{"member.mid":mid},
					function(data){
				$("#infoAddr").empty() ;
				$("#infoMid").text(data.mid) ;
				$("#infoPhone").text(data.phone) ;
				$("#infoEmail").text(data.email) ;
				$("#infoReg").text(data.reg) ;
				// 处理所有的地址数据
				for (var x = 0 ; x < data.allAddres.length ; x ++) {
					var ct = "<tr>" +
							"	<td class='text-center'>" +
							"		<input type='radio' id='flag' name='flag' "+ (data.allAddres[x].deflag == "1" ? "checked" : "") +">" +
							"	</td>" +
							"	<td class='text-center'>"+data.allAddres[x].receiver+"</td>" +
							"	<td class='text-center'>"+data.allAddres[x].phone+"</td>" +
							"	<td class='text-center'>"+data.allAddres[x].addr+"</td>" +
							"</tr>" ;
					$("#infoAddr").append(ct) ;
				}
				$("#userInfo").modal("toggle") ;	// 显示模态窗口
			},"json") ;
		})
	}) ;
	$("a[id*=editBtn-]").each(function(){
		// 拆分id数据
		var mid = this.id.substring(this.id.indexOf("-") + 1) ;
		$(this).on("click",function(){
			// Ajax异步读取管理员信息
			// 将异步加载信息填充到模态窗口的组件之中
			$("#userMid2").text(mid) ;
			$("#userPassword").modal("toggle") ;	// 显示模态窗口
		}) ;
	}) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			// alert("Ajax异步提交表单") ;
			var mid = $("#userMid2").text() ;
			var password = $("#password").val() ;
			$.post("pages/back/admin/member/MemberActionBack!edit.action",{"member.mid":mid,"member.password":password},function(data){
				$("#userPassword").modal("toggle")
				operateAlert(data.trim() == "true","用户密码修改成功！","用户密码修改失败！") ;
			},"text") ;
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"password" : {
				required : true
			}
		}
	});
})

