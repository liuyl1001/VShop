<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String formUrl = basePath + "pages/back/admin/admin/AdminActionBack!editPassword.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<jsp:include page="/pages/plugins/include_javascript.jsp" />
<script type="text/javascript" src="js/back/admin/admin/admin_password_edit.js"></script>
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
						<strong>修改管理员密码</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" id="myform" method="post" action="<%=formUrl%>">
							<fieldset>
								<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
								<div class="form-group" id="oldpasswordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="oldpassword">原始密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="oldpassword" name="oldpassword"
											class="form-control" placeholder="请输入原始登录密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="oldpasswordMsg"></div>
								</div>
								<div class="form-group" id="newpasswordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="newpassword">新的密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="newpassword" name="newpassword"
											class="form-control" placeholder="请输入新的登录密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="newpasswordMsg"></div>
								</div>
								<div class="form-group" id="confpasswordDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="confpassword">确认密码：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="password" id="confpassword" name="confpassword"
											class="form-control" placeholder="请重复输入密码">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="confpasswordMsg"></div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-3">
										<button type="submit" class="btn btn-primary">修改</button>
										<button type="reset" class="btn btn-warning">重置</button>
									</div>
								</div>
							</fieldset>
						</form>

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
