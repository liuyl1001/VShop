package cn.mldn.vshop.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.ISubitemDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Subitem;
@Component
public class SubitemDAOImpl extends AbstractDAO implements ISubitemDAO {

	@Override
	public boolean doCreate(Subitem vo) throws Exception {
		return false;
	}

	@Override
	public boolean doUpdate(Subitem vo) throws Exception {
		String hql = "UPDATE Subitem SET title=? WHERE sid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, vo.getTitle()) ;
		query.setParameter(1, vo.getSid()) ;
		return query.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Subitem findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subitem> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subitem> findAllSplit(String column, String keyWord,
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
	public List<Subitem> findAllByItem(Integer iid) throws Exception {
		String hql = "FROM Subitem AS s WHERE s.item.iid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, iid) ;
		return query.list()  ;
	}

	@Override
	public Subitem findByTitleAndSid(String title, Integer sid)
			throws Exception {
		String hql = "FROM Subitem AS s WHERE s.title=? AND s.sid<>?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, title) ;
		query.setParameter(1, sid) ;
		return (Subitem) query.uniqueResult(); 
	} 

}
