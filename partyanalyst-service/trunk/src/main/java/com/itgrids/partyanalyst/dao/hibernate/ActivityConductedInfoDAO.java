package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityConductedInfoDAO;
import com.itgrids.partyanalyst.model.ActivityConductedInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityConductedInfoDAO extends GenericDaoHibernate<ActivityConductedInfo, Long> implements IActivityConductedInfoDAO{

	public ActivityConductedInfoDAO() {
		super(ActivityConductedInfo.class);
	}

	public List<Object[]> getSpecialActivitiesDetails(Date fromDate,Date toDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.activityScope.activity.activityId," +
						" model.activityScope.activity.activityName,model.activityScope.orderNo" +
						" from ActivityConductedInfo model" +
						" where model.activityScope.isDeleted = 'N'" +
						" and model.activityScope.activity.isActive = 'Y'");
		if(stateId != null && stateId.longValue()>0L)
			sb.append(" and model.address.state.stateId = :stateId ");
		
		if(fromDate != null && toDate != null)
			sb.append(" and ((date(model.activityScope.startDate) between :fromDate and :toDate) or (date(model.activityScope.endDate) between :fromDate and :toDate))");
		sb.append(" order by model.activityScopeId desc");
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(stateId != null && stateId.longValue()>0L)
			query.setParameter("stateId", stateId);
		return query.list();
	}
	
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds){
		Query query = getSession().createQuery("select model.activityScope.activityScopeId," +
												" 0 " +
												" from ActivityConductedInfo model" +
												" where  " +
												"  model.activityScope.activityScopeId in (:activityScopeIds)" +
												" group by model.activityScope.activityScopeId");
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds){
		Query query = getSession().createQuery("select model.activityScope.activityScopeId," +
												//" sum(model.ivrCount)" +
												"count(distinct model.address.constituency.constituencyId) "+
												" from ActivityConductedInfo model" +
												" where  model.ivrCount is not null " +
												" and model.activityScope.activityScopeId in (:activityScopeIds)" +
												" group by model.activityScope.activityScopeId");
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds){
		Query query = getSession().createQuery("select model.activityScope.activityScopeId," +
				//" sum(model.infoCellCount) " +
				" count(distinct model.address.constituency.constituencyId) "+
				" from ActivityConductedInfo model" +
				" where model.infoCellCount is not null and " +
				"  model.activityScope.activityScopeId in (:activityScopeIds)" +
				" group by model.activityScope.activityScopeId");
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
public List<Object[]> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType,Long stateId,String countType){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("select ");
		
		if(searchType != null && searchType.equalsIgnoreCase("constituency"))
			queryStr.append(" model.address.constituency.constituencyId,model.address.constituency.name," );
		else if(searchType != null && searchType.equalsIgnoreCase("village"))
			queryStr.append("  model.address.panchayat.panchayatId, model.address.panchayat.panchayatName,");
		else if(searchType != null && searchType.equalsIgnoreCase("ward"))
			queryStr.append("  model.address.ward.constituencyId,model.address.constituency.name, ");
		else if( searchType != null && searchType.equalsIgnoreCase("mandal"))
			queryStr.append("  model.userAddress.tehsil.tehsilId, model.userAddress.tehsil.tehsilName ");
		else if( searchType != null && searchType.equalsIgnoreCase("town"))
			queryStr.append("  model.userAddress.localElectionBody.localElectionBodyId, model.userAddress.localElectionBody.name ");
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(" model.address.district.districtId,model.address.district.districtName," );
		else if(searchType != null && searchType.equalsIgnoreCase("state"))
			queryStr.append(" model.address.state.stateId,model.address.state.stateName," );
		queryStr.append(" count(model.activityConductedInfoId) " +
				" from ActivityConductedInfo model where model.isDeleted='N' " );
		
		if(countType != null && countType.equalsIgnoreCase("planned"))
			;//queryStr.append(" and  model.plannedDate is not null " );
		else if(countType != null && countType.equalsIgnoreCase("infocell"))
			queryStr.append("  and model.infoCellCount is not null " );
		else if(countType != null && countType.equalsIgnoreCase("ivr"))
			queryStr.append("  and model.ivrCount is not null " );
		
		
		queryStr.append(" and model.activityScope.activityScopeId =:activityScopeId  ");
		
		if(districtId != null && districtId.longValue() > 0l){
			queryStr.append("  and model.address.district.districtId = :districtId ");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && stateId.longValue() == 2l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && stateId.longValue() == 0l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
			}
		}
		
		if(searchType != null && searchType.equalsIgnoreCase("constituency"))
			queryStr.append(" group by model.address.constituency.constituencyId ");
		else if(searchType != null && searchType.equalsIgnoreCase("village"))
			queryStr.append(" group by model.address.panchayat.panchayatId ");
		else if(searchType != null && searchType.equalsIgnoreCase("ward"))
			queryStr.append(" group by model.address.ward.constituencyId ");
		else if( searchType != null && searchType.equalsIgnoreCase("mandal"))
			queryStr.append(" group by  model.userAddress.tehsil.tehsilId");
		else if( searchType != null && searchType.equalsIgnoreCase("town"))
			queryStr.append(" group by  model.userAddress.localElectionBody.localElectionBodyId ");
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(" group by  model.address.district.districtId " );
		else if(searchType != null && searchType.equalsIgnoreCase("state"))
			queryStr.append(" group by model.address.state.stateId" );
		queryStr.append(" order by model.address.constituency.name  ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(districtId != null && districtId.longValue() > 0l)
		query.setParameter("districtId",districtId );
		query.setParameter("activityScopeId",activityScopeId );
		
		return query.list();
	}
}
