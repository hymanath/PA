package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;
import com.itgrids.partyanalyst.model.UserVoterCategory;

public class UserVoterCategoryDAO extends GenericDaoHibernate<UserVoterCategory, Long> implements IUserVoterCategoryDAO{

	public UserVoterCategoryDAO() {
		super(UserVoterCategory.class);
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings("unchecked")
	public List<UserVoterCategory> getUserCategoryValues(){
		
		return getHibernateTemplate().find("from UserVoterCategory model");
		
	}


	public List<Long> checkCategoryExist(Long userId,String name) {
		Object[] values = {userId,name};
		return getHibernateTemplate().find("select count(*) from UserVoterCategory model where model.user.userId = ? and model.categoryName = ?",values);
		
	}
	
public List<Object[]> getCategoryValuesList(Long userId) {
		
		return getHibernateTemplate().find("select model.userVoterCategoryId ,model.categoryName from UserVoterCategory model where model.user.userId = ?",userId);
		
	}

	public List<UserVoterCategory> getUserCategoriesByUserId(Long userId){
		
		return getHibernateTemplate().find("from UserVoterCategory model where model.user.userId = ?",userId);
		
	}
	public List<Object[]> getUserCategoriesByUserList(List<Long> userId)
	{
		Query query = getSession().createQuery("select model.userVoterCategoryId ,model.categoryName from UserVoterCategory model where model.user.userId in(:userId)");
		query.setParameterList("userId", userId);
		return query.list();
	}
	
	public String getCategoryNameByCategoryId(Long userVoterCategoryId)
	{
		Query queryObj = getSession().createQuery("select model.categoryName from UserVoterCategory model where model.userVoterCategoryId =:userVoterCategoryId ");
		queryObj.setParameter("userVoterCategoryId", userVoterCategoryId);
		return (String) queryObj.uniqueResult();
	}
	
}
