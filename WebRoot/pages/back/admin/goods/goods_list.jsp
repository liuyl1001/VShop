<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editPreUrl = basePath + "pages/back/admin/goods/GoodsActionBack!editPre.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<jsp:include page="/pages/plugins/include_javascript.jsp" />
<script type="text/javascript" src="js/back/admin/goods/goods_list.js"></script>
</head>
<body class="back">
	<div class="container">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/back/include_menu_admin.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;商品信息列表</strong>
					</div>
					<div class="panel-body">
						<jsp:include page="/pages/plugins/split_page_plugin_search.jsp"/>
						<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center">
										<input type="checkbox" id="selectAll">
									</th>
									<th class="text-center"><strong>商品名称</strong></th>
									<th class="text-center"><strong>发布者</strong></th>
									<th class="text-center"><strong>价格</strong></th>
									<th class="text-center"><strong>发布日期</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allGoodss}" var="goods">
									<tr>
										<td class="text-center">
											<input type="checkbox" id="goods.gid" name="goods.gid" value="${goods.gid}">
										</td>
										<td class="text-center">
											<a id="showBtn-${goods.gid}" onmouseover="this.style.cursor='hand'">${goods.title}</a>
										</td>
										<td class="text-center">${goods.member.mid}</td>
										<td class="text-center">${goods.price}</td>
										<td class="text-center">${goods.pubdate}</td>
										<td class="text-center">
											<a type="button" class="btn btn-info btn-xs" href="<%=editPreUrl%>?goods.gid=${goods.gid}">
												<span class="glyphicon glyphicon-edit"></span>&nbsp;编辑</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div>
							<button class="btn btn-danger" id="rmBtn">删除商品</button>
						</div>
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
		<jsp:include page="/pages/plugins/back/include_goods_show.jsp"/>
	</div>
</body>
</html>
