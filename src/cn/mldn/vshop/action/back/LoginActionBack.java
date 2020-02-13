package cn.mldn.vshop.action.back;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.mldn.util.MD5Code;
import cn.mldn.vshop.pojo.Member;
import cn.mldn.vshop.service.back.IMemberServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;
@Repository
@ParentPackage("root")
@Namespace("/pages/back")
@Action("LoginActionBack")
@InterceptorRef("adminLoginStack")
@SuppressWarnings("serial")
@Results(value={
		@Result(name="login.errorPage",location="/pages/back/login.jsp")
})
public class LoginActionBack extends AbstractAction {
	private Member member = new Member();
	@Resource
	private IMemberServiceBack memberServiceBack ;
	public Member getMember() {
		return member;
	}

	public String login() {
		this.member.setPassword(new MD5Code().getMD5ofStr(this.member
				.getPassword()));	// 针对于密码进行MD5的加密处理操作
		try {
			if (this.memberServiceBack.login(this.member)) {	// 登录成功
				super.setMsgAndUrl("admin.login.success", "admin.index.page");
				super.getSession().setAttribute("mid", this.member.getMid());
			} else {
				super.setMsgAndUrl("admin.login.failure", "admin.login.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("admin.login.failure", "admin.login.page");
		}
		// 需要通过forward.page路径提示信息而后进行跳转处理
		return "forward.page";
	}
	
	public String logout() {
		super.getSession().invalidate(); 	// 所有session失效
		// 注销完成之后需要回到登录页面上
		super.setMsgAndUrl("admin.logout.success", "admin.login.page");
		return "forward.page" ;	// 返回到提示页
	}

	@Override
	public String getFlagName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultColumnt() {
		// TODO Auto-generated method stub
		return null;
	}
}
