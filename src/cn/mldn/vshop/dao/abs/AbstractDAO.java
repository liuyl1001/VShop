package cn.mldn.vshop.dao.abs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 提供DAO子类的公共处理操作部分支持
 * 
 * @author mldn
 */
public abstract class AbstractDAO {
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 返回SessionFactory，可以打开新的Session或者控制二级缓存
	 * 
	 * @return
	 */
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * 取得Session对象，实现数据的处理操作
	 * 
	 * @return 当前Session对象
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public <T> List<T> handleFindAll(Class<T> t) {
		Criteria criteria = this.getSession().createCriteria(t);
		return criteria.list();
	}

	public List handleSplit(String pojo, String column, String keyWord,
			Integer currentPage, Integer lineSize) {
		String hql = "FROM " + pojo + " AS p WHERE p." + column
				+ " LIKE ? ORDER BY p.reg DESC";
		Query query = this.getSession().createQuery(hql); 
		query.setParameter(0, "%" + keyWord + "%");
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		return query.list();
	}
	public List handleSplit(String pojo, String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn) {
		String hql = "FROM " + pojo + " AS p WHERE p." + column
				+ " LIKE ? ORDER BY p." + orderColumn + " DESC";
		Query query = this.getSession().createQuery(hql); 
		query.setParameter(0, "%" + keyWord + "%");
		query.setFirstResult((currentPage - 1) * lineSize);
		query.setMaxResults(lineSize);
		return query.list();
	}
	public Integer handleAllCount(String pojo, String column, String keyWord,
			String cond) {
		String hql = "SELECT COUNT(*) FROM " + pojo + " AS p WHERE p." + column
				+ " LIKE ?";
		if (cond != null) {
			hql += " AND " + cond;
		}
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, "%" + keyWord + "%");
		return ((Long) query.uniqueResult()).intValue();
	}

	public boolean handleUpdateDeleteBatchByInteger(String pojo,
			String columnName, String columnValue, String idColumn,
			Set<Integer> ids) {
		StringBuffer hql = new StringBuffer();
		// "" + pojo + " SET " + columnName + "=" + columnValue + " WHERE " +
		// idColumn + " IN ("
		hql.append("UPDATE ").append(pojo).append(" SET ").append(columnName)
				.append("=").append(columnValue);
		hql.append(" WHERE ").append(idColumn).append(" IN ( ");
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()) {
			hql.append(iter.next()).append(",");
		}
		hql.delete(hql.length() - 1, hql.length()).append(")");
		Query query = this.getSession().createQuery(hql.toString());
		return query.executeUpdate() > 0;
	}
}
