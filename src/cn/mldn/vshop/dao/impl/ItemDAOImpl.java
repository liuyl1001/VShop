package cn.mldn.vshop.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.mldn.vshop.dao.IItemDAO;
import cn.mldn.vshop.dao.abs.AbstractDAO;
import cn.mldn.vshop.pojo.Item;

@Component
public class ItemDAOImpl extends AbstractDAO implements IItemDAO {

	@Override
	public boolean doCreate(Item vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Item vo) throws Exception {
		String hql = "UPDATE Item SET title=? WHERE iid=?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, vo.getTitle());
		query.setParameter(1, vo.getIid());
		return query.executeUpdate() > 0;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findAll() throws Exception {
		return super.handleFindAll(Item.class);
	}

	@Override
	public List<Item> findAllSplit(String column, String keyWord,
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
	public Item findByTitleAndId(String title, Integer id) throws Exception {
		String hql = "FROM Item AS i WHERE i.title=? AND i.id<>?" ;
		Query query = super.getSession().createQuery(hql) ;
		query.setParameter(0, title) ;
		query.setParameter(1, id) ;
		return (Item) query.uniqueResult();
	} 

}
