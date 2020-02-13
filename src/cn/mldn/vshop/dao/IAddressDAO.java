package cn.mldn.vshop.dao;

import java.util.List;
import java.util.Set;

import cn.mldn.vshop.pojo.Address;

public interface IAddressDAO extends IDAO<Integer, Address> {
	/** 
	 * 实现指定用户的地址列表显示操作
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public List<Address> findAllByMember(String mid) throws Exception ;
	/**
	 * 实现指定用户地址信息的取得操作
	 * @param id 地址编号
	 * @param mid 用户编号
	 * @return
	 * @throws Exception
	 */
	public Address findByIdAndMember(Integer id,String mid) throws Exception ;
	/**
	 * 实现指定地址数据的修改
	 * @return
	 * @throws Exception 
	 */
	public boolean doUpdateByMember(Address vo) throws Exception ;
	/**
	 * 实现指定用户的地址的信息删除
	 * @param ids 要删除的所有地址
	 * @param mid 要删除的地址所属的用户编号
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveByMember(Set<Integer> ids,String mid) throws Exception ;
	/**
	 * 设置所有的指定用户的联系地址的deflag内容为0
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateFlagByMember(String mid) throws Exception ;
	/**
	 * 修改一个地址的默认选项
	 * @param mid
	 * @param deflag
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateFlag(String mid,Integer adid,Integer deflag) throws Exception ;
}
