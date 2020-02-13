package cn.mldn.vshop.dao;

import java.util.Map;
import java.util.Set;

import cn.mldn.vshop.pojo.Shopcar;

public interface IShopcarDAO extends IDAO<Integer, Shopcar> {
	/**
	 * 判断指定商品的编号与用户的编号是否存在于shopcar数据表中
	 * @param gid
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public boolean findExists(Integer gid,String mid) throws Exception ;
	/**
	 * 购买数据两增1
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateIncrement(String mid,Integer gid) throws Exception ;
	/**
	 * 实现购买数量的修改
	 * @param mid
	 * @param gid
	 * @param amount
	 * @throws Exception
	 */
	public boolean doUpdateAmount(String mid,Integer gid,Integer amount) throws Exception ;
	/**
	 * 得到一个指定用户的所有的购物车记录数据
	 * @param mid
	 * @return key为商品id、value为商品购买数量
	 * @throws Exception
	 */
	public Map<Integer,Integer> findAllByMember(String mid) throws Exception ; 
	/**
	 * 根据用户编号删除所有与之对应的购物车记录
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveByMember(String mid) throws Exception ;
	/**
	 * 批量保存用户的购物车中的购买记录数据 
	 * @param mid
	 * @param sc
	 * @return
	 * @throws Exception
	 */
	public boolean doCreateByMember(String mid,Map<Integer,Integer> sc) throws Exception ;
	/**
	 * 根据用户名称以及商品数量删除掉指定的购物车记录
	 * @param mid
	 * @param gids
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveByMemberAndGid(String mid,Set<Integer> gids) throws Exception ; 
}
