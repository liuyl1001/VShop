<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String addUrl = basePath + "pages/back/admin/goods/GoodsActionBack!add.action" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>微商城</title>
<meta name="viewport" content="width=device-width,initial-scale=1"> 
<jsp:include page="/pages/plugins/include_javascript.jsp" /> 
<script type="text/javascript" src="js/back/admin/goods/goods_add.js"></script>
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
						<strong><span class="glyphicon glyphicon-file"></span>&nbsp;增加新商品</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" action="<%=addUrl%>" id="myform" method="post" enctype="multipart/form-data">
							<fieldset>
								<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
								<div class="form-group" id="goods.titleDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="goods.title">商品名称：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="text" id="goods.title" name="goods.title" class="form-control"
											placeholder="请输入商品名称">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="goods.titleMsg"></div>
								</div>
								<div class="form-group" id="goods.item.iidDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="goods.item.iid">所属类别：</label>
									<div class="col-md-5">
										<select id="goods.item.iid" name="goods.item.iid" class="form-control">
										</select>
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="goods.item.iidMsg"></div>
								</div>
								<div class="form-group" id="goods.subitem.sidDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="goods.subitem.sid">所属子类别：</label>
									<div class="col-md-5">
										<select id="goods.subitem.sid" name="goods.subitem.sid" class="form-control">
										</select>
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="goods.subitem.sidMsg"></div>
								</div>
								<div class="form-group" id="goods.priceDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="goods.price">商品价格：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="text" id="goods.price" name="goods.price" class="form-control"
											placeholder="请输入商品价格">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="goods.priceMsg"></div>
								</div>
								<div class="form-group" id="goods.photoDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="photo">商品图片：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<input type="file" id="photo" name="photo" class="form-control"
											placeholder="请选择商品宣传图片">
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="photo"></div>
								</div>
								<!-- 定义输入表单样式，其中id主要用于设置颜色样式 -->
								<div class="form-group" id="goods.noteDiv">
									<!-- 定义表单提示文字 -->
									<label class="col-md-3 control-label" for="goods.note">项目描述：</label>
									<div class="col-md-5">
										<!-- 定义表单输入组件 -->
										<textarea id="goods.note" name="goods.note"
											class="form-control" placeholder="请输入商品描述信息" rows="10"></textarea>
									</div>
									<!-- 定义表单错误提示显示元素 -->
									<div class="col-md-4" id="project.noteMsg"></div>
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
