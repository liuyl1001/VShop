package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IAddressDAO;
import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.pojo.Member;
import cn.mldn.vshop.service.back.IMemberServiceBack;

@Service
public class MemberServiceBackImpl implements IMemberServiceBack {
	
	@Resource
	private IMemberDAO memberDAO ;
	@Resource
	private IAddressDAO addressDAO ;
	@Override
	public boolean login(Member vo) throws Exception {
		vo.setAdminflag(1);	// 必须保证验证的管理员级别是1 
		return this.memberDAO.findLogin(vo) ;
	}
	@Override
	public boolean editPassword(String mid, String newPass, String oldPass)
			throws Exception {
		Member oldMem = new Member() ;
		oldMem.setMid(mid);
		oldMem.setPassword(oldPass);
		oldMem.setAdminflag(1); 	// 管理员的标志位
		if (this.memberDAO.findLogin(oldMem)) {	// 原始密码正确
			Member newMem = new Member() ;
			newMem.setMid(mid);
			newMem.setPassword(newPass);
			return this.memberDAO.doUpdatePassword(newMem) ;
		}
		return false;
	}
	@Override
	public Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allMembers", this.memberDAO.findAllSplit(column, keyWord, currentPage, lineSize)) ;
		map.put("memberCount", this.memberDAO.getAllCount(column, keyWord)) ; 
		return map;
	}
	@Override
	public boolean editPassword(Member vo) throws Exception {
		return this.memberDAO.doUpdatePassword(vo) ; 
	}
	@Override
	public boolean editLock(Set<String> mids) throws Exception {
		if (mids == null) {
			return false ;
		}
		if (mids.contains("vadmin")) {
			mids.remove("vadmin") ;	// 管理员的数据不再更新的范围之内
		}
		if (mids.size() == 0) {	// 现在并没有要更新的用户信息
			return false ;
		}
		return this.memberDAO.doUpdateDelflag(mids, 1); 
	}
	@Override
	public Map<String,Object> show(String mid) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>() ;
		result.put("member", this.memberDAO.findById(mid)) ;
		result.put("allAddress", this.addressDAO.findAllByMember(mid)) ;
		return result ; 
	}
 
}
