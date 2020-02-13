package cn.mldn.vshop.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IProvinceDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Province;

@Component
public class ProvinceDAOImpl extends AbstractDAO implements IProvinceDAO {

	@Override
	public boolean doCreate(Province vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Province vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Province findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Province> findAll() throws Exception {
		Criteria c = super.getSession().createCriteria(Province.class) ;
		return c.list() ;
	}

	@Override
	public List<Province> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
