package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.model.Category;

public class CategoryDAO extends GenericDaoHibernate<Category, Long> implements ICategoryDAO {
	public CategoryDAO() {
		super(Category.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoryDetails(){
		   return getHibernateTemplate().find("select model.categoryId,model.categoryType from Category  model order by model.categoryType ");
	   }

	@SuppressWarnings("unchecked")
	public List<String> checkCategoryNameExist(String name)
	{
		Query query = getSession().createQuery(" select model.categoryType from Category model where model.categoryType =:categoryType ");
		query.setParameter("categoryType", name);
		return query.list();
	}
	
	public List<Object[]> checkCategoryNameIdExist(String name)
	{
		Query query = getSession().createQuery(" select model.categoryType,model.categoryId from Category model where model.categoryType =:categoryType ");
		query.setParameter("categoryType", name);
		return query.list();
	}
	
	public Long getMaxOrderNo()
	{
		Query query = getSession().createQuery(" select max(model.orderNo) from Category model ");
		return (Long) query.uniqueResult();
	}
}
