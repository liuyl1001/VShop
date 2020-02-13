package cn.mldn.vshop.service.front.abs;

import javax.annotation.Resource;

import cn.mldn.vshop.dao.IMemberDAO;

public abstract class AbstractServiceFront {
	@Resource 
	private IMemberDAO memberDAO ;
	public String getMid(String mid) throws Exception {
		if (mid.contains("@") && mid.contains(".")) { // 按照email登录
			mid = this.memberDAO.findByEmail(mid).getMid();
		}
		if (mid.matches("\\d{11}")) {
			mid = this.memberDAO.findByPhone(mid).getMid();
		}
		return mid ;
	}
}
