package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.model.UserVoterCategoryValue;

public class UserVoterCategoryValueDAO extends GenericDaoHibernate<UserVoterCategoryValue, Long> implements IUserVoterCategoryValueDAO{

	public UserVoterCategoryValueDAO() {
		super(UserVoterCategoryValue.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterCategoryValues(Long userVoterCategoryId,String letters) {
		String cName = ""+letters+"%";
		Query queryObject = getSession().createQuery("select model.userVoterCategoryValueId,model.categoryValue from UserVoterCategoryValue model where model.userVoterCategory.userVoterCategoryId = ? and model.categoryValue like ?");
		queryObject.setLong(0,userVoterCategoryId);
		queryObject.setString(1,cName);
		return queryObject.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserVoterCategoryValue> getCategoryValues(){
		
		return getHibernateTemplate().find("from UserVoterCategoryValue model");
	}
	
	public List<Long> checkCategoryExist(Long userId,String name, Long id) {
		Object[] values = {userId,name};
		return getHibernateTemplate().find("select count(*) from UserVoterCategoryValue model where model.user.userId = ? and model.categoryValue = ?",values);
		
	}
	
	public List<Object[]> getCategoryValuesByUserIdCategId(Long userId,Long userCategoryValueId){
		
		Object[] values = {userId,userCategoryValueId};
		return getHibernateTemplate().find("select model.userVoterCategoryValueId,model.categoryValue from UserVoterCategoryValue model where model.user.userId = ? and model.userVoterCategory.userVoterCategoryId=? ",values);
		
	}
}
