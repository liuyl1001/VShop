package cn.mldn.vshop.dao;

import java.util.Set;

import cn.mldn.vshop.pojo.Member;

public interface IMemberDAO extends IDAO<String, Member> {
	/**
	 * 实现用户检测处理操作，但是在进行登录处理的时候，要考虑到管理员与非管理员的区别
	 * @param vo 一定要包含adminflag字段的内容
	 * @return 如果登录成功返回true，如果用户被锁定或者用户名密码错误返回false 
	 * @throws Exception
	 */
	public boolean findLogin(Member vo) throws Exception;
	/**
	 * 实现密码的变更处理，在此VO对象之中保存的是要修改的mid与新密码
	 * @param vo 包含新的数据
	 * @return 修改成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdatePassword(Member vo) throws Exception ;
	/**
	 * 实现密码的更新处理操作
	 * @param vo 要更新的用户数据（email、phone）
	 * @return 修改成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdateBasic(Member vo) throws Exception ;
	
	
	/**
	 * 实现标志位信息的更新处理操作
	 * @param mids 所有要更新的用户的编号
	 * @param delflag 更新的标志位的内容
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateDelflag(Set<String> mids,Integer delflag) throws Exception ;
	
	/**
	 * 根据email进行查询，看是否存在有指定的member对象
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Member findByEmail(String email) throws Exception ;
	/**
	 * 根据电话进行查询，看是否存在有指定的member对象
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public Member findByPhone(String phone) throws Exception ;
}
