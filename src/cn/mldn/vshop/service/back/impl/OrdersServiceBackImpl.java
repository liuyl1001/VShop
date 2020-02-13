package cn.mldn.vshop.service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IDetailsDAO;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.pojo.Goods;
import cn.mldn.vshop.pojo.Orders;
import cn.mldn.vshop.service.back.IOrdersServiceBack;
@Service
public class OrdersServiceBackImpl implements IOrdersServiceBack {
	@Resource
	private IOrdersDAO ordersDAO ;
	@Resource
	private IDetailsDAO detailsDAO ;
	@Resource 
	private IGoodsDAO goodsDAO ;
	@Override
	public Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allOrders", this.ordersDAO.findAllSplit(column, keyWord, currentPage, lineSize)) ;
		map.put("ordersCount", this.ordersDAO.getAllCount(column, keyWord)) ;
		return map;
	}
	@Override
	public Map<String, Object> show(int oid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Orders orders = this.ordersDAO.findById(oid);	// 查询订单的具体内容
		if (orders != null) {
			orders.getAddress().getAddr(); // 加载地址数据
			Map<Integer, Integer> details = this.detailsDAO
					.findAllByOrdersAndMember(oid);
			// 查询所有的商品数据
			List<Goods> allGoods = this.goodsDAO.findAllByIds(details.keySet());
			map.put("orders", orders);
			map.put("details", details); // 确定数量的填充
			map.put("allGoods", allGoods); // 所有购买的商品内容
		}
		return map;
	}

}
