$(function() {
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