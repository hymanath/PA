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
	
	public List<Object[]> getActivityPlannedInfoCellAndIVRCountsByLocation(SearchAttributeVO searchAttributeVO){
				
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
				
				queryStr.append(" model.locationLevel,count(model.activityLocationInfoId) from ActivityLocationInfo model " );
				
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
				
				if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
					queryStr.append(" and model.activityScope.activityScopeId in (:activityScopeIdsList) ");
				
				if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
					queryStr.append(" and ( date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");
				}
				
				if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
				{
					if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
						queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
						queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
						queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
						queryStr.append(" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
						queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.locationValue ");
						queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
						queryStr.append(" and  model.locationValue = P.panchayatId and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
						queryStr.append(" and model.locationValue = C.constituencyId and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
				}	
				else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
				{
					if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
						queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
						queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
						queryStr.append(" and   model.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else{
						queryStr.append(" and model.locationValue = LEB.localElectionBodyId and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
				}
				else{
					
					if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
						queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
						queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
						queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
					}
					else
						queryStr.append(" and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				
				queryStr.append(" group by model.locationLevel order by ");
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
	
	public List<Object[]> getActivityAttributeCountsByLocation(SearchAttributeVO searchAttributeVO){
		
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
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
					queryStr.append(" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.locationValue ");
					queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" and  model.locationValue = P.panchayatId and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" and model.locationValue = C.constituencyId and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
			}	
			else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and   model.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else{
					queryStr.append(" and model.locationValue = LEB.localElectionBodyId and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
			}
			else{
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.constituency.state.stateId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.constituency.constituencyId in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
				}
				else
					queryStr.append(" and model.locationValue in (:locationIdsList) and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:locationTypeIdsList) ");
			}
			
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
}
