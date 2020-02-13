$(function() {
	$("#selectAll").on("click",function(){
		checkboxSelectAll('address.adid',this.checked) ;
	}) ;
	$("#delBtn").on("click",function(){	// 绑定用户锁定操作
		var adid = new Array() ;	// 保存所有要删除的地址ID
		var foot = 0 ;
		var ids = "" ;
		$("input[id='address.adid']:checked").each(function() {
			ids += $(this).val() + "|" ;
			adid[foot ++] = $(this).val() ;
		}) ;
		if (ids == "") {
			$("#alertDiv").attr("class","alert alert-danger") ;
	        $("#alertText").text("您还未选择任何数据，请确认您的操作！") ;
			$("#alertDiv").fadeIn(1000,function(){
	            $("#alertDiv").fadeOut(3000) ;
	        }) ;
		} else {
			if (window.confirm("您确定要继续执行此操作吗？")) {
				$.post("pages/front/center/address/AddressActionFront!rm.action",{"ids":ids},
						function(obj){
					for (var x = 0 ; x <adid.length ; x ++ ) {
						$("#addr-" + adid[x]).remove() ;
					}
					operateAlert(obj.trim() == "true","联系地址删除成功！","联系地址删除失败！") ;
				},"text") ;
			}
		}
	}) ;
})