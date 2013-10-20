package com.itgrids.partyanalyst.dao.hibernate;


import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserNewsCategoryDAO;
import com.itgrids.partyanalyst.model.UserNewsCategory;

public class UserNewsCategoryDAO extends GenericDaoHibernate<UserNewsCategory , Long> implements IUserNewsCategoryDAO{

	public UserNewsCategoryDAO()
	{
		super(UserNewsCategory.class);
	}
	
	public List<Object[]> allCategoriesOfUser(Long userId){
		Query query=getSession().createQuery("select model.category.categoryId,model.category.categoryType," +
				" model.isDelete,model.isPrivate from UserNewsCategory model where model.user.userId=:userId");
		
		query.setParameter("userId", userId);
		return query.list();
		
	}
	public Integer updateCategory(Long userId,Long categoryId,String categoryName,String visibility){
		Query query=getSession().createQuery("update UserNewsCategory model set " +
				" model.isPrivate=:visibility where model.user.userId=:userId and model.category.categoryId=:categoryId");
		
		query.setParameter("categoryId", categoryId);
		query.setParameter("userId", userId);
		
		query.setParameter("visibility", visibility);
		
		return query.executeUpdate();
		
	}
	public Integer updateCategoryName(Long userId,Long categoryId,String categoryName){
		Query query=getSession().createQuery("update Category model set " +
				" model.categoryType=:categoryName where model.categoryId=:categoryId");
		
		query.setParameter("categoryId", categoryId);
		query.setParameter("categoryName", categoryName);
		
		return query.executeUpdate();
	}
	public Integer updateCategoryStatus(Long userId,Long categoryId,String status){
		Query query=getSession().createQuery("update UserNewsCategory model set " +
				" model.isDelete=:status where model.user.userId=:userId and model.category.categoryId=:categoryId");
		
		query.setParameter("categoryId", categoryId);
		query.setParameter("userId", userId);
		query.setParameter("status", status);
		
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getDeletedCategoryIds()
	{
		Query query = getSession().createQuery(" select distinct model.category.categoryId from UserNewsCategory model " +
				" where model.isDelete = 'true'");
		return query.list();
	}
	
}
