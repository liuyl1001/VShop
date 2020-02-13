$(function() {
	$.post("pages/back/admin/IndexActionBack!show.action",{}, 
			function(data){
		$("#goodsInfo").text(data.goodsCount) ;
		$("#ordersInfo").text(data.ordersCount) ;
		$("#todayInfo").text(data.todayCount) ;
	},"json") ;
	
	$(createStaticBtn).on("click",function() {
		$.post("pages/back/admin/StaticDataActionBack!list.action",{},function(txt){
			operateAlert(txt.trim()=="true","商品分类数据生成成功！","商品分类数据生成失败！") ;
		},"text") ;
	}) ;	
	
	$(createGoodsBtn).on("click",function() {
		$.post("pages/back/admin/StaticDataActionBack!listGoods.action",{},function(txt){
			operateAlert(txt.trim()=="true","商品信息生成成功！","商品信息生成失败！") ;
		},"text") ;
	}) ;	
	
	$(createProvinceBtn).on("click",function() {
		$.post("pages/back/admin/StaticDataActionBack!listProvince.action",{},function(txt){
			operateAlert(txt.trim()=="true","省份与城市信息生成成功！","省份与城市信息生成失败！") ;
		},"text") ;
	}) ;	
})