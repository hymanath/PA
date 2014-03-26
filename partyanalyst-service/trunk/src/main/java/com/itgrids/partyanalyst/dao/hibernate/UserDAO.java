package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserDAO extends GenericDaoHibernate<User,Long> implements IUserDAO{

	public UserDAO()
	{
		super(User.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByProperty(RegistrationColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from User where " + propertyName + "=?", value);
	}
	
	public List<User> findByFirstName(Object firstName){

		return findByProperty(RegistrationColumnNames.FIRSTNAME, firstName);
	}
	
	public List<User> findByUserName(Object userName){

		return findByProperty(RegistrationColumnNames.USERNAME, userName);
	}
	
	public List<User> findByMiddleName(Object middleName){

		return findByProperty(RegistrationColumnNames.MIDDLENAME, middleName);
		
	}
	
	public List<User> findByLastName(Object lastName){

		return findByProperty(RegistrationColumnNames.LASTNAME, lastName);
	}
	
	public List<User> findByCountry(Object country){

		return findByProperty(RegistrationColumnNames.COUNTRY, country);
	}
	
	public List<User> findByEmail(Object email){

		return findByProperty(RegistrationColumnNames.EMAIL, email);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findUserName(){
		
		return getHibernateTemplate().find("select userName from User");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findPasswords(){
		return getHibernateTemplate().find("select password from User");
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
	
	public Integer changeUserPassword(String password,Long userId,String status,Date date)
	{
		StringBuilder query = new StringBuilder();
		query.append("update User model set model.password =?, model.isPwdChanged =?, model.updatedDate =? where model.userId =?");
		Query queryObj = getSession().createQuery(query.toString());
		queryObj.setParameter(0, password);
		queryObj.setParameter(1, status);
		queryObj.setParameter(2, date);
		queryObj.setParameter(3, userId);
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
				" model.district.districtName, model.constituency.constituencyId, model.constituency.name,model.userId,model.profileImg from User model " +
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
		query.append("select model.firstName,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId,model,model.constituency.district.districtId," +
				" model.constituency.district.districtName,model.constituency.state.stateId,model.constituency.state.stateName ");
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
		  query.append(" and (model.firstName like '"+nameString+"%' or model.lastName like '"+nameString+"%')");
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
		query.append("select model.firstName,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId, model,model.constituency.district.districtId, ");
		query.append(" model.constituency.district.districtName, model.constituency.state.stateId,model.constituency.state.stateName ");
		query.append(" from User model where ");
		query.append(" model.userId != :userId and ");
		
		if(otherUsers != null && otherUsers.size() > 0)
			query.append(" model.userId not in (:otherUsers) and ");
		
		if(locationIds != null && locationIds.size() > 0)
		{
			if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
				query.append("model.state.stateId in (:locationIds)");
			}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
				query.append("model.district.districtId in (:locationIds)");
			}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
				query.append("model.constituency.constituencyId in (:locationIds)");
			}
			query.append(" and ");
		}
		query.append(" model.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		
		if(nameString != null && nameString.trim().length() >0)
		 query.append(" and (model.firstName like '"+nameString+"%' or model.lastName like '"+nameString+"%')");
		query.append(" order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId",userId);
		
		if(otherUsers != null && otherUsers.size() > 0)
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
		query.append("select count(distinct model.userId)");
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
			query.append(" and (model.firstName like '"+nameStr+"%' or model.lastName like '"+nameStr+"%')");
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
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByUserName(String userName)
	{
		return getHibernateTemplate().find("select model from User model where model.userName= ?",userName);
	}
	@SuppressWarnings("unchecked")
	public List<User> changeUserNameAsEmail(String email)
	{
		return getHibernateTemplate().find("select model from User model where model.email = ? ",email);
	}
	
	@SuppressWarnings("unchecked")
	public Integer saveUserNameTOEmail(Long userId,String email){
		StringBuilder query = new StringBuilder();
		query.append("update User model set model.userName = ?,model.email= ? where model.userId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, email);
		queryObject.setParameter(1, email);	
		queryObject.setParameter(2, userId);	
				
		return queryObject.executeUpdate();	
	}
	
	public List getUserDetails(String userName)
	{
		return getHibernateTemplate().find("select model.email, model.password,model.firstName,model.lastName from User model where model.userName = ?",userName);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> checkAnonymousUserLogin(String userName, String password)
	{
		Object[] params = {userName,password};
		return getHibernateTemplate().find("from User model where model.userName=? and model.password=?" , params);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getPassword(String password)
	{
		return getHibernateTemplate().find("select model.password from User model where model.password = ?",password);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPasswordNotUpdatdUsersList()
	{
		return getHibernateTemplate().find("select model.firstName,model.lastName,DATE(model.registeredDate),model.userName,model.password,model.email " +
				" from User model where model.isPwdChanged = 'false' and model.email is not null and model.email not like '' ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUsersMobile()
	{
		return getHibernateTemplate().find("select distinct model.firstName,model.lastName,model.mobile,model.constituency.name,model.userId from User model where model.mobile is not null and model.mobile !=''");
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAllMobilenosAsUnique()
	{
		return getHibernateTemplate().find("select distinct model.mobile from User model where model.mobile is not null and model.mobile !=''");
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByUserRegistrationId(Long registrationId) {
		return getHibernateTemplate().find(" from User model where model.userId = ?", registrationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllRegisteredUsers() {
		return getHibernateTemplate().find(" select model.userId,model.firstName,model.lastName from User model ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubusersByParentUserId(Long parentUserId){
		return getHibernateTemplate().find("select model.parentUser.userId , model.userId From User model where model.parentUser.userId=?",parentUserId);
	}
	
	@SuppressWarnings("unchecked")
	public List getAnanymousUserLocationDetailsByIds(List<Long> userIds)
	{
		StringBuilder query = new StringBuilder();
		query.append(" select model.state.stateId,model.state.stateName,model.district.districtId,model.district.districtName,model.constituency.constituencyId,model.constituency.name");
		query.append(" from User model where ");
		query.append(" model.userId in (:userIds)");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
		
	}	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserEmailByUserId(Long userId){
		return getHibernateTemplate().find("select model.userId , model.firstName , model.lastName , model.email from User model where model.userId = ?",userId);
	}
	
	
	public List<Object[]> findByUserId(Long userId)
	{
		Query queryObject = getSession().createQuery("select model.stateId,model.constituencyId from User model where model.userId=? ");
		queryObject.setParameter(0, userId);
		return queryObject.list(); 
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserBasicDetailsForProfile(List<Long> userId)
	{
		Query queryObject = getSession().createQuery("select model.userId,model.firstName,model.lastName,model.profileImg from User model where model.userId in (:userIds)");
		queryObject.setParameterList("userIds", userId);
		return queryObject.list(); 
	}
	
	public List<Long> getTotalUsersCount(){
		return getHibernateTemplate().find("select count(*) from User model");
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserNameAndPwdByUserId(Long userId)
	{
		return getHibernateTemplate().find("select model.userName, model.password from User model where model.userId = ? ", userId);
	}
	
	public List<String> getEncryptedKeyByUserName(String userName)
	{
		Query query = getSession().createQuery("select model.hashKeyTxt from User model where model.userName = ?");
		
		query.setParameter(0, userName);
		
		return query.list();
		
	}
	
	public List<User> getModelByUserName(String userName){
		Query query = getSession().createQuery("select model from User model where model.userName = ?");
		
		query.setParameter(0, userName);
		
		return query.list();
	}
	
	public List<User> checkUsernameAndEncryptedPasswordForUser(String userName, String encryptedPassword)
	{
		Query query = getSession().createQuery("select model from User model where model.userName = ? and model.passwdHashTxt = ?");
		
		query.setParameter(0, userName);
		query.setParameter(1, encryptedPassword);
		
		return query.list();
		
	}
	
	
	public List<Object[]> updateAllUsersPasswords() 
	{
	
		Query query = getSession().createQuery("select model.userName,model.passwdHashTxt,model.hashKeyTxt,model.userId from User model " +
				" where model.passwdHashTxt is not null and model.hashKeyTxt is not null and model.userName is not null order by model.userId ");		
		
		return query.list();
	}
	
	
	public Long getCount(){
		String constiId = "221";
		String maxboothcount = "select max(counted) from (select count(*) as counted from booth where constituency_id = "+constiId+" and publication_date_id = 8 group by panchayat_id) sub";
		String panchayats = "select distinct panchayat_id from booth where constituency_id = "+constiId+" and publication_date_id = 8 ";
		
		List<Long> panchIds= new ArrayList<Long>();
		StringBuilder table = new StringBuilder();
		table.append("<table>");
		for(Long pancId:panchIds){
			
		}
		table.append("</table>");
		Query query = getSession().createQuery("select count(*) from User");
		return (Long)query.uniqueResult();
	}
	
	public List<BigInteger> getPanc(String s){
		Query query = getSession().createSQLQuery(s);
		return query.list();
	}
	
	public BigInteger count(String s){
		Query query = getSession().createSQLQuery(s);
		return (BigInteger)query.uniqueResult();
	}
	
	public List<Object[]> getData(String s){
		Query query = getSession().createSQLQuery(s);
		return query.list();
	}
	
	public Long Allcount(String s){
		Query query = getSession().createSQLQuery(s);
		return (Long)query.uniqueResult();
	}
	
   	
	public List<Object[]> getAgePanchayatHamletWithType(Long constituencyId,Long publicationId)
	{
		
	/*	
		
		StringBuilder query = new StringBuilder();
		query.append(" select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct  uvd.voter.voterId),ph.panchayat.panchayatName,");
		query.append(" uvd.voter.voterAgeRange.voterAgeRangeId, uvd.voter.gender ");
		query.append( " from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd");
		query.append( "  where bpv.voter.voterId=uvd.voter.voterId and ph.hamlet.hamletId=uvd.hamlet.hamletId  ");
		query.append("and bpv.booth.constituency.constituencyId = "+constituencyId+" and bpv.booth.publicationDate.publicationDateId = "+publicationId+" ");
		query.append("group by uvd.hamlet.hamletId,uvd.voter.voterAgeRange.voterAgeRangeId, uvd.voter.gender");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		return queryObject.list();
		
	*/
		
		StringBuilder query = new StringBuilder();
		query.append(" select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct  uvd.voter.voterId),ph.panchayat.panchayatName,");
		query.append(" uvd.voter.voterAgeRange.voterAgeRangeId");
		query.append( " from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd");
		query.append( "  where bpv.voter.voterId=uvd.voter.voterId and ph.hamlet.hamletId=uvd.hamlet.hamletId  ");
		query.append("and bpv.booth.constituency.constituencyId = "+constituencyId+" and bpv.booth.publicationDate.publicationDateId = "+publicationId+" ");
		query.append("group by uvd.hamlet.hamletId,uvd.voter.voterAgeRange.voterAgeRangeId");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		return queryObject.list();	
		
		
	}
	
/*	public List<Object[]> getAgePanchayatHamletWithType1(Long constituencyId,Long publicationId)
	{
		
  Query queryObject = getSession().createQuery("  select ph.hamlet.hamletId,ph.hamlet.hamletName,count(distinct uvd.voter.voterId),ph.panchayat.panchayatName,uvd.voter.gender"+   
" from PanchayatHamlet ph,BoothPublicationVoter bpv,UserVoterDetails uvd"+
 " where ph.hamlet.hamletId=uvd.hamlet.hamletId  and bpv.voter.voterId=uvd.voter.voterId and"+
       " bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId = "+publicationId+" "+
       " and uvd.user.userId=1 and uvd.voter.age>=18 and  uvd.voter.age<=22"+
 "group by  uvd.hamlet.hamletId,uvd.voter.gender ");
		
		return queryObject.list();
		
	}	
*/	
	
	public List<Object[]> getAgePanchayatBoothWiseWithType(Long constituencyId,Long publicationId)
	{
		Query queryObject = getSession().createQuery(" select bpv.booth.boothId,bpv.booth.partNo,count(distinct bpv.voter.voterId),bpv.booth.panchayat.panchayatName,"+
        "bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender from  BoothPublicationVoter bpv "+ 
        "where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"  "+
        "group by bpv.booth.boothId,bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender");
						
	    return queryObject.list();
						
	}
	
	public List<Object[]> getAgePanchayatBoothWiseWithType1(Long constituencyId,Long publicationId)
	{
		Query queryObject = getSession().createQuery("	select bpv.booth.boothId,bpv.booth.partNo,count(distinct bpv.voter.voterId),bpv.booth.panchayat.panchayatName,bpv.voter.gender "+
        "from  BoothPublicationVoter bpv "+ 
        "where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+" "+
        "and bpv.voter.age>=18 and  bpv.voter.age<=22 "+ 
        "group by bpv.booth.boothId,bpv.voter.gender");
								
	    return queryObject.list();	
		
   }

	
	public List<Object[]> getAgePanchayatWiseWithType(Long constituencyId,Long publicationId)	
	{

		Query queryObject = getSession().createQuery("	select bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName,count(distinct bpv.voter.voterId), "+
        "bpv.booth.tehsil.tehsilName,bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender  "+
        "from BoothPublicationVoter bpv "+
        "where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"  "+
        "group by bpv.booth.panchayat.panchayatId,bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender");
	  return queryObject.list();	
		
		
	}
	
	public List<Object[]> getAgePanchayatWiseWithType1(Long constituencyId,Long publicationId)	
	{

		Query queryObject = getSession().createQuery("select bpv.booth.panchayat.panchayatId,bpv.booth.panchayat.panchayatName,count(distinct bpv.voter.voterId), bpv.booth.tehsil.tehsilName, "+
         "bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender "+
         "from BoothPublicationVoter bpv "+
         "where bpv.booth.constituency.constituencyId="+constituencyId+" and bpv.booth.publicationDate.publicationDateId="+publicationId+"  "+ 
         " and   bpv.voter.age>=18 and  bpv.voter.age<=22  " +
         "group by bpv.booth.panchayat.panchayatId,bpv.voter.gender");
	   return queryObject.list();	
		
		
	}
	
	    public List<Long>  gettingLocalElectionBodyForAConstituency(Long constituencyId)
	    {

	    	Query queryObject = getSession().createQuery(" select  model.localElectionBody.localElectionBodyId from AssemblyLocalElectionBody model where " +
	    			                                     " model.constituency.constituencyId=? and model.year=? ");
	    	queryObject.setParameter(0, constituencyId);
			queryObject.setParameter(1, IConstants.PRESENT_ELECTION_YEAR);	
			
			return queryObject.list();	
	    	
	    }
	
	public List<Object[]> getAgeBoothWiseForMunWithType(Long constituencyId,Long publicationId,List<Long> localElecBodyList)	
	{

		Query queryObject = getSession().createQuery("select bpv.booth.boothId,bpv.booth.partNo,count(distinct bpv.voter.voterId),'Municipality',  bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender "+
		
           "from BoothPublicationVoter bpv "+
           "where  bpv.booth.constituency.constituencyId=:constituencyId and bpv.booth.publicationDate.publicationDateId=:publicationId "+
           "and bpv.booth.localBody.localElectionBodyId in (:localElecBodyList) "+
           "group by  bpv.booth.boothId,bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender ");
		    
		queryObject.setParameter("constituencyId", constituencyId);
		queryObject.setParameter("publicationId", publicationId);
		queryObject.setParameterList("localElecBodyList", localElecBodyList);
		
	   return queryObject.list();	
	}
	public List<Object[]> getAgeBoothWiseForMunWithType1(Long constituencyId,Long publicationId,List<Long> localElecBodyList)
	{
		
		Query queryObject = getSession().createQuery("select bpv.booth.boothId,bpv.booth.partNo,count(distinct bpv.voter.voterId),'Municipality', bpv.voter.gender "+
          "from BoothPublicationVoter bpv "+
          "where bpv.booth.constituency.constituencyId=:constituencyId and bpv.booth.publicationDate.publicationDateId=:publicationId "+
          "and bpv.booth.localBody.localElectionBodyId in (:localElecBodyList) and  bpv.voter.age>=18 and  bpv.voter.age<=22 "+
          "group by  bpv.booth.boothId,bpv.voter.gender   ");
		
		queryObject.setParameter("constituencyId", constituencyId);
		queryObject.setParameter("publicationId", publicationId);
		queryObject.setParameterList("localElecBodyList", localElecBodyList);
		
	   return queryObject.list();	
		
		
		
		
	}

	
	public List<Object[]> getAgePanchayatWiseForMunicpalWithType(Long constituencyId,Long publicationId,List<Long> localElecBodyList)
	{
		
		Query queryObject = getSession().createQuery(" select  1,' Municipality',count(distinct bpv.voter.voterId),'Municipality',bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender "+
		"from BoothPublicationVoter bpv "+
        "where  bpv.booth.constituency.constituencyId=:constituencyId and bpv.booth.publicationDate.publicationDateId=:publicationId  "+
        "and bpv.booth.localBody.localElectionBodyId in (:localElecBodyList)  "+
        "group by bpv.voter.voterAgeRange.voterAgeRangeId,bpv.voter.gender ");
		
		queryObject.setParameter("constituencyId", constituencyId);
		queryObject.setParameter("publicationId", publicationId);
		queryObject.setParameterList("localElecBodyList", localElecBodyList);
		
	    return queryObject.list();		
		
	}
	public List<Object[]> getAgePanchayatWiseForMunicpalWithType1(Long constituencyId,Long publicationId,List<Long> localElecBodyList)
	{
		Query queryObject = getSession().createQuery("select  1,' Municipality',count(distinct bpv.voter.voterId),'Municipality',bpv.voter.gender "+
          "from BoothPublicationVoter bpv "+
          "where  bpv.booth.constituency.constituencyId=:constituencyId and bpv.booth.publicationDate.publicationDateId=:publicationId "+
          "and bpv.booth.localBody.localElectionBodyId in (:localElecBodyList) and bpv.voter.age>=18 and  bpv.voter.age<=22 "+
           "group by bpv.voter.gender	");
		
		
		queryObject.setParameter("constituencyId", constituencyId);
		queryObject.setParameter("publicationId", publicationId);
		queryObject.setParameterList("localElecBodyList", localElecBodyList);
		
	    return queryObject.list();		
		
	}
	public List<Object[]> getData1(String query)
	{
		
		Query queryObject = getSession().createQuery(query);
		//queryObject.setFirstResult(1);
		//queryObject.setMaxResults(4);
		return queryObject.list();
		
		
	}
	

	
	
	
	
	
	
	
}
