$(function() {
	$.post("static-txt/front/goods/goods.txt",{},function(data){
		for (var x = 0 ; x < data.allGoods.length ; x ++) {
			var gid = data.allGoods[x].gid ;
			var photo = data.allGoods[x].photo ;
			var title = data.allGoods[x].title ;
			var price = data.allGoods[x].price ;
			$("#goodsDiv").append( 
					"<div class='col-md-3 text-center' style='height:200px;'>" + 
					"	<p><a href='pages/front/goods/GoodsActionFront!show.action?gid="+gid+"'>" +
					"		<img src='upload/goods/"+photo+"' style='width:100px;height:100px;'></a></p>" +
					"		<span class='text-warning h4'><strong>￥"+price+"</strong></span>" + 
					"		<p><a href='pages/front/goods/GoodsActionFront!show.action?gid="+gid+"'>"+title+"</a></p>" +
					"		<button id='addCar-"+gid+"' class='btn btn-primary btn-xs'>" + 
					"		<span class='glyphicon glyphicon-shopping-cart'></span>&nbsp;加入购物车</button></div>") ;
		}
		$("button[id*=addCar-]").each(function(){
			var gid = $(this).attr("id").split("-")[1] ;
			$(this).on("click",function(){
				// console.log("*** gid = " + gid) ;
				$.post("ShopcarActionFront!add.action",{"gid":gid},function(data){
					operateAlert(data.trim() == "true","购物车添加成功！","购物车添加失败！") ;
				},"text") ;
			}) ;
		}) ; 
	},"json") ;
}) 