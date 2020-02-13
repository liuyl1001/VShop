package cn.mldn.vshop.action.back;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Repository;

import cn.mldn.util.MD5Code;
import cn.mldn.vshop.service.back.IMemberServiceBack;
import cn.mldn.vshop.util.action.AbstractAction;
@Repository
@ParentPackage("root")
@Namespace("/pages/back/admin/admin")
@Action("AdminActionBack")
@InterceptorRef("adminStack")
@SuppressWarnings("serial")
public class AdminActionBack extends AbstractAction {
	private String oldpassword ;
	private String newpassword ;
	@Resource
	private IMemberServiceBack memberServiceBack ;

	public String editPassword() {
		// 修改密码时mid的内容应该通过session属性得到，绝对不可能为null
		String mid = (String) super.getSession().getAttribute("mid") ;
		// 将原始密码与新的密码进行加密的处理
		this.oldpassword = new MD5Code().getMD5ofStr(this.oldpassword) ;
		this.newpassword = new MD5Code().getMD5ofStr(this.newpassword) ;
		try {
			if (this.memberServiceBack.editPassword(mid, this.newpassword, this.oldpassword)) {	// 登录成功
				super.setMsgAndUrl("admin.password.edit.success", "admin.login.page");
			} else {
				super.setMsgAndUrl("admin.password.edit.failure", "admin.login.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("admin.password.edit.failure", "admin.login.page");
		}
		super.getSession().invalidate(); 	// 直接注销掉当前session中的数据
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
