<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
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
<script type="text/javascript" src="js/back/admin/item/subitem_list.js"></script>
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
						<strong>商品子分类列表</strong>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<td style="width:30%;" class="text-center"><strong>栏目名称</strong></td>
									<td style="width:10%;" class="text-center"><strong>操作</strong></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allSubitems}" var="subitem">
									<tr>
										<td class="text-center">
											<input type="text" id="title-${subitem.sid}" name="title-${subitem.sid}" class="form-control input-sm" value="${subitem.title}">
										</td>
										<td class="text-center">
											<button class="btn btn-primary" id="updateBtn-${subitem.item.iid}-${subitem.sid}"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
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
