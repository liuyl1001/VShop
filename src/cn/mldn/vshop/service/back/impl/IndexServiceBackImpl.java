package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.service.back.IIndexServiceBack;
@Service
public class IndexServiceBackImpl implements IIndexServiceBack {
	@Resource 
	private IGoodsDAO goodsDAO ;
	@Resource
	private IOrdersDAO ordersDAO ;
	@Override
	public Map<String, Object> show() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>() ;
		result.put("goodsCount", this.goodsDAO.getAllCount("title", "")) ;
		result.put("ordersCount", this.ordersDAO.getAllCount("member.mid", "")) ;
		result.put("todayCount", this.ordersDAO.getTodayCount()) ;  
		return result ; 
	}

}
