package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
	
	public List<Object[]> getTotalCountsForScopeIds(List<Long> activityScopeIds,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues){
		
		  
		StringBuilder queryStr = new StringBuilder();
		Long stateId = 1L;
		queryStr.append("select model.activityScope.activityScopeId, ");
			queryStr.append(" count(distinct model.activityConductedInfoId) " );
			
		queryStr.append(" from ActivityConductedInfo model where model.isDeleted='N' " );
		
		if(userAccessLevelId != null && userAccessLevelId.longValue() >0l){
		 	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
			 }
		
		}else if(stateId != null && stateId.longValue() == 1L)
			queryStr.append(" and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
		else if(stateId != null && ( stateId.longValue() == 2L || stateId.longValue() == 36L))
			queryStr.append(" and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		else{
			queryStr.append(" and  model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+", "+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		
		queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIds)  ");
		queryStr.append(" group by model.activityScope.activityScopeId ");
	
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityScopeIds", activityScopeIds);
		
		if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		return query.list();
	}
	
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues){
		
		  
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
				queryStr.append(" and ( (model.infoCellCount is not null and model.ivrStatus ='N' ) OR (model.infoCellCount is null and model.ivrStatus ='Y' ) ) ");
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue() >0l){
		 	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
			 }
		
		}else if(stateId != null && stateId.longValue() == 1L)
			queryStr.append(" and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
		else if(stateId != null && ( stateId.longValue() == 2L || stateId.longValue() == 36L))
			queryStr.append(" and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		else{
			queryStr.append(" and  model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+", "+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		
		queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIds)  ");
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
		
		if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		return query.list();
	}
	
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds,Long stateId,Long userAccessLevelId,Set<Long> userAccessLevelValues){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.activityScope.activityScopeId," +
				//" sum(model.ivrCount)" +
				" count(distinct model.activityConductedInfoId) "+
				" from ActivityConductedInfo model" +
				" where  model.ivrCount is not null ");
		if(userAccessLevelId != null && userAccessLevelId.longValue() >0l){
			 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
			 }
		}else if(stateId != null && stateId.longValue() == 1L)
			queryStr.append(" and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
		else if(stateId != null && ( stateId.longValue() == 2L || stateId.longValue() == 36L))
			queryStr.append(" and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		else{
			queryStr.append(" and  model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+", "+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIds)  " +
				" group by model.activityScope.activityScopeId");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityScopeIds", activityScopeIds);
		if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
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
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds,Long stateId1,Long userAccessLevelId,Set<Long> userAccessLevelValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.activityScope.activityScopeId," +
				//" sum(model.infoCellCount) " +
				" count(distinct model.activityConductedInfoId),sum(model.infoCellCount) "+
				" from ActivityConductedInfo model" +
				" where model.infoCellCount is not null and " +
				"  model.activityScope.activityScopeId in (:activityScopeIds)"); 
		/*queryStr.append(" and " +
				" model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") "); */	
		if(userAccessLevelId != null && userAccessLevelId.longValue() >0l){
			 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
			 }
		}else if(stateId1 != null && stateId1.longValue() == 1l){
			queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId1 != null && (stateId1.longValue() == 2l || stateId1.longValue() == 36l)){
			queryStr.append("  and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId1 != null && stateId1.longValue() == 0l){
			queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		queryStr.append(" group by model.activityScope.activityScopeId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityScopeIds", activityScopeIds);
		if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		return query.list();
	}
	
public List<Object[]> getDistrictWiseActivityCounts(Long locationId,Long activityScopeId, String searchType,Long stateId,String countType,String type,Long userAccessLevelId ,Set<Long> userAccessLevelValues){
		
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
		
		//if(type != null)
			queryStr.append(" count(model.activityConductedInfoId) ,sum(model.infoCellCount),'' " +
					" from ActivityConductedInfo model where model.isDeleted='N' " );
		//else{
		//	queryStr.append(" 0 ,0,'' " +
		//			" from ActivityConductedInfo model where model.isDeleted='N' " );			
		//}
		
		if(type != null){
			if(type.equalsIgnoreCase("yes"))
				queryStr.append(" and ((model.infoCellCount is not null and model.ivrStatus ='Y') or " +
						" (model.infoCellCount is null and model.ivrStatus ='Y') or (model.infoCellCount is not null and model.ivrStatus is null ) )  ");  
			else if(type.equalsIgnoreCase("no"))
				queryStr.append(" and ((model.infoCellCount is null and model.ivrStatus ='N') or  (model.infoCellCount is null and model.ivrStatus is null ) )   ");
			else if(type.equalsIgnoreCase("maybe"))
				queryStr.append(" and ( (model.infoCellCount is not null and model.ivrStatus ='N' ) ) ");
		}
		else{
			if(countType != null && countType.equalsIgnoreCase("planned"))
				;//queryStr.append(" and  model.plannedDate is not null " );
			else if(countType != null && countType.equalsIgnoreCase("infocell"))
				queryStr.append("  and model.infoCellCount is not null " );
			else if(countType != null && countType.equalsIgnoreCase("ivr"))
				queryStr.append("  and model.ivrStatus ='Y' " );
			
			if(countType != null && countType.equalsIgnoreCase("Conducted")){
				queryStr.append("  and model.ivrStatus ='Y' or model.infoCellCount is not null  ");
			}else if(countType != null && countType.equalsIgnoreCase("NotConducted")){
				queryStr.append("  and model.ivrStatus ='N'  or model.infoCellCount is null   ");
			}
			
				queryStr.append("     " );
		}
		
		queryStr.append(" and model.activityScope.activityScopeId =:activityScopeId");
		
		
			/*if(locationId != null && locationId.longValue() > 0l){
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
			
		}*/
		if(locationId.longValue() == 0l){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
				if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
				     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
				     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
				        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
				        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
				        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
				        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
				 }
		}}else{
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
		}
			/*else{
			if(stateId != null && stateId.longValue() == 1l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && stateId.longValue() == 2l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && stateId.longValue() == 0l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
			}
		}*/
		
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
		if(locationId.longValue() == 0l){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
		if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			}
		}
		return query.list();
	}


