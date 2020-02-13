package cn.mldn.vshop.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.vshop.pojo.Member;

public interface IMemberServiceBack {
	/**
	 * 查看用户完整信息的操作，同时在本程序里面需要提供有查看全部地址的功能
	 * @param mid 
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> show(String mid) throws Exception ; 
	
	/**
	 * 实现用户的登录检测操作
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean login(Member vo) throws Exception;
	/**
	 * 实现数据的分页列表显示操作
	 * @param column 
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return 返回的内容包含有如下的信息：<br>
	 * <li>key = allMembers、value = IMemberDAO.findAllSplit()</li>
	 * <li>key = memberCount、value = IMemberDAO.getAllCount()</li>
	 * @throws Exception
	 */
	public Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception;
	
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
	 * 实现密码的变更处理操作
	 * @param vo 包含有要修改的数据
	 * @return 修改成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean editPassword(Member vo) throws Exception ;
	/**
	 * 实现用户的锁定
	 * @param mids
	 * @return
	 * @throws Exception
	 */
	public boolean editLock(Set<String> mids) throws Exception ; 
}
