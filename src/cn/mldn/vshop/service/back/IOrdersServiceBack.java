package cn.mldn.vshop.service.back;

import java.util.Map;

public interface IOrdersServiceBack {
	/**
	 * 分页查看所有的订单信息
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return 返回的内容包含如下数据：<br>
	 * <li>key = allOrders、value = IOrdersDAO.findAllSplit()</li>
	 * <li>key = ordersCount、value = IOrdersDAO.getAllCount()</li>
	 * @throws Exception 
	 */
	public Map<String,Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception ;
	
	/**
	 * 查询订单的详细信息，包括配送地址数据以及所有购买的商品内容和数量信息
	 * @param id
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> show(int oid) throws Exception ;  
}
