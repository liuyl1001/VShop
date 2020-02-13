package cn.mldn.vshop.dao;

import java.util.List;

import cn.mldn.vshop.pojo.Subitem;

public interface ISubitemDAO extends IDAO<Integer, Subitem> {
	/**
	 * 根据item的id列出所有与之匹配的子栏目项
	 * @param iid 父栏目的id
	 * @return 所有的指定一级栏目下的子栏目数据
	 * @throws Exception
	 */
	public List<Subitem> findAllByItem(Integer iid) throws Exception;
	/**
	 * 查询除了指定的sid之外具备有指定标题的Subitem内容
	 * @param title
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public Subitem findByTitleAndSid(String title,Integer sid) throws Exception ;
}