public List<Object[]> getActivityTotalCntBasedOnUserAccesslevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long activityId,List<Long> activityLevelIds,Date fromDate,Date toDate){
	
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
		            " from ActivityConductedInfo model where  model.isDeleted='N' ");
 
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

public List<Object[]> activitiesDistrictWiseCount(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues){
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
											" model.activityScope.activityId in (:activityIdsLst)  " +
											" and (model.activityScope.startDate >=:startDate and model.activityScope.endDate <=:endDate) "); 
    
    if(userAccessLevelId != null && userAccessLevelId.longValue() >0l){
	     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
		 }
	 }
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
    if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
    /*if(scopeId != null && scopeId.longValue() > 0l){
  	  query.setParameter("scopeId", scopeId); 
    }*/
	return query.list();
}

public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues){
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
											" model.activityScope.activityId in (:activityIdsLst)  " +
											" and (model.activityScope.startDate >=:startDate and model.activityScope.endDate <=:endDate) "); 
     
     if(type != null && !type.isEmpty()){
	    	if(type.equalsIgnoreCase("yes")){
	    		queryStr.append(" and ( (model.infoCellCount is not null and  model.ivrStatus='Y' ) OR" +
	    				"  ( model.infoCellCount is not null and model.ivrStatus is null ) OR " +
	    				"  ( model.infoCellCount is null and model.ivrStatus='Y' ) ) ");
	    	}
	    	else if(type.equalsIgnoreCase("no")){
	    		queryStr.append(" and ( (model.infoCellCount is null and model.ivrStatus='N' )  OR " +
	    				" ( model.infoCellCount is null and model.ivrStatus is null )  ) ");
	    	}
	    	else if(type.equalsIgnoreCase("maybe")){
	    		queryStr.append(" and ( (model.infoCellCount is not null and model.ivrStatus='N' ) OR (model.infoCellCount is null and model.ivrStatus ='Y' ) ) ");
	    	}
	      } 
	      else{
	    	  queryStr.append(" and ( model.infoCellCount is not null or model.ivrStatus='Y') ");
	      }
     if(userAccessLevelId != null && userAccessLevelId.longValue() >0l){
	     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
		 }
	 }
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
     if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
     /*if(scopeId != null && scopeId.longValue() > 0l){
   	  query.setParameter("scopeId", scopeId); 
     }*/
	return query.list();
}
public List<Object[]> getActivityConductedCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId){
	
	StringBuilder queryStr = new StringBuilder();
	
	     queryStr.append("select '' ,");
	     queryStr.append(" count(distinct model.activityConductedInfoId),  ");
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append(" model.address.state.stateId ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" model.address.district.districtId ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" model.address.parliamentConstituency.constituencyId ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" model.address.constituency.constituencyId ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" model.address.tehsil.tehsilId ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" model.address.constituency.localElectionBody.localElectionBodyId "); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" model.address.panchayat.panchayatId "); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" model.address.ward.constituencyId  "); 
		 }
	  
	 queryStr.append(" FROM ActivityConductedInfo model  where ");
		
	  if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append(" model.address.state.stateId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" model.address.district.districtId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues)  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append(" model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" model.address.ward.constituencyId in (:userAccessLevelValues) "); 
			 }
	  }else if(stateId != null && stateId.longValue() > 0){
				if(stateId.longValue()==1l){
					//queryStr.append(" and UA.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
					queryStr.append(" and  model.address.district.districtId > 10 and model.address.stateId = 1 ");
				}else if(stateId.longValue()==36l){
					//queryStr.append(" and UA.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
                    queryStr.append(" and model.address.district.districtId < 11 ");
				}
		 }
	  	 /*if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append(" group by model.address.state.stateId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by  model.address.district.districtId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by model.address.parliamentConstituency.constituencyId ,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" group by model.address.constituency.constituencyId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" group by model.address.tehsil.tehsilId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" group by model.address.constituency.localElectionBody.localElectionBodyId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" group by model.address.panchayat.panchayatId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" group by  model.address.ward.constituencyId,"); 
		 }*/
        Session session = getSession();
        Query query = session.createQuery(queryStr.toString());
    	
          if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
        	  query.setParameterList("userAccessLevelValues", userAccessLevelValues);
  		  }
    	
    	
	return query.list();
}



