package cn.mldn.vshop.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.pojo.Goods;
import cn.mldn.vshop.service.back.IGoodsServiceBack;

@Service
public class GoodsServiceBackImpl implements IGoodsServiceBack {
	@Resource
	private IGoodsDAO goodsDAO;

	@Override
	public boolean add(Goods vo) throws Exception {
		if (vo.getPrice() < 0.0) { // 必须保证价格大于等于0
			return false;
		}
		vo.setPubdate(new Date()); // 发布日期必须是今天
		vo.setDelflag(0); // 没有必要删除
		return this.goodsDAO.doCreate(vo);
	}

	@Override
	public Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allGoodss", this.goodsDAO.findAllSplit(column, keyWord, currentPage, lineSize)) ;
		map.put("goodsCount", this.goodsDAO.getAllCount(column, keyWord)) ;
		return map ;
	}

	@Override
	public Goods editPre(int id) throws Exception {
		return this.goodsDAO.findById(id);
	}

	@Override
	public boolean edit(Goods vo) throws Exception {
		return this.goodsDAO.doUpdate(vo);
	}

	@Override
	public boolean rm(Set<Integer> ids) throws Exception {
		if (ids.size() == 0) {
			return false ;
		}
		return this.goodsDAO.doRemove(ids);
	}

	@Override
	public Goods show(int id) throws Exception {
		Goods vo = this.goodsDAO.findById(id) ;
		if (vo != null) {
			vo.getItem().getTitle() ;	// 表示查询item中的数据
			vo.getSubitem().getTitle() ;	// 表示查询subitem中的数据
		}
		return vo;
	}

	@Override
	public List<Goods> listStatic(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		return this.goodsDAO.findAllSplit(column, keyWord, currentPage, lineSize); 
	}

}
