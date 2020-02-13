package cn.mldn.vshop.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IOrdersDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Orders;
@Component
public class OrdersDAOImpl extends AbstractDAO implements IOrdersDAO {

	@Override
	public boolean doCreate(Orders vo) throws Exception {
		return super.getSession().save(vo) != null ;
	}

	@Override
	public boolean doUpdate(Orders vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Orders findById(Integer id) throws Exception {
		return (Orders) super.getSession().get(Orders.class, id); 
	}

	@Override
	public List<Orders> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		return super.handleSplit("Orders", column, keyWord, currentPage,
				lineSize, "subdate");
	} 

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		return super.handleAllCount("Orders", column, keyWord, null); 
	}

	@Override
	public List<Orders> findAllSplitByMember(String mid, Integer currentPage,
			Integer lineSize) throws Exception {
		String hql = "FROM Orders AS o WHERE o.member.mid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, mid) ;
		query.setFirstResult((currentPage - 1) * lineSize) ;
		query.setMaxResults(lineSize) ;
		return query.list() ;
	}

	@Override
	public Integer getAllCountByMember(String mid) throws Exception {
		String hql = "SELECT COUNT(*) FROM Orders AS o WHERE o.member.mid=?" ; 
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, mid) ;
		return ((Long)query.uniqueResult()).intValue() ;
	}

	@Override
	public Orders findByIdAndMember(String mid, Integer oid) throws Exception {
		String hql = "FROM Orders AS o WHERE o.member.mid=? AND o.oid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, mid) ;
		query.setParameter(1, oid) ;
		return (Orders) query.uniqueResult() ;
	}

	@Override
	public Integer getTodayCount() throws Exception {
		String hql = "SELECT COUNT(*) FROM Orders AS o WHERE o.subdate BETWEEN ? AND ?" ;
		Query query = super.getSession().createQuery(hql) ;
		Date date = new Date() ;
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date) + " 00:00:00" ;
		String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date) + " 23:59:59" ;
		query.setParameter(0, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(startDate)) ;
		query.setParameter(1, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(endDate)) ;
		return ((Long) query.uniqueResult()).intValue() ; 
	} 

}
