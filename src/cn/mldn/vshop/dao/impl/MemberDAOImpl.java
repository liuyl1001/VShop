package cn.mldn.vshop.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IMemberDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Member;
@Component
public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {

	@Override
	public boolean doCreate(Member vo) throws Exception {
		return super.getSession().save(vo) != null ;
	}

	@Override
	public boolean doUpdate(Member vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member findById(String id) throws Exception {
		return (Member) super.getSession().get(Member.class, id);
	}
	
	@Override
	public Member findByEmail(String email) throws Exception {
		String hql = "FROM Member AS m WHERE m.email=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, email) ;
		return (Member) query.uniqueResult() ;
	}
	
	@Override
	public Member findByPhone(String phone) throws Exception {
		String hql = "FROM Member AS m WHERE m.phone=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, phone) ;
		return (Member) query.uniqueResult() ;
	} 

	@Override
	public List<Member> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception { 
		return super.handleSplit("Member", column, keyWord, currentPage, lineSize);
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		return super.handleAllCount("Member", column, keyWord, null);
	}

	@Override
	public boolean findLogin(Member vo) throws Exception {
		String hql = "SELECT COUNT(*) FROM Member AS m WHERE m.mid=? AND m.password=? AND m.adminflag=? AND m.delflag=0" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, vo.getMid()) ;
		query.setParameter(1, vo.getPassword()) ;
		query.setParameter(2, vo.getAdminflag()) ;
		Object obj = query.uniqueResult() ;
		Integer res = ((Long) obj).intValue() ;
		if (res == 0) {
			return false;
		} 
		return true ; 
	}

	@Override
	public boolean doUpdatePassword(Member vo) throws Exception {
		String hql = "UPDATE Member SET password=? WHERE mid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, vo.getPassword()) ;
		query.setParameter(1, vo.getMid()) ;
		return query.executeUpdate() > 0 ;
	} 

	@Override
	public boolean doUpdateDelflag(Set<String> mids, Integer delflag)
			throws Exception {
		StringBuffer buf = new StringBuffer() ;
		buf.append("UPDATE Member SET delflag=").append(delflag).append(" WHERE mid IN (") ;
		Iterator<String> iter = mids.iterator() ;
		while (iter.hasNext()) {
			buf.append("'").append(iter.next()).append("'").append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		Query query = super.getSession().createQuery(buf.toString()) ;
		return query.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdateBasic(Member vo) throws Exception {
		String hql = "UPDATE Member SET email=?,phone=? WHERE mid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, vo.getEmail()) ;
		query.setParameter(1, vo.getPhone()) ;
		query.setParameter(2, vo.getMid()) ;
		return query.executeUpdate() > 0 ;
	}
 
}
