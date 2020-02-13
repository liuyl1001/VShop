<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl = basePath + "pages/front/center/member/MemberActionFront!editBasic.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1"> 
<jsp:include page="/pages/plugins/include_javascript.jsp" /> 
<script type="text/javascript" src="js/front/center/member/member_edit.js"></script>
</head>
<body class="back">
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-2 col-xs-2">
				<jsp:include page="/pages/plugins/front/center/include_center_item.jsp">
					<jsp:param value="2" name="ch"/>
				</jsp:include>
			</div>
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-edit"></span>&nbsp;编辑个人信息</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" action="<%=editUrl%>" id="myform" method="post">
							<fieldset>
								<div class="form-group" id="member.phoneDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="member.phone">电话：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="text" id="member.phone" name="member.phone" class="form-control"
											placeholder="请输入您的联系电话" value="${member.phone}">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="member.phoneMsg"></div>
								</div>
								<div class="form-group" id="member.emailDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="member.email">邮箱：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="text" id="member.email" name="member.email" class="form-control"
											placeholder="请输入您的联系邮箱" value="${member.email}">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="member.emailMsg"></div>
								</div>
								<div class="form-group">
									<div class="col-md-5 col-md-offset-3">
										<input type="hidden" id="goods.gid" name="goods.gid" value="1">
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
