package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.utils.IConstants;

public class AnanymousUserDAO extends GenericDaoHibernate<AnanymousUser, Long> implements IAnanymousUserDAO {

	public AnanymousUserDAO() {
		super(AnanymousUser.class);
	}

	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkUserPassword(String password,
			Long userId) {
		Object[] parameters = {password,userId};
		return getHibernateTemplate().find("select model.password from AnanymousUser model where model.password=? and model.userId=?",parameters);
	}
	
	public Integer changeUserPassword(String password,Long userId,String status,Date date)
	{
	StringBuilder query = new StringBuilder();
	query.append("update AnanymousUser model set model.password = ?,model.isPwdChanged=?,model.updatedDate=? where model.userId = ?");
	
	Query queryObject = getSession().createQuery(query.toString());
	queryObject.setParameter(0, password);
	queryObject.setParameter(1, status);
	queryObject.setParameter(2, date);
	queryObject.setParameter(3, userId);
	
	
	return queryObject.executeUpdate();	
	}

	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkAnonymousUserLogin(String userId,
			String password) {
		Object[] parameters = {userId,password};
		return getHibernateTemplate().find("from AnanymousUser model where model.username = ? and model.password = ?",parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkForUserNameAvailabiity(String userName) {

		return getHibernateTemplate().find("select model.username from AnanymousUser model where model.username =?",userName);
	}
	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkForUserNameAvailabiityForEmail(String userName) {
		
		return getHibernateTemplate().find("select model.email from AnanymousUser model where model.email = ?",userName);
	}
	
	@SuppressWarnings("unchecked")
	public List<AnanymousUser>  getPassword(String password){
		
		return getHibernateTemplate().find("select model.password from AnanymousUser model where password=?", password);
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString) {
		StringBuilder query = new StringBuilder();
		query.append("select model.name,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId,model ");
		query.append(" from AnanymousUser model where ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("and model.name like '"+nameString+"%' order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
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
		query.append(" from AnanymousUser model where ");
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
		
		query.append("order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		
		return (Long)queryObject.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<AnanymousUser> getDetailsForUsers(List<Long> userIds){
		StringBuilder query = new StringBuilder();				
	//	query.append(" select model.name,model.lastName,model.userId");
		query.append(" from AnanymousUser model where ");
		query.append(" model.userId in (:userIds)");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getAnanymousUserLocationDetailsByIds(List<Long> userIds)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.state.stateId,model.state.stateName,model.district.districtId,model.district.districtName,model.constituency.constituencyId,model.constituency.name");
		query.append(" from AnanymousUser model where ");
		query.append(" model.userId in (:userIds)");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
		
	}	
	
	@SuppressWarnings("unchecked")
	public List getConnectedUsersCount(Long locationId,String locationType)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select count(model.userId) ");
		query.append(" from AnanymousUser model where ");
		
		if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			query.append(" model.constituency.constituencyId = ? group by model.constituency.constituencyId");
		else if (locationType.equalsIgnoreCase(IConstants.DISTRICT)) {
			query.append(" model.district.districtId = ? group by model.district.districtId");
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, locationId);
		
		return queryObject.list();
		
	}
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName)
	{		
		StringBuilder query = new StringBuilder();
		query.append("update AnanymousUser model set model.profileImg = ? where model.userId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, imageName);
		queryObject.setParameter(1, userId);	
		
		return queryObject.executeUpdate();	
		
	}
	
	public List getUserProfileImageNameByUserId(Long userId)
	{		
		
		StringBuilder query = new StringBuilder();
		query.append("select model.profileImg from AnanymousUser model where model.userId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
		
		return queryObject.list();	
		
	}
	
	public List getUserDetails(String userName){
		return getHibernateTemplate().find("select model.email,model.password from AnanymousUser model where model.username= ?",userName);
	}
	public Integer saveUserNameTOEmail(Long userId,String email){
		StringBuilder query = new StringBuilder();
		query.append("update AnanymousUser model set model.username = ?,model.email= ? where model.userId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, email);
		queryObject.setParameter(1, email);	
		queryObject.setParameter(2, userId);	
				
		return queryObject.executeUpdate();	
	}
	public List<AnanymousUser> getUserByUserName(String userName)
	{
		return getHibernateTemplate().find("select model from AnanymousUser model where model.username= ?",userName);
	}
	public List<AnanymousUser> changeUserNameAsEmail(String email)
	{
		return getHibernateTemplate().find("select model from AnanymousUser model where model.email= ?",email);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getUserEmail(Long userId)
	{
		return getHibernateTemplate().find("select model.userId , model.name , model.lastName , model.email from AnanymousUser model where model.userId = ?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public AnanymousUser getAnanymousUserByUserId(Long userId)
	{
		
		Query queryObject=getSession().createQuery("select model from AnanymousUser model where model.userId=?");
		
		queryObject.setParameter(0,userId);
		
		return (AnanymousUser)queryObject.uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPasswordNotUpdatdUsersList()
	{
		return getHibernateTemplate().find("select model.name,model.lastName,DATE(model.registeredDate),model.username,model.password,model.email " +
				" from AnanymousUser model where model.isPwdChanged = 'false' and model.email is not null and model.email not like '' ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getNotConnectedUsersInSelectedLocations(Long userId,List<Long> locationIds,String locationType,List<Long> otherUsers,Long retrivalCount,Long startIndex,String nameString) 
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.name,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId, model ");
		query.append(" from AnanymousUser model where ");
		query.append(" model.userId != :userId and model.userId not in (:otherUsers) and ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		
		query.append("and model.name like '"+nameString+"%' order by model.userId desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId",userId);
		queryObject.setParameterList("otherUsers",otherUsers);
		
		if(startIndex!=null)
			queryObject.setFirstResult(startIndex.intValue());
		if(retrivalCount != null)
			queryObject.setMaxResults(retrivalCount.intValue());	
		
		return queryObject.list();
	}
	
	public Long getNotConnectedUsersCountForAUserInAFilterView(Long userId, List<Long> locationIds,String locationType, String nameStr, List<Long> otherUsers){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.userId)");
		query.append(" from AnanymousUser model where ");
		query.append(" model.userId != :userId and model.userId not in (:otherUsers) and ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("and model.state.stateId is not null and model.district.districtId is not null and model.constituency.constituencyId is not null ");
		query.append("and model.name like '"+nameStr+"%' order by model.userId desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId",userId);
		queryObject.setParameterList("otherUsers",otherUsers);		
		
		return (Long)queryObject.uniqueResult();
		
	}
	public List<Object[]> getAllUsersMobile()
	{
		return getHibernateTemplate().find("select distinct model.name,model.lastName,model.mobile,model.constituency.name,model.userId from AnanymousUser model where model.mobile is not null and model.mobile !=''");
	}
	public List<Object> getAllMobilenosAsUnique()
	{
		return getHibernateTemplate().find("select distinct model.mobile from AnanymousUser model where model.mobile is not null and model.mobile !=''");
	}
	
	
}
