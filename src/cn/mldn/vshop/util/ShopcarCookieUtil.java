package cn.mldn.vshop.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

public class ShopcarCookieUtil {
	private static final String KEY = "shopcar"; // 描述的是购物车的cookie的key信息

	public void add(int gid) { // 需要实现数据的增加处理
		Map<Integer,Integer> map = this.loadShopcar() ;	// 取得所有的Cookie数据
		int amount = 0 ;
		if (map.containsKey(gid)) {	// 当前gid数据存在
			amount = map.get(gid) ;
		}
		amount ++ ;
		map.put(gid, amount) ;	// 保存Map数据
		this.saveShopcar(map); 
	}
	public void clear() {
		Cookie c = new Cookie(KEY,"") ;
		c.setPath(ServletActionContext.getRequest().getContextPath());
		c.setMaxAge(0); 
		ServletActionContext.getResponse().addCookie(c); 
	}
	public void add(Map<Integer,Integer> map) {
		this.saveShopcar(map); 
	}
	public void add(int gid,int amount) { // 需要实现数据的增加处理
		Map<Integer,Integer> map = this.loadShopcar() ;	// 取得所有的Cookie数据
		map.put(gid, amount) ;	// 保存Map数据
		this.saveShopcar(map); 
	}
	public void rm(Set<Integer> gids) {
		Map<Integer,Integer> map = this.loadShopcar() ;	// 取得所有的Cookie数据
		Iterator<Integer> iter = gids.iterator() ;
		while(iter.hasNext()) {
			map.remove(iter.next()) ;
		}
		this.saveShopcar(map); 
	} 
	/**
	 * 将购物车数据保存在Cookie之中
	 * @param map
	 */
	public void saveShopcar(Map<Integer,Integer> map) {
		StringBuffer buf = new StringBuffer() ;
		Iterator<Map.Entry<Integer,Integer>> iter = map.entrySet().iterator() ;
		while (iter.hasNext()) {
			Map.Entry<Integer, Integer> me = iter.next() ;
			buf.append(me.getKey()).append(":").append(me.getValue()).append("|") ;
		}
		Cookie c = new Cookie(KEY,buf.toString()) ;
		c.setPath(ServletActionContext.getRequest().getContextPath());
		c.setMaxAge(36000);
		ServletActionContext.getResponse().addCookie(c); 
	}
	/**
	 * 读取指定的cookie的内容，并且为了方便处理，将其保存在Map集合之中
	 * @return
	 */
	public Map<Integer, Integer> loadShopcar() { // 读取所有的购物车数据
		Map<Integer,Integer> map = new HashMap<Integer,Integer>() ;
		String data = null ;
		Cookie cookie[] = ServletActionContext.getRequest().getCookies();
		if (cookie != null) {	// 当前的Cookie已经取得了
			for (int x = 0; x < cookie.length; x++) {
				if (cookie[x].getName().equals(KEY)) {	// 现在取得的是购物车数据
					data = cookie[x].getValue() ;	// 取得已经保存的全部数据
					break ;	// 退出循环
				}
			}
		}
		if (data == null || "".equals(data)) {
			return map ;
		}
		String result [] = data.split("\\|") ;
		for (int x = 0 ; x < result.length ; x ++) {
			String temp [] = result[x].split(":") ;
			map.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])) ;
		}
		return map ;
	}
}
