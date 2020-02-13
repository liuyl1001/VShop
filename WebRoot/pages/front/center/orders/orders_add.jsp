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
<script type="text/javascript" src="js/front/center/orders/orders_add.js"></script>
</head>
<body>
	<div class="container">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;选择配送地址</strong>
						<button id="addAddressBtn" class="btn btn-danger btn-xs">增加新地址</button>
					</div>
					<div class="panel-body">
						<div id="addressList">
							<c:forEach items="${allAddresses}" var="address"> 
								<div id="addr-${address.adid}"><input type="radio" value="${address.adid}" id="adid" name="adid" ${address.deflag == 1 ? "checked" : ""}>
								${address.receiver}，${address.phone}，${address.addr}
								<button class="btn btn-warning btn-xs" id="editAddressBtn-${address.adid}">编辑</button></div> 
							</c:forEach>
						</div>
						<div id="editAddressDiv" style="display:none;border:1px solid red;">
							<form class="form-horizontal" id="editform" method="post">
								<fieldset>
									<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
									<div class="form-group" id="receiverDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="receiver">收件人：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="receiver" name="receiver" class="form-control"
												placeholder="请输入收件人姓名" value="">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="receiverMsg"></div>
									</div>
									<div class="form-group" id="phoneDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="phone">联系电话：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="phone" name="phone" class="form-control"
												placeholder="请输入您的联系电话" value="">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="phoneMsg"></div>
									</div>
									<div class="form-group" id="province.pidDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="province.pid">省份：</label>
										<div class="col-md-5">
											<select id="province.pid" name="province.pid" class="form-control">
											</select>
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="province.pidMsg"></div>
									</div>
									<div class="form-group" id="city.cidDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="city.cid">城市：</label>
										<div class="col-md-5">
											<select id="city.cid" name="city.cid" class="form-control">
											</select>
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="city.cidMsg"></div>
									</div>
									<div class="form-group" id="addrDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="addr">地址：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="addr" name="addr" class="form-control"
												placeholder="请输入您的联系地址" value="">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="addrMsg"></div>
									</div>
									<div class="form-group">
										<div class="col-md-5 col-md-offset-3">
											<input type="hidden" name="ttadid" id="ttadid">
											<button type="submit" class="btn btn-primary">修改</button>
											<button type="reset" class="btn btn-warning">重置</button>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
						<div id="addAddressDiv" style="display:none;border:1px solid red;">
							<form class="form-horizontal" id="myform" method="post">
								<fieldset>
									<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
									<div class="form-group" id="address.receiverDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="address.receiver">收件人：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="address.receiver" name="address.receiver" class="form-control"
												placeholder="请输入收件人姓名" value="">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="address.receiverMsg"></div>
									</div>
									<div class="form-group" id="address.phoneDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="address.phone">联系电话：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="address.phone" name="address.phone" class="form-control"
												placeholder="请输入您的联系电话" value="">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="address.phoneMsg"></div>
									</div>
									<div class="form-group" id="address.province.pidDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="address.province.pid">省份：</label>
										<div class="col-md-5">
											<select id="address.province.pid" name="address.province.pid" class="form-control">
											</select>
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="address.province.pidMsg"></div>
									</div>
									<div class="form-group" id="address.city.cidDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="address.city.cid">城市：</label>
										<div class="col-md-5">
											<select id="address.city.cid" name="address.city.cid" class="form-control">
											</select>
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="address.city.cidMsg"></div>
									</div>
									<div class="form-group" id="address.addrDiv">
										<!-- 定义表单提示文字 -->
										<label class="col-md-3 control-label" for="address.addr">地址：</label>
										<div class="col-md-5">
											<!-- 定义表单输入组件 -->
											<input type="text" id="address.addr" name="address.addr" class="form-control"
												placeholder="请输入您的联系地址" value="">
										</div>
										<!-- 定义表单错误提示显示元素 -->
										<div class="col-md-4" id="address.addrMsg"></div>
									</div>
									<div class="form-group">
										<div class="col-md-5 col-md-offset-3">
											<button type="submit" class="btn btn-primary">增加</button>
											<button type="reset" class="btn btn-warning">重置</button>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
					<div class="panel-footer">
						
					</div>
				</div>
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-list"></span>&nbsp;要购买的商品内容</strong>
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
								<c:forEach items="${carGoods}" var="goods">
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
											<input type="text" id="amount-${goods.gid}" name="amount-${goods.gid}" class="shopcar-form-control" size="4" maxlength="4" value="${allCar[goods.gid]}" readonly="readonly">
											<button class="btn btn-primary" id="add-${goods.gid}">+</button> 
										</td>
										<td class="text-center"><button class="btn btn-primary" id="updateBtn-${goods.gid}">修改</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-right">
							总价￥<span id="allPrice" class="text-danger h2"></span>
						</div>
						<div>
							<button class="btn btn-primary" id="editBtn">修改数量</button>
							<button class="btn btn-danger" id="rmBtn">移出购物车</button>
						</div>
					</div>
					<div class="panel-footer">
						
					</div>
				</div>
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="text-right">
					<button class="btn btn-lg btn-danger" id="createOrdersBtn">下单</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
