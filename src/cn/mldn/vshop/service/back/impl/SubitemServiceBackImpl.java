package cn.mldn.vshop.service.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.vshop.dao.ISubitemDAO;
import cn.mldn.vshop.pojo.Subitem;
import cn.mldn.vshop.service.back.ISubitemServiceBack;

@Service
public class SubitemServiceBackImpl implements ISubitemServiceBack {
	@Resource
	private ISubitemDAO subitemDAO;

	@Override
	public List<Subitem> listByItem(int iid) throws Exception {
		return this.subitemDAO.findAllByItem(iid);
	}

	@Override
	public boolean edit(Subitem vo) throws Exception {
		if (this.subitemDAO.findByTitleAndSid(vo.getTitle(), vo.getSid()) == null) {
			return this.subitemDAO.doUpdate(vo) ;
		}
		return false;
	}

}
