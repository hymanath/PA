package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.utils.IConstants;

public class RegistrationDAO extends GenericDaoHibernate<Registration, Long> implements
		IRegistrationDAO {
	
	public RegistrationDAO() {
		super(Registration.class);
	}
	@SuppressWarnings("unchecked")
	public List<Registration> findByProperty(RegistrationColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from Registration where " + propertyName + "=?", value);
	}
	
	public List<Registration> findByFirstName(Object firstName){

		return findByProperty(RegistrationColumnNames.FIRSTNAME, firstName);
	}
	
	public List<Registration> findByUserName(Object userName){

		return findByProperty(RegistrationColumnNames.USERNAME, userName);
	}
	
	public List<Registration> findByMiddleName(Object middleName){

		return findByProperty(RegistrationColumnNames.MIDDLENAME, middleName);
		
	}
	
	public List<Registration> findByLastName(Object lastName){

		return findByProperty(RegistrationColumnNames.LASTNAME, lastName);
	}
	
		
	public List<Registration> findByCountry(Object country){

		return findByProperty(RegistrationColumnNames.COUNTRY, country);
	}
	
	public List<Registration> findByEmail(Object email){

		return findByProperty(RegistrationColumnNames.EMAIL, email);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findUserName(){
		
		return getHibernateTemplate().find("select userName from Registration");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findPasswords(){
		return getHibernateTemplate().find("select password from Registration");
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> findByUserNameAndPassword(String userName, String password){
		
	 Query queryObject = getSession().createQuery("from Registration as model where model.userName = ? and model.password = ?");
	 queryObject.setParameter(0, userName);
	 queryObject.setParameter(1, password);
	 return queryObject.list(); 

	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> findByUserRegistrationId(Long registrationId) {
		return getHibernateTemplate().find(" from Registration model where model.registrationId = ?", registrationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> getAllRegisteredUsers() {
		return getHibernateTemplate().find(" select model.registrationId,model.firstName,model.lastName from Registration model ");
	}
	@SuppressWarnings("unchecked")
	public List<Registration> checkForUserNameAvailabiity(String userName) {
		return getHibernateTemplate().find("select model.userName from Registration model where model.userName = ?",userName);
	}
    public List<Registration> checkForUserNameAvailabiityForEmail(String userName) {
		
		return getHibernateTemplate().find("select model.email from Registration model where model.email = ?",userName);
	}
	
	
	@SuppressWarnings("unchecked")
	public Integer saveUserNameTOEmail(Long userId,String email){
		StringBuilder query = new StringBuilder();
		query.append("update Registration model set model.userName = ?,model.email= ? where model.registrationId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, email);
		queryObject.setParameter(1, email);	
		queryObject.setParameter(2, userId);	
				
		return queryObject.executeUpdate();	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubusersByParentUserId(Long parentUserId){
		return getHibernateTemplate().find("select model.parentUser.registrationId , model.registrationId From Registration model where model.parentUser.registrationId=?",parentUserId);
	}
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName)
	{
		StringBuilder query = new StringBuilder();
		query.append("update Registration model set model.profileImg = ? where model.registrationId = ?");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, imageName);
		queryObject.setParameter(1, userId);	
		
		return queryObject.executeUpdate();	
		
	}
	public Integer changeUserPassword(String password,Long registrationId,String status,Date date)
	{
		StringBuilder query = new StringBuilder();
		query.append("update Registration model set model.password = ?,model.isPwdChanged=?,model.updatedDate=? where model.registrationId = ?");
		Query queryObj = getSession().createQuery(query.toString());
		queryObj.setParameter(0,password);
		queryObj.setParameter(1, status);
		queryObj.setParameter(2, date);
		queryObj.setParameter(3, registrationId);
		return queryObj.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Registration> checkUserPassword(String password,Long userId)
	{
		Object[] parameters = {password,userId};
		return getHibernateTemplate().find("select model.password from Registration model where model.password = ? and model.registrationId = ?",parameters);
	}
	
	/*public List getUserProfileImageNameByUserId(Long userId)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.profileImg from Registration model where model.registrationId = ?");
		Query queryObj = getSession().createQuery(query.toString());
		queryObj.setParameter(0, userId);
		return queryObj.list();
		
	}*/
	
	/*public List getAnanymousUserLocationDetailsByIds(List<Long> userIds)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.state.stateId,model.state.stateName,model.district.districtId,model.district.districtName,model.constituency.constituencyId,model.constituency.name");
		query.append(" from Registration model where ");
		query.append(" model.registrationId in (:userIds)");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
		
	}	*/
		
	
	public List<Object[]> getUserEmailByUserId(Long userId){
		return getHibernateTemplate().find("select model.registrationId , model.firstName , model.lastName , model.email from Registration model where model.registrationId = ?",userId);
	}
	
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString) {
		StringBuilder query = new StringBuilder();
		query.append("select model.firstName,model.lastName,model.registrationId,model.constituency.name,model.constituency.constituencyId,model ");
		query.append(" from Registration model where ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("and model.registrationId in (select model1.user.registrationId from UserRoles model1 where model1.role.roleType = :role )");
		if(nameString != null && nameString.trim().length() >0)
		  query.append(" and model.firstName like '"+nameString+"%' ");
		query.append("  order by model.registrationId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
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
		query.append("select count(model.registrationId)");
		query.append(" from Registration model where ");
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
		
		query.append(" and model.registrationId in (select model1.user.registrationId from UserRoles model1 where model1.role.roleType = :role )");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("role", IConstants.FREE_USER);
		return (Long)queryObject.uniqueResult();
	}
	
	/*public List getConnectedUsersCount(Long locationId,String locationType)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select count(model.registrationId) ");
		query.append(" from Registration model where ");
		query.append("  model.registrationId in (select model2.user.registrationId from UserRoles model2 where model2.role.roleType = ? )");
		if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			query.append(" and model.constituency.constituencyId = ? group by model.constituency.constituencyId");
		else if (locationType.equalsIgnoreCase(IConstants.DISTRICT)) {
			query.append(" and model.district.districtId = ? group by model.district.districtId");
			
		}
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, IConstants.FREE_USER);
		queryObject.setParameter(1, locationId);
		return queryObject.list();
		
	}*/
	public List<Registration> getDetailsForUsers(List<Long> userIds){
		StringBuilder query = new StringBuilder();				
	//	query.append(" select model.name,model.lastName,model.userId");
		query.append(" from Registration model where ");
		query.append(" model.registrationId in (:userIds)");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
	}
	
	public List<Object> getNotConnectedUsersInSelectedLocations(Long userId,List<Long> locationIds,String locationType,List<Long> otherUsers,Long retrivalCount,Long startIndex,String nameString) 
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.firstName,model.lastName,model.registrationId,model.constituency.name,model.constituency.constituencyId, model ");
		query.append(" from Registration model where ");
		query.append(" model.registrationId != :userId and model.registrationId not in (:otherUsers) and ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append(" and model.registrationId in (select model1.user.registrationId from UserRoles model1 where model1.role.roleType = :role )");
		
		if(nameString != null && nameString.trim().length() >0)
		 query.append("and model.name like '"+nameString+"%' ");
		query.append(" order by model.registrationId desc");
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
	
	public Long getNotConnectedUsersCountForAUserInAFilterView(Long userId, List<Long> locationIds,String locationType, String nameStr, List<Long> otherUsers){
		StringBuilder query = new StringBuilder();
		query.append("select count(model.registrationId)");
		query.append(" from Registration model where ");
		query.append(" model.registrationId != :userId and model.registrationId not in (:otherUsers)  ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append(" and model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append(" and model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append(" and model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("and model.state.stateId is not null and model.district.districtId is not null and model.constituency.constituencyId is not null ");
		query.append(" and model.registrationId in (select model2.user.registrationId from UserRoles model2 where model2.role.roleType = :role ) ");
		if(nameStr!= null && nameStr.trim().length() > 0 )
		query.append("and model.firstName like '"+nameStr+"%' ");
		query.append(" order by model.registrationId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId",userId);
		queryObject.setParameterList("otherUsers",otherUsers);		
		queryObject.setParameter("role", IConstants.FREE_USER);
		return (Long)queryObject.uniqueResult();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object> getUserIdByUserName(String userName)
	{
		return getHibernateTemplate().find("select model.registrationId from Registration model where model.userName = ?",userName);
	}
}
