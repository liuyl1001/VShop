package cn.mldn.vshop.service.front;

import java.util.List;
import java.util.Set;

import cn.mldn.vshop.pojo.Address;

public interface IAddressServiceFront {
	/**
	 * 实现地址数据的增加处理
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(String id,Address vo) throws Exception ;
	/**
	 * 根据指定的用户信息列出全部的地址内容
	 * @param id 用户的ID
	 * @return 返回指定用户的全部地址列表数据
	 * @throws Exception
	 */
	public List<Address> listByMember(String id) throws Exception ; 
	/**
	 * 实现地址修改前的数据查询处理操作
	 * @param adid 要修改的地址编号
	 * @param id 用户的登录信息
	 * @return
	 * @throws Exception
	 */
	public Address editPre(int adid,String id) throws Exception ;
	/**
	 * 实现地址数据的修改处理操作
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(String id,Address vo) throws Exception ;
	/**
	 * 实现指定用户的指定地址信息的删除处理操作 
	 * @param id
	 * @param adids
	 * @return
	 * @throws Exception
	 */
	public boolean rm(String id, Set<Integer> adids) throws Exception;
}