public List<Object[]> getLocationWiseTotalActivitiesCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesList){
	
	StringBuilder queryStr = new StringBuilder();
	
	     queryStr.append("select");
	     
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  UA.state_id as stateId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" UA.district_id as districtId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" UA.parliament_constituency_id as parliamentConstituencyId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  UA.constituency_id as assemblyId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" UA.tehsil_id as tehsilId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" UA.local_election_body as localElectionBoydId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" UA.panchayat_id as panchayatId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" UA.ward as wardId,"); 
		 }
	  
	  queryStr.append(" COUNT(DISTINCT EI.activity_conducted_info_id) as count ");
	
	  queryStr.append(" FROM activity_conducted_info EI," +
	  		         " activity_scope E," +
	  		         " user_address UA "+
	  		         " WHERE EI.activity_scope_id = E.activity_scope_id AND "+
		             "  " +
					 " EI.address_id = UA.user_address_id AND "+
					 " E.is_deleted = 'N'   ");
		if(activitiesList != null && activitiesList.size() >0){
			queryStr.append(" AND E.activity_scope_id in (:activitiesList)" );	
		}
	   if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
					queryStr.append(" and UA.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")  ");
				}
		 }
	     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  group by UA.state_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by UA.district_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by UA.parliament_constituency_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  group by UA.constituency_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" group by UA.tehsil_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" group by UA.local_election_body"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" group by UA.panchayat_id"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" group by UA.ward"); 
		 }
        Session session = getSession();
	      SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    sqlQuery.addScalar("stateId",Hibernate.LONG); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("districtId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("parliamentConstituencyId",Hibernate.LONG); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("assemblyId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			 sqlQuery.addScalar("tehsilId",Hibernate.LONG);  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			 sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			 sqlQuery.addScalar("panchayatId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			 sqlQuery.addScalar("wardId",Hibernate.LONG);
		 }
		    sqlQuery.addScalar("count",Hibernate.LONG);
		if(activitiesList != null && activitiesList.size() >0){
			sqlQuery.setParameterList("activitiesList", activitiesList);	
		}
	return sqlQuery.list();
}


