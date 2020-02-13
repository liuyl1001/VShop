<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="js/back/admin/admin_index.js"></script>
</head>
<body class="back">
	<div class="container contentback" >
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/back/include_menu_admin.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong>微商城后台管理系统</strong>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover">
							<tr>
								<td colspan="3"><span class="h1"><span class="glyphicon glyphicon-user"></span>&nbsp;欢迎“${mid}”光临！</span></td>
							</tr>
							<tr>
								<td rowspan="5" style="width:130px;">
									<img src="images/admin_logo.png" class="image" style="height:128px;width:128px;">
								</td>
							</tr>
							<tr>
								<td style="width:240px;"><strong>商品数量：</strong></td>
								<td><span class="glyphicon glyphicon-list-alt"></span>&nbsp;<a class="text-danger" href="<%=basePath%>pages/back/admin/goods/GoodsActionBack!list.action" id="goodsInfo"></a>件商品</td>
							</tr>
							<tr>
								<td><strong>订单总量</strong></td>
								<td><span class="glyphicon glyphicon-gift"></span>&nbsp;<a class="text-info" href="<%=basePath%>pages/back/admin/orders/OrdersActionBack!list.action" id="ordersInfo"></a>个订单</td>
							</tr>
							<tr>
								<td><strong>今日订单量：</strong></td>
								<td><span class="glyphicon glyphicon-tag"></span>&nbsp;<a class="text-warning" href="<%=basePath%>pages/back/admin/orders/OrdersActionBack!list.action" id="todayInfo"></a>个新订单</td>
							</tr>
							<tr>
								<td colspan="2">
									<button id="createStaticBtn" class="btn btn-primary btn-xs">生成分类数据</button>
									<button id="createGoodsBtn" class="btn btn-primary btn-xs">生成商品数据</button>
									<button id="createProvinceBtn" class="btn btn-primary btn-xs">生成省份数据</button>
									<a href="pages/back/admin/admin/admin_password_edit.jsp" class="btn btn-primary btn-xs">修改密码</a>
									<a href="pages/back/LoginActionBack!logout.action" class="btn btn-danger btn-xs">登录注销</a>
								</td>
							</tr>
						</table>
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
	</div>
</body>
</html>
