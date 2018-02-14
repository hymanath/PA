package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IActivityLocationParticipantInfoDAO;
import com.itgrids.partyanalyst.model.ActivityLocationParticipantInfo;

public class ActivityLocationParticipantInfoDAO extends GenericDaoHibernate<ActivityLocationParticipantInfo, Long> implements
		IActivityLocationParticipantInfoDAO {

	
	public ActivityLocationParticipantInfoDAO() {
		super(ActivityLocationParticipantInfo.class);
	}
	
	public List<Object[]> getCoveredPeopleOfActivity(Long activityId,Long locationScope, Set<Long> locationValues){
		
		StringBuilder sb = new StringBuilder();
		if(locationScope != null && locationScope.longValue()>0l){
			if(locationScope == 2l){
				sb.append("select sum(model.totalMembers),SUM(model.totalCovered),sum(model.todayCovered),sum(model.totalRegistration)," +
						" sum(model.todayRegistration),sum(model.totalLoanAapplied),sum(model.todayLoanApplied) ,1" );
			}else {
				sb.append("select model.totalMembers,model.totalCovered,model.todayCovered,model.totalRegistration," +
						" model.todayRegistration,model.totalLoanAapplied,model.todayLoanApplied,model.locationValue ");
			}
		}
		sb.append(" From ActivityLocationParticipantInfo model where model.activityScope.activityId =:activityId ");
		if(locationScope != null && locationScope.longValue()>0l){
			if(locationScope ==2l ){
				sb.append(" and model.locationLevel=3 ");
			}else{
				sb.append(" and model.locationLevel=:locationScope");
			}
			if(locationScope == 3l && locationValues!=null && locationValues.size()>0){
				sb.append(" and model.locationValue in(:locationValues)");
			}else if(locationScope ==4l && locationValues!=null && locationValues.size()>0){
				sb.append(" and model.locationValue in(:locationValues)");
			}else if(locationScope ==10l && locationValues!=null && locationValues.size()>0){
				sb.append(" and model.locationValue in(:locationValues)");
			}
			
		}
		if(locationScope != null && locationScope.longValue()>0l){
			if(locationScope != 2l){
					sb.append(" group by model.locationValue");
			}
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("activityId", activityId);
		if(locationValues !=null && locationValues.size()>0 && locationScope !=2l){
			query.setParameterList("locationValues", locationValues);
		}
		if(locationScope != null && locationScope.longValue()>0l && locationScope !=2l){
			query.setParameter("locationScope", locationScope);
		}
		return query.list();
		
	}

	@Override
	public void callProcedureofactivitySp() {
		Query query = getSession().createSQLQuery("call activity_sp()");
		query.executeUpdate();
	}
}
