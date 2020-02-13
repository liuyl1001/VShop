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
<script type="text/javascript" src="js/front/goods/goods_show.js"></script>
</head>
<body class="back">
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-edit"></span>&nbsp;商品信息</strong>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2 text-center"> 
								<p><img class="img" src="upload/goods/${goods.photo}" style="width:125px;"></p>
								<button id="addCar-${goods.gid}" class="btn btn-lg btn-danger">
									<span class="glyphicon glyphicon-eye-open"></span>&nbsp;加入购物车</button>
							</div>
							<div class="col-md-10">
								<div class="row">
									<div class="col-md-2 h3"><strong>商品名称：</strong></div>
									<div class="col-md-3 h3">${goods.title}</div>
								</div>
								<div class="row">
									<div class="col-md-2 h3"><strong>商品价格：</strong></div>
									<div class="col-md-3 h3">￥${goods.price}</div> 
								</div>
								<div class="row"> 
									<div class="col-md-12">
										${goods.note}
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="row" style="height:50px;">
							<jsp:include page="/pages/plugins/include_alert.jsp"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
