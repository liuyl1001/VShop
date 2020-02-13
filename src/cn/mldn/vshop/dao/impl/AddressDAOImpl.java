package cn.mldn.vshop.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IAddressDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Address;

@Component
public class AddressDAOImpl extends AbstractDAO implements IAddressDAO {

	@Override
	public boolean doCreate(Address vo) throws Exception {
		return super.getSession().save(vo) != null;
	}

	@Override
	public boolean doUpdate(Address vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address findById(Integer id) throws Exception {
		return null;
	}

	@Override
	public List<Address> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findAllSplit(String column, String keyWord,
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
	public List<Address> findAllByMember(String mid) throws Exception {
		String hql = "FROM Address AS a WHERE a.member.mid=?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, mid);
		return query.list();
	}

	@Override
	public Address findByIdAndMember(Integer id, String mid) throws Exception {
		String hql = "FROM Address AS a WHERE a.adid=? AND a.member.mid=?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, id);
		query.setParameter(1, mid);
		return (Address) query.uniqueResult();
	}

	@Override
	public boolean doUpdateByMember(Address vo) throws Exception {
		String hql = "UPDATE Address AS a SET a.province.pid=?,a.city.cid=?,a.addr=?,a.receiver=?,a.phone=? WHERE a.adid=? AND a.member.mid=?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, vo.getProvince().getPid());
		query.setParameter(1, vo.getCity().getCid());
		query.setParameter(2, vo.getAddr());
		query.setParameter(3, vo.getReceiver());
		query.setParameter(4, vo.getPhone());
		query.setParameter(5, vo.getAdid());
		query.setParameter(6, vo.getMember().getMid());
		return query.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveByMember(Set<Integer> ids, String mid)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM Address WHERE adid IN (");
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")")
				.append(" AND mid=").append("'").append(mid).append("'");
		Query query = super.getSession().createQuery(buf.toString());
		return query.executeUpdate() == ids.size();
	}

	@Override
	public boolean doUpdateFlagByMember(String mid) throws Exception {
		String hql = "UPDATE Address AS a SET deflag=0 WHERE a.member.mid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, mid) ;
		return query.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdateFlag(String mid,Integer adid, Integer deflag) throws Exception {
		String hql = "UPDATE Address AS a SET deflag=? WHERE a.adid=? AND a.member.mid=?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, deflag) ;
		query.setParameter(1, adid) ;
		query.setParameter(2, mid) ;
		return query.executeUpdate() > 0 ;
	} 

}
