package cn.mldn.vshop.action.front;

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
import cn.mldn.vshop.util.action.AbstractAction;

@Repository
@ParentPackage("root")
@Namespace("/pages/front/center/member")
@Action("MemberActionFront")
@InterceptorRef("memberStack")
@SuppressWarnings("serial")
@Results(value = { 
		@Result(name = "member.basic.edit.page", location = "/pages/front/center/member/member_edit.jsp")})
public class MemberActionFront extends AbstractAction {
	private String oldpassword;
	private String newpassword;
	private Member member = new Member();
	@Resource
	private IMemberServiceFront memberServiceFront;

	public Member getMember() {
		return member;
	}

	public String editBasicPre() {
		String id = (String) super.getSession().getAttribute("id");
		try {
			super.getRequest().setAttribute("member",
					this.memberServiceFront.editBasicPre(id));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "member.basic.edit.page";
	}

	public String editBasic() {
		String id = (String) super.getSession().getAttribute("id");
		this.member.setMid(id); // 设置要修改的用户编号，是当前的登录用户信息
		try {
			if (this.memberServiceFront.editBasic(this.member)) {
				super.setMsgAndUrl("vo.edit.success",
						"member.basic.edit.action");
			} else {
				super.setMsgAndUrl("vo.edit.failure",
						"member.basic.edit.action");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("vo.edit.failure", "member.basic.edit.action");
		}
		return "forward.page";
	}

	public String editPassword() {
		// 修改密码时id的内容应该通过session属性得到，绝对不可能为null
		String id = (String) super.getSession().getAttribute("id");
		// 将原始密码与新的密码进行加密的处理
		this.oldpassword = new MD5Code().getMD5ofStr(this.oldpassword);
		this.newpassword = new MD5Code().getMD5ofStr(this.newpassword);
		try {
			if (this.memberServiceFront.editPassword(id, this.newpassword,
					this.oldpassword)) { // 登录成功
				super.setMsgAndUrl("admin.password.edit.success", "login.page");
			} else {
				super.setMsgAndUrl("admin.password.edit.failure", "login.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("admin.password.edit.failure", "login.page");
		}
		super.getSession().invalidate(); // 直接注销掉当前session中的数据
		// 需要通过forward.page路径提示信息而后进行跳转处理
		return "forward.page";
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
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
