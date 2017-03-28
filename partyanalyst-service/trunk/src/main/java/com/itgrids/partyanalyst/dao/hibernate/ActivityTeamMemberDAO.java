package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityTeamMemberDAO;
import com.itgrids.partyanalyst.model.ActivityTeamMember;

public class ActivityTeamMemberDAO  extends GenericDaoHibernate<ActivityTeamMember, Long> implements IActivityTeamMemberDAO{

	public ActivityTeamMemberDAO() {
		super(ActivityTeamMember.class);
		
	}
	
	public List<Object[]> getTeamLeadersByActivityScope(List<Long> activityScopeIds){
		
		/*Query query = getSession().createQuery(" select model.activityTeamMemberId,model.tdpCadre.firstname from ActivityTeamMember model " +
							" where model.activityScope.activityScopeId in (:activityScopeIds) and model.teamLeadId is null and model.isActive = 'true' ");
		
		query.setParameterList("activityScopeIds", activityScopeIds);
		*/
		Query query = getSession().createQuery(" select model.activityTeamMemberId,model.tdpCadre.firstname from ActivityTeamMember model " +
				" where model.teamLeadId is null and model.isActive = 'true' ");
		return query.list();
	}
	
	public List<Object[]> getTeamMembersByTeamLeaderAndActivityScope(List<Long> teamLeaderIds,List<Long> activityScopeIds){
		
		Query query = getSession().createQuery(" select model.activityTeamMemberId,model.tdpCadre.firstname from ActivityTeamMember model " +
							" where model.teamLeadId in (:teamLeaderIds) and model.activityScope.activityScopeId in (:activityScopeIds) " +
							" and model.teamLeadId is not null and model.isActive = 'true' ");
		
		query.setParameterList("teamLeaderIds", teamLeaderIds);
		query.setParameterList("activityScopeIds", activityScopeIds);
		
		return query.list();
	}
	
	public List<Object[]> getTeamMembersByTeamLeaderId(Long leaderId){
		
		Query query = getSession().createQuery(" select model.activityTeamMemberId,model.tdpCadre.firstname from ActivityTeamMember model " +
							" where model.teamLeadId = :leaderId and model.teamLeadId is not null and model.isActive = 'true' ");
		
		query.setParameter("leaderId", leaderId);
		
		return query.list();
	}

}
