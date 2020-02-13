package cn.mldn.vshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/pages/back/admin/*", "/js/back/admin/*" })
public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) arg0;
		HttpSession session = servletRequest.getSession();
		if (session.getAttribute("mid") != null) {
			arg2.doFilter(arg0, arg1);
		} else {
			servletRequest.setAttribute("msg", "您还未登录，请先登录！");
			servletRequest.setAttribute("url", "/pages/back/login.jsp");
			servletRequest.getRequestDispatcher("/pages/forward.jsp").forward(
					arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
