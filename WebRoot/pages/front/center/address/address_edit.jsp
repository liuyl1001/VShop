<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl = "" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1"> 
<jsp:include page="/pages/plugins/include_javascript.jsp" /> 
<script type="text/javascript" src="js/front/center/address/address_edit.js"></script>
<script type="text/javascript"> 
	var adid = ${param.adid} ;
</script>
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
					<jsp:param value="5" name="ch"/>
				</jsp:include>
			</div>
			<div class="col-md-10 col-xs-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-pencil"></span>&nbsp;编辑联系地址</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" action="<%=editUrl%>" id="myform" method="post">
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
											placeholder="请输入您的联系电话" value="1234234235312">
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
											placeholder="请输入您的联系地址" value="山东省 青岛市 xxx楼xxx">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="address.addrMsg"></div>
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
