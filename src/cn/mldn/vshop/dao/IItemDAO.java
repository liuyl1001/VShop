package cn.mldn.vshop.dao;

import cn.mldn.vshop.pojo.Item;

public interface IItemDAO extends IDAO<Integer, Item> {
	/**
	 * 取得除了自己数据之外是否还有其他的复合于指定名称与编号的Item数据
	 * @param title 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Item findByTitleAndId(String title,Integer id) throws Exception ;
}