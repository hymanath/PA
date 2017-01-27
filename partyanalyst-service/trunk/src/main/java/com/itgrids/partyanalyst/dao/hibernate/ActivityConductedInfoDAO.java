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
	
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds,String type){
		
		StringBuilder queryStr = new StringBuilder();
		Long stateId = 1L;
		queryStr.append("select model.activityScope.activityScopeId, ");
		if(type != null)
			queryStr.append(" count(distinct model.activityConductedInfoId) " );
		else{
			queryStr.append(" 0 " );			
		}
		queryStr.append(" from ActivityConductedInfo model where model.isDeleted='N' " );
		if(type != null){
			if(type.equalsIgnoreCase("yes"))
				queryStr.append(" and ((model.infoCellCount is not null and model.ivrStatus ='Y') or " +
						" (model.infoCellCount is null and model.ivrStatus ='Y') or (model.infoCellCount is not null and model.ivrStatus is null ) )  ");  
			else if(type.equalsIgnoreCase("no"))
				queryStr.append(" and ((model.infoCellCount is null and model.ivrStatus ='N') or  (model.infoCellCount is null and model.ivrStatus is null ) )   ");
			else if(type.equalsIgnoreCase("maybe"))
				queryStr.append(" and ( (model.infoCellCount is not null and model.ivrStatus ='N' )  or " +
						"  (model.infoCellCount is null and model.ivrStatus ='Y' ) ) ");
		}
		
		if(stateId != null && stateId.longValue() == 1L)
			queryStr.append(" and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
		else if(stateId != null && ( stateId.longValue() == 2L || stateId.longValue() == 36L))
			queryStr.append(" and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		else{
			queryStr.append(" and  model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+", "+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		
		queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIds) and  model.address.state.stateId = 1 ");
		queryStr.append(" group by model.activityScope.activityScopeId ");
		
		
		
		/*
		Query query = getSession().createQuery("select model.activityScope.activityScopeId," +
												" 0 " +
												" from ActivityConductedInfo model" +
												" where  " +
												"  model.activityScope.activityScopeId in (:activityScopeIds) and " +
												" model.address.state.stateId = 1 " +
												" group by model.activityScope.activityScopeId");
												*/
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds){
		Query query = getSession().createQuery("select model.activityScope.activityScopeId," +
												//" sum(model.ivrCount)" +
												" count(distinct model.activityConductedInfoId) "+
												" from ActivityConductedInfo model" +
												" where  model.ivrCount is not null " +
												" and model.activityScope.activityScopeId in (:activityScopeIds) and " +
												" model.address.state.stateId = 1 " +
												" group by model.activityScope.activityScopeId");
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
	/*public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate){
		Query query = getSession().createQuery("select model.activityScope.activityScopeId," +
												" count(model.activityConductedInfoId)," +
												"  model.address.district.districtId," +
												"  model.address.district.districtName," +
												"  model.activityScope.activity.activityName, " +
												" model.activityScope.activityLevel.level " +
												" from ActivityConductedInfo model " +
												" where model.activityScope.isDeleted='N' and model.activityScope.activity.isActive='Y' and " +
												" model.activityScope.activityId in (:activityIdsLst) and  model.address.state.stateId = 1 " +
												" and (model.activityScope.startDate >=:startDate and model.activityScope.endDate <=:endDate) " +
												" group by model.activityScope.activityScopeId,model.address.district.districtId " +
												" order by model.address.district.districtId  ");
		query.setParameterList("activityIdsLst", activityIdsLst);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();
	}*/
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds){
		Query query = getSession().createQuery("select model.activityScope.activityScopeId," +
				//" sum(model.infoCellCount) " +
				" count(distinct model.activityConductedInfoId),sum(model.infoCellCount) "+
				" from ActivityConductedInfo model" +
				" where model.infoCellCount is not null and " +
				"  model.activityScope.activityScopeId in (:activityScopeIds) and " +
				" model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") " +
				" group by model.activityScope.activityScopeId");
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
public List<Object[]> getDistrictWiseActivityCounts(Long locationId,Long activityScopeId, String searchType,Long stateId,String countType){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("select ");
		
		if(searchType != null && searchType.equalsIgnoreCase("constituency"))
			queryStr.append(" model.address.constituency.constituencyId,model.address.constituency.name," );
		else if(searchType != null && searchType.equalsIgnoreCase("village") ||  searchType.equalsIgnoreCase("onlyvillage"))
			queryStr.append("  model.address.panchayat.panchayatId, model.address.panchayat.panchayatName,");
		else if(searchType != null && searchType.equalsIgnoreCase("ward"))
			queryStr.append("  model.address.ward.constituencyId,model.address.constituency.name, ");
		else if( searchType != null && searchType.equalsIgnoreCase("mandal"))
			queryStr.append("  model.address.tehsil.tehsilId, model.address.tehsil.tehsilName, ");
		else if( searchType != null && searchType.equalsIgnoreCase("town"))
			queryStr.append("  model.address.constituency.localElectionBody.localElectionBodyId, model.address.constituency.localElectionBody.name, ");
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(" model.address.district.districtId,model.address.district.districtName," );
		else if(searchType != null && searchType.equalsIgnoreCase("state"))
			queryStr.append(" model.address.state.stateId,model.address.state.stateName," );
		queryStr.append(" count(model.activityConductedInfoId) ,sum(model.infoCellCount) " +
				" from ActivityConductedInfo model where model.isDeleted='N' " );
		
		if(countType != null && countType.equalsIgnoreCase("planned"))
			;//queryStr.append(" and  model.plannedDate is not null " );
		else if(countType != null && countType.equalsIgnoreCase("infocell"))
			queryStr.append("  and model.infoCellCount is not null " );
		else if(countType != null && countType.equalsIgnoreCase("ivr"))
			queryStr.append("  and model.ivrCount is not null " );
		
		
		queryStr.append(" and model.activityScope.activityScopeId =:activityScopeId  ");
		
		
			if(locationId != null && locationId.longValue() > 0l){
				if(searchType != null && searchType.equalsIgnoreCase("constituency"))
					queryStr.append("  and model.address.district.districtId = :locationId ");
				else if(searchType != null && searchType.equalsIgnoreCase("village"))
					queryStr.append("  and model.address.tehsil.tehsilId = :locationId ");
				else if(searchType != null && searchType.equalsIgnoreCase("ward"))
					queryStr.append("  and model.address.constituency.localElectionBody.localElectionBodyId = :locationId ");
				else if( searchType != null && searchType.equalsIgnoreCase("mandal") || searchType != null && searchType.equalsIgnoreCase("town"))
					queryStr.append("  and model.address.constituency.constituencyId = :locationId ");
				else if(searchType != null && searchType.equalsIgnoreCase("onlyvillage"))
					queryStr.append("  and model.address.panchayat.panchayatId = :locationId ");
			
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
			queryStr.append(" group by model.address.constituency.constituencyId order by model.address.constituency.name ");
		else if(searchType != null && searchType.equalsIgnoreCase("village"))
			queryStr.append(" group by model.address.panchayat.panchayatId  order by model.address.panchayat.panchayatName ");
		else if(searchType != null && searchType.equalsIgnoreCase("ward"))
			queryStr.append(" group by model.address.ward.constituencyId order by model.address.ward.name ");
		else if( searchType != null && searchType.equalsIgnoreCase("mandal"))
			queryStr.append(" group by  model.address.tehsil.tehsilId order by model.address.tehsil.tehsilName ");
		else if( searchType != null && searchType.equalsIgnoreCase("town"))
			queryStr.append(" group by  model.address.constituency.localElectionBody.localElectionBodyId order by model.address.constituency.localElectionBody.name ");
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(" group by  model.address.district.districtId order by model.address.district.districtName " );
		else if(searchType != null && searchType.equalsIgnoreCase("state"))
			queryStr.append(" group by model.address.state.stateId order by model.address.state.stateName " );
		//queryStr.append(" order by model.address.constituency.name  ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if(locationId != null && locationId.longValue() > 0l)
		query.setParameter("locationId",locationId );
		query.setParameter("activityScopeId",activityScopeId );
		
		return query.list();
	}


public List<Object[]> getActivityConductedCntBasedOnUserAccesslevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long activityId,List<Long> activityLevelIds,Date fromDate,Date toDate){
	
    StringBuilder queryStr = new StringBuilder();
   
	  queryStr.append(" select");
	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
       queryStr.append(" model.address.state.stateId,");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
           queryStr.append(" model.address.district.districtId,");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
        queryStr.append(" model.address.parliamentConstituency.constituencyId, ");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
        queryStr.append(" model.address.constituency.constituencyId, ");  
	 }

  queryStr.append(" count(distinct model.activityConductedInfoId) " +
		            " from ActivityConductedInfo model where (model.infoCellCount is not null or model.ivrCount is not null ) and model.isDeleted='N' ");
 
 if(fromDate != null && toDate != null){
	 queryStr.append(" and ((date(model.activityScope.startDate) between :fromDate and :toDate) or (date(model.activityScope.endDate) between :fromDate and :toDate))");	
	}
		 
 if(activityId != null && activityId.longValue() > 0){
	   queryStr.append(" and model.activityScope.activity.activityId in(:activityId)");
 }
 if(activityLevelIds != null && activityLevelIds.size() > 0){
	   queryStr.append(" and model.activityScope.activityLevel.activityLevelId in(:activityLevelIds)");   
 }
 
 if(stateId != null && stateId.longValue() == 1l){
		queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
 }else if(stateId != null && stateId.longValue() == 2l){
		queryStr.append("  and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
 }

if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
   queryStr.append(" and model.address.state.stateId in (:userAccessLevelValues)");  
}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
       queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues)");  
}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
    queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
    queryStr.append(" and model.address.constituency.constituencyId in (:userAccessLevelValues)");  
}