public List<Object[]> getLocationWiseConductedActivitiesCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesList){
	
	StringBuilder queryStr = new StringBuilder();
	
	     queryStr.append("select");
	     
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  UA.state_id as stateId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" UA.district_id as districtId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" UA.parliament_constituency_id as parliamentConstituencyId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  UA.constituency_id as assemblyId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" UA.tehsil_id as tehsilId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" UA.local_election_body as localElectionBoydId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" UA.panchayat_id as panchayatId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" UA.ward as wardId,"); 
		 }
	  
	  queryStr.append(" COUNT(DISTINCT EI.activity_conducted_info_id) as count ");
	
	  queryStr.append(" FROM activity_conducted_info EI," +
	  		         " activity_scope E," +
	  		         " user_address UA "+
	  		         " WHERE EI.activity_scope_id = E.activity_scope_id AND ( EI.info_cell_count is not null or EI.ivr_status ='Y') and "+
		             "  " +
					 " EI.address_id = UA.user_address_id AND "+
					 " E.is_deleted = 'N'   ");
		if(activitiesList != null && activitiesList.size() >0){
			queryStr.append(" AND E.activity_scope_id in(:activitiesList)" );	
		}
	   if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
					queryStr.append(" and UA.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")  ");
				}
		 }
	     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  group by UA.state_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by UA.district_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by UA.parliament_constituency_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  group by UA.constituency_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" group by UA.tehsil_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" group by UA.local_election_body"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" group by UA.panchayat_id"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" group by UA.ward"); 
		 }
        Session session = getSession();
	      SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    sqlQuery.addScalar("stateId",Hibernate.LONG); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("districtId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("parliamentConstituencyId",Hibernate.LONG); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("assemblyId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			 sqlQuery.addScalar("tehsilId",Hibernate.LONG);  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			 sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			 sqlQuery.addScalar("panchayatId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			 sqlQuery.addScalar("wardId",Hibernate.LONG);
		 }
		    sqlQuery.addScalar("count",Hibernate.LONG);
		if(activitiesList != null && activitiesList.size() >0){
			sqlQuery.setParameterList("activitiesList", activitiesList);	
		}
	return sqlQuery.list();
}


public List<Object[]> getLocationWiseEventInviteedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesIdsList,String locationType){
	
    StringBuilder queryStr = new StringBuilder();

     queryStr.append("select");

     if(locationType != null && locationType.equalsIgnoreCase("District")){
      queryStr.append(" d.district_id as districtId,"); //1
      queryStr.append(" d.district_name as districtName,"); //2
     }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
      queryStr.append(" c.constituency_id as assemblyId,"); //3
	     queryStr.append(" c.name as assemblyName,"); //4
	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
      queryStr.append(" t.tehsil_id as tehsilId,");
      queryStr.append(" t.tehsil_name as tehsilName,");
     }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
     queryStr.append(" p.panchayat_id as panchayatId,");
     queryStr.append(" p.panchayat_name as panchayatName,");
     }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
        queryStr.append(" c.constituency_id as wardId,");
        queryStr.append(" c.name as wardName,");
       }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
        queryStr.append(" leb.local_election_body_id as localElectionBoydId,");
        queryStr.append(" leb.name as localElectionBoydName,");
       }
    
    queryStr.append(" COUNT(DISTINCT EI.activity_conducted_info_id) as count ");

    queryStr.append(" FROM activity_conducted_info EI," +
	                   " activity_scope E," +
	                   " user_address UA ");

	       if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(",district d"); 
	        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append(",constituency c"); 
	  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         queryStr.append(",tehsil t");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append(",panchayat p");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
	        queryStr.append(",constituency c");
	        }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
	        queryStr.append(",local_election_body leb");
	        }
	        queryStr.append(" WHERE  EI.activity_scope_id = E.activity_scope_id AND "+
			" EI.address_id = UA.user_address_id  " );
		
		if(activitiesIdsList != null && activitiesIdsList.size() >0){
			queryStr.append(" AND EI.activity_scope_id in(:activitiesIdsList)" );	
		}
		
		if(locationType != null && locationType.equalsIgnoreCase("District")){
		  queryStr.append(" and UA.district_id=d.district_id");
		}else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		  queryStr.append("  and UA.constituency_id=c.constituency_id ");
		 }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		 	queryStr.append(" and UA.tehsil_id=t.tehsil_id ");  
		}else if(locationType != null && locationType.equalsIgnoreCase("Village")){
			   queryStr.append(" and UA.panchayat_id=p.panchayat_id "); 
		}else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
			   queryStr.append(" and UA.ward=c.constituency_id"); 
		  }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
			   queryStr.append(" and UA.local_election_body=leb.local_election_body_id "); 
		  }
		
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		  queryStr.append(" and UA.state_id in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and UA.district_id in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		   queryStr.append(" and UA.parliament_constituency_id in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		   queryStr.append(" and UA.constituency_id in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and UA.tehsil_id in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" and UA.local_election_body in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" and UA.panchayat_id in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		      queryStr.append(" and UA.ward in (:userAccessLevelValues)"); 
		}
		if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
					queryStr.append(" and UA.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				}
		}
		if(locationType != null && locationType.equalsIgnoreCase("District")){
		  queryStr.append(" group by d.district_id "); 
		 }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		  queryStr.append(" group by c.constituency_id "); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		  queryStr.append(" group by t.tehsil_id ");
		 }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		 queryStr.append(" group by p.panchayat_id ");
		 }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		    queryStr.append(" group by c.constituency_id ");
		   }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		    queryStr.append(" group by leb.local_election_body_id");
		   }
		
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		
		if(locationType != null && locationType.equalsIgnoreCase("District")){
			 sqlQuery.addScalar("districtId",Hibernate.LONG); 
			 sqlQuery.addScalar("districtName",Hibernate.STRING); 
		 }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		  sqlQuery.addScalar("assemblyId",Hibernate.LONG); 
		  sqlQuery.addScalar("assemblyName",Hibernate.STRING); 
		 }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		  sqlQuery.addScalar("tehsilId",Hibernate.LONG); 
			 sqlQuery.addScalar("tehsilName",Hibernate.STRING); 
		 }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		  sqlQuery.addScalar("panchayatId",Hibernate.LONG); 
			 sqlQuery.addScalar("panchayatName",Hibernate.STRING); 
		 }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
			 sqlQuery.addScalar("wardId",Hibernate.LONG); 
			 sqlQuery.addScalar("wardName",Hibernate.STRING); 
		  } else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
			  sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG); 
			  sqlQuery.addScalar("localElectionBoydName",Hibernate.STRING); 
		}
		 sqlQuery.addScalar("count",Hibernate.LONG);
			
		if(activitiesIdsList != null && activitiesIdsList.size() >0){
			sqlQuery.setParameterList("activitiesIdsList", activitiesIdsList);	
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			sqlQuery.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return sqlQuery.list();
}


