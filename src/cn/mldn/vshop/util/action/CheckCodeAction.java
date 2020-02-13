package cn.mldn.vshop.util.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Repository;

@Repository
@ParentPackage("root")
@Action("CheckCode")
@SuppressWarnings("serial")
public class CheckCodeAction extends AbstractAction {
	private String code;

	public void setCode(String code) {
		this.code = code;
	}

	public void check() { // 属于ajax异步调用
		String rand = (String) super.getSession().getAttribute("rand") ;	// 取得生成的验证码
		if (rand == null || "".equals(rand)) {
			super.print(false);
		} else {
			super.print(rand.equalsIgnoreCase(this.code));
		}
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
