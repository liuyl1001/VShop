package cn.mldn.vshop.service.front;

import java.util.Map;
import java.util.Set;

public interface IShopcarServiceFront {
	/**
	 * 实现购物车数据的追加处理
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public boolean add(String mid,int gid) throws Exception ;
	/**
	 * 实现单个购买信息的变更
	 * @param mid
	 * @param gid
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public boolean editSingle(String mid,int gid,int amount) throws Exception ;
	/**
	 * 全体数据更新
	 * @param mid
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean edit(String mid,Map<Integer,Integer> map) throws Exception ;
	/**
	 * 删除指定的购物车数据
	 * @param mid
	 * @param gids
	 * @return
	 * @throws Exception
	 */
	public boolean rm(String mid,Set<Integer> gids) throws Exception ;
}
