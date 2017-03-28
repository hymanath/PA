package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityTeamLocationDAO;
import com.itgrids.partyanalyst.model.ActivityTeamLocation;

public class ActivityTeamLocationDAO extends GenericDaoHibernate<ActivityTeamLocation, Long> implements IActivityTeamLocationDAO{

	public ActivityTeamLocationDAO() {
		super(ActivityTeamLocation.class);
		
	}
	
	public List<Object[]> getActivityLocationsForTeamLeders(List<Long> teamLeaderIds){
		
		Query query = getSession().createQuery(" select model.activityTeamLocationId,model.activityTeamMemberId,model.locationScopeId,count(model.locationScopeValue) " +
							" from ActivityTeamLocation model where model.activityTeamMember.activityTeamMemberId in (:teamLeaderIds) " +
							" and model.isActive = 'true' group by model.locationScopeId,model.activityTeamMemberId ");
		
		query.setParameterList("teamLeaderIds", teamLeaderIds);
		
		return query.list();
	}

	public List<Long> getAssignedLocationsForTeamMembers(Long teamMemberId){
		
		Query query = getSession().createQuery(" select model.locationScopeValue from ActivityTeamLocation model where model.activityTeamMemberId = :teamMemberId ) " +
							" and model.isActive = 'true' ");
		
		query.setParameter("teamMemberId", teamMemberId);
		
		return query.list();
	}
	
	public List<Object[]> getTeamLeaderDetailsAndAssignedLocationsByLeaderId(Long leaderId){
		
		//Query query = getSession().createQuery(" select model.activityTeamLocationId,model.activityTeamMember.activityTeamMemberId,model.activityTeamMember.tdpCadre.firstname, " +
							//" model.locationScopeValue from ActivityTeamLocation model where model.activityTeamMemberId = :leaderId and model.isActive = 'true' ");
		Query query = getSession().createQuery("select ATM.tdpCadre.firstname,ATL.locationScopeValue from ActivityTeamLocation ATL,ActivityTeamMember ATM " +
				"where ATM.activityTeamMemberId = :leaderId and ATL.activityTeamMemberId = ATM.activityTeamMemberId and ATL.isActive='true' ");
		
		query.setParameter("leaderId", leaderId);
		
		return query.list();
	}
	
	public List<Long> getAssignedConstituenciesForTeamMembers(Long memberId){
		
		Query query = getSession().createQuery(" select model.locationScopeValue from ActivityTeamLocation model where model.locationScopeId = 4 " +
							" and model.activityTeamMemberId = :memberId and model.isActive = 'true' ");
		
		query.setParameter("memberId", memberId);
		
		return query.list();
	}
	
	public List<Long> getAssignedLocationsForTeamMembersByTeamLeaderId(Long teamLeaderId){
		
		Query query = getSession().createQuery(" select model.locationScopeValue from ActivityTeamLocation model " +
							" where model.activityTeamMemberId in (select model1.activityTeamMemberId from ActivityTeamMember model1 where model1.teamLeadId = :teamLeaderId " +
							" and model1.isActive = 'true' ) and model.isActive = 'true' and model.locationScopeId = 4");
		
		query.setParameter("teamLeaderId", teamLeaderId);
		
		return query.list();
	}
}