if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
   queryStr.append(" group by model.address.state.stateId ");  
}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
       queryStr.append(" group by model.address.district.districtId ");  
}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
    queryStr.append(" group by model.address.parliamentConstituency.constituencyId ");  
}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
    queryStr.append(" group by model.address.constituency.constituencyId ");  
}
   
 Query query = getSession().createQuery(queryStr.toString());
 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
 }
 if(activityId != null && activityId.longValue() > 0){
	  query.setParameter("activityId", activityId);
 }
 if(activityLevelIds != null && activityLevelIds.size() > 0){
	  query.setParameterList("activityLevelIds", activityLevelIds);   
 }
 if(fromDate != null && toDate != null){
	query.setParameter("fromDate", fromDate);
	query.setParameter("toDate", toDate);
 }
return query.list();
}
public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId){
	 StringBuilder queryStr = new StringBuilder();
     queryStr.append("select model.activityScope.activityScopeId," +
											" count(model.activityConductedInfoId),"); 
     if(scopeId !=null && scopeId.longValue() == 3l){
   	  queryStr.append("  model.address.district.districtId," +
											"  model.address.district.districtName," );
     }else if(scopeId !=null && scopeId.longValue() == 2l){
   	  queryStr.append("  model.address.state.stateId," +
					"  model.address.state.stateName," );
     }
     queryStr.append("  model.activityScope.activity.activityName, " +
											" model.activityScope.activityLevel.level,model.activityScope.activityLevel.activityLevelId " +
											" from ActivityConductedInfo model " +
											" where model.activityScope.isDeleted='N' and model.activityScope.activity.isActive='Y' and " +
											" model.activityScope.activityId in (:activityIdsLst) and  model.address.state.stateId = 1 " +
											" and (model.activityScope.startDate >=:startDate and model.activityScope.endDate <=:endDate) "); 
     if(scopeId != null && scopeId.longValue() == 3l){
   	   queryStr.append(" group by model.activityScope.activityScopeId,model.address.district.districtId "); 
   	   queryStr.append(" order by model.address.district.districtId ");
     }
     if(scopeId != null && scopeId.longValue() == 2l){
   	  queryStr.append(" group by model.activityScope.activityScopeId,model.address.state.stateId "); 
   	   queryStr.append(" order by model.address.state.stateId ");
     }
     Query query = getSession().createQuery(queryStr.toString());  
     query.setParameterList("activityIdsLst", activityIdsLst);
     query.setParameter("startDate", startDate);
     query.setParameter("endDate", endDate);
     /*if(scopeId != null && scopeId.longValue() > 0l){
   	  query.setParameter("scopeId", scopeId); 
     }*/
	return query.list();
}


}
