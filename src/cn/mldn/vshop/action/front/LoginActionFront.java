package cn.mldn.vshop.action.front;

import java.util.Map;

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
import cn.mldn.vshop.service.front.IMemberServiceFront;
import cn.mldn.vshop.util.ShopcarCookieUtil;
import cn.mldn.vshop.util.action.AbstractAction;
@Repository
@ParentPackage("root")
@Namespace("/")
@Action("LoginActionFront")
@InterceptorRef("memberLoginStack")
@SuppressWarnings("serial")
@Results(value={
		@Result(name="login.errorPage",location="/login.jsp") ,
		@Result(name="regist.errorPage",location="/regist.jsp") , 
		@Result(name="check.errorPage",location="/regist.jsp") 
})
public class LoginActionFront extends AbstractAction {
	private Member member = new Member();
	@Resource
	private IMemberServiceFront memberServiceFront ;
	public Member getMember() {
		return member;
	}
	public void check() {	// 进行数据的检测处理
		String id = super.getRequest().getParameter("id") ;	// 接收当前输入的id数据
		try {
			// 如果是true表示不能够使用，如果是false表示可以使用
			super.print(this.memberServiceFront.checkId(id));
		} catch (Exception e) {
			e.printStackTrace();
		} // 交给业务层进行处理
	}
	public String regist() {	// 实现注册处理
		String id = super.getRequest().getParameter("id") ;	// 接收当前输入的id数据
		if (id.matches("\\d{11}")) {	// 此时输入的是11位的手机号码
			this.member.setPhone(id); 
		} else if (id.contains("@") && id.contains(".")) {	// 使用的是email注册
			this.member.setEmail(id);
		} else {
			this.member.setMid(id);
		} 
		this.member.setPassword(new MD5Code().getMD5ofStr(this.member.getPassword()));
		try {
			if (this.memberServiceFront.add(this.member)) {
				super.setMsgAndUrl("vo.add.success", "index.page");
			} else {
				super.setMsgAndUrl("vo.add.failure", "regist.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("vo.add.failure", "regist.page");
		}
		return "forward.page" ;	// 注册完成之后进行信息提示处理
	}

	public String login() {
		String id = super.getRequest().getParameter("id") ;
		this.member.setPassword(new MD5Code().getMD5ofStr(this.member.getPassword()));
		try {
			ShopcarCookieUtil scu = new ShopcarCookieUtil() ;
			Map<Integer,Integer> cshop = scu.loadShopcar() ;	// 取得Cookie中的全部记录
			// 此处Cookie中的记录属于引用传递，既然是引用传递，那么就应该进行在业务层中对此集合的修改可以影响原始集合
			if (this.memberServiceFront.login(id,this.member.getPassword(),cshop)) {	// 登录成功
				super.setMsgAndUrl("member.login.success", "index.page");
				scu.saveShopcar(cshop);	// 将新的记录重新写回到Cookie之中
				super.getSession().setAttribute("id", id);
			} else {
				super.setMsgAndUrl("member.login.failure", "login.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("member.login.failure", "login.page");
		}
		// 需要通过forward.page路径提示信息而后进行跳转处理
		return "forward.page";
	}
	
	public String logout() {
		super.getSession().invalidate(); 	// 所有session失效
		// 注销完成之后需要回到登录页面上
		super.setMsgAndUrl("member.logout.success", "index.page");
		return "forward.page" ;	// 返回到提示页
	}

	@Override
	public String getFlagName() {
		return "用户";
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
