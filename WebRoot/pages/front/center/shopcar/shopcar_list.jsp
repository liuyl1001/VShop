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
<script type="text/javascript" src="js/front/center/shopcar/shopcar_list.js"></script>
</head>
<body class="back">
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-2 col-xs-2">
				<jsp:include page="/pages/plugins/front/center/include_center_item.jsp">
					<jsp:param value="4" name="ch"/>
				</jsp:include>
			</div>
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;我的购物车</strong>
					</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center">
										<input type="checkbox" id="selectAll">
									</th>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>商品单价</strong></th>
									<th class="text-center"><strong>购买数量</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${all}" var="goods">
									<tr id="sc-${goods.gid}"> 
										<td class="text-center">
											<input type="checkbox" id="goods.gid" name="goods.gid" value="${goods.gid}">
										</td>
										<td class="text-center">
											<a href="pages/front/goods/GoodsActionFront!show.action?gid=${goods.gid}" onmouseover="this.style.cursor='hand'">${goods.title}</a>
										</td>
										<td class="text-center"><span id="price-${goods.gid}">${goods.price}</span></td>
										<td class="text-center">
											<button class="btn btn-primary" id="sub-${goods.gid}">-</button>
											<input type="text" id="amount-${goods.gid}" name="amount-${goods.gid}" class="shopcar-form-control" size="4" maxlength="4" value="${allGoods[goods.gid]}" readonly="readonly">
											<button class="btn btn-primary" id="add-${goods.gid}">+</button> 
										</td>
										<td class="text-center"><button class="btn btn-primary" id="updateBtn-${goods.gid}">修改</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-right">
							总价￥<span id="allPrice" class="text-danger h2"></span>
							<a href="pages/front/center/orders/OrdersActionFront!addPre.action" class="btn btn-lg btn-danger">创建订单</a>
						</div>
						<div>
							<button class="btn btn-primary" id="editBtn">修改数量</button>
							<button class="btn btn-danger" id="rmBtn">移出购物车</button>
						</div>
					</div>
					<div class="panel-footer">
						<jsp:include page="/pages/plugins/include_alert.jsp"/>
					</div>
				</div>
			</div>
		</div>
		<div id="footDiv" class="row navbar-fixed-bottom">
			<jsp:include page="/pages/plugins/include_foot.jsp" />
		</div>
		<jsp:include page="/pages/plugins/back/include_goods_show.jsp"/>
	</div>
</body>
</html>
