package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.model.User;

public class UserDAO extends GenericDaoHibernate<User,Long> implements IUserDAO{

	public UserDAO()
	{
		super(User.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> checkForUserNameAvailabiity(String userName)
	{
		return getHibernateTemplate().find("select model.userName from User model where model.userName = ?",userName);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> checkForUserNameAvailabiityForEmail(String userName)
	{
		return getHibernateTemplate().find("select model.email from User model where model.email = ?",userName);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> checkUserPassword(String password,Long userId) 
	{
		Object[] parameters = {password,userId};
		return getHibernateTemplate().find("select model.password from User model where model.password = ? and model.userId = ?",parameters);
	}
	
	public Integer changeUserPassword(String password,Long registrationId,String status,Date date)
	{
		StringBuilder query = new StringBuilder();
		query.append("update User model set model.password =?, model.isPwdChanged =?, model.updatedDate =? where model.userId =?");
		Query queryObj = getSession().createQuery(query.toString());
		queryObj.setParameter(0, password);
		queryObj.setParameter(1, status);
		queryObj.setParameter(2, date);
		queryObj.setParameter(3, registrationId);
		return queryObj.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getUserIdByUserName(String userName)
	{
		return getHibernateTemplate().find("select model.userId from User model where model.userName = ?",userName);
	}
	
	public User findByUserNameAndPassword(String userName, String password)
	{
		Query queryObject = getSession().createQuery("select model from User model where model.userName = ? and model.password = ?");
		queryObject.setParameter(0, userName);
		queryObject.setParameter(1, password);
		return (User)queryObject.uniqueResult(); 
	}
	
	public String getUserProfileImageNameByUserId(Long userId)
	{
		Query query = getSession().createQuery("select model.profileImg from User model where model.userId = ?");
		query.setParameter(0, userId);
		return (String)query.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserLocationDetailsByUserIds(List<Long> userIds)
	{
		Query query = getSession().createQuery("select model.state.stateId, model.state.stateName, model.district.districtId, " +
				" model.district.districtName, model.constituency.constituencyId, model.constituency.name from User model " +
				" where model.userId in (:userIds)");	
		
		query.setParameterList("userIds", userIds);
		return query.list();
	}
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName)
	{
		StringBuilder query = new StringBuilder();
		query.append("update User model set model.profileImg =? where model.userId =?");
		Query queryObj = getSession().createQuery(query.toString());
		queryObj.setParameter(0, imageName);
		queryObj.setParameter(1, userId);
		return queryObj.executeUpdate();
	}
	
	
	
}
