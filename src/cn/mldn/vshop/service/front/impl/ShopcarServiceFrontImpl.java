package cn.mldn.vshop.service.front.impl;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.pojo.Shopcar;
import cn.mldn.vshop.service.front.IShopcarServiceFront;

@Service
public class ShopcarServiceFrontImpl implements IShopcarServiceFront {
	@Resource
	private IShopcarDAO shopcarDAO;
	@Resource
	private IMemberDAO memberDAO ;
	@Override
	public boolean add(String mid, int gid) throws Exception {
		if (mid.contains("@") && mid.contains(".")) {	// 按照email登录
			mid = this.memberDAO.findByEmail(mid).getMid() ;
		}
		if (mid.matches("\\d{11}")) {
			mid = this.memberDAO.findByPhone(mid).getMid() ;
		}
		if (this.shopcarDAO.findExists(gid, mid)) { // 当前数据已经存在
			return this.shopcarDAO.doUpdateIncrement(mid, gid);
		} else {
			Shopcar vo = new Shopcar();
			vo.getMember().setMid(mid);
			vo.getGoods().setGid(gid);
			vo.setAmount(1);
			return this.shopcarDAO.doCreate(vo);
		}
	}
	@Override
	public boolean editSingle(String mid, int gid, int amount) throws Exception {
		if (mid.contains("@") && mid.contains(".")) {	// 按照email登录
			mid = this.memberDAO.findByEmail(mid).getMid() ;
		}
		if (mid.matches("\\d{11}")) {
			mid = this.memberDAO.findByPhone(mid).getMid() ;
		}
		return this.shopcarDAO.doUpdateAmount(mid, gid, amount); 
	}
	@Override
	public boolean edit(String mid, Map<Integer, Integer> map) throws Exception {
		if (mid.contains("@") && mid.contains(".")) {	// 按照email登录
			mid = this.memberDAO.findByEmail(mid).getMid() ;
		}
		if (mid.matches("\\d{11}")) {
			mid = this.memberDAO.findByPhone(mid).getMid() ;
		}
		this.shopcarDAO.doRemoveByMember(mid) ;
		return this.shopcarDAO.doCreateByMember(mid, map);
	}
	@Override
	public boolean rm(String mid, Set<Integer> gids) throws Exception {
		if (mid.contains("@") && mid.contains(".")) {	// 按照email登录
			mid = this.memberDAO.findByEmail(mid).getMid() ;
		}
		if (mid.matches("\\d{11}")) {
			mid = this.memberDAO.findByPhone(mid).getMid() ;
		}
		return this.shopcarDAO.doRemoveByMemberAndGid(mid, gids) ; 
	}

}
