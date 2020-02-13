$(function() {
	$.post("static-txt/back/item.txt",{},function(obj){
		for (var x = 0 ; x < obj.allItems.length ; x ++) {
			var iid = obj.allItems[x].iid ;
			var title = obj.allItems[x].title ;
			// console.log("iid = " + iid + "，title = " + title) ;
			if (temp_iid == iid) {
				$("#goods\\.item\\.iid").append("<option value='"+iid+"' selected>"+title+"</option>") ;
			} else {
				$("#goods\\.item\\.iid").append("<option value='"+iid+"'>"+title+"</option>") ;
			}
		}
	},"json") ;
	$.post("static-txt/back/subitem-"+temp_iid+".txt",{},function(obj){
		for(var x = 0 ; x < obj.length ; x ++) {
			var sid = obj[x].sid ;
			var title = obj[x].title ;
			if (temp_sid == sid) {
				$("#goods\\.subitem\\.sid").append("<option value='"+sid+"' selected>"+title+"</option>") ;
			} else {
				$("#goods\\.subitem\\.sid").append("<option value='"+sid+"'>"+title+"</option>") ;
			}
		}			
	},"json") ;
	$("#goods\\.item\\.iid").on("change",function(){
		$("#goods\\.subitem\\.sid option").remove() ;
		// console.log(this.value) ;
		$.post("static-txt/back/subitem-"+this.value+".txt",{},function(obj){
			for(var x = 0 ; x < obj.length ; x ++) {
				var sid = obj[x].sid ;
				var title = obj[x].title ;
				$("#goods\\.subitem\\.sid").append("<option value='"+sid+"'>"+title+"</option>") ;
			}			
		},"json") ;
	}) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
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
			"goods.title" : {
				required : true,
				//remote : {
//									url : "check.jsp", // 后台处理程序
//									type : "post", // 数据发送方式
//									dataType : "html", // 接受数据格式
//									data : { // 要传递的数据
//										code : function() {
//											return $("#code").val();
//										}
//									},
//									dataFilter : function(data, type) {
//										if (data.trim() == "true")
//											return true;
//										else
//											return false;
//									}
				//}
			},
			"goods.iid" : {
				required : true
			} ,
			"goods.sid" : {
				required : true
			},
			"goods.price" : {
				required : true ,
				number : true
			},
			"goods.photo" : {
				required : true
			},
			"goods.note" : {
				required : true
			}
		}
	});
})