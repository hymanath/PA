package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
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
		queryStr.append(" order by  model.locationValue ");
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
		" select   model.constituency.constituencyId,count(*),count(date(model.insertionTime))" +
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
			" select   model.constituency.constituencyId,count(date(model.insertionTime)) " +
			" from     ActivityLocationInfo model " +
			" where    model.activityScopeId =:activityScopeId and model.constituency.constituencyId in (:constIds) and model.plannedDate is null and model.insertionTime is not null ");
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
		" where    model.activityScopeId =:activityScopeId and model.constituency.constituencyId in (:constIds) and model.plannedDate is not null and model.insertionTime is null ");
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
				
				if(searchAttributeVO.getConditionType().trim().trim().equalsIgnoreCase("planned")){
					queryStr.append(" where model.plannedDate is not null ");
				}
				else if(searchAttributeVO.getConditionType().trim().trim().equalsIgnoreCase("infocell")){
					//queryStr.append(" where model.plannedDate is not null and  model.conductedDate is not null ");
					queryStr.append(" where  model.plannedDate is not null and model.insertionTime is not null ");
				}
				else if(searchAttributeVO.getConditionType().trim().trim().equalsIgnoreCase("ivr")){
					//queryStr.append(" where  model.plannedDate is not null and model.ivrStatus = 'Y' ");
					queryStr.append(" where model.plannedDate is not null and model.ivrStatus = 'Y' ");
				}
				
				if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
					queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIdsList) ");
				
				if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
					queryStr.append(" and ( date(model.insertionTime) >= :startDate and date(model.insertionTime) <= :endDate ) ");
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
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append(" S.stateId,S.stateName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append(" D.districtId, D.districtName  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() != 9999L){
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
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append("  ,State S ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append("  ,District D ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
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
			
			if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("infocell")){
				queryStr.append(" where model.insertionTime is not null ");
			}
			else if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("ivr")){
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
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() == 9999L){
					queryStr.append(" and model.locationValue = S.stateId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() != 9999L){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId().longValue() == 9999L){
					queryStr.append(" and model.locationValue = D.districtId and model.locationValue in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
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
			
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId() != 9999L){
				queryStr.append(" group by model.constituency.state.stateId,model.locationLevel order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId() != 9999L ){
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
			else
				queryStr.append(" order by ");
			
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
			
			queryStr.append(" model.locationLevel,date(model.conductedDate),count(distinct model.locationValue),date(model.insertionTime) from ActivityLocationInfo model " );
			
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
				queryStr.append(" where model.plannedDate is not null and model.insertionTime is not null ");
			}
			else if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("ivr")){
				queryStr.append(" where model.ivrStatus = 'Y' and model.plannedDate is not null ");
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
				queryStr.append(" group by model.constituency.district.districtId,date(model.insertionTime)  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" group by  model.constituency.constituencyId,date(model.insertionTime)");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append("  group by  T.tehsilId,date(model.insertionTime) ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append("  group by  LEB.localElectionBodyId,date(model.insertionTime) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append("  group by  P.panchayatId,date(model.insertionTime) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" group by   C.constituencyId,date(model.insertionTime) ");
			}
			queryStr.append(" order by  date(model.insertionTime) asc  ");
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

public List<Object[]> getActivityNotPlannedDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
				searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
		{
			queryStr.append(" select ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append(" S.stateId,S.stateName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append("  D.districtId, D.districtName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() != 9999L){
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
			
			queryStr.append(" model.locationLevel,date(model.conductedDate),count(distinct model.locationValue),date(model.insertionTime) from ActivityLocationInfo model " );
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append("  ,State S ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId().longValue() == 9999L){
				queryStr.append("  ,District D ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
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
			
			 if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("infocell")){
				queryStr.append(" where model.insertionTime is not null ");
			}
			else if(searchAttributeVO.getConditionType().trim().equalsIgnoreCase("ivr")){
				queryStr.append(" where model.ivrStatus = 'Y'  ");
			}
			else {
				queryStr.append(" where model.activityLocationInfoId is not null ");
			}
			 
			 queryStr.append(" and model.plannedDate is null ");
			 
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
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() == 9999L){
					queryStr.append(" and model.locationValue = S.stateId and S.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE) && searchAttributeVO.getTypeId().longValue() != 9999L){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId().longValue() == 9999L){
					queryStr.append(" and model.locationValue = D.districtId and D.districtId in (:locationIdsList) and model.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
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
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT) && searchAttributeVO.getTypeId() != 9999L){
				queryStr.append(" group by model.constituency.district.districtId,date(model.insertionTime)  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" group by  model.constituency.constituencyId,date(model.insertionTime)");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append("  group by  T.tehsilId,date(model.insertionTime) ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append("  group by  LEB.localElectionBodyId,date(model.insertionTime) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append("  group by  P.panchayatId,date(model.insertionTime) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" group by   C.constituencyId,date(model.insertionTime) ");
			}
			queryStr.append(" order by  date(model.insertionTime) asc  ");
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
		" select   model.constituency.district.districtId,count(*),count(date(model.insertionTime)),model.activityScope.activity.noOfTimes " +
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
	
	public List<Object[]> getActivityDetailsInCadreLocation(List<Long> activityScopeIds,Long locationId,Long locationValue){
		Query query = getSession().createQuery("select model.activityLocationInfoId, " +
							" model.activityScopeId, " +
							" model.plannedDate, " +
							" model.conductedDate, " +
							" model.ivrStatus " +
							" from ActivityLocationInfo model " +
							" where model.activityScope.activityScopeId in (:activityScopeIds) " +
							" and model.locationLevel = :locationId and model.locationValue = :locationValue ");
		query.setParameterList("activityScopeIds", activityScopeIds);
		query.setParameter("locationId", locationId);
		query.setParameter("locationValue", locationValue);
		return query.list();
	}
	
	public List<Object[]> getLocationDetailsByInfoIds(List<Long> activityInfoIds){
		Query query = getSession().createQuery(" select model.locationLevel, " +
							" model.locationValue " +
							" from ActivityLocationInfo model " +
							" where model.activityLocationInfoId in (:activityInfoIds) ");
		query.setParameterList("activityInfoIds", activityInfoIds);
		return query.list();
	}
	
	public List<Object[]> getActivityLocationNames(List<Long> activityInfoIds,Long locationlevel){
		
		Query query = getSession().createQuery(" select distinct model.activityScopeId, constituency.name " +
							" from ActivityLocationInfo model left join model.constituency constituency " +
							" where model.activityLocationInfoId in (:activityInfoIds) ");
		query.setParameterList("activityInfoIds", activityInfoIds);
		return query.list();
	}
	
	
	public List<Object[]> getLocationWiseUpdatedCountDetails(SearchAttributeVO searchVO){
		
		StringBuilder sb=new StringBuilder();
		sb.append("select  ");
		if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
			sb.append("  model.constituency.constituencyId, ");
		else if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.DISTRICT))
			sb.append("  model.constituency.district.districtId, ");
		sb.append(" model.locationLevel, count(model.activityLocationInfoId) from     ActivityLocationInfo model " +
			"  where    model.activityScopeId =:activityScopeId   ");
		if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
			sb.append(" and model.constituency.constituencyId in (:locationIds) group by model.constituency.constituencyId,model.locationLevel ");
		else if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.DISTRICT))
			sb.append(" and model.constituency.district.districtId in (:locationIds) group by model.constituency.district.districtId,model.locationLevel ");
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", searchVO.getScopeId());
		query.setParameterList("locationIds",searchVO.getLocationIdsList());
		return query.list();
	}
	
	
	public List<Object[]> getConductedActivityDetailsbyScopeAndLocationID(AddressVO addressVO,Long activityLevelId){
		StringBuilder queryStr = new StringBuilder();
		boolean isLocationAvailable =false;

		queryStr.append("select distinct model.activityScope.activityScopeId,model.activityScope.activity.activityName, " +
				" model.activityScope.activityLevelId, model.activityScope.activityLevel.level,model.activityScope.activity.activityId," +
				" '','' from ActivityLocationInfo model where model.activityScope.activityLevelId=:activityLevelId ");
			queryStr.append(" and model.activityScope.activity.isActive ='Y' and model.activityScope.isDeleted ='N' ");
			
		if(!isLocationAvailable && (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && (addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getStateId() != null && addressVO.getStateId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			isLocationAvailable =true;
		
		if(isLocationAvailable)
			queryStr.append(" and ( ");
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L)){
			queryStr.append(" 	( model.locationLevel in (6,8) and model.locationValue =:villageORWardId)  ");
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			queryStr.append(" OR ( model.locationLevel in (5,7) and model.locationValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.locationLevel = 11 and model.locationValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}		
		else if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L)){
			queryStr.append(" OR ( model.locationLevel in (5,7) and model.locationValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.locationLevel = 11 and model.locationValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}
		else if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L){
			queryStr.append(" OR ( model.locationLevel = 11 and model.locationValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}
		else if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}
		else if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		
		
		if(isLocationAvailable)
			queryStr.append(" )   ");
		
		//queryStr.append(" group by model.activityScope.activityScopeId");
		Query query = getSession().createQuery(queryStr.toString());
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			query.setParameter("villageORWardId", (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) ? addressVO.getPanchaytId() :(addressVO.getWardId() != null && addressVO.getWardId()>0L)?addressVO.getWardId():null );
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			query.setParameter("mandalORMuncId", addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L ? addressVO.getTehsilId()  : (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L) ?addressVO.getLocalElectionBodyId():null);
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			query.setParameter("districtId",addressVO.getDistrictId() );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			Long id = addressVO.getDistrictId() <=10?36L:1L;
			query.setParameter("stateId", id);
		}
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			query.setParameter("constituencyId",addressVO.getConstituencyId() );
		
		query.setParameter("activityLevelId", activityLevelId);
		
		return query.list();
	}
	
	public List<Object[]> getConductedActivityDetailsbyScopeAndLocationID(Long activityLevelId,Long panchayatId,Long mandalId,Long lebId,Long assemblyId,Long districtId,
			Long  stateId,Long participatedAssemblyId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.activityScope.activityScopeId,model.activityScope.activity.activityName, model.activityScope.activityLevel.activityLevelId,"+
			" model.activityScope.activityLevel.level,model.activityScope.activity.activityId,model.activityLocationInfoId,date(model.conductedDate) from ActivityLocationInfo model where model.activityScope.activityLevelId=:activityLevelId ");
		queryStr.append(" and model.locationValue =:locationValue and model.activityScope.activity.isActive ='Y' and model.activityScope.isDeleted ='N' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityLevelId", activityLevelId);
			if(activityLevelId != null && activityLevelId.longValue()>0L){
				
				if(activityLevelId.longValue() == 1L){//village/ward
					query.setParameter("locationValue", panchayatId);
				}
				else if(activityLevelId.longValue() == 2L){//Mandal/Town/Division
					query.setParameter("locationValue", mandalId);
				}
				else if(activityLevelId.longValue() == 3L){//district
					query.setParameter("locationValue", districtId);
				}
				else if(activityLevelId.longValue() == 4L){//state
					query.setParameter("locationValue", stateId);
				}
				else if(activityLevelId.longValue() == 5L){//cosntituency
					query.setParameter("locationValue", assemblyId);
				}
					
					
			}
		return query.list();
	}
	
	public List<Object[]> getActivityDetailsByAddressDetails(AddressVO addressVO){
		StringBuilder queryStr = new StringBuilder();
		boolean isLocationAvailable =false;
		queryStr.append(" select distinct model.activityScopeId,model.activityScope.activity.activityName,model.activityScope.activityLevelId from ActivityLocationInfo model " +
							" where ");
		if(!isLocationAvailable && (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && (addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getStateId() != null && addressVO.getStateId()>0L)
			isLocationAvailable =true;
		else if(!isLocationAvailable && addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			isLocationAvailable =true;
		
		if(isLocationAvailable)
			queryStr.append(" ( ");
		
		/*if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L)){
			queryStr.append(" 	( model.activityScope.scopeId in (6,8) and model.activityScope.scopeValue =:villageORWardId)  ");
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			queryStr.append(" OR ( model.activityScope.scopeId in (5,7) and model.activityScope.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 3 and model.activityScope.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}		
		else if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L)){
			queryStr.append("  ( model.activityScope.scopeId = in (5,7) and model.activityScope.scopeValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 3 and model.activityScope.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L){
			queryStr.append("  ( model.activityScope.scopeId = 3 and model.activityScope.scopeValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			queryStr.append(" ( model.activityScope.scopeId = 2 and model.activityScope.scopeValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		}
		else if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append("  ( model.activityScope.scopeId = 4 and model.activityScope.scopeValue =:constituencyId)  " );
		
		if(isLocationAvailable)
			queryStr.append(" ) and  ");
		*/
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L)){
			queryStr.append(" 	( model.locationLevel in (6,8) and model.locationValue =:villageORWardId)  ");
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			queryStr.append(" OR ( model.locationLevel in (5,7) and model.locationValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.locationLevel = 11 and model.locationValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}		
		else if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L)){
			queryStr.append(" OR ( model.locationLevel in (5,7) and model.locationValue =:mandalORMuncId)  " );
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			queryStr.append(" OR ( model.locationLevel = 11 and model.locationValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}
		else if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L){
			queryStr.append(" OR ( model.locationLevel = 11 and model.locationValue =:districtId )  " );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L)
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}
		else if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			queryStr.append(" OR ( model.locationLevel = 10 and model.locationValue =:stateId)  ");
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		}
		else if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			queryStr.append(" OR ( model.locationLevel = 13 and model.locationValue =:constituencyId)  " );
		
		if(isLocationAvailable)
			queryStr.append(" ) and  ");
		
		
		queryStr.append("  model.activityScope.isDeleted = 'N' and model.activityScope.activity.isActive='Y' ");
		Query query = getSession().createQuery(queryStr.toString());
		
		if((addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) || (addressVO.getWardId() != null && addressVO.getWardId()>0L))
			query.setParameter("villageORWardId", (addressVO.getPanchaytId() != null && addressVO.getPanchaytId()>0L) ? addressVO.getPanchaytId() :(addressVO.getWardId() != null && addressVO.getWardId()>0L)?addressVO.getWardId():null );
		if((addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L) || (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L))
			query.setParameter("mandalORMuncId", addressVO.getTehsilId() != null && addressVO.getTehsilId()>0L ? addressVO.getTehsilId()  : (addressVO.getLocalElectionBodyId() != null && addressVO.getLocalElectionBodyId()>0L) ?addressVO.getLocalElectionBodyId():null);
		if(addressVO.getDistrictId() != null && addressVO.getDistrictId()>0L)
			query.setParameter("districtId",addressVO.getDistrictId() );
		if(addressVO.getStateId() != null && addressVO.getStateId()>0L){
			Long id = addressVO.getDistrictId() <=10?36L:1L;
			query.setParameter("stateId", id);
		}
		if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId()>0L)
			query.setParameter("constituencyId",addressVO.getConstituencyId() );
		
		return query.list();
	
		
	}
	
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds,String type){
		StringBuilder queryStr = new StringBuilder();
		Long stateId = 1L;
		queryStr.append("select model.activityScope.activityScopeId,count(model.activityLocationInfoId)" +
				" from ActivityLocationInfo model where" +
				"  model.activityScope.activityScopeId in (:activityScopeIds) ");
		if(type != null){
			if(type.equalsIgnoreCase("yes"))
				queryStr.append(" and (model.conductedDate is not null and  model.ivrStatus ='Y' ) ");  
			else if(type.equalsIgnoreCase("no"))
				queryStr.append(" and ( model.conductedDate is null and  model.ivrStatus ='N') ");
			else if(type.equalsIgnoreCase("maybe"))
				queryStr.append(" and ( (model.conductedDate is not null and  model.ivrStatus='N')  " +
						"or (model.conductedDate is not  null and  model.ivrStatus is null) ) ");			
		}else{
			queryStr.append(" and model.plannedDate is not null ");
		}
		
		if(stateId != null && stateId.longValue() == 1L)
			queryStr.append(" and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
		else if(stateId != null && ( stateId.longValue() == 2L || stateId.longValue() == 36L))
			queryStr.append(" and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		else{
			queryStr.append(" and  model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+", "+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		
		/*if(type != null){
			if(type.equalsIgnoreCase("yes"))
				queryStr.append(" and  (( model.conductedDate is not null and  model.ivrStatus ='Y') or " +
						" ( model.conductedDate is not null and  model.ivrStatus is null) or " +
						" ( model.conductedDate is null and  model.ivrStatus ='Y' ) ) ");  
			else if(type.equalsIgnoreCase("no"))
				queryStr.append(" and ( ( model.conductedDate is null and  model.ivrStatus ='N' ) or ( model.conductedDate is null and  model.ivrStatus is null )  )");
			else if(type.equalsIgnoreCase("maybe"))
				queryStr.append(" and ( (model.conductedDate is not null and  model.ivrStatus='N') or (model.conductedDate is null and  model.ivrStatus='Y')) ");			
		}else{
			queryStr.append(" and model.plannedDate is not null ");
		}
		 * */
		queryStr.append(" group by model.activityScope.activityScopeId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds,Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.activityScope.activityScopeId,count(model.activityLocationInfoId)" +
				" from ActivityLocationInfo model where model.ivrStatus = 'Y' ");
		
		if(stateId != null && stateId.longValue() == 1L)
			queryStr.append(" and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
		else if(stateId != null && ( stateId.longValue() == 2L || stateId.longValue() == 36L))
			queryStr.append(" and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		else{
			queryStr.append(" and  model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+", "+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIds)" +
				" group by model.activityScope.activityScopeId");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds,Long stateId1){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select model.activityScope.activityScopeId," +
												" count(model.activityLocationInfoId), sum(model.attendedCount)" +
												" from ActivityLocationInfo model" +
												" where model.conductedDate is not null" +
												" and model.activityScope.activityScopeId in (:activityScopeIds)"); 
		if(stateId1 != null && stateId1.longValue() == 1l){
			queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId1 != null && (stateId1.longValue() == 2l || stateId1.longValue() == 36l)){
			queryStr.append("  and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId1 != null && stateId1.longValue() == 0l){
			queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		queryStr.append(" group by model.activityScope.activityScopeId");
		 Query query = getSession().createQuery(queryStr.toString()); 
		query.setParameterList("activityScopeIds", activityScopeIds);
		return query.list();
	}
	
	public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId,Long stateId,String type){
		 StringBuilder queryStr = new StringBuilder();		
	      queryStr.append("select model.activityScope.activityScopeId," +
												" count(model.activityLocationInfoId),"); 
	      if(scopeId !=null && scopeId.longValue() == 3l){
	    	  queryStr.append("  model.address.district.districtId," +
												"  model.address.district.districtName," );
	      }else if(scopeId !=null && scopeId.longValue() == 2l){
	    	  queryStr.append("  model.address.state.stateId," +
						"  model.address.state.stateName," );
	      }
	      queryStr.append("  model.activityScope.activity.activityName, " +
												" model.activityScope.activityLevel.level,model.activityScope.activityLevel.activityLevelId " +
												" from ActivityLocationInfo model " +
												" where model.activityScope.isDeleted='N' and model.activityScope.activity.isActive='Y' and " +
												" model.activityScope.activityId in (:activityIdsLst)  " +
												" and (model.activityScope.startDate >=:startDate and model.activityScope.endDate <=:endDate) " +
												"  "); 
	     
	      if(type != null && !type.isEmpty()){
	    	if(type.equalsIgnoreCase("yes")){
	    		queryStr.append(" and ( (model.conductedDate is not null and  model.ivrStatus='Y' ) OR" +
	    				"  ( model.conductedDate is not null and model.ivrStatus is null ) OR " +
	    				"  ( model.conductedDate is null and model.ivrStatus='Y' ) ) ");
	    	}
	    	else if(type.equalsIgnoreCase("no")){
	    		queryStr.append(" and ( (model.conductedDate is null and model.ivrStatus='N' )  OR " +
	    				" ( model.conductedDate is null and model.ivrStatus is null )  ) ");
	    	}
	    	else if(type.equalsIgnoreCase("maybe")){
	    		queryStr.append(" and ( (model.conductedDate is not null and model.ivrStatus='N' )  OR " +
	    				" ( model.conductedDate is not null and model.ivrStatus ='N' )  ) ");
	    	}
	      }
	      else{
	    	  queryStr.append(" and ( model.conductedDate is not null or model.ivrStatus='Y') ");
	      }
	      
	      if(stateId != null && stateId.longValue() == 1l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && (stateId.longValue() == 2l || stateId.longValue() == 36l)){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && stateId.longValue() == 0l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
			}
	      
	      if(scopeId != null && scopeId.longValue() == 3l){
	    	  
	    	   queryStr.append(" group by model.activityScope.activityScopeId,model.address.district.districtId "); 
	    	   queryStr.append(" order by model.address.district.districtId ");
	      }
	     else if(scopeId != null && scopeId.longValue() == 2l){
	    	//  queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
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
	public List<Object[]> getDistrictWiseActivityCounts(Long locationId,Long activityScopeId, String searchType,Long stateId,String countType,String type){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("select ");
		
		if(searchType != null && searchType.equalsIgnoreCase("constituency"))
			queryStr.append(" model.address.constituency.constituencyId,model.address.constituency.name," );
		else if(searchType != null && searchType.equalsIgnoreCase("village") || searchType != null && searchType.equalsIgnoreCase("onlyvillage"))
			queryStr.append("  model.address.panchayat.panchayatId, model.address.panchayat.panchayatName,");
		else if(searchType != null && searchType.equalsIgnoreCase("ward"))
			queryStr.append("  model.address.ward.constituencyId,model.address.ward.name, ");
		else if( searchType != null && searchType.equalsIgnoreCase("mandal"))
			queryStr.append("  model.address.tehsil.tehsilId, model.address.tehsil.tehsilName, ");
		else if( searchType != null && searchType.equalsIgnoreCase("town"))
			queryStr.append("  model.address.localElectionBody.localElectionBodyId, model.address.localElectionBody.name, ");
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(" model.address.district.districtId,model.address.district.districtName," );
		else if(searchType != null && searchType.equalsIgnoreCase("state"))
			queryStr.append(" model.address.state.stateId,model.address.state.stateName," );
		queryStr.append(" count(model.activityLocationInfoId) , sum(model.attendedCount)  " );
		
		if( searchType != null && searchType.equalsIgnoreCase("town"))
			queryStr.append("  , model.address.localElectionBody.electionType.electionType ");
		else{
			queryStr.append("  ,'' ");
		}
		
		queryStr.append("  from ActivityLocationInfo model where " );
		
		if(type != null){
			if(type.equalsIgnoreCase("yes"))
				queryStr.append(" ( model.conductedDate is not null and  model.ivrStatus ='Y' ) ");  
			else if(type.equalsIgnoreCase("no"))
				queryStr.append(" ( model.conductedDate is null and  model.ivrStatus ='N') ");
			else if(type.equalsIgnoreCase("maybe"))
				queryStr.append(" ( (model.conductedDate is not null and  model.ivrStatus='N')  " +
						"or (model.conductedDate is not  null and  model.ivrStatus is null) ) ");			
		}else{
			if(countType != null && countType.equalsIgnoreCase("planned"))
				queryStr.append(" model.plannedDate is not null " );
			else if(countType != null && countType.equalsIgnoreCase("infocell"))
				queryStr.append(" model.conductedDate is not null " );
			else if(countType != null && countType.equalsIgnoreCase("ivr"))
				queryStr.append(" model.ivrStatus = 'Y' " );
		}
		
		queryStr.append(" and model.activityScope.activityScopeId =:activityScopeId");
		
		if(locationId != null && locationId.longValue() > 0l){
			if(searchType != null && searchType.equalsIgnoreCase("constituency"))
				queryStr.append("  and model.address.district.districtId = :locationId ");
			else if(searchType != null && searchType.equalsIgnoreCase("village"))
				queryStr.append("  and model.address.tehsil.tehsilId = :locationId ");
			else if(searchType != null && searchType.equalsIgnoreCase("ward"))
				queryStr.append("  and model.address.localElectionBody.localElectionBodyId = :locationId ");
			else if( searchType != null && searchType.equalsIgnoreCase("mandal") || searchType != null && searchType.equalsIgnoreCase("town"))
				queryStr.append("  and model.address.constituency.constituencyId = :locationId ");
			else if(searchType != null && searchType.equalsIgnoreCase("onlyvillage"))
				queryStr.append("  and model.address.panchayat.panchayatId = :locationId ");
		}else{
			if(stateId != null && stateId.longValue() == 1l){
				queryStr.append("  and model.address.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && (stateId.longValue() == 2l || stateId.longValue() == 36l)){
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
			queryStr.append(" group by  model.address.localElectionBody.localElectionBodyId order by model.address.constituency.localElectionBody.localElectionBodyId ");
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(" group by  model.address.district.districtId  order by model.address.district.districtName " );
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
		 
		    queryStr.append(" count(distinct model.activityLocationInfoId) " +
				            " from ActivityLocationInfo model where (model.conductedDate is not null or model.ivrStatus='Y') ");
		   
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
		   }else if(stateId != null && (stateId.longValue() == 2l || stateId.longValue() == 36l)){
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
	public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
public List<Object[]> getActivityAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activityIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		     queryStr.append("select");
		     
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  UA.state_id as stateId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.district_id as districtId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.parliament_constituency_id as parliamentConstituencyId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.constituency_id as assemblyId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" UA.tehsil_id as tehsilId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" UA.local_election_body as localElectionBoydId,"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" UA.panchayat_id as panchayatId,"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" UA.ward as wardId,"); 
			 }
		  
		  queryStr.append(" COUNT(DISTINCT ALI.activity_location_info_id) as count ");
		
		  queryStr.append(" FROM activity_location_info ALI," +
		  		          " activity_scope  AS,activity A," +
		  		          " user_address UA "+
		                  " WHERE ALI.address_id = UA.user_address_id " +
		                  " and AS.activity_id = A.activity_id "+
						  " AS.is_deleted = 'N' AND "+
						  " A.is_active = 'Y' ");
			if(activityIds != null && activityIds.size() >0){
				queryStr.append(" AND A.activity_id in(:activityIds)" );	
			}
		    if(stateId != null && stateId.longValue() > 0){
					if(stateId.longValue()==1l){
						//queryStr.append(" and UA.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
						queryStr.append(" and UA.district_id > 10 and UA.state_id = 1 ");
					}else if(stateId.longValue()==36l){
						//queryStr.append(" and UA.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
                        queryStr.append(" and UA.district_id < 11 ");
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
			if(activityIds != null && activityIds.size() >0){
				sqlQuery.setParameterList("activityIds", activityIds);	
			}
		return sqlQuery.list();
	}
public List<Object[]> getConductedCounts(Long locationId,Long activityScopeId, String searchType,Long stateId,String levelType){
	
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
	
	queryStr.append("  from ActivityLocationInfo model where " +
			" model.activityScope.activityScopeId =:activityScopeId" +
			" and (model.conductedDate is not null or model.ivrStatus = 'Y') ");
	
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
		}else if(stateId != null && (stateId.longValue() == 2l || stateId.longValue() == 36l)){
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
		queryStr.append(" group by  model.address.district.districtId  order by model.address.district.districtName " );
	else if(searchType != null && searchType.equalsIgnoreCase("state"))
		queryStr.append(" group by model.address.state.stateId order by model.address.state.stateName " );
	
	
	
	//queryStr.append(" order by model.address.constituency.name  ");
	Query query = getSession().createQuery(queryStr.toString());
	
	if(locationId != null && locationId.longValue() > 0l)
	query.setParameter("locationId",locationId );
	query.setParameter("activityScopeId",activityScopeId );
	
	return query.list();
}

public List<Object[]> getActivityConductedCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId){
	
	StringBuilder queryStr = new StringBuilder();
	
	     queryStr.append("select '' ,");
	     queryStr.append(" count(distinct model.activityLocationInfoId),  ");
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append(" model.address.state.stateId ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" model.address.district.districtId ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" model.address.parliamentConstituency.constituencyId  ");  
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
	  
	  
	
	  queryStr.append(" FROM ActivityLocationInfo model  where ");
		
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
}
