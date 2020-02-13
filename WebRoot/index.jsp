<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String url = basePath + "pages/front/goods/GoodsActionFront!listSearch.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<jsp:include page="/pages/plugins/include_javascript.jsp" />
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/menu_car.js"></script>
</head>
<body>
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/plugins/front/include_menu_member.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-3">
				<jsp:include page="/pages/plugins/front/include_menu_item.jsp"/>
			</div>
			<div class="col-md-9">
				<div class="row" style="height:100px;">
					<form action="<%=url%>" method="post" class="form-group" >
						<fieldset>
							<div class="form-group">
								<div class="col-md-5 col-md-offset-3">
									<input type="text" name="kw" id="kw" class="form-control input-sm"
										value="" placeholder="请输入检索关键字">
								</div>
								<div class="col-md-2">
									<button type="submit" class="btn btn-primary">搜索商品</button>
									<input type="hidden" name="cp" value="1">
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="row" id="goodsDiv">
					
				</div> 
			</div>
		</div>
		<div class="row" style="height:50px;">
			<jsp:include page="/pages/plugins/include_alert.jsp"/>
		</div>
	</div>
</body>
</html>
