<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="js/back/admin/orders/orders_list.js"></script>
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
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;订单信息列表</strong>
					</div>
					<div class="panel-body">
						<jsp:include page="/pages/plugins/split_page_plugin_search.jsp"/>
						<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center"><strong>订单编号</strong></th>
									<th class="text-center"><strong>用户ID</strong></th>
									<th class="text-center"><strong>总价</strong></th>
									<th class="text-center"><strong>下单日期</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allOrders}" var="order">
								<tr>
									<td class="text-center">${order.oid}</td>
									<td class="text-center"><a id="userBtn-mldn" onmouseover="this.style.cursor='hand'">${order.member.mid}</a></td>
									<td class="text-center">${order.price}</td>
									<td class="text-center">${order.subdate}</td>
									<td class="text-center">
										<a type="button" class="btn btn-info btn-xs" href="pages/back/admin/orders/OrdersActionBack!show.action?oid=${order.oid}">
											<span class="glyphicon glyphicon-list-alt"></span>&nbsp;查看详情</a>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="splitBarDiv" style="float:right">
							<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/> 
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
		<jsp:include page="/pages/plugins/back/include_member_show.jsp"/>
	</div>
</body>
</html>
