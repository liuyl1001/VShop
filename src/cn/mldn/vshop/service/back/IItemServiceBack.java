package cn.mldn.vshop.service.back;

import java.util.List;

import cn.mldn.vshop.pojo.Item;

public interface IItemServiceBack {
	/**
	 * 实现一级分类的列表显示操作
	 * @return 返回所有的item数据，没有分页
	 * @throws Exception
	 */ 
	public List<Item> list() throws Exception ;
	/**
	 * 进行栏目信息更新处理操作，本操作之中，要执行如下的步骤：<br>
	 * <li>调用IItemDAO.findByTitleAndId()方法判断当前的名称是否与其他的分类名称相同。</li>
	 * <li>调用IItemDAO.doUpdate()方法更新分类信息数据</li>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Item vo) throws Exception ;
	/**
	 * 本操作会存在有“1 + N”次查询问题，要求查询全部的item数据，以及对应的所有subitem数据，执行过程：<br>
	 * <li>首先要通过IItemDAO.findAll()方法取得所有的item数据，返回的是List<Item></li>
	 * <li>利用List集合中每一个item的数据查询出所有的subitem信息（不需要调用DAO，用延迟加载）</li>
	 * @return
	 * @throws Exception
	 */
	public List<Item> listDetails() throws Exception ;
	
}
