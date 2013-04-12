package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.model.UserConnectedto;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserConnectedtoDAO extends GenericDaoHibernate<UserConnectedto,Long> implements
		IUserConnectedtoDAO {

	public UserConnectedtoDAO() {
		super(UserConnectedto.class);
	}

	public Integer deleteRejectedRequest(List<Long> senderId,List<Long> recipeintId){
		StringBuilder query = new StringBuilder();	
		query.append(" delete from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (:senderId) and  model.userTarget.userId in (:recipeintId) ) ");
		query.append(" or (model.userSource.userId in (:recipeintId) and  model.userTarget.userId in (:senderId)) ");
		Query queryObject = getSession().createQuery(query.toString());		
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		return queryObject.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserConnectedto> checkForRelationBetweenUsers(List<Long> senderId,List<Long> recipeintId){
		StringBuilder query = new StringBuilder();				
		query.append(" from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (:senderId) and  model.userTarget.userId in (:recipeintId)) or");
		query.append(" (model.userSource.userId in (:recipeintId) and model.userTarget.userId in (:senderId)) ");	
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("recipeintId", recipeintId);
		queryObject.setParameterList("senderId", senderId);
		return queryObject.list();
	}
	

	
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllConnectedPeopleForUser(List<Long> senderId){
		StringBuilder query = new StringBuilder();	
		query.append(" select model.userSource.userId,model.userTarget.userId from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (:senderId) or  model.userTarget.userId in (:senderId)) ");	
		query.append(" and model.userSource.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role ) ");
		query.append(" and model.userTarget.userId in (select model3.user.userId from UserRoles model3 where model3.role.roleType = :role ) ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameter("role", IConstants.FREE_USER);
		return queryObject.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllConnectedPeopleForFreeUser(Long senderId){
		return getHibernateTemplate().find(" select model.userTarget.userId,model.userTarget.firstName,model.userTarget.lastName,model.userTarget.email from UserConnectedto model where "+
					"model.userSource.userId = ? ",senderId);	
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllConnectedPeoplesForFreeUser(Long recepientId)
	{
		return getHibernateTemplate().find("select model.userSource.userId,model.userSource.firstName,model.userSource.lastName,model.userSource.email from UserConnectedto model where "+
	"model.userTarget.userId = ?",recepientId);
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAllPeopleThatMayBeKnownForUser(Long userId){
		StringBuilder query = new StringBuilder();				
		query.append(" select model.userSource.userId,model.userTarget.userId from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (select model2.userSource.userId from UserConnectedto model2 where ");
		query.append(" (model2.userSource.userId =? or  model2.userTarget.userId = ?)) or");
		query.append("  model.userTarget.userId in (select model3.userSource.userId from UserConnectedto model3 where");
		query.append("(model3.userSource.userId =? or  model3.userTarget.userId = ?))) ");	
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0, userId);
		queryObject.setLong(1, userId);
		queryObject.setLong(2, userId);
		queryObject.setLong(3, userId);
		return queryObject.list();
	}
	
	public String getCountOfAllConnectedPeopleForUser(List<Long> senderId){
		StringBuilder query = new StringBuilder();	
		query.append(" select count(distinct model.userSource.userId) from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (:senderId) or  model.userTarget.userId in (:senderId)) ");
		query.append(" and model.userSource.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		query.append(" and model.userTarget.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role )");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameter("role", IConstants.FREE_USER);
		return queryObject.list().get(0).toString();
	}
	
	public Long getConnectedMembersCountForAFreeUser(Long userId)
	{
		Query query = getSession().createQuery("select count(model.userConnectedtoId) from UserConnectedto model where " +
				" (model.userSource.userId = :userId or model.userTarget.userId = :userId ) and  model.userSource.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role ) " +
		" and model.userTarget.userId in (select model3.user.userId from UserRoles model3 where model3.role.roleType = :role ) ");
		query.setParameter("userId",userId);
		query.setParameter("role",IConstants.FREE_USER);
		return (Long)query.uniqueResult();
		
	}
	
	public Long getConnectedUsersCountForAUserInAFilterView(Long userId,List<Long> locationIds,String locationType,String nameStr)
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(distinct model.userSource.userId)");
		query.append(" from UserConnectedto model where (model.userSource.userId = :userId or model.userTarget.userId = :userId) and ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("(model.userSource.state.stateId in (:locationIds) and model.userTarget.state.stateId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("(model.userSource.district.districtId in (:locationIds) and model.userTarget.district.districtId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("(model.userSource.constituency.constituencyId in (:locationIds) and model.userTarget.constituency.constituencyId in (:locationIds)) ");
		}
		
		if(nameStr != null && !nameStr.trim().equalsIgnoreCase(""))
		{
			query.append("and ((model.userSource.firstName like '"+nameStr+"%' or model.userSource.lastName like '"+nameStr+"%') or " +
					"(model.userTarget.firstName like '"+nameStr+"%' or model.userTarget.lastName like '"+nameStr+"%'))");
		}
		query.append(" and  model.userSource.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role ) " +
	           " and model.userTarget.userId in (select model3.user.userId from UserRoles model3 where model3.role.roleType = :role ) ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("role", IConstants.FREE_USER);
		
		return (Long)queryObject.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getConnectedUsersInSelectedLocations(Long userId, List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString) {
		StringBuilder query = new StringBuilder();
		query.append("select distinct model.firstName,model.lastName,model.userId,model.constituency.name,model.constituency.constituencyId, model, model.constituency.district.districtId,model.constituency.district.districtName,model.constituency.state.stateId,model.constituency.state.stateName  ");
		query.append("from User model, UserConnectedto model1 where (model1.userSource.userId = :userId or model1.userTarget.userId = :userId) and ");
		query.append("(model.userId = model1.userTarget.userId or model.userId = model1.userSource.userId) and model.userId != :userId  ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append(" and (model1.userSource.state.stateId in (:locationIds) and model1.userTarget.state.stateId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append(" and (model1.userSource.district.districtId in (:locationIds) and model1.userTarget.district.districtId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append(" and (model1.userSource.constituency.constituencyId in (:locationIds) or model1.userTarget.constituency.constituencyId in (:locationIds)) and model.constituency.constituencyId in (:locationIds) ");
		}
		if(nameString != null && !nameString.trim().equalsIgnoreCase(""))
		{
			query.append("and (model.firstName like '"+nameString+"%'  or model.lastName like '"+nameString+"%' )");
			/*query.append("and ((model1.userSource.firstName like '"+nameString+"%'  or model.lastName like '"+nameString+"%' or model1.userSource.lastName like '"+nameString+"%') or " +
			"(model1.userTarget.firstName like '"+nameString+"%' or model1.userTarget.lastName like '"+nameString+"%'))");
			 */
		}
		
		query.append(" and model.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role ) order by model.userId desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("role", IConstants.FREE_USER);
		
		if(startIndex!=null)
			queryObject.setFirstResult(startIndex.intValue());
		if(retrivalCount != null)
			queryObject.setMaxResults(retrivalCount.intValue());	
		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConnectedUserIdsInSelectedLocations(Long userId, List<Long> locationIds,String locationType) 
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.userId from User model, UserConnectedto model1 where (model1.userSource.userId = :userId or model1.userTarget.userId = :userId) and ");
		query.append("(model.userId = model1.userTarget.userId or model.userId = model1.userSource.userId) and model.userId != :userId  ");
		
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append(" and (model1.userSource.state.stateId in (:locationIds) and model1.userTarget.state.stateId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append(" and (model1.userSource.district.districtId in (:locationIds) and model1.userTarget.district.districtId in (:locationIds)) ");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append(" and (model1.userSource.constituency.constituencyId in (:locationIds) and model1.userTarget.constituency.constituencyId in (:locationIds)) ");
		}
		query.append(" and model.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role ) order by model.userId desc");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameter("userId", userId);
		queryObject.setParameter("role", IConstants.FREE_USER);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllConnectedPeoplesForPublicProfile(Long recepientId)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select model.userSource.userId,model.userSource.firstName,model.userSource.lastName,model.userSource.profileImg from UserConnectedto model where");
		stringBuilder.append(" model.userTarget.userId = ?");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		query.setParameter(0, recepientId);
		return query.list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllConnectedPeopleForPublicProfile(Long userId){
		StringBuilder query = new StringBuilder();	
		query.append(" select model.userSource.userId, model.userTarget.userId from UserConnectedto model where ");
		query.append(" (model.userSource.userId =:senderId or  model.userTarget.userId =:senderId) ");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter("senderId", userId);
		
		return queryObject.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getUserConnectStatus(Long profileId,Long userId){
		StringBuilder query = new StringBuilder();				
		query.append(" select model.userConnectedtoId from UserConnectedto model where ");
		query.append(" (model.userSource.userId=? and  model.userTarget.userId =?) or");
		query.append(" (model.userSource.userId =? and model.userTarget.userId =?) ");	
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, profileId);
		queryObject.setParameter(1, userId);
		queryObject.setParameter(2, userId);
		queryObject.setParameter(3, profileId);
		return queryObject.list();
	}

	public String getCountOfAllConnectedPeopleForUsers(List<Long> senderId,List<Long> locationIds, String constituencyType) {
		StringBuilder query = new StringBuilder();	
		Query queryObject = null;
		query.append(" select count(model.userSource.userId) from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (:senderId) or  model.userTarget.userId in (:senderId)) ");
		query.append(" and model.userSource.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		query.append(" and model.userTarget.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role )");
		query.append(" and (model.userSource.userId in (select model2.userId from User model2 where  model2.constituency.constituencyId in (:locationIds))");
		query.append(" and model.userTarget.userId in (select model2.userId from User model2 where  model2.constituency.constituencyId in (:locationIds)))");
	
		queryObject = getSession().createQuery(query.toString());		
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameterList("locationIds", locationIds);		
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameter("role", IConstants.FREE_USER);
		return queryObject.list().get(0).toString();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCountOfAllConnectedPeopleForUserByDistrict(List<Long> senderId, List<Long> locationIds, String locationType,String constituencyType) {
		StringBuilder query = new StringBuilder();	
		Query queryObject = null;
		query.append(" select distinct model.userSource.userId,model.userTarget.userId from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (:senderId) or  model.userTarget.userId in (:senderId)) ");
		query.append(" and model.userSource.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		query.append(" and model.userTarget.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role )");
		if(locationType.equalsIgnoreCase("CONSTITUENCY")){
			if(constituencyType.equalsIgnoreCase("SAME")){
				query.append(" and model.userSource.constituency.constituencyId = model.userTarget.constituency.constituencyId");
				queryObject = getSession().createQuery(query.toString());
			}
			if(constituencyType.equalsIgnoreCase("NOTSAME")){
				query.append(" and (model.userSource.userId in (select model2.userId from User model2 where  model2.constituency.constituencyId in(:locationIds))");
				query.append(" or model.userTarget.userId in (select model2.userId from User model2 where  model2.constituency.constituencyId in(:locationIds)))");
				queryObject = getSession().createQuery(query.toString());
				queryObject.setParameterList("locationIds", locationIds);
				queryObject.setParameterList("locationIds", locationIds);
			}
		}
		if(locationType.equalsIgnoreCase("DISTRICT")){
			if(constituencyType.equalsIgnoreCase("SAME")){
			query.append(" and model.userSource.district.districtId = model.userTarget.district.districtId ");
			queryObject = getSession().createQuery(query.toString());
			}
			if(constituencyType.equalsIgnoreCase("NOTSAME")){
				query.append(" and model.userSource.userId in (select model2.userId from User model2 where  model2.district.districtId = :locationIds and (model.userSource.userId = :senderId or model.userTarget.userId = :senderId)) ");
				query.append(" or model.userTarget.userId in (select model2.userId from User model2 where   model2.district.districtId = :locationIds and (model.userSource.userId = :senderId or model.userTarget.userId = :senderId))");
				queryObject = getSession().createQuery(query.toString());
				queryObject.setParameterList("locationIds", locationIds);
				queryObject.setParameterList("locationIds", locationIds);
			}
		}
		
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("senderId", senderId);		
		queryObject.setParameter("role", IConstants.FREE_USER);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCountOfAllConnectedPeopleForUserInSameLocation(List<Long> senderId,List<Long> locationIds,String constituencyType){
		StringBuilder query = new StringBuilder();	
		Query queryObject = null;
		query.append(" select distinct model.userSource.userId,model.userTarget.userId from UserConnectedto model where ");
		query.append(" (model.userSource.userId in (:senderId) or  model.userTarget.userId in (:senderId)) ");
		query.append(" and model.userSource.userId in (select model1.user.userId from UserRoles model1 where model1.role.roleType = :role )");
		query.append(" and model.userTarget.userId in (select model2.user.userId from UserRoles model2 where model2.role.roleType = :role )");
		query.append(" and (model.userSource.userId in (select model2.userId from User model2 where  model2.constituency.constituencyId in (:locationIds))");
		query.append(" and model.userTarget.userId in (select model2.userId from User model2 where  model2.constituency.constituencyId in (:locationIds)))");
		queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		queryObject.setParameterList("locationIds", locationIds);		
		queryObject.setParameterList("senderId", senderId);
		queryObject.setParameterList("senderId", senderId);		
		queryObject.setParameter("role", IConstants.FREE_USER);
		return queryObject.list();
	}
}
