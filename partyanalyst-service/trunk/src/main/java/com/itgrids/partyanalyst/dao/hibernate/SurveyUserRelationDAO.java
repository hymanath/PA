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
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName,model1.booth.partNo from " +
				"  SurveyUserRelation model ,SurveyUserBoothAssign model1 where model.surveyUser.surveyUserId =  model1.surveyUser.surveyUserId and model.activeStatus = 'Y'" +
				"  and  model.surveyLeader.surveyUserId = :leaderId and model.constituency.constituencyId =:constituencyId");
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
	
	public List<Object[]> getUserForAssignedUser(Long leaderId)
	{
		Query query = getSession().createQuery("select distinct model.surveyUser.surveyUserId, model.surveyUser.userName,model.constituency.constituencyId,model.constituency.name from SurveyUserRelation model where " +
				"  model.surveyLeader.surveyUserId = :leaderId  and model.activeStatus = 'Y'");
		query.setParameter("leaderId", leaderId);
		return query.list();
	}
	
	public int updateUserLeaderRelations(Long userTypeId,List<Long> surveyUserIds,Long leaderId,Long constituencyId)
	{
		Query query = getSession().createQuery("update SurveyUserRelation model set model.activeStatus = 'N' where model.surveyUser.surveyUserId in (:surveyUserIds) and model.constituency.constituencyId = :constituencyId " +
				" and  model.surveyLeader.surveyUserId = :leaderId and model.surveyUserType.surveyUsertypeId = :userTypeId");
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("leaderId", leaderId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("surveyUserIds", surveyUserIds);
		int count = query.executeUpdate();
		return count;
	}
}
