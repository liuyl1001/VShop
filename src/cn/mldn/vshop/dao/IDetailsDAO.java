package cn.mldn.vshop.dao;

import java.util.Map;

import cn.mldn.vshop.pojo.Details;

public interface IDetailsDAO extends IDAO<Integer, Details> {
	/**
	 * 查询出一个用户的指定订单编号的所有购买的信息内容
	 * @param mid
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Integer> findAllByOrdersAndMember(
			Integer oid) throws Exception; 
}
