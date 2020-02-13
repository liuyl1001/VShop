package cn.mldn.vshop.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.pojo.Member;
import cn.mldn.vshop.service.front.IMemberServiceFront;

@Service
public class MemberServiceFrontImpl implements IMemberServiceFront {
	@Resource
	private IMemberDAO memberDAO;
	@Resource
	private IShopcarDAO shopcarDAO ;

	@Override
	public boolean editPassword(String mid, String newPass, String oldPass)
			throws Exception {
		Member oldMem = new Member() ;
		oldMem.setMid(mid);
		oldMem.setPassword(oldPass);
		oldMem.setAdminflag(0); 	// 用户的标志位
		if (this.memberDAO.findLogin(oldMem)) {	// 原始密码正确
			Member newMem = new Member() ;
			newMem.setMid(mid);
			newMem.setPassword(newPass);
			return this.memberDAO.doUpdatePassword(newMem) ;
		}
		return false;
	}
	
	@Override
	public boolean checkId(String id) throws Exception {
		if (id == null || "".equals(id)) {
			return true; // 不能够使用返回true
		}
		if (id.matches("\\d{11}")) { // 此时输入的是11位的手机号码
			return this.memberDAO.findByPhone(id) != null;
		} else if (id.contains("@") && id.contains(".")) { // 使用的是email注册
			return this.memberDAO.findByEmail(id) != null;
		} else {
			return this.memberDAO.findById(id) != null;
		}
	}

	@Override
	public boolean add(Member vo) throws Exception {
		vo.setReg(new Date());
		vo.setDelflag(0);
		vo.setAdminflag(0);
		if (vo.getMid() == null || "".equals(vo.getMid())) { // 表示此id不存在
			vo.setMid("mem-" + UUID.randomUUID().toString());
		}
		if (vo.getEmail() != null) {
			if (this.memberDAO.findByEmail(vo.getEmail()) == null) { // 表示此email数据不存在
				return this.memberDAO.doCreate(vo);
			}
		} else if (vo.getPhone() != null) {
			if (this.memberDAO.findByPhone(vo.getPhone()) == null) {
				return this.memberDAO.doCreate(vo);
			}
		} else {
			if (this.memberDAO.findById(vo.getMid()) == null) {
				return this.memberDAO.doCreate(vo);
			}
		}
		return false;
	}

 	@Override
	public boolean login(String id, String password, Map<Integer, Integer> csc)
			throws Exception {
		Member vo = null;
		if (id.matches("\\d{11}")) { // 此时输入的是11位的手机号码
			vo = this.memberDAO.findByPhone(id);
		} else if (id.contains("@") && id.contains(".")) { // 使用的是email注册
			vo = this.memberDAO.findByEmail(id);
		} else {
			vo = this.memberDAO.findById(id);
		}
		if (vo.getDelflag().equals(0) && password.equals(vo.getPassword())) {
			// 登录成功了，而后此时的mid也取得了
			// 取出所有已经购买过的记录内容
			Map<Integer,Integer> dbShopcar = this.shopcarDAO.findAllByMember(vo.getMid()) ;
			// 无法确认有那些数据是重复的，毕竟是两个集合
			Map<Integer,Integer> newMap = new HashMap<Integer,Integer>() ;
			if (dbShopcar == null || dbShopcar.size() == 0) {	// 表示此时没有过购买记录
				this.shopcarDAO.doCreateByMember(vo.getMid(), csc) ; 
			} else {	// 如果此时发现原始的数据库之中也存在有相应的内容，同步比较，保存大的
				// 查询数据库中的购物车数据
				Iterator<Map.Entry<Integer,Integer>> iterA = dbShopcar.entrySet().iterator() ;
				while (iterA.hasNext()) {	// 开始进行计算比较
					Map.Entry<Integer, Integer> me = iterA.next() ;
					if (csc.containsKey(me.getKey())) {	// 首先要判断数据库中的数据是否在cookie中存在
						newMap.put(me.getKey(), csc.get(me.getKey()) > me.getValue() ? csc.get(me.getKey()) : me.getValue()) ;
					} else {	// 数据不存在
						newMap.put(me.getKey(), me.getValue()) ;
					}
				}
				// 查询Cookie中保存的购物车数据
				Iterator<Map.Entry<Integer,Integer>> iterB = csc.entrySet().iterator() ;
				while (iterB.hasNext()) {
					Map.Entry<Integer, Integer> me = iterB.next() ;
					if (dbShopcar.containsKey(me.getKey())) {
						newMap.put(me.getKey(), dbShopcar.get(me.getKey()) > me.getValue() ? dbShopcar.get(me.getKey()) : me.getValue()) ;
					} else {
						newMap.put(me.getKey(), me.getValue()) ;
					}
				}
				this.shopcarDAO.doRemoveByMember(vo.getMid()) ;	// 删除已有的购买记录
				this.shopcarDAO.doCreateByMember(vo.getMid(), newMap) ;// 重新保存所有的购买记录
			}
			
			// 更新原始传递过来的Cookie的内容
			csc.putAll(newMap);	// 重新保存Map集合
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editBasic(Member mem) throws Exception {
		Member vo = null ;
		if (mem.getMid().matches("\\d{11}")) { // 此时输入的是11位的手机号码
			vo = this.memberDAO.findByPhone(mem.getMid());
		} else if (mem.getMid().contains("@") && mem.getMid().contains(".")) { // 使用的是email注册
			vo = this.memberDAO.findByEmail(mem.getMid());
		} else {
			vo = this.memberDAO.findById(mem.getMid());
		}
		mem.setMid(vo.getMid());
		// 要确定该email是否存在
		Member emailMember = this.memberDAO.findByEmail(mem.getEmail()) ;
		// 表示该邮箱可用
		if (emailMember == null || emailMember.getMid().equals(vo.getMid())) {
			Member phoneMember = this.memberDAO.findByPhone(mem.getPhone()) ;
			// 该电话可用
			if (phoneMember == null || phoneMember.getMid().equals(vo.getMid())) {
				return this.memberDAO.doUpdateBasic(mem) ;
			}
		}
		return false ;
	} 

	@Override
	public Member editBasicPre(String id) throws Exception {
		Member vo = null ;
		if (id.matches("\\d{11}")) { // 此时输入的是11位的手机号码
			vo = this.memberDAO.findByPhone(id);
		} else if (id.contains("@") && id.contains(".")) { // 使用的是email注册
			vo = this.memberDAO.findByEmail(id);
		} else {
			vo = this.memberDAO.findById(id);
		}
		return this.memberDAO.findById(vo.getMid()); 
	}
}
