package cn.mldn.vshop.service.back;

import java.util.List;

import cn.mldn.vshop.pojo.Subitem;

public interface ISubitemServiceBack {
	/**
	 * 根据一级分类列出所有对应的二级分类
	 * @param iid
	 * @return
	 * @throws Exception
	 */
	public List<Subitem> listByItem(int iid) throws Exception;
	/**
	 * 实现二级菜单信息的更新处理，本操作中要执行如下调用：<br>
	 * <li>调用ISubitemDAO.findTitleAndSid()方法判断当前的名称是否重复！</li>
	 * <li>调用ISubitemDAO.doUpdate()方法进行数据的更新处理。</li>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Subitem vo) throws Exception ;
}
