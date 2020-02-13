<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
		<a class="navbar-brand" href="index.jsp"><strong>微商城</strong></a>
	</div>
	<ul class="nav navbar-nav">
		<li><a href="pages/back/admin/admin_index.jsp">管理首页</a></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">用户管理<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="pages/back/admin/member/MemberActionBack!list.action"><span class="glyphicon glyphicon-user"></span>&nbsp;用户列表</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">商品管理<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="pages/back/admin/goods/GoodsActionBack!addPre.action"><span class="glyphicon glyphicon-gift"></span>&nbsp;添加商品</a></li>
				<li><a href="pages/back/admin/goods/GoodsActionBack!list.action"><span class="glyphicon glyphicon-list"></span>&nbsp;商品列表</a></li>
				<li class="divider"><a href="#"></a></li>
				<li><a href="pages/back/admin/item/ItemActionBack!list.action"><span class="glyphicon glyphicon-screenshot"></span>&nbsp;分类管理</a></li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown">订单管理<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="pages/back/admin/orders/OrdersActionBack!list.action">
					<span class="glyphicon glyphicon-list-alt"></span>&nbsp;订单列表</a></li>
			</ul></li>
	</ul> 
	<ul class="nav navbar-nav navbar-right">
		<li class="dropdown"><a href="javascript:;"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="glyphicon glyphicon-user"></i>&nbsp;${mid}&nbsp;<span
				class="glyphicon glyphicon-chevron-down"></span></a>
			<ul class="dropdown-menu main-list">
				<li><a href="pages/back/admin/admin/admin_password_edit.jsp"><i class="glyphicon glyphicon-edit"></i>&nbsp;修改密码</a></li>
				<li class="divider"></li>
				<li><a href="pages/back/LoginActionBack!logout.action"><i class="glyphicon glyphicon-off"></i>&nbsp;登录注销</a></li>
			</ul></li>
		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	</ul>
</nav>
