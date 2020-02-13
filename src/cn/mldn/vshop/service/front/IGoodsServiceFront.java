package cn.mldn.vshop.service.front;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.vshop.pojo.Goods;

public interface IGoodsServiceFront {
	/**
	 * 实现前台商品信息的列表，根据而级栏目进行列出
	 * @param sid
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listBySubitem(int sid, String column,
			String keyWord, int currentPage, int lineSize) throws Exception; 
	/**
	 * 商品的检索处理
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> listSearch(String column,
			String keyWord, int currentPage, int lineSize) throws Exception ;
	/**
	 * 根据一个商品编号查询商品的信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Goods show(int id) throws Exception ;
	/**
	 * 列出购物车中的所有商品信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<Goods> listCar(Set<Integer> ids) throws Exception ;
	/**
	 * 根据指定的登录用户id，列出所购买的全部商品信息
	 * @param mid
	 * @return 返回的内容包含以下两种结果：<br>
	 * <li>key = allGoods、value = IGoodsDAO.findAllByMemberShopcar()，返回的List集合</li>
	 * <li>key = shopcar、value = IShopcarDAO.findAllByMember()，返回的Map集合</li>
	 * @throws Exception
	 */
	public Map<String,Object> listMemberCar(String id) throws Exception ;
}
 