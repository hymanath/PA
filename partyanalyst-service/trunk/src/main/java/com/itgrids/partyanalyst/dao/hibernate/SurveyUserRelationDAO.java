package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;
import com.itgrids.partyanalyst.model.SurveyUserRelation;

public class SurveyUserRelationDAO extends GenericDaoHibernate<SurveyUserRelation, Long> implements ISurveyUserRelationDAO{

	public SurveyUserRelationDAO()
	{
		super(SurveyUserRelation.class);
	}

	public List<Object[]> getLeadersByConstituency()
	{
		Query query = getSession().createQuery("select distinct model.surveyLeader.surveyUserId,model.surveyLeader.userName,model.constituency.constituencyId,model.constituency.name from SurveyUserRelation model where model.activeStatus = 'Y'");
		return query.list();
	}
	
	public List<Object[]> getUsersByConstituencyAndLeader(Long leaderId,Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName, model1.booth.partNo, model1.booth.boothId from " +
				"  SurveyUserRelation model ,SurveyUserBoothAssign model1 where model.surveyUser.surveyUserId =  model1.surveyUser.surveyUserId and model.activeStatus = 'N'" +
				"  and  model.surveyLeader.surveyUserId = :leaderId and model1.booth.constituency.constituencyId =:constituencyId and model1.isDelete = 'Y' and  model.surveyUser.activeStatus = 'Y'" +
				"order by model1.booth.partNo asc");
		query.setParameter("leaderId", leaderId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getUsersList(Long usertypeId)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName from SurveyUserRelation model where " +
				" model.surveyUser.surveyUserType.surveyUsertypeId = :usertypeId  and model.activeStatus = 'Y'");
		query.setParameter("usertypeId", usertypeId);
		return query.list();
	}
	
	public List<Object[]> getUserForAssignedUser(Long leaderId,Long userType)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName from SurveyUserRelation model where " +
				"  model.surveyLeader.surveyUserId = :leaderId  and model.activeStatus = 'Y' and model.surveyUser.surveyUserType.surveyUsertypeId = :userType");
		query.setParameter("leaderId", leaderId);
		query.setParameter("userType", userType);
		return query.list();
	}
	
	public List<Object[]> getUserForAssignedUsers(Long leaderId)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName from SurveyUserRelation model where " +
				"  model.surveyLeader.surveyUserId = :leaderId  and model.activeStatus = 'Y' ");
		query.setParameter("leaderId", leaderId);
		return query.list();
	}
	
	public int updateUserLeaderRelations(Long userTypeId,List<Long> surveyUserIds,Long leaderId)
	{
		Query query = getSession().createQuery("update SurveyUserRelation model set model.activeStatus = 'N' where model.surveyUser.surveyUserId in (:surveyUserIds)  " +
				" and model.surveyUserType.surveyUsertypeId = :userTypeId");
		query.setParameter("userTypeId", userTypeId);
		//query.setParameter("leaderId", leaderId);
		//query.setParameter("constituencyId", constituencyId);
		query.setParameterList("surveyUserIds", surveyUserIds);
		int count = query.executeUpdate();
		return count;
	}
	
	
	public List<Object[]> getConstituencyForSurveyUser(List<Long> surveyUserIds)
	{
		Query query = getSession().createQuery("select model.constituency.constituencyId,model.surveyUser.surveyUserId,model.surveyUserRelationId from SurveyUserRelation model where model.surveyUser.surveyUserId in (:surveyUserIds) and model.activeStatus = 'Y' ");
		query.setParameterList("surveyUserIds", surveyUserIds);
		return query.list();
		
					
	}
	
	
	public List<Object[]> getUsersForAssignedUser(Long leaderId,Long userType)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId,model.surveyUser.userName from SurveyUserRelation model where " +
				"model.surveyLeader.surveyUserId = :leaderId and model.activeStatus = 'Y' and model.surveyUserType.surveyUsertypeId = :userType");
		query.setParameter("leaderId", leaderId);
		query.setParameter("userType", userType);
		return query.list();
	}
	
	public int updateActiveStatusByIDs(List<Long> Ids)
	{
		Query query = getSession().createQuery("update SurveyUserRelation model set model.activeStatus = 'N' where model.surveyUserRelationId in (:Ids)");
		
		query.setParameterList("Ids", Ids);
		int count = query.executeUpdate();
		return count;
		
	}
	
	
	public List<Object[]> getExistedSurveyUsersByUserType(Long userTypeId)
	{
		Query query = getSession().createQuery("select distinct SUR.surveyUser.surveyUserId ,SUR.surveyUser.userName  from  " +
				"SurveyUserRelation SUR where SUR.surveyUser.surveyUserType.surveyUsertypeId = :surveyUserTypeId and SUR.activeStatus = 'Y'");
		
		query.setParameter("surveyUserTypeId", userTypeId);
		
		return query.list();
		
	}
	
	public List<Object[]> getSurveyUsersByLeaderWise(Long leaderId)
	{
		Query query = getSession().createQuery("distinct SUR.surveyUser.surveyUserId ,SUR.surveyUser.userName,SUR.surveyUser.password  from  " +
				"SurveyUserRelation SUR where SUR.surveyLeader.surveyUserId = :leaderId and SUR.activeStatus = 'Y' ");
		
		return query.list();
	}
	
	public List<Object[]> getAllUserForAssignedUsers(List<Long> leaderId)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName from SurveyUserRelation model where " +
				"  model.surveyLeader.surveyUserId in(:leaderId)  and model.activeStatus = 'Y' ");
		query.setParameterList("leaderId", leaderId);
		return query.list();
	}
	
	public List<Object[]> getAssignedUsersOfAConstituency(Long constituencyId)
	{
		Query query = getSession()
				.createQuery(
						"select distinct SUR.surveyUser.surveyUserId,SUR.surveyUser.userName from SurveyUserRelation SUR where SUR.surveyLeader.surveyUserId " +
						"in(select SUC.surveyUser.surveyUserId from SurveyUserConstituency SUC " +
						"where SUC.constituency.constituencyId  = :constituencyId) and SUR.surveyUser.activeStatus = 'Y'  ");
		
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Long> getAssignedUsersForLeader(Long leaderId)
	{
		Query query = getSession().createQuery("select SUR.surveyUser.surveyUserId from SurveyUserRelation SUR where SUR.surveyLeader.surveyUserId = :surveyUserId and " +
				"SUR.surveyUser.activeStatus = 'Y'");
		
		query.setParameter("surveyUserId", leaderId);
		
		return query.list();
		
	}
	
	public List<Object[]> getusersBysurveyUserIds(List<Long> surveyUserIds,Long surveyUsertypeId)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName from SurveyUserRelation model   where " +
				" model.surveyLeader.surveyUserId in (:surveyUserIds) and model.surveyUser.surveyUserType.surveyUsertypeId = :surveyUsertypeId  and model.surveyUser.activeStatus = 'Y' and model.activeStatus = 'Y'");
		query.setParameterList("surveyUserIds", surveyUserIds);
		query.setParameter("surveyUsertypeId", surveyUsertypeId);
		
		return query.list();
	}
	
	public List<Object[]> getLeadersBysurveyUserIds(List<Long> surveyUserIds,Long surveyUsertypeId)
	{
		Query query = getSession().createQuery("select distinct model.surveyLeader.surveyUserId, model.surveyLeader.userName from SurveyUserRelation model   where " +
				" model.surveyLeader.surveyUserId in (:surveyUserIds) and model.surveyLeader.surveyUserType.surveyUsertypeId = :surveyUsertypeId  and model.activeStatus = 'Y'");
		query.setParameterList("surveyUserIds", surveyUserIds);
		query.setParameter("surveyUsertypeId", surveyUsertypeId);
		
		return query.list();
	}
	

	public List<Object[]> getUserLeaderIds(List<Long> surveyUserIds)
	{
	Query query = getSession().createQuery("select model.surveyUser.surveyUserId,model.surveyLeader.surveyUserId,model.surveyLeader.userName,model.surveyLeader.mobileNo " +
	" from SurveyUserRelation model where model.surveyUser.surveyUserId in (:surveyUserIds) and model.activeStatus = 'Y' ");
	query.setParameterList("surveyUserIds", surveyUserIds);
	return query.list();
	}
}
