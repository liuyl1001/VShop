package cn.mldn.vshop.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IGoodsDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Goods;

@Component
public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {
	
	@Override
	public boolean doCreate(Goods vo) throws Exception {
		return super.getSession().save(vo) != null ;
	} 

	@Override
	public boolean doUpdate(Goods vo) throws Exception {
		String hql = "UPDATE Goods SET title=?,price=?,note=?,photo=?,iid=?,sid=? WHERE gid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, vo.getTitle()) ;
		query.setParameter(1, vo.getPrice()) ;
		query.setParameter(2, vo.getNote()) ;
		query.setParameter(3, vo.getPhoto()) ;
		query.setParameter(4, vo.getItem().getIid()) ;
		query.setParameter(5, vo.getSubitem().getSid()) ;
		query.setParameter(6, vo.getGid()) ;
		return query.executeUpdate() > 0 ;
	} 

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		return super.handleUpdateDeleteBatchByInteger("Goods", "delflag", "1", "gid", ids);
	}

	@Override
	public Goods findById(Integer id) throws Exception {
		return (Goods) super.getSession().get(Goods.class, id);
	} 

	@Override
	public List<Goods> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		String hql = "FROM Goods AS s WHERE s." + column + " LIKE ? AND s.delflag=0 ORDER BY pubdate DESC" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, "%" + keyWord + "%");
		query.setFirstResult((currentPage - 1) * lineSize) ;
		query.setMaxResults(lineSize) ;
		return query.list() ; 
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		return super.handleAllCount("Goods", column, keyWord,"delflag=0"); 
	}

	@Override
	public List<Goods> findAllBySubitem(Integer sid, String column,
			String keyWord, Integer currentPage, Integer lineSize)
			throws Exception {
		String hql = "FROM Goods AS s WHERE s." + column + " LIKE ? AND s.delflag=0 AND s.subitem.sid=? ORDER BY pubdate DESC" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, "%" + keyWord + "%");
		query.setParameter(1, sid) ;
		query.setFirstResult((currentPage - 1) * lineSize) ;
		query.setMaxResults(lineSize) ;
		return query.list() ; 
	}

	@Override
	public Integer getAllCountBySubitem(Integer sid, String column,
			String keyWord) throws Exception {
		String hql = "SELECT COUNT(*) FROM Goods AS g WHERE g." + column + " LIKE ? AND g.delflag=0 AND g.subitem.sid=? ORDER BY pubdate DESC" ; 
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, "%"+keyWord+"%") ;
		query.setParameter(1, sid) ;
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public List<Goods> findAllByIds(Set<Integer> ids) throws Exception {
		Criteria criteria = super.getSession().createCriteria(Goods.class) ;
		criteria.add(Restrictions.in("gid", ids)) ;
		return criteria.list() ;
	}
 
	@Override
	public List<Goods> findAllByMemberShopcar(String mid) throws Exception {
		String sql = "SELECT gid,title,price,pubdate,note,delflag,photo "
				+ " FROM goods WHERE gid IN ("
				+ "   SELECT gid FROM shopcar WHERE mid=?)" ;
		SQLQuery query = super.getSession().createSQLQuery(sql) ;
		query.setResultTransformer(new AliasToBeanResultTransformer(Goods.class)) ;
		query.setParameter(0, mid) ;
		return query.list() ;
	}
 
}
