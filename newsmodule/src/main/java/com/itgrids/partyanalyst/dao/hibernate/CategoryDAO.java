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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCategories()
	{
		Query query = getSession().createQuery(" select distinct model.categoryId,model.categoryType from Category model " +
				" order by model.categoryType ");
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoriesByUserId(Long userId)
	{
		Query query = getSession().createQuery(" select model.categoryId,model.categoryType,model.isDelete,model.isPrivate from Category model " +
				" where model.userId =:userId ");
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public Integer updateCategoryByUserId(Long userId,Long categoryId,String status)
	{
	   Query query = getSession().createQuery(" update Category model set model.isDelete =:status where model.categoryId =:categoryId and model.userId =:userId ");
	   
	   query.setParameter("userId", userId);
	   query.setParameter("categoryId", categoryId);
	   query.setParameter("status", status);
	   
	   return query.executeUpdate();
	}
	
}
