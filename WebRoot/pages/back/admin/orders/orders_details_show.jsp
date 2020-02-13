<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String updateUrl = basePath + "pages/manager/manager/ManagerActionBack!update.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1"> 
<jsp:include page="/pages/plugins/include_javascript.jsp" /> 
<script type="text/javascript" src="js/front/center/orders/orders_details_show.js"></script>
</head>
<body class="back">
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/back/include_menu_admin.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;订单详情</strong>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3"><strong>订单编号：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.oid}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>下单用户：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.member.mid}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>下单日期：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.subdate}</div>
						</div>
						<div class="row">
							<div class="col-md-3"><strong>总金额：</strong></div>
							<div class="col-md-9 col-md-pull-1">${orders.price}</div>
						</div>
						<div class="row">
							<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>价格</strong></th>
									<th class="text-center"><strong>购买数量</strong></th>
									<th class="text-center"><strong>总额</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allGoods}" var="goods">
									<tr>
										<td class="text-center">
											<a href="pages/front/goods/GoodsActionFront!show.action?gid=${goods.gid}" onmouseover="this.style.cursor='hand'">${goods.title}</a>
										</td>
										<td class="text-center">${goods.price}</td>
										<td class="text-center">${details[goods.gid]}</td>
										<td class="text-center">${goods.price*details[goods.gid]}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
