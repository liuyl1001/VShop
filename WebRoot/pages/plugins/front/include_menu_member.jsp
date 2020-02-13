<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="index.jsp"><strong>微商城</strong></a>
	</div>
	<ul class="nav navbar-nav">
		<c:if test="${id == null}">
			<li><a href="login.jsp"><span class="glyphicon glyphicon-certificate"></span>&nbsp;登录</a></li>
		</c:if>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><span class="glyphicon glyphicon-globe"></span>&nbsp;个人中心<span class="caret"></span></a>
			<ul class="dropdown-menu"> 
				<li><a href="pages/front/center/member/MemberActionFront!editBasicPre.action">
					<span class="glyphicon glyphicon-user"></span>&nbsp;个人资料</a></li>
				<li><a href="pages/front/center/member/member_password_edit.jsp">
					<span class="glyphicon glyphicon-cog"></span>&nbsp;修改密码</a></li>
				<li><a href="pages/front/center/address/AddressActionFront!list.action">
					<span class="glyphicon glyphicon-plane"></span>&nbsp;地址管理</a></li>
				<li class="divider">&nbsp;</li>
				<li><a href="pages/front/center/orders/OrdersActionFront!list.action">
					<span class="glyphicon glyphicon-list-alt"></span>&nbsp;订单列表</a></li>
			</ul></li>
		<c:if test="${id != null }">
			<li><a href="ShopcarActionFront!list.action">
				<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;我的购物车</a></li>
		</c:if>
		<c:if test="${id == null }">
		<li><a href="ShopcarActionFront!list.action">
			<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;我的购物车</a></li>
		</c:if>
	</ul>  
	<c:if test="${id != null}">
	<ul class="nav navbar-nav navbar-right">
		<li class="dropdown"><a href="javascript:;"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="glyphicon glyphicon-user"></i>&nbsp;${id}&nbsp;<span
				class="glyphicon glyphicon-chevron-down"></span></a>
			<ul class="dropdown-menu main-list">
				<li><a href="pages/front/center/member/member_password_edit.jsp"><i class="glyphicon glyphicon-edit"></i>&nbsp;修改密码</a></li>
				<li class="divider"></li>
				<li><a href="LoginActionFront!logout.action"><i class="glyphicon glyphicon-off"></i>&nbsp;登录注销</a></li>
			</ul></li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	</ul>
	</c:if>
</nav>
