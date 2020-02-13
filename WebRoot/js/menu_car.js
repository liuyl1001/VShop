$(function() {
	// 加载所有的栏目信息
	$.post("static-txt/front/item-all.txt",{},function(data){
		for (var x = 0 ; x < data.allItems.length ; x ++) {
			var iid = data.allItems[x].iid ;
			var title = data.allItems[x].title ;
			var str = 
					"<div class='panel panel-primary'>" + 
					"	<div class='panel-heading'>" + 
					"		<h4 class='panel-title'>" + 
					"			<a data-toggle='collapse' data-parent='#item' href='#content-" + iid + "'>" + title + "</a>" +
					"		</h4>" + 
					"	</div>" + 
					"	<div id='content-"+iid+"' class='panel-collapse collapse " + (x == 0 ? 'in' : '')  + "'>" + 
					"		<div class='panel-body'>" + 
					"			<div class='row'>" ;
			for (var y = 0 ; y < data.allItems[x].subitems.length ; y ++) {
				var sid = data.allItems[x].subitems[y].sid ;
				var stitle = data.allItems[x].subitems[y].title ;
				str += "			<div class='col-md-4'><a id='sid-"+sid+"'>"+stitle+"</a></div>" ; 
			}
			str += "</div></div></div></div>" ;
			$("#item").append(str) ;
		}
		$("a[id*='sid-']").each(function(){
			// console.log(this.id) ;
			var sid = this.id.split("-")[1] ;	// 取出每一个sid
			$(this).attr("href","pages/front/goods/GoodsActionFront!listBySubitem.action?sid=" + sid) ;
		}) ;
	},"json") ; 
}) 