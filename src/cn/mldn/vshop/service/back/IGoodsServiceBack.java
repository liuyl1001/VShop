package cn.mldn.vshop.service.back;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.vshop.pojo.Goods;

public interface IGoodsServiceBack {
	/**
	 * 实现商品的添加处理操作 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(Goods vo) throws Exception;
	/**
	 * 实现数据的分页显示处理操作
	 * @param column 
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return 返回的结果包含如下数据内容：<br>
	 * <li>key = allGoodss、value = IGoodsDAO.findAllSplit()</li>
	 * <li>key = goodsCount、value = IGoodsDAO.getAllCount()</li>
	 * @throws Exception
	 */
	public Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception; 
	/**
	 * 列出指定条数的商品数据
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public List<Goods> listStatic(String column, String keyWord,
			int currentPage, int lineSize) throws Exception ;
	
	public Goods editPre(int id) throws Exception ;
	public boolean edit(Goods vo) throws Exception ;
	
	public boolean rm(Set<Integer> ids) throws Exception ;
	/**
	 * 此处是查询一个商品的详细信息，包括商品所属的分类以及子类分类。<br>
	 * 由于使用的是Hibernate，所以在整个代码处理过程之中，可以直接利用级联处理关系来实现Item与Subitem数据加载
	 * @param id 要查询的商品ID
	 * @return
	 * @throws Exception
	 */
	public Goods show(int id) throws Exception ;
}
