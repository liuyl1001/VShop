package cn.mldn.vshop.service.front;

import java.util.Map;

public interface IOrdersServiceFront {
	/**
	 * 实现订单的创建处理操作，在创建订单前需要执行如下的操作：<br>
	 * <li>key = flag、value = 如果有购物数据返回true，否则返回false</li>
	 * <li>key = allAddreses、value = List<Address>，取得所有的地址数据。</li>
	 * <li>key = carGoods、value = List<Goods>，取得所有的购买的商品数据。</li>
	 * <li>key = allCar、value = List<Shopcar>，取得所有的购买数量。</li>
	 * @param id 登录的id数据
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addPre(String id) throws Exception;
	/**
	 * 进行订单的创建处理操作，只需要传入id，以及操作的地址id
	 * @param id
	 * @param adid
	 * @return
	 * @throws Exception
	 */
	public boolean add(String id,int adid) throws Exception ;
	/**
	 * 实现指定用户的订单信息列表操作
	 * @param mid
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listByMember(String mid, int currentPage,
			int lineSize) throws Exception;
	/**
	 * 查询订单的详细信息，包括配送地址数据以及所有购买的商品内容和数量信息
	 * @param id
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> show(String id,int oid) throws Exception ; 
}
