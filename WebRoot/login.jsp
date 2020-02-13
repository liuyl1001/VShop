<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "LoginActionFront!login.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1"> 
<jsp:include page="/pages/plugins/include_javascript.jsp" /> 
<script type="text/javascript" src="js/login.js"></script>
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
						<strong><span class="glyphicon glyphicon-user"></span>&nbsp;用户登录</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" action="<%=loginUrl%>" id="myform" method="post">
							<fieldset>
								<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
								<div class="form-group" id="idDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="id">登录名：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="text" id="id" name="id" class="form-control"
											placeholder="用户名 / 邮箱 / 手机">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="idMsg"></div>
								</div>
								<div class="form-group" id="member.passwordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="member.password">登录密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="member.password" name="member.password" class="form-control"
											placeholder="请输入登录密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="member.passwordMsg"></div>
								</div>
								<div class="form-group" id="codeDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="code">验证码：</label>
									<div class="col-md-3">
										<!-- 定义表单输入组件 -->
										<input type="text" id="code" name="code" class="form-control"
											placeholder="验证码" size="4" maxlength="4">
									</div> 
									<div class="col-md-2">
										<img src="ImageCode" id="imageCode" title="看不清？单击换一张图片">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="codeMsg"></div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-3">
										<button type="submit" class="btn btn-primary">登录</button>
										<a href="regist.jsp" class="btn btn-warning">注册</a>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="panel-footer">
						<div class="alert alert-success" id="alertDiv" style="display: none;">
	                        <button type="button" class="close" data-dismiss="alert">&times;</button>
	                        <span id="alertText"></span>
	                    </div>
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
