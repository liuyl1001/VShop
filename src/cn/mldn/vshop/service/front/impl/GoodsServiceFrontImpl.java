package cn.mldn.vshop.service.front.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.pojo.Goods;
import cn.mldn.vshop.service.front.IGoodsServiceFront;

@Service
public class GoodsServiceFrontImpl implements IGoodsServiceFront {
	@Resource
	private IShopcarDAO shopcarDAO;
	@Resource
	private IGoodsDAO goodsDAO;
	@Resource
	private IMemberDAO memberDAO ;

	@Override
	public Map<String, Object> listBySubitem(int sid, String column,
			String keyWord, int currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allGoodss", this.goodsDAO.findAllBySubitem(sid, column,
				keyWord, currentPage, lineSize));
		map.put("goodsCount",
				this.goodsDAO.getAllCountBySubitem(sid, column, keyWord));
		return map;
	}

	@Override
	public Map<String, Object> listSearch(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allGoodss", this.goodsDAO.findAllSplit(column, keyWord,
				currentPage, lineSize));
		map.put("goodsCount", this.goodsDAO.getAllCount(column, keyWord));
		return map;
	}

	@Override
	public Goods show(int id) throws Exception {
		return this.goodsDAO.findById(id);
	}

	@Override
	public List<Goods> listCar(Set<Integer> ids) throws Exception {
		if (ids.size() > 0) {
			return this.goodsDAO.findAllByIds(ids);
		}
		return null;
	}

	@Override
	public Map<String, Object> listMemberCar(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String mid = id ;
		if (mid.contains("@") && mid.contains(".")) {	// 按照email登录
			mid = this.memberDAO.findByEmail(mid).getMid() ;
		}
		if (mid.matches("\\d{11}")) {
			mid = this.memberDAO.findByPhone(mid).getMid() ;
		}
		map.put("allGoods", this.goodsDAO.findAllByMemberShopcar(mid)) ;
		map.put("shopcar", this.shopcarDAO.findAllByMember(mid)) ;
		return map;
	}

}
