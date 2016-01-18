package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityLocationInfoDAO extends GenericDaoHibernate<ActivityLocationInfo, Long> implements IActivityLocationInfoDAO{

	public ActivityLocationInfoDAO() {
		super(ActivityLocationInfo.class);
		
	}

	
	public List<Object[]> getUpdatedLocationsListForScope(Long activityScopeId,Date startDate,Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.locationValue,date(model.plannedDate),date(model.conductedDate),model.locationLevel from ActivityLocationInfo model where " +
				"model.activityScopeId =:activityScopeId ");
		if(startDate != null && endDate != null)
			queryStr.append(" and (date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityScopeId", activityScopeId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getAssemblyConstWiseDetails(Date startDate,Date endDate,Long activityScopeId,List<Long> constIds){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("" +
		" select   model.constituency.constituencyId,count(*),count(conductedDate)" +
		" from     ActivityLocationInfo model " +
		" where    model.activityScopeId =:activityScopeId and model.constituency.constituencyId in (:constIds) ");
		if(startDate!=null){
			sb.append(" and date(model.plannedDate) >=:startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.plannedDate) <=:endDate ");
		}
		sb.append(" group by model.constituency.constituencyId ");
		
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameterList("constIds",constIds);
		if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getPlannedCountForAssemblyConstWise(Date startDate,Date endDate,Long activityScopeId,List<Long> constIds){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("" +
			" select   model.constituency.constituencyId,count(model.plannedDate) " +
			" from     ActivityLocationInfo model " +
			" where    model.activityScopeId =:activityScopeId and model.constituency.constituencyId in (:constIds) and model.plannedDate is not null ");
		if(startDate!=null){
			sb.append(" and date(model.plannedDate) >=:startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.plannedDate) <=:endDate ");
		}
		sb.append(" group by model.constituency.constituencyId ");
		
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameterList("constIds",constIds);
		if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}
		return query.list();
	}
	
public List<Object[]> getNotPlannedCountForAssemblyConstWise(Long activityScopeId,List<Long> constIds){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("" +
			" select   model.constituency.constituencyId,count(model.conductedDate) " +
			" from     ActivityLocationInfo model " +
			" where    model.activityScopeId =:activityScopeId and model.constituency.constituencyId in (:constIds) and model.plannedDate is null and model.conductedDate is not null ");
		/*if(startDate!=null){
			sb.append(" and date(model.plannedDate) >=:startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.plannedDate) <=:endDate ");
		}*/
		sb.append(" group by model.constituency.constituencyId ");
		
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameterList("constIds",constIds);
		/*if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}*/
		return query.list();
	}
	
public List<Object[]> getNotConductedCountForAssemblyConstWise(Date startDate,Date endDate,Long activityScopeId,List<Long> constIds){
	
	StringBuilder sb=new StringBuilder();
	
	sb.append("" +
		" select   model.constituency.constituencyId,count(model.plannedDate) " +
		" from     ActivityLocationInfo model " +
		" where    model.activityScopeId =:activityScopeId and model.constituency.constituencyId in (:constIds) and model.plannedDate is not null and model.conductedDate is null ");
	if(startDate!=null){
		sb.append(" and date(model.plannedDate) >=:startDate ");
	}
	if(endDate!=null){
		sb.append(" and date(model.plannedDate) <=:endDate ");
	}
	sb.append(" group by model.constituency.constituencyId ");
	
	Query query=getSession().createQuery(sb.toString());
	query.setParameter("activityScopeId", activityScopeId);
	query.setParameterList("constIds",constIds);
	if(startDate!=null){
		query.setDate("startDate",startDate);
	}
	if(endDate!=null){
		query.setDate("endDate",endDate);
	}
	return query.list();
}

	public List<Object[]> getActivityPlannedInfoCellAndIVRCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId){
				
			StringBuilder queryStr = new StringBuilder();
			Query query = null;
			if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
					searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
			{
				queryStr.append(" select ");
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" model.constituency.state.stateId,model.constituency.state.stateName, ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append("  model.constituency.district.districtId, model.constituency.district.districtName, ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" model.constituency.constituencyId, model.constituency.name,");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" T.tehsilId,T.tehsilName, ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" P.panchayatId,P.panchayatName, ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append("  C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
				}
				
				queryStr.append(" model.locationLevel,count(model.locationValue) from ActivityLocationInfo model " );
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append("  ,Tehsil T ,Panchayat P ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" ,LocalElectionBody LEB, Constituency C  ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" , Panchayat P ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" ,Constituency C ");
				}
				
				if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("planned")){
					queryStr.append(" where model.plannedDate is not null ");
				}
				else if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("infocell")){
					queryStr.append(" where model.plannedDate is not null and  model.conductedDate is not null ");
				}
				else if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("ivr")){
					queryStr.append(" where  model.plannedDate is not null and model.ivrStatus = 'Y' ");
				}
				
				if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
					queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIdsList) ");
				
				if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
					queryStr.append(" and ( date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");
				}
				
				if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
				{
					if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
						queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
						queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
						queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
						queryStr.append(" and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
						queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.locationValue ");
						queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
						queryStr.append(" and  model.locationValue = P.panchayatId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
						queryStr.append(" and model.locationValue = C.constituencyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
				}	
				else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
				{
					if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
						queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
						queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
						queryStr.append(" and   model.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else{
						queryStr.append(" and model.locationValue = LEB.localElectionBodyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
				}
				else{
					
					if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
						queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
						queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
					}
					else
						queryStr.append(" and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				
				if(stateId != null && stateId.longValue() == 36L)
					queryStr.append("   and model.constituency.district.districtId between 1 and 10  ");
				else if(stateId != null && stateId.longValue() == 1L)
					queryStr.append("   and model.constituency.district.districtId between 11 and 23  ");
				
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" group by model.constituency.state.stateId,model.locationLevel order by ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" group by model.constituency.district.districtId,model.locationLevel order by ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" group by  model.constituency.constituencyId,model.locationLevel order by ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" group by T.tehsilId,model.locationLevel order by ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
						queryStr.append(" group by  LEB.localElectionBodyId,model.locationLevel order by ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" group by P.panchayatId,model.locationLevel order by ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" group by C.constituencyId,model.locationLevel order by ");
				}
			
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append("  model.constituency.district.districtName  ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" model.constituency.name");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" T.tehsilName ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" LEB.name ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" P.panchayatName ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" C.name ");
				}
				else
				{
					queryStr.append(" model.locationLevel ");
				}
				queryStr.append(" asc ");
				
				query = getSession().createQuery(queryStr.toString());
				
				if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
					query.setParameterList("activityScopeIdsList", searchAttributeVO.getAttributesIdsList());
				
				if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
					query.setDate("startDate", searchAttributeVO.getStartDate());
					query.setDate("endDate", searchAttributeVO.getEndDate());
				}
				if(searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0)
					query.setParameterList("locationIdsList", searchAttributeVO.getLocationIdsList());
				if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
					query.setParameterList("locationTypeIdsList", searchAttributeVO.getLocationTypeIdsList());

				return query.list();
			}
			else
				return null;
		
	}
	
	public List<Object[]> getActivityNotPlannedInfoCellAndIVRCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
				searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
		{
			queryStr.append(" select ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" model.constituency.state.stateId,model.constituency.state.stateName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.constituency.district.districtId, model.constituency.district.districtName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.constituency.constituencyId, model.constituency.name,");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" T.tehsilId,T.tehsilName, ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" P.panchayatId,P.panchayatName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append("  C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
			}
			
			queryStr.append(" model.locationLevel,count(model.locationValue) from ActivityLocationInfo model " );
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append("  ,Tehsil T ,Panchayat P ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" ,LocalElectionBody LEB, Constituency C  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" , Panchayat P ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" ,Constituency C ");
			}
			
			if(searchAttributeVO.getConditionType().equalsIgnoreCase("infocell")){
				queryStr.append(" where model.conductedDate is not null ");
			}
			else if(searchAttributeVO.getConditionType().equalsIgnoreCase("ivr")){
				queryStr.append(" where model.ivrStatus = 'Y' ");
			}
			
			queryStr.append(" and model.plannedDate is null ");
			
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIdsList) ");
			
			
			
			if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId  in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
					queryStr.append(" and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.locationValue ");
					queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" and  model.locationValue = P.panchayatId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" and model.locationValue = C.constituencyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}	
			else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and   model.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else{
					queryStr.append(" and model.locationValue = LEB.localElectionBodyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}
			else{
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else
					queryStr.append(" and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			
			if(stateId != null && stateId.longValue() == 36L)
				queryStr.append("   and model.constituency.district.districtId between 1 and 10  ");
			else if(stateId != null && stateId.longValue() == 1L)
				queryStr.append("   and model.constituency.district.districtId between 11 and 23  ");
			
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" group by model.constituency.state.stateId,model.locationLevel order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" group by model.constituency.district.districtId,model.locationLevel order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" group by  model.constituency.constituencyId,model.locationLevel order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" group by T.tehsilId,model.locationLevel order by ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append("  group by  LEB.localElectionBodyId,model.locationLevel order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" group by P.panchayatId,model.locationLevel order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" group by C.constituencyId,model.locationLevel order by ");
			}
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.constituency.district.districtName  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.constituency.name");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" T.tehsilName ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" LEB.name ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" P.panchayatName ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" C.name ");
			}
			else
			{
				queryStr.append(" model.locationLevel ");
			}
			queryStr.append(" asc ");
			
			query = getSession().createQuery(queryStr.toString());
			
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				query.setParameterList("activityScopeIdsList", searchAttributeVO.getAttributesIdsList());
			
			/*if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
				query.setDate("startDate", searchAttributeVO.getStartDate());
				query.setDate("endDate", searchAttributeVO.getEndDate());
			}*/
			if(searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0)
				query.setParameterList("locationIdsList", searchAttributeVO.getLocationIdsList());
			if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
				query.setParameterList("locationTypeIdsList", searchAttributeVO.getLocationTypeIdsList());

			return query.list();
		}
		else
			return null;
	
	}
	
	
	public List<Object[]> getActivityAttributeCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
				searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
		{
			queryStr.append(" select ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" model.constituency.state.stateId,model.constituency.state.stateName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.constituency.district.districtId, model.constituency.district.districtName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.constituency.constituencyId, model.constituency.name,");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" T.tehsilId,T.tehsilName, ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" P.panchayatId,P.panchayatName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append("  C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
			}
			
			if(searchAttributeVO.getAttributeType().equalsIgnoreCase("status")){
				queryStr.append(" count(model1.activityQuestionAnswerId) ");
			}
			else if(searchAttributeVO.getAttributeType().equalsIgnoreCase("statusCount")){
				queryStr.append(" sum(model1.count) ");
			}
			
			queryStr.append(" from ActivityLocationInfo model,ActivityQuestionAnswer model1 " );
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append("  ,Tehsil T ,Panchayat P ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" ,LocalElectionBody LEB, Constituency C  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" , Panchayat P ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" ,Constituency C ");
			}
			
			queryStr.append(" where model1.activityLocationInfoId = model.activityLocationInfoId and model1.activityOptionId = 1 ");
			
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIdsList) ");
			
			if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
				queryStr.append(" and ( date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");
			}
			
			if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
					queryStr.append(" and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.locationValue ");
					queryStr.append(" and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" and  model.locationValue = P.panchayatId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" and model.locationValue = C.constituencyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}	
			else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and   model.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else{
					queryStr.append(" and model.locationValue = LEB.localElectionBodyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}
			else{
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else
					queryStr.append(" and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			if(stateId != null && stateId.longValue() == 36L)
				queryStr.append("   and model.constituency.district.districtId between 1 and 10  ");
			else if(stateId != null && stateId.longValue() == 1L)
				queryStr.append("   and model.constituency.district.districtId between 11 and 23  ");
			
			
			queryStr.append(" group by model1.activityOptionId order by ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.constituency.district.districtName  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.constituency.name");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" T.tehsilName ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" LEB.name ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" P.panchayatName ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" C.name ");
			}
			else
			{
				queryStr.append(" model1.activityOptionId ");
			}
			queryStr.append(" asc ");
			
			query = getSession().createQuery(queryStr.toString());
			
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				query.setParameterList("activityScopeIdsList", searchAttributeVO.getAttributesIdsList());
			
			if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
				query.setDate("startDate", searchAttributeVO.getStartDate());
				query.setDate("endDate", searchAttributeVO.getEndDate());
			}
			if(searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0)
				query.setParameterList("locationIdsList", searchAttributeVO.getLocationIdsList());
			if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
				query.setParameterList("locationTypeIdsList", searchAttributeVO.getLocationTypeIdsList());

			return query.list();
		}
		else
			return null;
	
	}
	
	public List<Object[]> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
				searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
		{
			queryStr.append(" select ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" model.constituency.state.stateId,model.constituency.state.stateName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.constituency.district.districtId, model.constituency.district.districtName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.constituency.constituencyId, model.constituency.name,");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" T.tehsilId,T.tehsilName, ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" P.panchayatId,P.panchayatName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append("  C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
			}
			
			queryStr.append(" model.locationLevel,date(model.plannedDate),count(distinct model.locationValue) from ActivityLocationInfo model " );
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append("  ,Tehsil T ,Panchayat P ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" ,LocalElectionBody LEB, Constituency C  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" , Panchayat P ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" ,Constituency C ");
			}
			
			if(searchAttributeVO.getConditionType().equalsIgnoreCase("planned")){
				queryStr.append(" where model.plannedDate is not null ");
			}
			else if(searchAttributeVO.getConditionType().equalsIgnoreCase("infocell")){
				queryStr.append(" where model.conductedDate is not null ");
			}
			else if(searchAttributeVO.getConditionType().equalsIgnoreCase("ivr")){
				queryStr.append(" where model.ivrStatus = 'Y' ");
			}
			else {
				queryStr.append(" where model.activityLocationInfoId is not null ");
			}
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIdsList) ");
			
			if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
				queryStr.append(" and ( date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");
			}
			
			if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
					queryStr.append(" and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.locationValue ");
					queryStr.append(" and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" and  model.locationValue = P.panchayatId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" and model.locationValue = C.constituencyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}	
			else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and   model.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else{
					queryStr.append(" and model.locationValue = LEB.localElectionBodyId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}
			else{
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else
					queryStr.append(" and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			if(stateId != null && stateId.longValue() == 36L)
				queryStr.append("   and model.constituency.district.districtId between 1 and 10  ");
			else if(stateId != null && stateId.longValue() == 1L)
				queryStr.append("   and model.constituency.district.districtId between 11 and 23  ");
			
			queryStr.append("   ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" group by model.constituency.district.districtId,date(model.plannedDate)  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" group by  model.constituency.constituencyId,date(model.plannedDate)");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append("  group by  T.tehsilId,date(model.plannedDate) ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append("  group by  LEB.localElectionBodyId,date(model.plannedDate) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append("  group by  P.panchayatId,date(model.plannedDate) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" group by   C.constituencyId,date(model.plannedDate) ");
			}
			queryStr.append(" order by  date(model.plannedDate) asc  ");
			query = getSession().createQuery(queryStr.toString());
			
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				query.setParameterList("activityScopeIdsList", searchAttributeVO.getAttributesIdsList());
			
			if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
				query.setDate("startDate", searchAttributeVO.getStartDate());
				query.setDate("endDate", searchAttributeVO.getEndDate());
			}
			if(searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0)
				query.setParameterList("locationIdsList", searchAttributeVO.getLocationIdsList());
			if(searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
				query.setParameterList("locationTypeIdsList", searchAttributeVO.getLocationTypeIdsList());
			return query.list();
		}
		else
			return null;
	
	}

	public List<Long> getActivityLocationInfoIdByLocationLevelAndLocationValue(Long activityScopeId,Long locationLevel,Long locationValue){
		
		Query query = getSession().createQuery(" select model.activityLocationInfoId from ActivityLocationInfo model " +
							" where model.locationLevel = :locationLevel and model.locationValue = :locationValue and model.activityScopeId=:activityScopeId ");
		
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("locationLevel", locationLevel);
		query.setParameter("locationValue", locationValue);
		
		return query.list();
	}
	
public List<Object[]> getDistrictWiseDetails(Date startDate,Date endDate,Long activityScopeId,List<Long> distIds){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("" +
		" select   model.constituency.district.districtId,count(*),count(conductedDate),model.activityScope.activity.noOfTimes " +
		" from     ActivityLocationInfo model " +
		" where    model.activityScopeId =:activityScopeId and model.constituency.district.districtId in (:distIds) ");
		if(startDate!=null){
			sb.append(" and date(model.plannedDate) >=:startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.plannedDate) <=:endDate ");
		}
		sb.append(" group by model.constituency.district.districtId ");
		
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameterList("distIds",distIds);
		if(startDate!=null){
			query.setDate("startDate",startDate);
		}
		if(endDate!=null){
			query.setDate("endDate",endDate);
		}
		return query.list();
	}

	public List<Long> isAlreadyAvailableActivityLocationDtls(Long activityScopeId,Long locationLevel,Long locationValue){
		
		StringBuilder sb=new StringBuilder();
		sb.append(" select distinct model.activityLocationInfoId from ActivityLocationInfo model where model.activityScopeId =:activityScopeId and" +
				"  model.locationLevel =:locationLevel and model.locationValue =:locationValue");
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId",activityScopeId);
		query.setParameter("locationLevel",locationLevel);
		query.setParameter("locationValue",locationValue);
		
		return query.list();
	} 
	
	public List<Object[]> getActivityLocationDetailsByScopeId(Long scopeId){
		
		Query query = getSession().createQuery(" select model.activityLocationInfoId, " +
							" model.activityScope.activityScopeId, " +
							" model.locationLevel, " +
							" model.locationValue " +
							" from ActivityLocationInfo model " +
							" where model.activityScope.activityScopeId = :scopeId ");
		query.setParameter("scopeId", scopeId);
		
		return query.list();
	}
}
