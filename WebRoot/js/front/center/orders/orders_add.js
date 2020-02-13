$(function() { 
	$("#createOrdersBtn").on("click",function() {
		// 首先要判断购物车之中是否存在有待购买的数据信息;
		var len = $("[id*=sc-]").length ; 
		if (len > 0) {	// 现在有购买的数据记录
			var selAdid = $("#adid:checked") ;
			if(selAdid.length == 0) {
				alert("请选择收货地址！") ; 
			} else {
				if (selAdid.length == 1) {	// 表示有一个被选中
					window.location = "pages/front/center/orders/OrdersActionFront!add.action?adid=" + selAdid.val() ;
				}
			}
		} else {
			alert("您还未购买任何的商品，请先去挑选宝贝！") ;
			window.location = "index.jsp" ;	// 回到首页
		}
	}) ;
	bindFun() ;
	$("#addAddressBtn").on("click",function(){
		$("#addAddressDiv").fadeIn(100,function(){	// 显示完成之后进行数据的加载处理
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
		}) ;
	}) ;
	$("#allPrice").text(calSum()) ;
	$("#selectAll").on("click",function(){
		checkboxSelectAll('goods.gid',this.checked) ;
	}) ;
	// 设置修改单独数量的操作
	$("button[id*=updateBtn-]").each(function(){
		var gid = this.id.split("-")[1] ;
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			$.post("ShopcarActionFront!editSingle.action",{"gid":gid,"amount":amount},function(data){
				operateAlert(data.trim() == "true","商品数量修改成功！","商品数量修改失败！") ;
				if (amount == 0) {	// 表示当前的商品数据不需要了
					$("#sc-" + gid).remove() ;	// 直接删除
				}
			},"text") ;
		}) ;
	}) ;  
	// 实现整体修改操作的功能
	$(editBtn).on("click",function(){
		// 定义一个数组，保存所有需要被删除的gid数据
		var delGid = new Array() ;
		var foot = 0 ;
		var data = "" ; // 实现最终数据拼凑的字符串
		$("[id*=amount-]").each(function(){
			var gid = this.id.split("-")[1] ;
			var amount = this.value ;
			if (amount != "0") {
				data += gid + ":" + amount + "|" ;
			} else {
				delGid[foot ++] = gid ;
			}
		}) ;
		// 进行ajax异步数据处理操作
		$.post("ShopcarActionFront!edit.action",{"data":data},function(obj){
			operateAlert(obj.trim() == "true","商品数量修改成功！","商品数量修改失败！") ;
			for (var x = 0 ; x < delGid.length ; x ++) {
				$("#sc-" + delGid[x]).remove() ;
			}
		},"text") ; 
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		var data = "" ;
		$(":checked").each(function() {
			if(this.id == "goods.gid") {	// 要删除的内容
				data += this.value + "|" ;
			}
		}) ;
		if (data != "") {
			$.post("ShopcarActionFront!rm.action",{"data":data},function(obj){
				operateAlert(obj.trim() == "true","商品已从购物车中成功移除！","商品从购物车移除失败！") ;
				$(":checked").each(function() {
					$("#sc-" + this.value).remove() ;
				});
				$("#allPrice").text(calSum()) ;
			},"text") ;
		}
	}) ;
	$("button[id*=add-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			$("#amount-" + gid).val(amount+1) ;
			$("#allPrice").text(calSum()) ;
		})
	}) ;
	$("button[id*=sub-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			if (amount > 0) {
				$("#amount-" + gid).val(amount - 1) ;
				$("#allPrice").text(calSum()) ;
			}
		})
	}) ;

	$("#editform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			var adid = $("#ttadid").val() ;
			var receiver = $("#receiver").val() ;
			var pid = $("#province\\.pid").val() ;
			var cid = $("#city\\.cid").val() ;
			var phone = $("#phone").val() ;
			var addr = $("#addr").val() ; 
//				console.log(receiver + " ， " + pid + " ， " + cid + " ， " + phone + " ， " + addr) ;
			$.post("pages/front/center/address/AddressActionFront!edit.action",
					{"address.adid":adid,"address.receiver":receiver,"address.province.pid":pid,"address.city.cid":cid,"address.phone":phone,"address.addr":addr},function(o) {
						//operateAlert(o.trim() == "true","联系地址修改成功！","联系地址修改失败！") ;
						$("#editAddressDiv").fadeOut(50) ;
						$("#addr-" + adid).html("<div id='addr-"+adid+"'><input type='radio' value='"+adid+"' id='adid' name='adid' >" + 
								receiver + "，" + phone + "，" + addr + 
								"<button class='btn btn-warning btn-xs' id='editAddressBtn-"+adid+"'>编辑</button></div>") ;
						bindFun() ;
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
			"receiver" : {
				required : true,
			},
			"phone" : {
				required : true ,
				digits : true
			} ,
			"province.pid" : {
				required : true 
			},
			"city.cid" : {
				required : true 
			},
			"addr" : {
				required : true  
			}
		}
	});
	
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
					var flag = o.trim().split("|") [0] ;
					var tempAdid = o.trim().split("|") [1] ; 
					operateAlert(flag == "true","联系地址添加成功！","联系地址添加失败！") ;
					if (flag == "true") {
						resetForm() ;
						// 将新的地址追加到项目列表之中。
						$("#addressList").append("<div id='addr-"+tempAdid+"'><input type='radio' value='"+tempAdid+"' id='adid' name='adid' >" + 
								receiver + "，" + phone + "，" + addr + 
								"<button class='btn btn-warning btn-xs' id='editAddressBtn-"+tempAdid+"'>编辑</button></div>") ;
						$("#addAddressDiv").fadeOut(10) ;
						bindFun() ;
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
function calSum() {
	var sumPrice = 0.0 ;	// 保存总价
	// 计算购买的商品的总价数据
	$("span[id*=price-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		var price = $(this).text() ;	// 取得文本内容
		var amount = $("#amount-" + gid).val() ;	// 直接取得value属性
		sumPrice += parseFloat(price) * parseInt(amount) ;
	}) ;
	return round(sumPrice,2) ;
}

