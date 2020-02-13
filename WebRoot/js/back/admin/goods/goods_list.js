$(function(){
	$("#selectAll").on("click",function(){
		checkboxSelectAll('goods.gid',this.checked) ;
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		operateChecked("确定要删除这些商品吗？","goods.gid",'pages/back/admin/goods/GoodsActionBack!rm.action?p=p') ;
	}) ;
	$("a[id*=showBtn-]").each(function(){
		// 拆分id数据
		var gid = this.id.split("-")[1] ;
		$(this).on("click",function(){
			console.log("商品ID：" + gid) ;
			// Ajax异步读取用户信息，进行数据的填充，而所有的填充数据的id都在单独的包含文件里面
			$.post("pages/back/admin/goods/GoodsActionBack!show.action",{"gid":gid},
					function(obj){
				$("#goodsImg").attr("src","upload/goods/" + obj.photo) ;
				$("#goodsImg").attr("style","width:160px;") ;
				$("#title").text(obj.title) ;
				$("#ititle").text(obj.ititle) ;
				$("#stitle").text(obj.stitle) ;
				$("#price").text(obj.price) ;
				$("#mid").text(obj.mid) ;
				$("#pubdate").text(obj.pubdate) ;
				$("#note").html(obj.note) ;
				// 将异步加载信息填充到模态窗口的组件之中
				$("#goodsInfo").modal("toggle") ;	// 显示模态窗口
			},"json") ;
		})
	}) ;
}) ;