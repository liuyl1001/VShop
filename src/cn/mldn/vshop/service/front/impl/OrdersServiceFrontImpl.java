package cn.mldn.vshop.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IAddressDAO;
import cn.mldn.vshop.dao.IDetailsDAO;
import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.pojo.Details;
import cn.mldn.vshop.pojo.Goods;
import cn.mldn.vshop.pojo.Orders;
import cn.mldn.vshop.service.front.IOrdersServiceFront;
import cn.mldn.vshop.service.front.abs.AbstractServiceFront;

@Service
public class OrdersServiceFrontImpl extends AbstractServiceFront implements
		IOrdersServiceFront {
	@Resource
	private IDetailsDAO detailsDAO;
	@Resource
	private IOrdersDAO ordersDAO;
	@Resource
	private IShopcarDAO shopcarDAO; // 取得购物车的数据
	@Resource
	private IGoodsDAO goodsDAO; // 取得商品数据
	@Resource
	private IAddressDAO addressDAO; // 取得所有的地址数据信息

	@Override
	public Map<String, Object> addPre(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String mid = super.getMid(id); // 取得用户的真正mid数据
		// 取出用户购物车中的所有数据，其中key为商品编号、value为商品购买数量
		Map<Integer, Integer> shopcarMap = this.shopcarDAO.findAllByMember(mid);
		if (shopcarMap.size() == 0) { // 现在没有任何的购买记录
			map.put("flag", false);
		} else { // 现在购物车中存在有数据
			map.put("flag", true); // 表示一切正常，可以正常生成订单
			// 需要取得用户所购买的全部的商品的具体信息内容
			map.put("carGoods", this.goodsDAO.findAllByMemberShopcar(mid));
			map.put("allAddresses", this.addressDAO.findAllByMember(mid));
			map.put("allCar", shopcarMap);
		}
		return map;
	}

	@Override
	public boolean add(String id, int adid) throws Exception {
		String mid = super.getMid(id);
		// 1、首先需要确认好用户的默认地址信息
		if (this.addressDAO.doUpdateFlagByMember(mid)) { // 已经清楚了所有的默认地址的编号内容
			if (this.addressDAO.doUpdateFlag(mid, adid, 1)) { // 默认的地址已经更新完毕
				// 2、如果要进行总价的计算处理操作，那么必须知道买了那些内容
				Map<Integer, Integer> car = this.shopcarDAO
						.findAllByMember(mid);
				// 3、需要知道所有购买的商品的内容
				List<Goods> carGoods = this.goodsDAO
						.findAllByMemberShopcar(mid);
				double sum = 0.0;
				Iterator<Goods> iter = carGoods.iterator();
				while (iter.hasNext()) {
					Goods gvo = iter.next(); // 取出每一个商品数据
					sum += car.get(gvo.getGid()) * gvo.getPrice(); // 计算总价
				}
				// 4、保存订单数据
				Orders ovo = new Orders();
				ovo.getMember().setMid(mid); // 设置订单所属的用户编号
				ovo.getAddress().setAdid(adid); // 设置配送的位置
				ovo.setSubdate(new Date());
				ovo.setPrice(sum);
				// 5、创建订单数据，同时可以自动取回当前的订单id数据
				if (this.ordersDAO.doCreate(ovo)) {
					// 6、订单取得之后下面就需要进行订单详情的保存，数据都保存在car这个Map集合里面
					Iterator<Map.Entry<Integer, Integer>> iterCar = car
							.entrySet().iterator();
					while (iterCar.hasNext()) {
						Map.Entry<Integer, Integer> me = iterCar.next();
						Details dvo = new Details();
						dvo.getOrders().setOid(ovo.getOid());
						dvo.setAmount(me.getValue());
						dvo.getGoods().setGid(me.getKey());
						this.detailsDAO.doCreate(dvo); // 保存订单详情的信息内容
					}
					// 6、删除掉购物车中的内容
					if (this.shopcarDAO.doRemoveByMember(mid)) {
						return true;
					}
				}

			}
		}
		return false;
	}

	@Override
	public Map<String, Object> listByMember(String id, int currentPage,
			int lineSize) throws Exception {
		String mid = super.getMid(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allOrderses",
				this.ordersDAO.findAllSplitByMember(mid, currentPage, lineSize));
		map.put("ordersCount", this.ordersDAO.getAllCountByMember(mid));
		return map;
	}

	@Override
	public Map<String, Object> show(String id, int oid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String mid = super.getMid(id);
		Orders orders = this.ordersDAO.findByIdAndMember(mid, oid);
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
