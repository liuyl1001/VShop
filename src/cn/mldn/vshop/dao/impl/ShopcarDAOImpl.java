package cn.mldn.vshop.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IShopcarDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Shopcar;
@Component
public class ShopcarDAOImpl extends AbstractDAO implements IShopcarDAO {

	@Override
	public boolean doCreate(Shopcar vo) throws Exception {
		return super.getSession().save(vo) != null ;
	}

	@Override
	public boolean doUpdate(Shopcar vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shopcar findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAllSplit(String column, String keyWord,
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
	public boolean findExists(Integer gid, String mid) throws Exception {
		String hql = "FROM Shopcar AS s WHERE s.member.mid=? AND s.goods.gid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, mid) ;
		query.setParameter(1, gid) ;
		if (query.list().size() > 0) {
			return true ;
		}
		return false;
	} 

	@Override
	public boolean doUpdateIncrement(String mid, Integer gid) throws Exception {
		String hql = "UPDATE Shopcar AS s SET s.amount=s.amount+1 WHERE s.member.mid=? AND s.goods.gid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, mid) ;
		query.setParameter(1, gid) ;
		return query.executeUpdate() > 0 ;
	}

	@Override
	public Map<Integer, Integer> findAllByMember(String mid) throws Exception {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>() ;
		String sql = "SELECT gid,amount FROM shopcar WHERE mid=?" ;
		SQLQuery query = super.getSession().createSQLQuery(sql) ;
		query.setParameter(0, mid) ;
		List<Object[]> all =  query.list() ;
		Iterator<Object []> iter = all.iterator() ;
		while (iter.hasNext()) {
			Object[] obj = iter.next() ;
			map.put((Integer) obj[0], (Integer) obj[1]) ;
		}
		return map ;
	}

	@Override
	public boolean doRemoveByMember(String mid) throws Exception {
		String hql = "DELETE FROM Shopcar WHERE mid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, mid) ;
		return query.executeUpdate() > 0 ;
	}

	@Override
	public boolean doCreateByMember(String mid, Map<Integer, Integer> sc)
			throws Exception {
		Iterator<Map.Entry<Integer, Integer>> iter = sc.entrySet().iterator() ;
		while (iter.hasNext()) {
			Map.Entry<Integer, Integer> me = iter.next() ;
			Shopcar shopcar = new Shopcar() ;
			shopcar.getMember().setMid(mid);
			shopcar.getGoods().setGid(me.getKey());
			shopcar.setAmount(me.getValue());
			super.getSession().save(shopcar) ;
		}
		return true ;
	}

	@Override
	public boolean doUpdateAmount(String mid, Integer gid, Integer amount)
			throws Exception {
		String hql = "UPDATE Shopcar SET amount=? WHERE mid=? AND gid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, amount) ;
		query.setParameter(1, mid) ;
		query.setParameter(2, gid) ;
		return query.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemoveByMemberAndGid(String mid, Set<Integer> gids)
			throws Exception {
		StringBuffer buf = new StringBuffer() ;
		buf.append("DELETE FROM Shopcar WHERE mid=? AND gid IN (") ;
		Iterator<Integer> iter = gids.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		Query query = super.getSession().createQuery(buf.toString()) ;
		query.setParameter(0, mid) ;
		return query.executeUpdate() > 0 ;
	} 

}
