package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityQuestionAnswerDAO extends GenericDaoHibernate<ActivityQuestionAnswer, Long> implements IActivityQuestionAnswerDAO{

	public ActivityQuestionAnswerDAO() {
		super(ActivityQuestionAnswer.class);
		
	}

	public List<Object[]> getActivityQuestionnairesCountsByLocation(SearchAttributeVO searchAttributeVO){
		
		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
				searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
		{
			queryStr.append(" select ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.constituency.state.stateName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
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
			
			queryStr.append(" model.activityQuestionnaireId,sum(model.count) from ActivityQuestionAnswer model " );
			
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
			
			queryStr.append(" where model.activityQuestionnaireId is not null ");
			queryStr.append(" and model.isDeleted ='N' ");
			if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
				queryStr.append(" and model.activityQuestionnaire.activityQuestionnaireId in (:questionnaireIdsList) ");
			}
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				queryStr.append(" and model.activityLocationInfo.activityScope.activityScopeId in (:activityScopeIdsList) ");
			
			/*if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
				queryStr.append(" and ( date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");
			}*/
			
			if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
					queryStr.append(" and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.activityLocationInfo.locationValue ");
					queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" and model.activityLocationInfo.locationValue = C.constituencyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}	
			else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and   model.activityLocationInfo.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else{
					queryStr.append(" and model.activityLocationInfo.locationValue = LEB.localElectionBodyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}
			else{
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else
					queryStr.append(" and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" group by model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" group by model.activityLocationInfo.constituency.district.districtId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" group by  model.activityLocationInfo.constituency.constituencyId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" group by T.tehsilId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" group by LEB.localElectionBodyId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" group by P.panchayatId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" group by C.constituencyId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.activityLocationInfo.constituency.district.districtName  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.activityLocationInfo.constituency.name");
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
				queryStr.append(" model.activityLocationInfo.locationLevel ");
			}
			queryStr.append(" asc ");
			
			query = getSession().createQuery(queryStr.toString());
			
			if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
				query.setParameterList("questionnaireIdsList", searchAttributeVO.getQuestionnaireIdsList());
			}
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
	
public List<Object[]> getActivityQuestionnairesAttributeCountsByLocation(SearchAttributeVO searchAttributeVO,Long optionId){
		
		StringBuilder queryStr = new StringBuilder();
		Query query = null;
		if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
				searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
		{
			queryStr.append(" select ");
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.constituency.state.stateName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
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
			
			queryStr.append(" model.activityQuestionnaireId,count(model.activityQuestionAnswerId) from ActivityQuestionAnswer model " );
			
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
			
			queryStr.append(" where model.activityOptionId =:optionId ");
			
			if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
				queryStr.append(" and model.activityQuestionnaire.activityQuestionnaireId in (:questionnaireIdsList) ");
			}
			if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
				queryStr.append(" and model.activityLocationInfo.activityScope.activityScopeId in (:activityScopeIdsList) ");
			
			/*if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
				queryStr.append(" and ( date(model.plannedDate) >= :startDate and date(model.plannedDate) <= :endDate ) ");
			}*/
			queryStr.append(" and model.isDeleted ='N' ");
			if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
					queryStr.append(" and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.activityLocationInfo.locationValue ");
					queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
					queryStr.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
					queryStr.append(" and model.activityLocationInfo.locationValue = C.constituencyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}	
			else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
			{
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
					queryStr.append(" and   model.activityLocationInfo.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else{
					queryStr.append(" and model.activityLocationInfo.locationValue = LEB.localElectionBodyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
			}
			else{
				
				if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
				}
				else
					queryStr.append(" and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" group by model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" group by model.activityLocationInfo.constituency.district.districtId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" group by  model.activityLocationInfo.constituency.constituencyId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" group by T.tehsilId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
					queryStr.append(" group by  LEB.localElectionBodyId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" group by P.panchayatId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" group by C.constituencyId,model.activityLocationInfo.locationLevel,model.activityQuestionnaireId order by ");
			}
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append("  model.activityLocationInfo.constituency.district.districtName  ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" model.activityLocationInfo.constituency.name");
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
				queryStr.append(" model.activityLocationInfo.locationLevel ");
			}
			queryStr.append(" asc ");
			
			query = getSession().createQuery(queryStr.toString());
			
			if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
				query.setParameterList("questionnaireIdsList", searchAttributeVO.getQuestionnaireIdsList());
			}
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
			query.setParameter("optionId", optionId);
			return query.list();
		}
		else
			return null;
	
	}


public List<Object[]> getActivityQuestionnairesCountsByDayWise(SearchAttributeVO searchAttributeVO){
	
	StringBuilder queryStr = new StringBuilder();
	Query query = null;
	if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
			searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
	{
		queryStr.append(" select ");
		if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
			queryStr.append(" model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.constituency.state.stateName, ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
			queryStr.append("  model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			queryStr.append(" model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
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
		
		queryStr.append("model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),sum(model.count) from ActivityQuestionAnswer model " );
		
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
		
		queryStr.append(" where model.activityQuestionnaireId is not null ");
		
		if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
			queryStr.append(" and model.activityQuestionnaire.activityQuestionnaireId in (:questionnaireIdsList) ");
		}
		if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
			queryStr.append(" and model.activityLocationInfo.activityScope.activityScopeId in (:activityScopeIdsList) ");
		
		if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
			queryStr.append(" and ( date(model.activityLocationInfo.plannedDate) >= :startDate and date(model.activityLocationInfo.plannedDate) <= :endDate ) ");
		}
		
		if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
		{
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
				queryStr.append(" and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.activityLocationInfo.locationValue ");
				queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" and model.activityLocationInfo.locationValue = C.constituencyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
		}	
		else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
		{
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" and   model.activityLocationInfo.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else{
				queryStr.append(" and model.activityLocationInfo.locationValue = LEB.localElectionBodyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
		}
		else{
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else
				queryStr.append(" and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
		}
		
		if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
			queryStr.append(" group by model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
			queryStr.append(" group by model.activityLocationInfo.constituency.district.districtId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			queryStr.append(" group by  model.activityLocationInfo.constituency.constituencyId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			queryStr.append(" group by T.tehsilId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append("  group by  LEB.localElectionBodyId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			queryStr.append(" group by P.panchayatId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			queryStr.append(" group by C.constituencyId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		
		
		queryStr.append(" date(model.activityLocationInfo.plannedDate) asc ");
		
		query = getSession().createQuery(queryStr.toString());
		
		if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
			query.setParameterList("questionnaireIdsList", searchAttributeVO.getQuestionnaireIdsList());
		}
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

public List<Object[]> getActivityQuestionnairesAttributeCountsByDayWise(SearchAttributeVO searchAttributeVO,Long optionId){
	
	StringBuilder queryStr = new StringBuilder();
	Query query = null;
	if((searchAttributeVO.getLocationIdsList() != null && searchAttributeVO.getLocationIdsList().size()>0 ) && 
			searchAttributeVO.getLocationTypeIdsList() != null && searchAttributeVO.getLocationTypeIdsList().size()>0)
	{
		queryStr.append(" select ");
		if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
			queryStr.append(" model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.constituency.state.stateName, ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
			queryStr.append("  model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			queryStr.append(" model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
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
		
		queryStr.append(" model.activityQuestionnaireId,date(model.activityLocationInfo.plannedDate),count(model.activityQuestionAnswerId) from ActivityQuestionAnswer model " );
		
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
		
		queryStr.append(" where model.activityOptionId =:optionId ");
		
		if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
			queryStr.append(" and model.activityQuestionnaire.activityQuestionnaireId in (:questionnaireIdsList) ");
		}
		if(searchAttributeVO.getAttributesIdsList() != null && searchAttributeVO.getAttributesIdsList().size()>0)
			queryStr.append(" and model.activityLocationInfo.activityScope.activityScopeId in (:activityScopeIdsList) ");
		
		if(searchAttributeVO.getStartDate() != null && searchAttributeVO.getEndDate() != null){
			queryStr.append(" and ( date(model.activityLocationInfo.plannedDate) >= :startDate and date(model.activityLocationInfo.plannedDate) <= :endDate ) ");
		}
		
		if(searchAttributeVO.getTypeId().longValue() == 1L)// Village or Ward
		{
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) ");
				queryStr.append(" and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and C.constituencyId = model.activityLocationInfo.locationValue ");
				queryStr.append(" and LEB.localElectionBodyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				queryStr.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				queryStr.append(" and model.activityLocationInfo.locationValue = C.constituencyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
		}	
		else if(searchAttributeVO.getTypeId().longValue() == 2L)// Mandal/Town/Division
		{
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				queryStr.append(" and   model.activityLocationInfo.locationValue = T.tehsilId and T.tehsilId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else{
				queryStr.append(" and model.activityLocationInfo.locationValue = LEB.localElectionBodyId and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
		}
		else{
			
			if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and model.activityLocationInfo.constituency.state.stateId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
			}
			else
				queryStr.append(" and model.activityLocationInfo.locationValue in (:locationIdsList) and model.activityLocationInfo.activityPerformLevel.activityPerformLevelId in (:locationTypeIdsList) ");
		}
		
		if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.STATE)){
			queryStr.append(" group by model.activityLocationInfo.constituency.state.stateId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
			queryStr.append(" group by model.activityLocationInfo.constituency.district.districtId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			queryStr.append(" group by  model.activityLocationInfo.constituency.constituencyId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			queryStr.append(" group by T.tehsilId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if( searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				queryStr.append("  group by  LEB.localElectionBodyId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			queryStr.append(" group by P.panchayatId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		else if(searchAttributeVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			queryStr.append(" group by C.constituencyId,model.activityLocationInfo.locationLevel,date(model.activityLocationInfo.plannedDate),model.activityQuestionnaireId order by ");
		}
		
		queryStr.append(" date(model.activityLocationInfo.plannedDate) asc ");
		
		query = getSession().createQuery(queryStr.toString());
		
		if(searchAttributeVO.getQuestionnaireIdsList() != null && searchAttributeVO.getQuestionnaireIdsList().size() > 0){
			query.setParameterList("questionnaireIdsList", searchAttributeVO.getQuestionnaireIdsList());
		}
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
		query.setParameter("optionId", optionId);
		return query.list();
	}
	else
		return null;

}

}
