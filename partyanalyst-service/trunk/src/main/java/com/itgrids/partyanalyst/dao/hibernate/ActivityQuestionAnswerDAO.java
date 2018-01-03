package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityQuestionAnswerDAO extends GenericDaoHibernate<ActivityQuestionAnswer, Long> implements IActivityQuestionAnswerDAO{

	public ActivityQuestionAnswerDAO() {
		super(ActivityQuestionAnswer.class);
		
	}

	public List<Object[]> getActivityQuestionnairesCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId){
		
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
			
			if(stateId != null && stateId.longValue() == 36L)
				queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
			else if(stateId != null && stateId.longValue() == 1L)
				queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
			
			
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
	
public List<Object[]> getActivityQuestionnairesAttributeCountsByLocation(SearchAttributeVO searchAttributeVO,Long optionId,Long stateId){
		
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
			
			if(stateId != null && stateId.longValue() == 36L)
				queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
			else if(stateId != null && stateId.longValue() == 1L)
				queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
			
			
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


public List<Object[]> getActivityQuestionnairesCountsByDayWise(SearchAttributeVO searchAttributeVO,Long stateId){
	
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
		
		if(stateId != null && stateId.longValue() == 36L)
			queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
		else if(stateId != null && stateId.longValue() == 1L)
			queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
		
		
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

public List<Object[]> getActivityQuestionnairesAttributeCountsByDayWise(SearchAttributeVO searchAttributeVO,Long optionId,Long stateId){
	
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
		
		if(stateId != null && stateId.longValue() == 36L)
			queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
		else if(stateId != null && stateId.longValue() == 1L)
			queryStr.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
		
		
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
public List<Object[]> getActivityQuestionAnswerCountReasonWise(List<Long> questionIdsList) {
	
	  StringBuilder queryStr=new StringBuilder();
	
	     queryStr.append("select model.activityOption.activityOptionId," +
	     		" model.activityOption.option," +
	     		" count(model.activityQuestionAnswerId) " +
	     		" from ActivityQuestionAnswer model where model.activityQuestionnaireId in (:questionIdsList) group by model.activityOptionId");
	     
	     Query query=getSession().createQuery(queryStr.toString());
	     query.setParameterList("questionIdsList", questionIdsList);
	return query.list();
}

	public List<Object[]> getActivityQuestionAnswerCountByQuestionAndLocation(Long questionId,String searchType,Long searchValue,Long activityScopeId){
		
		StringBuilder str = new StringBuilder();
		str.append("select model.activityOption.activityOptionId," +
						" model.activityOption.option," +
						" count(model.activityQuestionAnswerId)" +
						" from ActivityQuestionAnswer model");
		
		if(searchType != null && searchType.equalsIgnoreCase("muncipality")){
			str.append(",Constituency const");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("mandal")){
			str.append(",Panchayat pancha");
		}
		
		str.append(" where model.activityQuestionnaireId = :questionId and model.activityLocationInfo.activityScopeId =:activityScopeId ");
		
		if(searchType != null && searchType.equalsIgnoreCase("village")){
			str.append(" and model.activityLocationInfo.locationValue = :searchValue" +
						" and model.activityLocationInfo.locationLevel = 6");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("ward")){
			str.append(" and model.activityLocationInfo.locationValue = :searchValue" +
						" and model.activityLocationInfo.locationLevel = 8");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("muncipality")){
			str.append(" and const.localElectionBody.localElectionBodyId = :searchValue" +
						" and model.activityLocationInfo.locationLevel = 8" +
						" and model.activityLocationInfo.locationValue = const.constituencyId");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("mandal")){
			str.append(" and pancha.tehsil.tehsilId = :searchValue" +
						" and model.activityLocationInfo.locationLevel = 6" +
						" and model.activityLocationInfo.locationValue = pancha.panchayatId");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("constituency")){
			str.append(" and model.activityLocationInfo.constituency.constituencyId = :searchValue");
		}
		
		str.append(" group by model.activityOptionId");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("questionId", questionId);
		query.setParameter("activityScopeId", activityScopeId);
		if(searchType != null){
			query.setParameter("searchValue", searchValue);
		}
		
		return query.list();
	}
	
	public List<Object[]> getTheLocationWiseData(Long questionId,Long activityScopeId,Long constituencyId) {
		
		Query query = getSession().createQuery("select model.activityOptionId," +
				"model.activityLocationInfo.locationValue " +
				"from ActivityQuestionAnswer model  " +
				"where model.activityQuestionnaire.activityQuestionId =:questionId " +
				"and model.activityQuestionnaire.activityScopeId =:activityScopeId and " +
				" model.activityLocationInfo.constituencyId =:constituencyId ");
		
		query.setParameter("questionId", questionId);
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
public List<Object[]> getOptionsCountByScopId(Long activityScopeId,Long reportType,Long questionId) {
	
	 StringBuilder queryStr=new StringBuilder();
	 queryStr.append("select model.activityOption.activityOptionId, model.activityOption.option, " );
	if(reportType == 1l){
		queryStr.append("model.activityLocationInfo.constituency.district.districtName,model.activityLocationInfo.constituency.district.districtId,   " );
	}else if(reportType == 2l){
		queryStr.append("model.activityLocationInfo.constituency.name,model.activityLocationInfo.constituency.constituencyId,   " );
	}
	queryStr.append("count(distinct model.activityQuestionAnswerId) from ActivityQuestionAnswer model where" +
			" model.activityLocationInfo.activityScopeId =:activityScopeId and model.activityQuestionnaire.activityQuestionId =:questionId and model.isDeleted = 'N' ");
	if(reportType == 1l){
		queryStr.append(" group by model.activityLocationInfo.constituency.district.districtId,model.activityOption.activityOptionId ");
	}else if(reportType == 2l){
		queryStr.append(" group by model.activityLocationInfo.constituency.constituencyId,model.activityOption.activityOptionId ");
	}
	Query query=getSession().createQuery(queryStr.toString());
	
	query.setParameter("questionId", questionId);
	query.setParameter("activityScopeId", activityScopeId);
	return query.list();
}

public List<Object[]> getOptionsCountByScopIdForComments(Long activityScopeId,Long reportType,Long questionId) {
	
	 StringBuilder queryStr=new StringBuilder();
	 queryStr.append("select model.activityOption.activityOptionId, model.activityOption.option, " );
	if(reportType == 1l){
		queryStr.append("model.activityLocationInfo.constituency.district.districtName,model.activityLocationInfo.constituency.district.districtId,   " );
	}else if(reportType == 2l){
		queryStr.append("model.activityLocationInfo.constituency.name,model.activityLocationInfo.constituency.constituencyId,   " );
	}
	queryStr.append("count(model.optionTxt) from ActivityQuestionAnswer model where" +
			" model.activityLocationInfo.activityScopeId =:activityScopeId and model.activityQuestionnaire.activityQuestionId =:questionId and model.isDeleted = 'N' " +
			" and model.optionTxt is not null and model.optionTxt !='' ");
	if(reportType == 1l){
		queryStr.append(" group by model.activityLocationInfo.constituency.district.districtId,model.activityOption.activityOptionId ");
	}else if(reportType == 2l){
		queryStr.append(" group by model.activityLocationInfo.constituency.constituencyId,model.activityOption.activityOptionId ");
	}
	Query query=getSession().createQuery(queryStr.toString());
	
	query.setParameter("questionId", questionId);
	query.setParameter("activityScopeId", activityScopeId);
	return query.list();
}

	public List<Long> getActivityQuestionnaireAnswerIdsList(Long activityScopeId,Long locationValue,Long constituencyId){
		Query query = getSession().createQuery("select distinct model.activityQuestionAnswerId " +
				"from ActivityQuestionAnswer model  " +
				" where model.activityLocationInfo.activityScopeId =:activityScopeId and " +
				" model.activityLocationInfo.locationValue =:locationValue and " +
				" model.activityLocationInfo.constituencyId =:constituencyId  ");
		
		query.setParameter("locationValue", locationValue); 
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}

	public int updateActivityQuestionnaireAnswerIdsList(Long activityScopeId,Long locationValue,Long constituencyId){
		/*Query query = getSession().createQuery("select distinct model.activityQuestionAnswerId " +
				"from ActivityQuestionAnswer model  " +
				" where model.activityLocationInfo.activityScopeId =:activityScopeId and " +
				" model.activityLocationInfo.locationValue =:locationValue and " +
				" model.activityLocationInfo.constituencyId =:constituencyId  ");
		*/
		Query query = getSession().createQuery(" update ActivityQuestionAnswer model set model.isDeleted='Y'   " +
				" where model.activityLocationInfo.activityScopeId =:activityScopeId and " +
				" model.activityLocationInfo.locationValue =:locationValue and " +
				" model.activityLocationInfo.constituencyId =:constituencyId  ");
		
		query.setParameter("locationValue", locationValue); 
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("constituencyId", constituencyId);
		return query.executeUpdate();
	}
	public List<Object[]> getCommentDetails(Long activityScopeId,Long reportType,Long questnId, Long levelId, Long reportTypeId,String locationLevelStr){
				
		 StringBuilder queryStr=new StringBuilder();
		 
		 queryStr.append("select distinct  model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName," +
		 		" model.activityLocationInfo.constituency.constituencyId,  model.activityLocationInfo.constituency.name, ");
		 if(locationLevelStr.equalsIgnoreCase(IConstants.VILLAGE))
			 queryStr.append(" model2.tehsil.tehsilId, model2.tehsil.tehsilName, model1.panchayatId, model1.panchayatName, ");
		 else  if(locationLevelStr.equalsIgnoreCase(IConstants.WARD))
			 queryStr.append(" model2.localBody.localElectionBodyId,model2.localBody.name,model1.constituencyId,model1.name, ");
		 else  if(locationLevelStr.equalsIgnoreCase(IConstants.MANDAL))
			 queryStr.append(" model1.tehsilId,model1.tehsilName, model2.localBody.localElectionBodyId,model2.localBody.name, ");
		 else  if(locationLevelStr.equalsIgnoreCase(IConstants.TOWN) || locationLevelStr.equalsIgnoreCase(IConstants.URBAN))
			 queryStr.append(" model2.localBody.localElectionBodyId, model2.localBody.name,model1.localElectionBodyId,model1.name, ");
		 
		 queryStr.append(" model.optionTxt from ActivityQuestionAnswer model " );
		 
		 if(locationLevelStr.equalsIgnoreCase(IConstants.VILLAGE))
			 queryStr.append(" , Panchayat model1 , Booth model2 ");
		 else  if(locationLevelStr.equalsIgnoreCase(IConstants.WARD))
			 queryStr.append(" , Constituency model1, Booth model2 ");
		 else  if(locationLevelStr.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 queryStr.append(" , Constituency model1 ");
		 else  if(locationLevelStr.equalsIgnoreCase(IConstants.MANDAL))
			 queryStr.append(" ,  Tehsil model1, Booth model2 ");
		 else  if(locationLevelStr.equalsIgnoreCase(IConstants.TOWN) || locationLevelStr.equalsIgnoreCase(IConstants.URBAN))
			 queryStr.append(" , LocalElectionBody model1,  Booth model2 ");
		 
		 queryStr.append(" where model.activityLocationInfo.activityScopeId =:activityScopeId ");
				if(reportType == 1l){
					queryStr.append(" and model.activityLocationInfo.constituency.district.districtId =:reportTypeId  " );
				}else if(reportType == 2l){
					queryStr.append(" and model.activityLocationInfo.constituency.constituencyId =:reportTypeId " );
				}
				
				if(locationLevelStr.equalsIgnoreCase(IConstants.VILLAGE))
					 queryStr.append(" and model.activityLocationInfo.locationValue = model1.panchayatId and model2.panchayat.panchayatId = model1.panchayatId ");
				 else  if(locationLevelStr.equalsIgnoreCase(IConstants.WARD))
					 queryStr.append(" and model.activityLocationInfo.locationValue = model1.constituencyId and model1.localElectionBody.localElectionBodyId = model2.localBody.localElectionBodyId ");
				 else  if(locationLevelStr.equalsIgnoreCase(IConstants.CONSTITUENCY))
					 queryStr.append(" and model.activityLocationInfo.locationValue = model1.constituencyId ");
				 else  if(locationLevelStr.equalsIgnoreCase(IConstants.MANDAL))
					 queryStr.append(" and model.activityLocationInfo.locationValue = model1.tehsilId and model2.tehsil.tehsilId = model1.tehsilId ");
				 else  if(locationLevelStr.equalsIgnoreCase(IConstants.TOWN) || locationLevelStr.equalsIgnoreCase(IConstants.URBAN))
					 queryStr.append(" and model.activityLocationInfo.locationValue = model1.localElectionBodyId and model2.localBody.localElectionBodyId = model1.localElectionBodyId ");
				
				
				queryStr.append("  and model.activityQuestionnaire.activityQuestionId =:questnId and model.isDeleted = 'N' " +
						" and model.activityLocationInfo.locationLevel =:levelId and model.optionTxt is not null");
		Query query=getSession().createQuery(queryStr.toString());	
		
		query.setParameter("levelId", levelId);
		query.setParameter("reportTypeId", reportTypeId);
		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("questnId", questnId);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseResponseDetails(SearchAttributeVO searchVO,List<Long> activityScopeQuestionIdsLsit){
		StringBuilder queryStr = new StringBuilder();
		CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
		if(commonMethodsUtilService.isTextEmpty(searchVO.getSearchType())){
			if(searchVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY))
				queryStr.append(" select   model.activityLocationInfo.constituencyId, ");
			else if(searchVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT))
				queryStr.append("  select  model.activityLocationInfo.constituency.district.districtId, ");
		}
		queryStr.append(" model.activityQuestionnaireId,count(distinct model.activityLocationInfoId)");
		queryStr.append(" from ActivityQuestionAnswer model where model.activityQuestionnaireId in (:activityQuestionnaireIdsList) and " +
				" model.isDeleted ='N' ");
		if(commonMethodsUtilService.isTextEmpty(searchVO.getSearchType())){
			if(searchVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY))
				queryStr.append(" group by model.activityLocationInfo.constituencyId,model.activityQuestionnaireId ");
			else if(searchVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT))
				queryStr.append(" group by model.activityLocationInfo.constituency.district.districtId,model.activityQuestionnaireId ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityQuestionnaireIdsList", activityScopeQuestionIdsLsit);
		return query.list();
		
	}
	
	public List<Object[]> getActivityLocationInfoByScope(SearchAttributeVO searchVO,String optionType){
		//searchVO.setLevelId(2L);
		//searchVO.setId(6L);
		StringBuilder sb = new StringBuilder();
		if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			sb.append("select model.activityQuestionnaireId," +
						" model.activityLocationInfo.constituency.district.districtId," +
						" count(distinct model.activityLocationInfoId)," +
						" model.activityQuestionnaire.activityQuestion.question");
		}
		else if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sb.append("select model.activityQuestionnaireId," +
					" model.activityLocationInfo.constituency.constituencyId," +
					" count(distinct model.activityLocationInfoId)," +
					" model.activityQuestionnaire.activityQuestion.question");
		}
		if(optionType != null && optionType.toString().equalsIgnoreCase("Count Description Box"))
			sb.append(" ,sum(model.count)");
		
		sb.append(" from ActivityQuestionAnswer model" +
					" where model.activityLocationInfo.activityScope.activity.activityId = :activityId");
		if(searchVO.getLevelId().longValue() == 1l)
			sb.append(" and model.activityLocationInfo.locationLevel in (6,8)");
		else if(searchVO.getLevelId().longValue() == 2l)
			sb.append(" and model.activityLocationInfo.locationLevel in (5,7,9)");
		else if(searchVO.getLevelId().longValue() == 3l)
			sb.append(" and model.activityLocationInfo.locationLevel in (11)");
		else if(searchVO.getLevelId().longValue() == 4l)
			sb.append(" and model.activityLocationInfo.locationLevel in (10)");
		else if(searchVO.getLevelId().longValue() == 5l)
			sb.append(" and model.activityLocationInfo.locationLevel in (13)");
		
		if(searchVO.getQuestionnaireIdsList() != null && searchVO.getQuestionnaireIdsList().size() > 0)
			sb.append(" and model.activityQuestionnaireId in (:questionIdsList) ");
		
		if(optionType != null && optionType.toString().equalsIgnoreCase("selectBox"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 1");
		else if(optionType != null && optionType.toString().equalsIgnoreCase("checkBox"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 2");
		else if(optionType != null && optionType.toString().equalsIgnoreCase("Text Description Box"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 3");
		else if(optionType != null && optionType.toString().equalsIgnoreCase("Count Description Box"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 4");
		
		if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
			sb.append(" and model.isDeleted = 'N' and model.activityQuestionnaire.isDeleted ='N'" +
					" and model.activityLocationInfo.activityScope.isDeleted = 'N'" +
					" group by model.activityLocationInfo.constituency.district.districtId,model.activityQuestionnaireId");
		}
		else if(searchVO.getSearchType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sb.append(" and model.isDeleted = 'N' and model.activityQuestionnaire.isDeleted ='N'" +
					" and model.activityLocationInfo.activityScope.isDeleted = 'N'" +
					" group by model.activityLocationInfo.constituency.constituencyId,model.activityQuestionnaireId");
		}
		
		
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("activityId", searchVO.getId());
		if(searchVO.getQuestionnaireIdsList() != null && searchVO.getQuestionnaireIdsList().size() > 0)
			query.setParameterList("questionIdsList", searchVO.getQuestionnaireIdsList());
		
		return query.list();
	}
	
	public List<Object[]> getActivityLocationInfoByScope(Long activityLevel,Long activityId,Long questionId,String optionType){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.activityQuestionnaire.activityQuestionnaireId," +
						" model.activityLocationInfo.constituency.district.districtId," +
						" count(distinct model.activityLocationInfoId)," +
						" model.activityQuestionnaire.activityQuestion.question");
		if(optionType != null && optionType.toString().equalsIgnoreCase("Count Description Box"))
			sb.append(" ,sum(model.count)");
		
		sb.append(" from ActivityQuestionAnswer model" +
					" where model.activityLocationInfo.activityScope.activity.activityId = :activityId");
		if(activityLevel.longValue() == 1l)
			sb.append(" and model.activityLocationInfo.locationLevel in (6,8)");
		else if(activityLevel.longValue() == 2l)
			sb.append(" and model.activityLocationInfo.locationLevel in (5,7,9)");
		else if(activityLevel.longValue() == 3l)
			sb.append(" and model.activityLocationInfo.locationLevel in (11)");
		else if(activityLevel.longValue() == 4l)
			sb.append(" and model.activityLocationInfo.locationLevel in (10)");
		else if(activityLevel.longValue() == 5l)
			sb.append(" and model.activityLocationInfo.locationLevel in (13)");
		
		if(questionId != null && questionId.longValue() > 0l)
			sb.append(" and model.activityQuestionnaire.activityQuestionnaireId = :questionId");
		
		if(optionType != null && optionType.toString().equalsIgnoreCase("selectBox"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 1");
		else if(optionType != null && optionType.toString().equalsIgnoreCase("checkBox"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 2");
		else if(optionType != null && optionType.toString().equalsIgnoreCase("Text Description Box"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 3");
		else if(optionType != null && optionType.toString().equalsIgnoreCase("Count Description Box"))
			sb.append(" and model.activityQuestionnaire.activityOptionType.activityOptionTypeId = 4");
		
		sb.append(" and model.isDeleted = 'N' and model.activityQuestionnaire.isDeleted ='N'" +
						" and model.activityLocationInfo.activityScope.isDeleted = 'N'" +
						" group by model.activityLocationInfo.constituency.district.districtId,model.activityQuestionnaire.activityQuestion.activityQuestionId");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("activityId", activityId);
		if(questionId != null && questionId.longValue() > 0l)
			query.setParameter("questionId", questionId);
		
		return query.list();
	}
public List<Object[]> getActivityQuesAndOptionsByDayWise(Long day){
		
		Query query = getSession().createQuery(" select model.activityQuestionAnswerId," +
				" model.activityQuestionnaireId ," +
				" model.activityQuestionnaire.activityQuestion.question," +
				" model.activityOptionId, " +
				" model.activityOption.option " +
				" from ActivityQuestionAnswer model where model.day = :day and model.isDeleted = 'Y' " );
		
			      query.setParameter("day", day);
			      return query.list();
		     
		    
	}
public List<Object[]> getQuestionsPerc(Long activityId,Long activityScopeId){
	StringBuilder queryStr = new  StringBuilder();
		queryStr.append("SELECT  ");
		queryStr.append("ali.activity_scope_id,aqa.activity_questionnaire_id,aqa.activity_option_id,count(distinct aqa.activity_location_info_id)"); 
		//queryStr.append("aqa.activity_location_info_id ") ;
		queryStr.append("from  ");
		queryStr.append("activity a, ");
		queryStr.append("activity_scope a1, ");
		queryStr.append("activity_location_info ali, ");
		queryStr.append("activity_question_answer aqa ");
		queryStr.append("where  ");
		queryStr.append("a.activity_id = a1.activity_id and ");
		queryStr.append("a.is_active='Y' and "); 
		queryStr.append("a1.is_deleted ='N' and  ");
		queryStr.append("a1.activity_scope_id = ali.activity_scope_id and  ");
		queryStr.append("aqa.activity_location_info_id = ali.activity_location_info_id  and  ");
		queryStr.append("ali.ivr_status = 'Y'  ");
		if(activityId != null && activityId.longValue() > 0l)
			queryStr.append(" and a.activity_id = :activityId ");
		else if(activityScopeId != null && activityScopeId.longValue() > 0l)
			queryStr.append("and a1.activity_scope_id = : activityScopeId");
		queryStr.append("group by ali.activity_scope_id,aqa.activity_questionnaire_id,aqa.activity_option_id ");
		
		Query query = getSession().createSQLQuery(queryStr.toString());
		if(activityId != null && activityId.longValue() > 0l)
		  query.setParameter("activityId", activityId);
		else if(activityScopeId != null && activityScopeId.longValue() > 0l)
			query.setParameter("activityScopeId", activityScopeId);
		
		return query.list();
	
}

 public List<Object[]> getQuestionAnswerDetails(Long activityLocationInfoId){
		Query query = getSession().createQuery(" " +
				" select " +
				" model.activityQuestionnaireId," +
				" model.activityOptionId," +
				" model.optionTxt," +
				" model.count " +
				" from ActivityQuestionAnswer model " +
				" where " +
				" model.isDeleted = 'N' " +
				" and model.activityLocationInfoId=:activityLocationInfoId " +
				" order by model.activityQuestionnaireId,model.activityOptionId ");
	   query.setParameter("activityLocationInfoId", activityLocationInfoId);
	   return query.list();
	}

@Override
public List<Object[]> getCountanswereddetails(Long activityScopeId, Long locationScopeId) {
	//0-qid,1-question,3-optionId,4-optionType,5-optionName 6-optionId,7-count, 8-memcount,9-locationId
	StringBuilder sb = new StringBuilder();
	sb.append("select AQS.activity_question_id as questionId,AQS.question as question, AOT.activity_option_type_id as optionTypeId, AOT.type as optionType," +
			" AO.option as optionname,AO.activity_option_id as optionId,count(DISTINCT ALI.activity_location_info_id) as locationcount ");
	sb.append( " ,sum(AQA.count) as memberCount ");
	if(locationScopeId !=null && locationScopeId > 0l ){
		if(locationScopeId ==3l){
			sb.append(" ,ua.district_id as locationId ");
		}else if(locationScopeId ==4l){
			sb.append(" ,ua.constituency_id as locationId ");
		}else if(locationScopeId ==10l){
			sb.append(" ,parliament_constituency_id as locationId ");
		}
	}
	sb.append(" from activity_question_answer AQA left Join activity_option AO on AO.activity_option_id = AQA.activity_option_id," +
			" activity_location_info ALI,user_address ua,activity_questionnaire AQ,activity_question AQS," +
			" activity_option_type AOT");
	
	sb.append(" WHERE ALI.activity_location_info_id = AQA.activity_location_info_id " +
			" AND ua.user_address_id = ALI.address_id " +
			" AND AQ.activity_questionnaire_id = AQA.activity_questionnaire_id " +
			" AND AQS.activity_question_id = AQ.activity_question_id " +
			" AND AOT.activity_option_type_id = AQ.activity_option_type_id " +
			" AND AQA.is_deleted = 'N'  AND ALI.conducted_date is not null " +
			" AND ALI.updated_status ='UPDATED' ");
	
	if(locationScopeId !=null && locationScopeId > 0l ){
		if (locationScopeId == 3l|| locationScopeId == 4l ) {
			sb.append(" AND ua.district_id >10 ");
		} else {
			sb.append(" AND ua.state_id =1");
		}
	}
	if(activityScopeId != null && activityScopeId >0){
		sb.append(" and AQ.activity_scope_id =:activityScopeId ");
	}
	sb.append(" GROUP BY AQA.activity_questionnaire_id, AQA.activity_option_id ");
	
	if(locationScopeId !=null && locationScopeId > 0l ){
		if(locationScopeId ==3l){
			sb.append(" ,ua.district_id ");
		}else if(locationScopeId ==4l){
			sb.append(" ,ua.constituency_id ");
		}else if(locationScopeId ==10l){
			sb.append(" ,parliament_constituency_id");
		}
	}
	Query query = getSession().createSQLQuery(sb.toString()).addScalar("questionId",Hibernate.LONG)
			.addScalar("question",Hibernate.STRING)
			.addScalar("optionTypeId",Hibernate.LONG)
			.addScalar("optionType",Hibernate.STRING)
			.addScalar("optionname",Hibernate.STRING)
			.addScalar("optionId",Hibernate.LONG)
			.addScalar("locationcount",Hibernate.LONG)
			.addScalar("memberCount",Hibernate.LONG)
			.addScalar("locationId",Hibernate.LONG);
	
	query.setParameter("activityScopeId", activityScopeId);
	return query.list();
}
 public List<Object[]> getDayWiseQuestionAnswerDetails(Long activityLocationInfoId,Date activityDate){
		Query query = getSession().createQuery(" " +
				" select " +
				" model.activityQuestionnaireId," +
				" model.activityOptionId," +
				" model.optionTxt," +
				" model.count " +
				" from ActivityQuestionAnswer model " +
				" where " +
				" model.isDeleted = 'N' and model.activityDaywiseQuestionnaire.isDeleted='true' " +
				" and date(model.activityDaywiseQuestionnaire.activityDate)=:activityDate " +
				" and model.activityLocationInfoId=:activityLocationInfoId " +
				" order by model.activityQuestionnaireId,model.activityOptionId ");
	   query.setParameter("activityLocationInfoId", activityLocationInfoId);
	   query.setParameter("activityDate", activityDate);
	   return query.list();
	}
    public Long updateAnswerDlts(Long activityLocationInfoId,Long activityQuestionnaireId,Long activityOptionId) {
		StringBuilder sb = new StringBuilder();
		 sb.append(" update ActivityQuestionAnswer model set model.isDeleted='Y'  " +
				  "  where model.activityLocationInfoId =:activityLocationInfoId " +
				  "  and model.activityQuestionnaireId =:activityQuestionnaireId " );
				  if (activityOptionId != null && activityOptionId.longValue() > 0) {
					  sb.append(" and model.activityOptionId=:activityOptionId ");
				  }
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("activityLocationInfoId", activityLocationInfoId);
		query.setParameter("activityQuestionnaireId", activityQuestionnaireId);
		if (activityOptionId != null && activityOptionId.longValue() > 0) {
			query.setParameter("activityOptionId", activityOptionId);
		}
		return (long) query.executeUpdate();
    }
    public List<Long> checkIsAnswerIsSubmitted(Long activityLocationInfoId) {
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select  model.activityQuestionAnswerId from ActivityQuestionAnswer " +
		 		   " model where model.isDeleted='N' " +
		 		   " and model.activityLocationInfoId =:activityLocationInfoId ");
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("activityLocationInfoId", activityLocationInfoId);
		 return query.list();
    }
}
