package cn.mldn.vshop.service.back.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.pojo.Item;
import cn.mldn.vshop.service.back.IItemServiceBack;

@Service
public class ItemServiceBackImpl implements IItemServiceBack {
	@Resource
	private IItemDAO itemDAO;

	@Override
	public List<Item> list() throws Exception {
		return this.itemDAO.findAll();
	}

	@Override
	public boolean edit(Item vo) throws Exception {
		if (this.itemDAO.findByTitleAndId(vo.getTitle(), vo.getIid()) == null) {
			return this.itemDAO.doUpdate(vo); // 更新item信息
		}
		return false;
	}

	@Override
	public List<Item> listDetails() throws Exception {
		List<Item> all = this.itemDAO.findAll() ;
		Iterator<Item> iter = all.iterator() ;
		while(iter.hasNext()) {
			iter.next().getSubitems().size() ;	// 延迟加载解锁
		}
		return all;
	}
}
