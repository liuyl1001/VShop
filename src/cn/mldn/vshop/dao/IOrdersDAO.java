package cn.mldn.vshop.dao;

import java.util.List;

import cn.mldn.vshop.pojo.Orders;

public interface IOrdersDAO extends IDAO<Integer, Orders> {
	/**
	 * 进行指定用户的订单信息列表
	 * @param mid
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public List<Orders> findAllSplitByMember(String mid, Integer currentPage,
			Integer lineSize) throws Exception;
	/**
	 * 统计出指定用户的订单量
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByMember(String mid) throws Exception;
	/**
	 * 根据指定的订单编号以及用户的编号，查询其对应的订单详情内容
	 * @param mid
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public Orders findByIdAndMember(String mid,Integer oid) throws Exception ;
	/**
	 * 查询今日的订单数量
	 * @return
	 * @throws Exception
	 */
	public Integer getTodayCount() throws Exception ;
}
