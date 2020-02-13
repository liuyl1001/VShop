<%@ page pageEncoding="UTF-8"%>
<%--
	实现数据搜索条的控制
<jsp:include page="split_page_plugin_search.jsp"/>
--%>
<%
	request.setCharacterEncoding("UTF-8") ;
	String url = (String)request.getAttribute("url") ;
	String columnData = (String)request.getAttribute("columnData") ;
	String keyWord = (String)request.getAttribute("keyWord") ;
	String column = (String)request.getAttribute("column") ;
%>
<script type="text/javascript">
	function goSplit() {
		var colObj = $("#col option:selected") ;
		var kw = document.getElementById("kw").value ;
		col = "${column}" ;	// 默认的呢日哦那个
		if (colObj != null) {	// 此时存在有下拉列表框
			col = colObj.val() ;	// 取得下拉列表框的内容
		} 
		window.location = "<%=url%>?cp=1&col="+col+"&kw="+kw+"&${paramName}=${paramValue}&${paramNameB}=${paramValueB}" ;
	}
</script> 
<div class="row">
	<form action="<%=url%>" method="post" class="form-group"
		id="splitSearchForm">
		<fieldset>
			<div class="form-group">
				<div class="col-md-2">&nbsp;</div>
				<%
					if (columnData != null) {
				%>
					<div class="col-md-3">
						<select id="col" name="col" class="form-control">
				<%
							String result [] = columnData.split("\\|") ;
							for (int x = 0 ; x < result.length ; x ++) {
								String temp[] = result[x].split(":") ;
				%>
								<option value="<%=temp[1]%>" <%=column.equals(temp[1])?"selected":""%>><%=temp[0]%></option>
				<%
							}
				%>
						</select>
					</div>
				<%
					}
				%>
				<div class="col-md-5">
					<input type="text" name="kw" id="kw" class="form-control input-sm"
						value="${keyWord}" placeholder="请输入检索关键字">
				</div>
				<div class="col-md-2">
					<button type="button" onclick="goSplit()" class="btn btn-primary">检索</button>
					<input type="hidden" name="${paramName}" value="${paramValue}">
					<input type="hidden" name="cp" value="1">
				</div>
			</div>
		</fieldset>
	</form>
</div>
<div class="row">
	<div class="col-md-7 col-md-push-5"> 
		<p class="text-info">满足查询条件的数据量：${allRecorders}</p>
	</div>
</div>
