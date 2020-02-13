package cn.mldn.vshop.service.front;

import java.util.Map;

import cn.mldn.vshop.pojo.Member;

public interface IMemberServiceFront {
	/**
	 * 查询指定的注册id是否存在，如果只是由11位数字组成，按照手机检测；
	 * 如果包含有@和.按照email检测，如果都不包含的，按照mid检测
	 * @param id 可能注册的形式
	 * @return 如果存在则返回true（不能够使用），如果不存在返回false（可以使用）
	 * @throws Exception
	 */
	public boolean checkId(String id) throws Exception ;
	/**
	 * 注册新的用户，如果此时mid不存在，那么请记得，生成一个默认的id，简单的方法。
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(Member vo) throws Exception ;
	/**
	 * 实现密码的变更处理（针对于管理员的操作实现），在本操作之中将执行如下的操作方法：<br>
	 * <li>将执行IMemberDAO.findLogin()方法确认原始用户名与密码是否匹配；</li>
	 * <li>如果原始密码匹配则使用IMemberDAO.doUpdatePassword()方法进行更新处理</li>
	 * @param mid 要更新密码的用户ID
	 * @param newPass 新密码（MD5加密处理）
	 * @param oldPass 旧密码（MD5加密处理）
	 * @return 密码修改成功，则返回true，否则返回false
	 * @throws Exception
	 */
	public boolean editPassword(String mid,String newPass,String oldPass) throws Exception ;
	/**
	 * 实现用户基础的信息
	 * @param vo 包含有基础的信息的VO对象，调用IMemberDAO.doUpdateBasic()方法
	 * @return
	 * @throws Exception
	 */
	public boolean editBasic(Member vo) throws Exception ;
	/**
	 * 数据更新前的查询处理操作
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Member editBasicPre(String mid) throws Exception ;
	/**
	 * 实现用户登录的处理操作，处理的时候可以根据用户名、邮箱、电话进行登录控制
	 * @param id
	 * @param password
	 * @param csc 表示是Cookie中取出的所有的已经保存的购物车数据记录
	 * @return
	 * @throws Exception
	 */
	public boolean login(String id,String password,Map<Integer,Integer> csc) throws Exception ; 
}
