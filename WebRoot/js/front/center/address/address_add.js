$(function() {
	// 加载所有的省份数据
	$.post("static-txt/front/city/province.txt",{},function(obj){
		$("#address\\.province\\.pid").on("change",function(){	// 实现城市数据追加
			$("#address\\.city\\.cid option").remove() ;	// 清空已有的option信息
			if(/^\d+$/.test(this.value)) {
				// console.log($("#address\\.province\\.pid option:selected").text()) ;
				setAddrVal() ;
				$.post("static-txt/front/city/city-" + this.value + ".txt",{},function(obj2){
					$("#address\\.city\\.cid").append("<option>====== 请选择城市 ======</option>") ;
					for (var y = 0 ; y < obj2.length ; y ++) {
						var cid = obj2[y].cid ;
						var ctitle = obj2[y].title ;
						$("#address\\.city\\.cid").append("<option value='"+cid+"'>"+ctitle+"</option>") ;
					}
					$("#address\\.city\\.cid").on("change",function(){
						setAddrVal() ;
					}) ;
				},"json") ;
			}  
		}) ;  
		$("#address\\.province\\.pid").append("<option>====== 请选择省份 ======</option>") ;
		for (var x = 0 ; x < obj.length ; x ++) {
			var pid = obj[x].pid ;
			var title = obj[x].title ;
			$("#address\\.province\\.pid").append("<option value='"+pid+"'>"+title+"</option>") ;
		}
	},"json") ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
	submitHandler : function(form) {	// 在此处实现数据的提交处理
		var receiver = $("#address\\.receiver").val() ;
		var pid = $("#address\\.province\\.pid").val() ;
		var cid = $("#address\\.city\\.cid").val() ;
		var phone = $("#address\\.phone").val() ;
		var addr = $("#address\\.addr").val() ;
//			console.log(receiver + " ， " + pid + " ， " + cid + " ， " + phone + " ， " + addr) ;
		$.post("pages/front/center/address/AddressActionFront!add.action",
				{"address.receiver":receiver,"address.province.pid":pid,"address.city.cid":cid,"address.phone":phone,"address.addr":addr},function(o) {
					var flag = o.trim().split("|")[0] ;
					operateAlert(flag == "true","联系地址添加成功！","联系地址添加失败！") ;
					if (flag == "true") {
						resetForm() ;
					}
				} , "text") ;
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
			"address.receiver" : {
				required : true,
			},
			"address.phone" : {
				required : true ,
				digits : true
			} ,
			"address.province.pid" : {
				required : true 
			},
			"address.city.cid" : {
				required : true 
			},
			"address.addr" : {
				required : true 
			}
		}
	});
})
function setAddrVal() {
	var province = $("#address\\.province\\.pid option:selected").text() + " " ;
	var city = $("#address\\.city\\.cid option:selected").text() + " " ;
	$("#address\\.addr").val(province + city) ;
}
function resetForm() {	// 重置表单的内容
	$("#address\\.receiver").val("") ;
	$("#address\\.phone").val("") ;
	$("#address\\.addr").val("") ;
}