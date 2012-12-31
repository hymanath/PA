package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserCategoryValuesDAO;
import com.itgrids.partyanalyst.model.UserCategoryValues;

public class UserCategoryValuesDAO extends GenericDaoHibernate<UserCategoryValues, Long> implements IUserCategoryValuesDAO{

	public UserCategoryValuesDAO() {
		super(UserCategoryValues.class);
		// TODO Auto-generated constructor stub
	}

	
	@SuppressWarnings("unchecked")
	public List<UserCategoryValues> getUserCategoryValues(){
		
		return getHibernateTemplate().find("from UserCategoryValues model");
		
	}


	public List<Long> checkCategoryExist(Long userId,String name) {
		Object[] values = {userId,name};
		return getHibernateTemplate().find("select count(*) from UserCategoryValues model where model.user.userId = ? and model.userCategoryName = ?",values);
		
	}
	
public List<Object[]> getCategoryValuesList(Long userId) {
		
		return getHibernateTemplate().find("select model.userCategoryValuesId ,model.userCategoryName from UserCategoryValues model where model.user.userId = ?",userId);
		
	}



	
}