public List<Object[]> getLocationWiseEventInviteeAttendedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesIdsList,String locationType){
	
    StringBuilder queryStr = new StringBuilder();

     queryStr.append("select");

       if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(" d.district_id as districtId,"); //1
	         queryStr.append(" d.district_name as districtName,"); //2
	        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append(" c.constituency_id as assemblyId,"); //3
	  	     queryStr.append(" c.name as assemblyName,"); //4
	  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         queryStr.append(" t.tehsil_id as tehsilId,");
	         queryStr.append(" t.tehsil_name as tehsilName,");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append(" p.panchayat_id as panchayatId,");
	        queryStr.append(" p.panchayat_name as panchayatName,");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
	        queryStr.append(" c.constituency_id as wardId,");
	        queryStr.append(" c.name as wardName,");
	       }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
	        queryStr.append(" leb.local_election_body_id as localElectionBoydId,");
	        queryStr.append(" leb.name as localElectionBoydName,");
	       }
	        
    
	       queryStr.append(" COUNT(DISTINCT EA.activity_conducted_info_id) as count ");
	
	       queryStr.append(" FROM activity_conducted_info EA," +
			               " activity_scope E, user_address UA  ");

       if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(",district d"); 
	        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append(",constituency c"); 
	  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         queryStr.append(",tehsil t");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append(",panchayat p");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
	        queryStr.append(",constituency c");
	        }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
	        queryStr.append(",local_election_body leb");
	       }
		     
		     queryStr.append(" WHERE  EA.activity_scope_id = E.activity_scope_id AND "+
          " EA.address_id = UA.user_address_id and  ( EA.info_cell_count is not null or EA.ivr_status='Y' ) ");
		
		if(activitiesIdsList != null && activitiesIdsList.size() >0){
			queryStr.append(" AND E.activity_scope_id in(:activitiesIdsList)" );	
		}
		if(locationType != null && locationType.equalsIgnoreCase("District")){
		     queryStr.append(" and UA.district_id=d.district_id");
		}else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		     queryStr.append("  and UA.constituency_id=c.constituency_id ");
			  }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		  	queryStr.append(" and UA.tehsil_id=t.tehsil_id ");  
		}else if(locationType != null && locationType.equalsIgnoreCase("Village")){
			   queryStr.append(" and UA.panchayat_id=p.panchayat_id "); 
		}else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
			   queryStr.append(" and UA.ward=c.constituency_id"); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
			   queryStr.append(" and UA.local_election_body=leb.local_election_body_id "); 
		   }
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			    queryStr.append(" and UA.state_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			        queryStr.append(" and UA.district_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and UA.parliament_constituency_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append(" and UA.constituency_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and UA.tehsil_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and UA.local_election_body in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and UA.panchayat_id in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and UA.ward in (:userAccessLevelValues)"); 
			 }
		if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
					queryStr.append(" and UA.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				}
		}
		if(locationType != null && locationType.equalsIgnoreCase("District")){
		    queryStr.append(" group by d.district_id "); 
		 }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		    queryStr.append(" group by c.constituency_id "); 
			}else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		    queryStr.append(" group by t.tehsil_id ");
		 }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		    queryStr.append(" group by p.panchayat_id ");
		 }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		    queryStr.append(" group by c.constituency_id ");
		 }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		    queryStr.append(" group by leb.local_election_body_id");
		 }
		
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		
		if(locationType != null && locationType.equalsIgnoreCase("District")){
			 sqlQuery.addScalar("districtId",Hibernate.LONG); 
			 sqlQuery.addScalar("districtName",Hibernate.STRING); 
		}else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		 sqlQuery.addScalar("assemblyId",Hibernate.LONG); 
		 sqlQuery.addScalar("assemblyName",Hibernate.STRING); 
		}else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		 sqlQuery.addScalar("tehsilId",Hibernate.LONG); 
			 sqlQuery.addScalar("tehsilName",Hibernate.STRING); 
		}else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		 sqlQuery.addScalar("panchayatId",Hibernate.LONG); 
			sqlQuery.addScalar("panchayatName",Hibernate.STRING); 
		}else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
			 sqlQuery.addScalar("wardId",Hibernate.LONG); 
			 sqlQuery.addScalar("wardName",Hibernate.STRING); 
		 } else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
			  sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG); 
			  sqlQuery.addScalar("localElectionBoydName",Hibernate.STRING); 
		}
		 sqlQuery.addScalar("count",Hibernate.LONG);
			
		if(activitiesIdsList != null && activitiesIdsList.size() >0){
			sqlQuery.setParameterList("activitiesIdsList", activitiesIdsList);	
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			sqlQuery.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		return sqlQuery.list();
}



