package cn.mldn.vshop.service.back;

import java.util.Map;

public interface IIndexServiceBack {
	/**
	 * 实现登录首页的信息统计查询处理操作
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> show() throws Exception ;
}
