$(function() {
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
})