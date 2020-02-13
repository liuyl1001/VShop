package cn.mldn.vshop.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IDetailsDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Details;

@Component
public class DetailsDAOImpl extends AbstractDAO implements IDetailsDAO {

	@Override
	public boolean doCreate(Details vo) throws Exception {
		return super.getSession().save(vo) != null;
	}

	@Override
	public boolean doUpdate(Details vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Details findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Details> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Integer> findAllByOrdersAndMember(Integer oid) throws Exception {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String hql = "SELECT d.goods.gid,d.amount FROM Details AS d WHERE d.orders.oid=?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, oid);
		List<Object[]> all = query.list();
		Iterator<Object[]> iter = all.iterator();
		while (iter.hasNext()) {
			Object obj[] = iter.next();
			map.put((Integer) obj[0], (Integer) obj[1]);
		}
		return map;
	}

}
