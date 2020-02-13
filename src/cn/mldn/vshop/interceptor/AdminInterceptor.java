package cn.mldn.vshop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AdminInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (invocation.getInvocationContext().getSession().get("mid") != null) { // 没有登录过
			return invocation.invoke() ;
		} else {
			ServletActionContext.getRequest().setAttribute("msg", "您还未登录，请先登录！");
			ServletActionContext.getRequest().setAttribute("url", "/pages/back/login.jsp");
			return "forward.page"; // 回到指定的路径上
		}
	}

}
