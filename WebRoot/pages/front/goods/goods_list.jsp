<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<jsp:include page="/pages/plugins/include_javascript.jsp" />
<script type="text/javascript" src="js/front/goods/goods_list.js"></script>
<script type="text/javascript" src="js/menu_car.js"></script>
</head>
<body>
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-3">
				<jsp:include page="/pages/plugins/front/include_menu_item.jsp"/>
			</div>
			<div class="col-md-9">
				<div class="row">
					<jsp:include page="/pages/plugins/split_page_plugin_search.jsp"/>
				</div>
				<div class="row">
					<c:forEach items="${allGoodss}" var="goods">
						<div class="col-md-3 text-center" style='height:200px;'>
							<p>
								<a href="pages/front/goods/GoodsActionFront!show.action?gid=${goods.gid}">
									<img src="upload/goods/${goods.photo}" style='width:100px;height:100px;'></a></p>
							<span class="text-warning h4"><strong>￥${goods.price}</strong></span>
							<p><a href="pages/front/goods/GoodsActionFront!show.action?gid=${goods.gid}">${goods.title}</a></p>
							<button id="addCar-${goods.gid}" class="btn btn-primary btn-xs">
								<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;加入购物车</button>
						</div> 
					</c:forEach>
			</div>
			<div id="splitBarDiv" style="float:right">
				<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/> 
			</div>
		</div>
		<div class="row" style="height:50px;">
			<jsp:include page="/pages/plugins/include_alert.jsp"/>
		</div>
	</div>
</body>
</html>
