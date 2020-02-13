<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal fade" id="goodsInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3 class="modal-title">
					<strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;查看商品信息</strong></h3>
			</div>
			<div class="modal-body">
				<table class="table table-hover table-condensed">
					<tbody>
						<tr>
							<td class="text-center" rowspan="7" style="width:30%"><img src="upload/goods/goods.png" id="goodsImg"></td>
							<td class="text-center" style="width:20%"><strong>商品名称：</strong></td>
							<td class="text-center" style="width:50%" id="title">Java开发实战经典</td>
						</tr>
						<tr>
							<td class="text-center"><strong>所属类别：</strong></td>
							<td class="text-center" id="ititle">图书音响</td>
						</tr>
						<tr>
							<td class="text-center"><strong>所属子类别：</strong></td>
							<td class="text-center" id="stitle">计算机图书</td>
						</tr>
						<tr>
							<td class="text-center"><strong>商品价格：</strong></td>
							<td class="text-center" id="price">79.8</td>
						</tr>
						<tr>
							<td class="text-center"><strong>发布管理员：</strong></td>
							<td class="text-center" id="mid">mldn</td>
						</tr>
						<tr>
							<td class="text-center"><strong>发布日期：</strong></td>
							<td class="text-center" id="pubdate">2017-10-10</td>
						</tr>
						<tr>
							<td class="text-center" colspan="3">
								<pre class="pre-scrollable" style="height:100px;width:600px;" id="note"></pre>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
			</div>
		</div>
	</div>
</div>