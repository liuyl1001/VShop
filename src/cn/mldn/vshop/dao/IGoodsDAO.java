package cn.mldn.vshop.dao;

import java.util.List;
import java.util.Set;

import cn.mldn.vshop.pojo.Goods;

public interface IGoodsDAO extends IDAO<Integer, Goods> {
	/**
	 * 根据二级栏目的列表查询出所有对应的商品数据
	 * @param sid
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public List<Goods> findAllBySubitem(Integer sid, String column,
			String keyWord, Integer currentPage, Integer lineSize)
			throws Exception;
	/**
	 * 查询指定商品编号的商品信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<Goods> findAllByIds(Set<Integer> ids) throws Exception ;
	/**
	 * 统计出指定二级栏目中的商品数量
	 * @param sid
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountBySubitem(Integer sid, String column,
			String keyWord) throws Exception;
	/**
	 * 列出指定mid登录的（要取出mid的内容）所有购物车商品记录，使用子查询控制
	 * @param mid 登录的ID，可能是用户名可能是手机或者是email
	 * @return
	 * @throws Exception
	 */
	public List<Goods> findAllByMemberShopcar(String mid) throws Exception ;
}