public List<Object[]> getConductedCounts(Long locationId,Long activityScopeId, String searchType,Long stateId,String levelType,Long userAccessLevelId
		,Set<Long> userAccessLevelValues){
	
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append("select ");
	
	if(searchType != null && searchType.equalsIgnoreCase("constituency"))
		queryStr.append(" model.address.constituency.constituencyId,model.address.constituency.name," );
	else if(searchType != null && searchType.equalsIgnoreCase("village") || searchType != null && searchType.equalsIgnoreCase("onlyvillage"))
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
	
	/*queryStr.append(" count(distinct model.activityLocationInfoId) " +
			" from ActivityLocationInfo model where " +
			" model.activityScope.activityScopeId =:activityScopeId" +
			" and (model.conductedDate is not null or model.ivrStatus = 'Y') ");
	*/
	if(levelType != null && levelType.equalsIgnoreCase("constituency"))
		queryStr.append(" count(distinct model.address.constituency.constituencyId)  ");
	else if(levelType != null && levelType.equalsIgnoreCase("village"))
		queryStr.append(" count(distinct  model.address.panchayat.panchayatId) ");
	else if(levelType != null && levelType.equalsIgnoreCase("wards"))
		queryStr.append("  count(distinct  model.address.ward.constituencyId)  ");
	else if( levelType != null && levelType.equalsIgnoreCase("mandal"))
		queryStr.append("  count(distinct model.address.tehsil.tehsilId) ");
	else if( levelType != null && levelType.equalsIgnoreCase("town"))
		queryStr.append("  count(distinct  model.address.constituency.localElectionBody.localElectionBodyId) ");
	else if(levelType != null && levelType.equalsIgnoreCase("district"))
		queryStr.append(" count(distinct   model.address.district.districtId)  " );
	else if(levelType != null && levelType.equalsIgnoreCase("state"))
		queryStr.append("  count(distinct model.address.state.stateId ) " );
	queryStr.append(" , sum(model.infoCellCount) ");
	queryStr.append("  from ActivityConductedInfo model where " +
			" model.activityScope.activityScopeId =:activityScopeId" +
			" and (model.infoCellCount is not null or model.ivrStatus = 'Y' ) ");
	
	
	if(locationId.longValue() == 0l){
	if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
			 }
	}}else{
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
		queryStr.append(" group by  model.address.district.districtId  order by model.address.district.districtName " );
	else if(searchType != null && searchType.equalsIgnoreCase("state"))
		queryStr.append(" group by model.address.state.stateId order by model.address.state.stateName " );
	
	
	
	//queryStr.append(" order by model.address.constituency.name  ");
	Query query = getSession().createQuery(queryStr.toString());
	
	if(locationId != null && locationId.longValue() > 0l)
	query.setParameter("locationId",locationId );
	query.setParameter("activityScopeId",activityScopeId );
	if(locationId.longValue() == 0l){
		if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
	if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
		query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
	}
	return query.list();
}