function setAddrVal() {
	var province = $("#address\\.province\\.pid option:selected").text() + " " ;
	var city = $("#address\\.city\\.cid option:selected").text() + " " ;
	$("#address\\.addr").val(province + city) ;
}

function setAddrVal2() {
	var province = $("#province\\.pid option:selected").text() + " " ;
	var city = $("#city\\.cid option:selected").text() + " " ;
	$("#addr").val(province + city) ;
}

function resetForm() {	// 重置表单的内容
	$("#address\\.receiver").val("") ;
	$("#address\\.phone").val("") ;
	$("#address\\.addr").val("") ;
}
function bindFun() {
	$("[id*=editAddressBtn-]").each(function(){
		$(this).unbind("click") ;	// 先解除所有的事件
	}) ;
	$("[id*=editAddressBtn-]").each(function(){
		var tAdid = this.id.split("-")[1] ;
		$(this).on("click",function(){ 
			$("#editAddressDiv").fadeIn(100,function(){
				$.post("pages/front/center/address/AddressActionFront!editPre.action",{"adid":tAdid},
						function(addobj){
					$("#receiver").val(addobj.receiver) ;
					$("#phone").val(addobj.phone) ; 
					$("#addr").val(addobj.addr) ; 
					$("#ttadid").val(addobj.adid) ;
					$.post("static-txt/front/city/province.txt",{},function(obj){
						$("#province\\.pid").on("change",function(){	// 实现城市数据追加
							$("#city\\.cid option").remove() ;	// 清空已有的option信息
							if(/^\d+$/.test(this.value)) {
								// console.log($("#address\\.province\\.pid option:selected").text()) ;
								setAddrVal2() ;
								$.post("static-txt/front/city/city-" + this.value + ".txt",{},function(obj2){
									$("#city\\.cid").append("<option>====== 请选择城市 ======</option>") ;
									for (var y = 0 ; y < obj2.length ; y ++) {
										var cid = obj2[y].cid ;
										var ctitle = obj2[y].title ;
										$("#city\\.cid").append("<option value='"+cid+"'>"+ctitle+"</option>") ;
									}
									$("#city\\.cid").on("change",function(){
										setAddrVal2() ;
									}) ;
								},"json") ;
							}  
						}) ;  
						for (var x = 0 ; x < obj.length ; x ++) {
							var pid = obj[x].pid ;
							var title = obj[x].title ;
							if (pid == addobj.pid) {
								$("#province\\.pid").append("<option value='"+pid+"' selected>"+title+"</option>") ;
							} else {
								$("#province\\.pid").append("<option value='"+pid+"'>"+title+"</option>") ;
							}
						}
						$.post("static-txt/front/city/city-" + addobj.pid + ".txt",{},function(obj3){
							for (var y = 0 ; y < obj3.length ; y ++) {
								var cid = obj3[y].cid ;
								var ctitle = obj3[y].title ;
								if (cid == addobj.cid) {
									$("#city\\.cid").append("<option value='"+cid+"' selected>"+ctitle+"</option>") ;
								} else {
									$("#city\\.cid").append("<option value='"+cid+"'>"+ctitle+"</option>") ;
								}
							}
							$("#city\\.cid").on("change",function(){
								setAddrVal2() ;
							}) ;
						},"json") ;
					},"json") ;
				},"json") ; 
			}) ;
		}) ;
	});
}