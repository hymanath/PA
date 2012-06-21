package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	public List getConnectedUsersCount(Long locationId,String locationType)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select count(model.userId) ");
		query.append(" from User model where ");
		query.append("  model.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = ? )");
		if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			query.append(" and model.constituency.constituencyId = ? group by model.constituency.constituencyId");
		else if (locationType.equalsIgnoreCase(IConstants.DISTRICT)) {
			query.append(" and model.district.districtId = ? group by model.district.districtId");
			
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, IConstants.FREE_USER);
		queryObject.setParameter(1, locationId);
		return queryObject.list();
		
	}

	
	public User getUserByUserId(Long userId)
	{
		Query query = getSession().createQuery("select model from User model where model.userId = ?");
		query.setParameter(0, userId);
		return (User)query.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> allRegisteredUsersData()
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.userId, model.firstName,model.lastName from User model where model.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = ?)");
		Query queryObj = getSession().createQuery(query.toString());
		queryObj.setParameter(0, IConstants.PARTY_ANALYST_USER);
		return queryObj.list();
	}
	public List<User> getDetailsForUsers(List<Long> userIds){
		StringBuilder query = new StringBuilder();				
	//	query.append(" select model.name,model.lastName,model.userId");
		query.append(" from User model where ");
		query.append(" model.userId in (:userIds)");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
	}
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString) {
		StringBuilder query = new StringBuilder();
		query.append("select model.firstName,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId,model ");
		query.append(" from User model where ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("and model.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		if(nameString != null && nameString.trim().length() >0)
		  query.append(" and model.firstName like '"+nameString+"%' ");
		query.append("  order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("role", IConstants.FREE_USER);
		if(startIndex!=null)
			queryObject.setFirstResult(startIndex.intValue());
		if(retrivalCount != null)
			queryObject.setMaxResults(retrivalCount.intValue());	
		
		return queryObject.list();
	}
	public List<Object> getNotConnectedUsersInSelectedLocations(Long userId,List<Long> locationIds,String locationType,List<Long> otherUsers,Long retrivalCount,Long startIndex,String nameString) 
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.firstName,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId, model ");
		query.append(" from User model where ");
		query.append(" model.userId != :userId and model.userId not in (:otherUsers) and ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append(" and model.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		
		if(nameString != null && nameString.trim().length() >0)
		 query.append("and model.name like '"+nameString+"%' ");
		query.append(" order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId",userId);
		queryObject.setParameterList("otherUsers",otherUsers);
		queryObject.setParameter("role", IConstants.FREE_USER);
		if(startIndex!=null)
			queryObject.setFirstResult(startIndex.intValue());
		if(retrivalCount != null)
			queryObject.setMaxResults(retrivalCount.intValue());	
		
		return queryObject.list();
	}
	public Long getAllUsersCountInSelectedLocations(List<Long> locationIds,String locationType, String nameStr)
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(model.userId)");
		query.append(" from User model where ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds) ");
		}
		query.append("and model.state.stateId is not null and model.district.districtId is not null and model.constituency.constituencyId is not null ");
		
		if(nameStr != null && !nameStr.trim().equalsIgnoreCase(""))
		{
			query.append(" and (model.name like '"+nameStr+"%' or model.lastName like '"+nameStr+"%')");
		}
		
		query.append(" and model.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("role", IConstants.FREE_USER);
		return (Long)queryObject.uniqueResult();
	}
	public Long getNotConnectedUsersCountForAUserInAFilterView(Long userId, List<Long> locationIds,String locationType, String nameStr, List<Long> otherUsers){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.userId)");
		query.append(" from User model where ");
		query.append(" model.userId != :userId and model.userId not in (:otherUsers)  ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append(" and model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append(" and model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append(" and model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("and model.state.stateId is not null and model.district.districtId is not null and model.constituency.constituencyId is not null ");
		query.append(" and model.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role ) ");
		if(nameStr!= null && nameStr.trim().length() > 0 )
		query.append("and model.firstName like '"+nameStr+"%' ");
		query.append(" order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId",userId);
		queryObject.setParameterList("otherUsers",otherUsers);		
		queryObject.setParameter("role", IConstants.FREE_USER);
		return (Long)queryObject.uniqueResult();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserEmail(Long userId)
	{
		return getHibernateTemplate().find("select model.userId, model.firstName, model.lastName, model.email from User model where model.userId = ?",userId);
	}
}
