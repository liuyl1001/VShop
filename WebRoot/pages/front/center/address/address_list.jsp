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
<script type="text/javascript" src="js/front/center/address/address_list.js"></script>
</head>
<body>
	<div class="container">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-2 col-xs-2">
				<jsp:include page="/pages/plugins/front/center/include_center_item.jsp">
					<jsp:param value="5" name="ch"/>
				</jsp:include>
			</div>
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;个人地址信息列表</strong>
					</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th class="text-center">
										<input type="checkbox" id="selectAll">
									</th>
									<th class="text-center"><strong>收件人</strong></th>
									<th class="text-center"><strong>联系电话</strong></th>
									<th class="text-center"><strong>地址</strong></th>
									<th class="text-center"><strong>操作</strong></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${allAddresses}" var="address">
									<tr id="addr-${address.adid}">
										<td class="text-center">
											<input type="checkbox" id="address.adid" name="address.adid" value="${address.adid}">
										</td>
										<td class="text-center">${address.receiver}</td>
										<td class="text-center">${address.phone}</td>
										<td class="text-center">${address.addr}</td>
										<td class="text-center"><a id="editBtn-1" href="pages/front/center/address/address_edit.jsp?adid=${address.adid}" class="btn btn-primary btn-xs">编辑</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-right">
							<button type="button" class="btn btn-danger" id="delBtn">删除地址</button>
							<a href="pages/front/center/address/address_add.jsp" class="btn btn-primary">增加新地址</a>
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