public List<Object[]> getActivityLocationDetails(Long userAccessLevelId,Long userAccessLevelValues,Long activityScopeId,String locationType,String checkedValue){
	StringBuilder queryStr = new StringBuilder();

    queryStr.append("select distinct");

    if(userAccessLevelId.longValue() == 3l || userAccessLevelId.longValue() == 5l){
   	 queryStr.append(" d.districtId ,");
   	 queryStr.append(" d.districtName ,");
   	 queryStr.append(" c.constituencyId ,"); //3
	     queryStr.append(" c.name ,");
	     queryStr.append("'' ,");
	     queryStr.append("'' ,");
	     queryStr.append("'' ,");
	     queryStr.append("'' ,");
    }else if(userAccessLevelId.longValue() == 1l || userAccessLevelId.longValue() == 2l){
   	 queryStr.append(" t.tehsilId ,");
        queryStr.append(" t.tehsilName ,");
        queryStr.append(" leb.localElectionBodyId ,");
        queryStr.append(" leb.name ,");
        queryStr.append(" p.panchayatId ,");
        queryStr.append(" p.panchayatName ,");
        queryStr.append(" w.constituencyId ,");
        queryStr.append(" w.name ,");
	    }
   
   queryStr.append(" date(model.plannedDate) ,date(model.conductedDate) ,model.ivrStatus,model.activityConductedInfoId,model.updatedStatus ");
   queryStr.append(" FROM ActivityConductedInfo model " +
          // " userAddress UA " +
           " left join model.address.district d "+
           " left join model.address.constituency c " +
           " left join model.address.tehsil t " +
           " left join model.address.localElectionBody leb " +
           " left join model.address.panchayat p " +
           " left join model.address.ward w ");
	queryStr.append(" WHERE model.activityScope.activityScopeId = :activityScopeId " );
	
	if(userAccessLevelValues != null && userAccessLevelValues.longValue() > 0){
		if(locationType != null && locationType.equalsIgnoreCase("District")){
		      queryStr.append(" and model.address.district.districtId = :userAccessLevelValues");  
		}else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		   queryStr.append(" and model.address.constituency.constituencyId = :userAccessLevelValues");  
		}else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
			   queryStr.append(" and model.address.tehsil.tehsilId = :userAccessLevelValues");  
		}else if(locationType != null && locationType.equalsIgnoreCase("Panchayat")){
			   queryStr.append(" and model.address.panchayat.panchayatId = :userAccessLevelValues");  
		}else if(locationType != null && locationType.equalsIgnoreCase("Muncipality")){
			   queryStr.append(" and leb.localElectionBodyId = :userAccessLevelValues");  
		}else if(locationType != null && locationType.equalsIgnoreCase("ward")){
			   queryStr.append(" and w.constituencyId = :userAccessLevelValues");  
		}
	}
	
	if(checkedValue.trim().equalsIgnoreCase("notConducted")){
		queryStr.append("  and model.updatedStatus ='UPDATED' and (model.infoCellCount is null or (model.ivrStatus is null and model.ivrStatus = 'N')) ");
	}else if(checkedValue.trim().equalsIgnoreCase("conducted")){
		queryStr.append("  and model.updatedStatus ='UPDATED' and (model.infoCellCount is not null or model.ivrStatus = 'Y') ");
	}else if(checkedValue.trim().equalsIgnoreCase("notupdated")){
		queryStr.append(" and model.updatedStatus ='NOT UPDATED' ");
	}
	/*queryStr.append(" and model.activityScope.activity.isActive = 'Y'" +
			" and model.activityScope.isDeleted = 'N' ");*/
	Query sqlQuery = getSession().createQuery(queryStr.toString());
		sqlQuery.setParameter("activityScopeId", activityScopeId);
	if(userAccessLevelValues != null && userAccessLevelValues.longValue() > 0) {
		sqlQuery.setParameter("userAccessLevelValues", userAccessLevelValues);
	}
		
	return sqlQuery.list();
}
public ActivityConductedInfo isAlreadyAvailableLocationDtls(Long locationInfoId){
	
	StringBuilder sb=new StringBuilder();
	sb.append(" select  model from ActivityConductedInfo model where model.activityConductedInfoId =:locationInfoId ");
	Query query=getSession().createQuery(sb.toString());
	query.setParameter("locationInfoId",locationInfoId);
	
	
	return  (ActivityConductedInfo) query.uniqueResult();
} 
public List<Long> getActivityLocationInfoIdByLocationLevelAndLocationValue(Long activityScopeId,Long locationLevel,Long locationValue){
	
	Query query = getSession().createQuery(" select model.activityConductedInfoId from ActivityConductedInfo model " +
						" where model.locationLevel = :locationLevel and model.locationValue = :locationValue and model.activityScopeId=:activityScopeId ");
	
	query.setParameter("activityScopeId", activityScopeId);
	query.setParameter("locationLevel", locationLevel);
	query.setParameter("locationValue", locationValue);
	
	return query.list();
}
}
