package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityInfoDocumentDAO extends GenericDaoHibernate<ActivityInfoDocument, Long> implements IActivityInfoDocumentDAO{

	public ActivityInfoDocumentDAO() {
		super(ActivityInfoDocument.class);
		
	}
	
	public List<Object[]> getEventDocuments(EventDocumentVO inputVO,Date startDate,Date endDate,
			Long userAccessLevelId,Set<Long> userAccessLevelValues)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select activityDocument.documentName,activityDocument.path,model.day,model.activityInfoDocumentId, " );
		//				4				5				6					7											8  					9
		str.append( " state.stateId, state.stateName,district.districtId, district.districtName, parliamentConstituency.constituencyId, parliamentConstituency.name, " );
		//				10							11					12									13						14		
		str.append(	" constituency.constituencyId,constituency.name, localElectionBody.localElectionBodyId,localElectionBody.name,electionType.electionType, ");
		//				15				16					17				18			19						20
		str.append(	" tehsil.tehsilId, tehsil.tehsilName,ward.constituencyId,ward.name,panchayat.panchayatId,panchayat.panchayatName ");
		str.append("  from ActivityInfoDocument model left join model.activityDocument activityDocument " );
				str.append(" left join model.userAddress userAddress ");
				str.append(" left join userAddress.state state ");
				str.append(" left join userAddress.district district ");
				str.append(" left join userAddress.parliamentConstituency parliamentConstituency ");
				str.append(" left join userAddress.constituency constituency ");
				str.append(" left join userAddress.tehsil tehsil ");
				str.append(" left join userAddress.panchayat panchayat ");
				str.append(" left join userAddress.localElectionBody localElectionBody left join localElectionBody.electionType electionType ");
				str.append(" left join userAddress.ward ward ");
				
			
				str.append(" where activityDocument.activityScopeId = :activityScopeId and model.isDeleted = 'N' " );
		if(inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
				 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
					 str.append("  and state.stateId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
						 str.append(" and district.districtId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						 str.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						 str.append("  and constituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
						 str.append(" and tehsil.tehsilId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
						 str.append(" and localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
						 str.append(" and panchayat.panchayatId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
						 str.append(" and ward.constituencyId in (:userAccessLevelValues) "); 
					 }
			}
			
		}else{
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			str.append(" and state.stateId = :locationValue ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and localElectionBody.localElectionBodyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and ward.constituencyId = :locationValue");
		}
		if(startDate != null)
		{
			str.append(" and date(activityDocument.activityDate) >=:startDate and date(activityDocument.activityDate) <=:endDate");
		}
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityScopeId", inputVO.getActivityId());
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}*/
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelValues != null && userAccessLevelValues.size() > 0l){
				query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			}}else {
			if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
				query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
				else
				{
					//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
					query.setParameter("locationValue",inputVO.getLocationValue());
				}
		}	
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getMaxIndex() > 0)
		{
			query.setFirstResult(inputVO.getStartIndex());
			query.setMaxResults(inputVO.getMaxIndex());
		}
			
		return query.list();
	}
	public Long getEventDocumentsCount(EventDocumentVO inputVO,Date startDate,Date endDate,Long userAccessLevelId,Set<Long> userAccessLevelValues)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct count(model.activityDocument.activityDocumentId)" +
				"  from ActivityInfoDocument model where model.activityDocument.activityScopeId = :activityScopeId and model.isDeleted = 'N'  ");
		if(inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
				 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
					 str.append("  and model.userAddress.state.stateId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.district.districtId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						 str.append("  and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
						 str.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
						 str.append(" and model.userAddress.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
						 str.append(" and model.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
						 str.append(" and model.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
					 }
			}
			
		}else{
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		}
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityScopeId", inputVO.getActivityId());
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}*/
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelValues != null && userAccessLevelValues.size() > 0l){
				query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			}}else {
			if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
				query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
				else
				{
					//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
					query.setParameter("locationValue",inputVO.getLocationValue());
				}
		}	
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getLocations(EventDocumentVO inputVO,Date startDate,Date endDate,String type)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			str.append(" select distinct model.userAddress.district.districtId,model.userAddress.district.districtName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
		{
			str.append(" select distinct model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.MANDAL))
		{
			str.append(" select distinct model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append(" select distinct model.userAddress.localElectionBody.localElectionBodyId, model.userAddress.localElectionBody.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
		{
			str.append(" select  distinct model.userAddress.panchayat.panchayatId, model.userAddress.panchayat.panchayatName ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
		{
			str.append(" select  model.userAddress.ward.constituencyId, model.userAddress.ward.name ");
		}
		str.append(" from ActivityInfoDocument model where model.activityDocument.activityDate is not null ");
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");*/
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		if(inputVO.getActivityId() > 0)
		{
			str.append(" and model.activityDocument.activityScopeId = :activityScopeId");
		}
		Query query = getSession().createQuery(str.toString());
		/*if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());*/
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getActivityId() > 0)
			query.setParameter("activityScopeId",inputVO.getActivityId());
		return query.list();
	}
	
	public List<Object[]> getDayWiseImagesCount(EventDocumentVO inputVO,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select date(model.activityDocument.activityDate)," +
						" count(model.activityDocument.activityDocumentId)" +
						" from ActivityInfoDocument model" +
						" where model.isDeleted = 'N'" +
						" and model.activityDocument.activityDate is not null");
		if(startDate != null)
			sb.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		if(inputVO.getActivityId() > 0)
			sb.append(" and model.activityDocument.activityScopeId = :activityScopeId");
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
			sb.append(" and model.userAddress.state.stateId = :locationValue");
		sb.append(" group by date(model.activityDocument.activityDate)");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationValue",inputVO.getLocationValue());
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getActivityId() > 0)
			query.setParameter("activityScopeId",inputVO.getActivityId());
			
		return query.list();
	}
	
	public List<Object[]> getLocationWiseImageCount(EventDocumentVO inputVO,Date startDate,Date endDate,String type)
	{
		StringBuilder str = new StringBuilder();
		
		if(inputVO.getLocationScope().equalsIgnoreCase("state") && inputVO.getTypeId().longValue() == 9999L)
		{
			str.append(" select distinct model.userAddress.state.stateId,model.userAddress.state.stateName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district") && inputVO.getTypeId().longValue() == 9999L)
		{
			str.append(" select distinct model.userAddress.district.districtId,model.userAddress.district.districtName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("state") && inputVO.getTypeId().longValue() != 9999L)
		{
			str.append(" select distinct model.userAddress.district.districtId,model.userAddress.district.districtName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district") && inputVO.getTypeId().longValue() != 9999L)
		{
			str.append(" select distinct model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.MANDAL))
		{
			str.append(" select distinct model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			str.append(" select distinct model.userAddress.localElectionBody.localElectionBodyId, model.userAddress.localElectionBody.name ");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
		{
			str.append(" select  distinct model.userAddress.panchayat.panchayatId, model.userAddress.panchayat.panchayatName");
		}
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
		{
			str.append(" select  distinct model.userAddress.ward.constituencyId, model.userAddress.ward.name");
		}
		str.append(" ,count(model.activityDocument.activityDocumentId)");
		str.append(" from ActivityInfoDocument model where model.isDeleted = 'N' and model.activityDocument.activityDate is not null ");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		if(inputVO.getActivityId() > 0)
		{
			str.append(" and model.activityDocument.activityScopeId = :activityScopeId");
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("state") && inputVO.getTypeId().longValue() == 9999L )
				str.append(" and model.userAddress.state.stateId = :locationValue group by model.userAddress.state.stateId ");
		else if(inputVO.getLocationScope().equalsIgnoreCase("state") && inputVO.getTypeId().longValue() != 9999L )
			str.append(" and model.userAddress.state.stateId = :locationValue group by model.userAddress.district.districtId ");
		if(inputVO.getLocationScope().equalsIgnoreCase("district") && inputVO.getTypeId().longValue() == 9999L)
			str.append(" and model.userAddress.district.districtId = :locationValue group by model.userAddress.district.districtId ");
		if(inputVO.getLocationScope().equalsIgnoreCase("district") && inputVO.getTypeId().longValue() != 9999L)
			str.append(" and model.userAddress.district.districtId = :locationValue group by model.userAddress.constituency.constituencyId ");
		else if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.MANDAL))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue group by model.userAddress.tehsil.tehsilId ");
		else if(inputVO.getLocationScope().equalsIgnoreCase("constituency") && type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue group by model.userAddress.localElectionBody.localElectionBodyId ");
		else if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue group by model.userAddress.panchayat.panchayatId");
		else if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue group by model.userAddress.ward.constituencyId ");
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getActivityId() > 0)
			query.setParameter("activityScopeId",inputVO.getActivityId());
		return query.list();
	}
	
	public Integer deleteEventUploadFilebyActivityInfoDocId(List<Long> activityInfoDocIdList)
	{
		Query query = getSession().createQuery(" update ActivityInfoDocument model set model.isDeleted = 'Y' where model.activityInfoDocumentId in(:activityInfoDocIdList) ");
		
		query.setParameterList("activityInfoDocIdList", activityInfoDocIdList);
		return query.executeUpdate();
	}
	
	public Integer deleteEventUploadFilebyActivityInfoId(List<Long> activityInfoIdList)
	{
		Query query = getSession().createQuery(" update ActivityInfoDocument model set model.isDeleted = 'Y' where model.activityLocationInfoId in(:activityInfoIdList) ");
		
		query.setParameterList("activityInfoIdList", activityInfoIdList);
		return query.executeUpdate();
	}
	
	public Integer deleteEventUploadFilebyActivityConductedInfoId(List<Long> activityInfoIdList)
	{
		Query query = getSession().createQuery(" update ActivityInfoDocument model set model.isDeleted = 'Y' where model.activityConductedInfoId in(:activityConductedInfoId) ");
		
		query.setParameterList("activityConductedInfoId", activityInfoIdList);
		return query.executeUpdate();
	}
	
	public List<Object[]> getAvailableDates(EventDocumentVO inputVO,Date startDate,Date endDate,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.day,''  ");
		str.append(" from ActivityInfoDocument model where model.activityDocument.activityDate is not null and model.day is not null and model.isDeleted ='N' ");
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");*/
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		if(inputVO.getActivityId() > 0)
		{
			str.append(" and model.activityDocument.activityScopeId = :activityScopeId");
		}
		str.append(" order by date(model.activityDocument.activityDate) ");
		Query query = getSession().createQuery(str.toString());
		/*if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());*/
		
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") || inputVO.getLocationScope().equalsIgnoreCase("village") )
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getActivityId() > 0)
			query.setParameter("activityScopeId",inputVO.getActivityId());
		return query.list();
	}
	
	public List<Object[]> getActivityDocumentsImagesByLevelIdAndLevelValue(Long levelId,Long levelValue,Long day,Long activityScopeId,Date activityDate,Integer startIndex,Integer maxIndex)
	{
		Query query = getSession().createQuery(" select model.activityInfoDocumentId,model.activityDocument.path from ActivityInfoDocument model where model.locationScopeId =:levelId" +
				"  and model.locationValueAddress =:levelValue and model.day =:day and model.activityDocument.activityScopeId =:activityScopeId and date(model.activityDocument.activityDate) =:activityDate and model.isDeleted = 'N' ");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("day", day);
		query.setParameter("activityScopeId", activityScopeId);
		query.setDate("activityDate", activityDate);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	
	public Long getActivityDocumentsImagesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long day,Long activityScopeId,Date activityDate)
	{
		Query query = getSession().createQuery(" select count(model.activityInfoDocumentId) from ActivityInfoDocument model where model.locationScopeId =:levelId" +
				"  and model.locationValueAddress =:levelValue and model.day =:day and model.activityDocument.activityScopeId =:activityScopeId and date(model.activityDocument.activityDate) =:activityDate and model.isDeleted = 'N' ");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("day", day);
		query.setParameter("activityScopeId", activityScopeId);
		query.setDate("activityDate", activityDate);
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getActivityInfoImagesCount(SearchAttributeVO inputVO,Long stateId)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getSearchType().equalsIgnoreCase("state"))
		{
			str.append(" select model.activityLocationInfo.constituency.state.stateId, model.activityLocationInfo.constituency.state.stateName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("district"))
		{
			str.append(" select model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("constituency"))
		{
			str.append(" select model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append(" select T.tehsilId,T.tehsilName, ");
		}
		
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" select LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" select P.panchayatId,P.panchayatName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append("  select C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
		}
		
		//Count
		str.append(" count(model.activityDocument.activityDocumentId)");
		str.append(",model.insertType");
		
		str.append(" from ActivityInfoDocument model ");
		
		if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append("  ,Tehsil T ,Panchayat P ");
		}
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" ,LocalElectionBody LEB, Constituency C  ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" , Panchayat P,Tehsil T ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append(" ,Constituency C ");
		}
		
		str.append(" where model.activityDocument.activityDate is not null ");
		
		if(stateId != null && stateId.longValue() == 36L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
		else if(stateId != null && stateId.longValue() == 1L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
		
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
		{
			str.append(" and model.activityLocationInfo.locationLevel in(:levelIds)");
		}
		
		if(inputVO.getAttributesIdsList() != null && inputVO.getAttributesIdsList() .size() > 0)
		{
			str.append(" and model.activityDocument.activityScopeId in(:attributesIdsList)");
		}
		 if(inputVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" group by model.insertType,model.activityLocationInfo.constituency.district.districtId ");
			}
		if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0){
			if(inputVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				str.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) group by model.insertType,model.activityLocationInfo.constituency.constituencyId ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				str.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by  model.insertType,P.tehsil.tehsilId ");
			}
			else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				str.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by model.insertType,LEB.localElectionBodyId ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				str.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and T.tehsilId in (:locationIdsList) group by model.insertType,model.activityLocationInfo.locationValue");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				str.append(" and model.activityLocationInfo.locationValue = C.constituencyId and LEB.localElectionBodyId in (:locationIdsList) group by model.insertType,model.activityLocationInfo.locationValue");
			}
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getAttributesIdsList() != null && inputVO.getAttributesIdsList() .size() > 0)
			query.setParameterList("attributesIdsList",inputVO.getAttributesIdsList());
		if(!inputVO.getSearchType().equalsIgnoreCase("state"))
			if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0)
				query.setParameterList("locationIdsList",inputVO.getLocationIdsList());
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
			query.setParameterList("levelIds",inputVO.getLocationTypeIdsList());
		
		return query.list();
	}
	
	public List<Object[]> getEventDocumentsByLocationInfo(EventDocumentVO inputVO,Date startDate,Date endDate,
			Long userAccessLevelId,Set<Long> userAccessLevelValues)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select activityDocument.documentName,activityDocument.path,model.day,model.activityInfoDocumentId, " );
		//				4				5				6					7											8  					9
		str.append( " state.stateId, state.stateName,district.districtId, district.districtName, parliamentConstituency.constituencyId, parliamentConstituency.name, " );
		//				10							11					12									13						14		
		str.append(	" constituency.constituencyId,constituency.name, localElectionBody.localElectionBodyId,localElectionBody.name,electionType.electionType, ");
		//				15				16					17				18			19						20
		str.append(	" tehsil.tehsilId, tehsil.tehsilName,ward.constituencyId,ward.name,panchayat.panchayatId,panchayat.panchayatName ");
		str.append("  from ActivityInfoDocument model " +
				" left join model.activityDocument activityDocument " );
				str.append(" left join model.userAddress userAddress ");
				str.append(" left join userAddress.state state ");
				str.append(" left join userAddress.district district ");
				str.append(" left join userAddress.parliamentConstituency parliamentConstituency ");
				str.append(" left join userAddress.constituency constituency ");
				str.append(" left join userAddress.localElectionBody localElectionBody " +
						" left join localElectionBody.electionType electionType ");
				str.append(" left join userAddress.tehsil tehsil ");
				str.append(" left join userAddress.ward ward ");
				str.append(" left join userAddress.panchayat panchayat ");
				
		str.append(" where activityDocument.activityScopeId = :activityDocumentId  and model.isDeleted='N'");
		if(inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
				 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
					 str.append("  and model.userAddress.state.stateId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.district.districtId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						 str.append("  and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
						 str.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
						 str.append(" and model.userAddress.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
						 str.append(" and model.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
						 str.append(" and model.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
					 }
			}
			
		}else{
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		else if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		else if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		else if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		else if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		else if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		}
		if(startDate != null)
		{
			str.append(" and date(activityDocument.activityDate) >=:startDate and date(activityDocument.activityDate) <=:endDate");
		}
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityDocumentId", inputVO.getActivityId());
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}*/
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(inputVO.getMaxIndex() > 0)
		{
			query.setFirstResult(inputVO.getStartIndex());
			query.setMaxResults(inputVO.getMaxIndex());
		}
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0l){
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}}else {
			if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
				query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
				else
				{
					//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
					query.setParameter("locationValue",inputVO.getLocationValue());
				}
		}	
		return query.list();
	}
	public Long getEventDocumentsCountByLocationInbfo(EventDocumentVO inputVO,Date startDate,Date endDate,
			Long userAccessLevelId,Set<Long> userAccessLevelValues)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.activityDocument.activityDocumentId)" +
				"  from ActivityInfoDocument model where model.activityDocument.activityScopeId = :activityDocumentId and model.isDeleted='N' ");
		if(inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
				 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
					 str.append("  and model.userAddress.state.stateId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.district.districtId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						 str.append("  and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
						 str.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
						 str.append(" and model.userAddress.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
						 str.append(" and model.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
						 str.append(" and model.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
					 }
			}
			
		}else{
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		}
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityDocumentId", inputVO.getActivityId());
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}*/
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelValues != null && userAccessLevelValues.size() > 0l){
				query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			}}else {
			if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
				query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
				else
				{
					//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
					query.setParameter("locationValue",inputVO.getLocationValue());
				}
		}	
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return (Long) query.uniqueResult();
	}
	
	
	
	public List<Object[]> getEventsDocumentsCountByLocationInbfo(EventDocumentVO inputVO,Date startDate,Date endDate,
			Long userAccessLevelId,Set<Long> userAccessLevelValues)
	{
		StringBuilder str = new StringBuilder();
		str.append("select date(model.activityDocument.activityDate),model.day,count(model.activityDocument.activityDocumentId), count(distinct model.activityLocationInfoId) , count(distinct model.activityConductedInfoId)" +
				"  from ActivityInfoDocument model where model.activityDocument.activityScopeId = :activityDocumentId and model.isDeleted='N' ");
		if(inputVO.getDay() != null && inputVO.getDay() > 0)
			str.append(" and model.day = :day");
		
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
				 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
					 str.append("  and model.userAddress.state.stateId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.district.districtId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						 str.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						 str.append("  and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
						 str.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
						 str.append(" and model.userAddress.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
						 str.append(" and model.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
						 str.append(" and model.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
					 }
			}
			
		}else{
		if(inputVO.getLocationScope().equalsIgnoreCase("state"))
		{
			if(inputVO.getLocationValue() == 36)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
			
			if(inputVO.getLocationValue() == 1)
			{
				str.append(" and model.userAddress.state.stateId = :locationValue ");
			}
		}
		if(inputVO.getLocationScope().equalsIgnoreCase("district"))
			str.append(" and model.userAddress.district.districtId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("constituency"))
			str.append(" and model.userAddress.constituency.constituencyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.tehsil.tehsilId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("mandal") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.localElectionBody.localElectionBodyId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
			str.append(" and model.userAddress.panchayat.panchayatId = :locationValue");
		if(inputVO.getLocationScope().equalsIgnoreCase("village") && inputVO.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
			str.append(" and model.userAddress.ward.constituencyId = :locationValue");
		}
		if(startDate != null)
		{
			str.append(" and date(model.activityDocument.activityDate) >=:startDate and date(model.activityDocument.activityDate) <=:endDate");
		}
		str.append(" group by date(model.activityDocument.activityDate),model.day  order by model.activityDocument.activityDate desc ");
		
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getDay() != null && inputVO.getDay() > 0)
			query.setParameter("day", inputVO.getDay());
		query.setParameter("activityDocumentId", inputVO.getActivityId());
		/*if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
		query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
		else
		{
			//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
			query.setParameter("locationValue",inputVO.getLocationValue());
		}*/
		if(inputVO.getCallFrom() != null && inputVO.getCallFrom().equalsIgnoreCase("firstClick")){
			if(userAccessLevelValues != null && userAccessLevelValues.size() > 0l){
				query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			}}else {
			if(inputVO.getLocationScope().equalsIgnoreCase("village") || inputVO.getLocationScope().equalsIgnoreCase("mandal"))
				query.setParameter("locationValue", new Long(inputVO.getLocationValue().toString().substring(1)));
				else
				{
					//if(!inputVO.getLocationScope().equalsIgnoreCase("state"))
					query.setParameter("locationValue",inputVO.getLocationValue());
				}
		}	
		if(startDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getDayWiseActivityInfoImagesCount(SearchAttributeVO inputVO,Long stateId)
	{
		StringBuilder str = new StringBuilder();
		if(inputVO.getSearchType().equalsIgnoreCase("state"))
		{
			str.append(" select model.activityLocationInfo.constituency.state.stateId, model.activityLocationInfo.constituency.state.stateName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("district"))
		{
			str.append(" select model.activityLocationInfo.constituency.district.districtId, model.activityLocationInfo.constituency.district.districtName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase("constituency"))
		{
			str.append(" select model.activityLocationInfo.constituency.constituencyId, model.activityLocationInfo.constituency.name,");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append(" select T.tehsilId,T.tehsilName, ");
		}
		
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" select LEB.localElectionBodyId, concat(LEB.name,' ',LEB.electionType.electionType), ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" select P.panchayatId,P.panchayatName, ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append("  select C.constituencyId, concat(C.name,' (',C.localElectionBody.electionType.electionType,')') ,  ");
		}
		
		//Count
		str.append(" count(model.activityDocument.activityDocumentId)");
		str.append(",model.insertType,model.day");
		
		str.append(" from ActivityInfoDocument model ");
		
		if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
			str.append("  ,Tehsil T ,Panchayat P ");
		}
		else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
			str.append(" ,LocalElectionBody LEB, Constituency C  ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
			str.append(" , Panchayat P,Tehsil T ");
		}
		else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
			str.append(" ,Constituency C ");
		}
		
		str.append(" where model.activityDocument.activityDate is not null ");
		
		if(stateId != null && stateId.longValue() == 36L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 1 and 10  ");
		else if(stateId != null && stateId.longValue() == 1L)
			str.append("   and model.activityLocationInfo.constituency.district.districtId between 11 and 23  ");
		
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
		{
			str.append(" and model.activityLocationInfo.locationLevel in(:levelIds)");
		}
		
		if(inputVO.getAttributesIdsList() != null && inputVO.getAttributesIdsList() .size() > 0)
		{
			str.append(" and model.activityLocationInfo.activityScopeId in(:attributesIdsList)");
		}
		 if(inputVO.getSearchType().equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and model.activityLocationInfo.constituency.district.districtId in (:locationIdsList) group by model.insertType,model.activityLocationInfo.constituency.district.districtId,model.day ");
			}
		if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0){
			if(inputVO.getSearchType().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				str.append(" and model.activityLocationInfo.constituency.constituencyId in (:locationIdsList) group by model.insertType,model.activityLocationInfo.constituency.constituencyId,model.day ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.MANDAL)){
				str.append(" and P.tehsil.tehsilId = T.tehsilId and  model.activityLocationInfo.locationValue = P.panchayatId and P.tehsil.tehsilId in (:locationIdsList) group by  model.insertType,P.tehsil.tehsilId,model.day ");
			}
			else if( inputVO.getSearchType().equalsIgnoreCase(IConstants.URBAN)){
				str.append(" and LEB.localElectionBodyId = C.localElectionBody.localElectionBodyId and LEB.localElectionBodyId in (:locationIdsList) group by model.insertType,LEB.localElectionBodyId,model.day ");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.VILLAGE)){
				str.append(" and  model.activityLocationInfo.locationValue = P.panchayatId and model.activityLocationInfo.locationValue in (:locationIdsList) group by model.insertType,model.activityLocationInfo.locationValue,model.day");
			}
			else if(inputVO.getSearchType().equalsIgnoreCase(IConstants.WARD)){
				str.append(" and model.activityLocationInfo.locationValue = C.constituencyId and model.activityLocationInfo.locationValue in (:locationIdsList) group by model.insertType,model.activityLocationInfo.locationValue,model.day");
			}
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVO.getAttributesIdsList() != null && inputVO.getAttributesIdsList() .size() > 0)
			query.setParameterList("attributesIdsList",inputVO.getAttributesIdsList());
		if(!inputVO.getSearchType().equalsIgnoreCase("state"))
			if(inputVO.getLocationIdsList() != null &&inputVO.getLocationIdsList().size() >0)
				query.setParameterList("locationIdsList",inputVO.getLocationIdsList());
		if(inputVO.getLocationTypeIdsList() != null && inputVO.getLocationTypeIdsList().size() > 0)
			query.setParameterList("levelIds",inputVO.getLocationTypeIdsList());
		
		return query.list();
	}
	
	public List<Object[]> getLocationValue(Long activityScopeId)
	{
		Query query = getSession().createQuery(" select AID.activityInfoDocumentId,AID.activityLocationInfo.activityLocationInfoId," +
				" AID.activityLocationInfo.locationValue,AID.locationValueAddress from ActivityInfoDocument AID where AID.isDeleted = 'N' and AID.activityDocument.activityScopeId = :activityScopeId ");
		query.setParameter("activityScopeId",activityScopeId);
		return query.list();
		
	}
/*public List<Object[]> activityInfoDocumentCount(List<Long> scopeIds){
		
		Query query=getSession().createQuery(" " +
				  " select  model.activityDocument.activityScope.activityScopeId,count(model.activityInfoDocumentId) " +
				  " from   ActivityInfoDocument model,UserAddress UA" +
				  " where   model.activityDocument.activityScope.activityScopeId in (:scopeIds) and model.isDeleted ='N' " +
				  " and model.activityAddressId = UA.userAddressId " +
				  " group by model.activityDocument.activityScope.activityScopeId ");
		
		query.setParameterList("scopeIds",scopeIds);
		
		return query.list();
	}*/

public List<Object[]>  getDistrictNamesByScopeId(Long activityScopeId,Long stateId,Date stDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.district.districtId,UA.district.districtName," +
				" count(model.activityDocument.activityDocumentId) " +
				" from ActivityInfoDocument model,UserAddress UA" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
					//" and UA.state.stateId = 1 ");
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && (stateId.longValue() == 2l || stateId.longValue() == 36l)){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		if(stDate != null && endDate != null){
			sb.append(" and model.activityDocument.activityDate between :stDate and :endDate ");
		}
		
		sb.append(" group by UA.district.districtId order by UA.district.orderNo asc");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(stDate != null && endDate != null){
		 query.setDate("stDate", stDate);
		 query.setDate("endDate", endDate);
	}
	return query.list();
}

public List<Object[]>  getDistrictNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long stateId,Date stDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.district.districtId,UA.district.districtName," +
				" count(distinct model.activityLocationInfoId) " +
				" from ActivityInfoDocument model,UserAddress UA" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
					//" and UA.state.stateId = 1 ");
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && (stateId.longValue() == 2l || stateId.longValue() == 36l)){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		if(stDate != null && endDate != null){
			sb.append(" and model.activityDocument.activityDate between :stDate and :endDate ");
		}
		sb.append(" group by UA.district.districtId order by UA.district.districtName asc");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(stDate != null && endDate != null){
		 query.setDate("stDate", stDate);
		 query.setDate("endDate", endDate);
	}
	return query.list();
}

public List<Object[]>  getDistrictNamesConductedInfocoveredLocationsByScopeId(Long activityScopeId,Long stateId,Date stDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.district.districtId,UA.district.districtName," +
				" count(distinct model.activityConductedInfoId) " +
				" from ActivityInfoDocument model,UserAddress UA" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
					//" and UA.state.stateId = 1 ");
		if(stateId != null && stateId.longValue() == 1l){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && (stateId.longValue() == 2l || stateId.longValue() == 36l)){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
		}
		if(stDate != null && endDate != null){
			sb.append(" and model.activityDocument.activityDate between :stDate and :endDate ");
		}
		
		sb.append(" group by UA.district.districtId order by UA.district.districtName asc");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(stDate != null && endDate != null){
		 query.setDate("stDate", stDate);
		 query.setDate("endDate", endDate);
	}
	
	return query.list();
}
public List<Object[]>  getConstituencyNamesByDistrictId(Long activityScopeId,Long districtId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.constituency.constituencyId,UA.constituency.name," +
				" count(distinct model.activityLocationInfoId), count(distinct model.activityConductedInfoId) " +
				" from ActivityInfoDocument model,UserAddress UA" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and UA.district.districtId = :districtId");
		if(startDate != null && endDate != null){
			sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
			}
		sb.append(" group by UA.constituency.constituencyId order by UA.constituency.name asc");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(districtId != null && districtId.longValue() > 0l)
		 query.setParameter("districtId", districtId);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	return query.list();
}

public List<Object[]>  getMandalNamesByConstiencyId(Long activityScopeId,Long constitencyId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.tehsil.tehsilId,UA.tehsil.tehsilName," +
				" count(model.activityDocument.activityDocumentId) " +
				" from ActivityInfoDocument model,UserAddress UA" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
		if(constitencyId != null && constitencyId.longValue() > 0l)
			sb.append(" and UA.constituency.constituencyId = :constitencyId");
		if(startDate != null && endDate != null){
			sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
			}
			
		sb.append(" group by UA.tehsil.tehsilId order by UA.tehsil.tehsilName asc ");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(constitencyId != null && constitencyId.longValue() > 0l)
		 query.setParameter("constitencyId", constitencyId);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	return query.list();
}

public List<Object[]>  getMuncipalityNamesByConstiencyId(Long activityScopeId,Long constitencyId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.localElectionBody.localElectionBodyId,UA.localElectionBody.name," +
				" count(model.activityDocument.activityDocumentId)," +
				" ET.electionType " +
				" from ActivityInfoDocument model,UserAddress UA,ElectionType ET" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId " +
				" and UA.localElectionBody.electionType.electionTypeId = ET.electionTypeId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
		if(constitencyId != null && constitencyId.longValue() > 0l)
			sb.append(" and UA.constituency.constituencyId = :constitencyId");
		if(startDate != null && endDate != null){
			sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
			}
			
			
		sb.append(" group by UA.localElectionBody.localElectionBodyId order by UA.localElectionBody.name asc ");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(constitencyId != null && constitencyId.longValue() > 0l)
		 query.setParameter("constitencyId", constitencyId);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	return query.list();
}

public List<Object[]>  getPanchaytNamesByMandalId(Long activityScopeId,Long mandalId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.panchayat.panchayatId,UA.panchayat.panchayatName," +
				" count(model.activityDocument.activityDocumentId) " +
				" from ActivityInfoDocument model,UserAddress UA" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
		if(mandalId != null && mandalId.longValue() > 0l)
			sb.append(" and UA.tehsil.tehsilId = :mandalId");
		if(startDate != null && endDate != null){
			sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
			}
			
		sb.append(" group by UA.panchayat.panchayatId order by UA.panchayat.panchayatName asc ");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(mandalId != null && mandalId.longValue() > 0l)
		 query.setParameter("mandalId", mandalId);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	return query.list();
}
public List<Object[]>  getWardNamesByMuncipalityId(Long activityScopeId,Long muncipalityId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
		sb.append("select UA.ward.constituencyId,UA.ward.name," +
				" count(model.activityDocument.activityDocumentId) " +
				" from ActivityInfoDocument model,UserAddress UA,Constituency C" +
				" where model.isDeleted ='N'" +
				" and model.activityAddressId = UA.userAddressId " +
				" and UA.ward.constituencyId = C.constituencyId ");
		if(activityScopeId != null && activityScopeId.longValue() > 0l)
			sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
		if(muncipalityId != null && muncipalityId.longValue() > 0l)
			sb.append(" and UA.localElectionBody.localElectionBodyId = :muncipalityId");
		if(startDate != null && endDate != null){
			sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
			}
			
			
		sb.append(" group by UA.ward.constituencyId order by UA.ward.name asc ");
	Query query =  getSession().createQuery(sb.toString());
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
	 query.setParameter("activityScopeId", activityScopeId);
	if(muncipalityId != null && muncipalityId.longValue() > 0l)
		 query.setParameter("muncipalityId", muncipalityId);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	
	return query.list();
}
public List<Object[]> getDocumentsCuntByScopeId(Long activityScopeId,List<Long> villageIdsList,List<Long> wardIdsList){
	StringBuilder sb = new StringBuilder();
	sb.append("select locationInfo.activityLocationInfoId,count(distinct model.activityDocument.activityDocumentId)," +
			" conductedInfo.activityConductedInfoId " +
			" from ActivityInfoDocument model " +
			" left join model.activityLocationInfo locationInfo " +
			" left join model.activityConductedInfo conductedInfo " +
			" where model.activityDocument.activityScope.activityScopeId = :activityScopeId "+
			" and model.isDeleted = 'N' and model.activityDocument.activityScope.activity.isActive = 'Y'" +
			" and model.activityDocument.activityScope.isDeleted = 'N' " );
	
	if(villageIdsList != null && villageIdsList.size() > 0) {
		if(wardIdsList.isEmpty()){
			sb.append(" and model.userAddress.panchayat.panchayatId in (:villageIdsList) ");
		}else{
			sb.append(" and model.userAddress.panchayat.panchayatId in (:villageIdsList) ");
			if(wardIdsList != null && wardIdsList.size() > 0)
				sb.append(" or  model.userAddress.ward.constituencyId in (:wardIdsList) ");
		}
			
	} else if(wardIdsList != null && wardIdsList.size() > 0){
		if(wardIdsList != null && wardIdsList.size() > 0){
			sb.append(" and  model.userAddress.ward.constituencyId in (:wardIdsList) ");
		}else{
			sb.append(" and model.userAddress.ward.constituencyId in (:wardIdsList) ");
		}
	}
	
	sb.append(" group by model.activityLocationInfo.activityLocationInfoId ");
	if(villageIdsList != null && villageIdsList.size() > 0){
		sb.append(", model.userAddress.panchayat.panchayatId ");
	}else if(wardIdsList != null && wardIdsList.size() > 0){
		sb.append(", model.userAddress.ward.constituencyId ");
	}
	
	Query query = getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", activityScopeId);
	if(villageIdsList != null && villageIdsList.size() > 0) {
		query.setParameterList("villageIdsList", villageIdsList);
	}if(wardIdsList != null && wardIdsList.size() > 0){
		query.setParameterList("wardIdsList", wardIdsList);
	} 
	return query.list();
}
public List<Object[]> getDocumentCuntByScopeId(Long activityScopeId,List<Long> districtIds,List<Long> constiIdsList){
	StringBuilder sb = new StringBuilder();
	sb.append("select locationInfo.activityLocationInfoId,count(distinct model.activityDocument.activityDocumentId)," +
			" conductedInfo.activityConductedInfoId " +
			" from ActivityInfoDocument model " +
			" left join model.activityLocationInfo locationInfo " +
			" left join model.activityConductedInfo conductedInfo " +
			" where model.activityDocument.activityScope.activityScopeId = :activityScopeId "+
			" and model.isDeleted = 'N' and model.activityDocument.activityScope.activity.isActive = 'Y'" +
			" and model.activityDocument.activityScope.isDeleted = 'N' " );
	if(districtIds != null && districtIds.size() > 0){
		sb.append(" and model.userAddress.district.districtId in (:districtIds)"); 
	}
	if(constiIdsList != null && constiIdsList.size() > 0){
		sb.append(" and model.userAddress.constituency.constituencyId in (:constiIdsList)"); 
	}
	sb.append(" group by model.activityLocationInfo.activityLocationInfoId ");
	if(districtIds != null && districtIds.size() > 0){
		sb.append(", model.userAddress.district.districtId ");
	}
	else if(constiIdsList != null && constiIdsList.size() > 0){
		sb.append(", model.userAddress.constituency.constituencyId ");
	}
	
	Query query = getSession().createQuery(sb.toString());
		query.setParameter("activityScopeId", activityScopeId);
	if(districtIds != null && districtIds.size() > 0){
		query.setParameterList("districtIds", districtIds);	
	} if(constiIdsList != null && constiIdsList.size() > 0){
		query.setParameterList("constiIdsList", constiIdsList);	
	}
	return query.list();
}

public List<Object[]> setDayWiseImagesDetails(Long locationId){
	StringBuilder str = new StringBuilder();
    str.append("select date(model.activityDocument.activityDate),count(model.activityDocument.activityDocumentId),model.day, count(distinct model.activityLocationInfoId) , count(distinct model.activityConductedInfoId)" +
        "  from ActivityInfoDocument model where model.activityLocationInfoId =:locationId and model.isDeleted='N' ");
    str.append(" group by date(model.activityDocument.activityDate) ");
    
    Query query = getSession().createQuery(str.toString());
    query.setParameter("locationId",locationId);
    return query.list();
}


public List<Object[]> getConstituencyNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long districtId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
	sb.append("select UA.constituency.constituencyId,UA.constituency.name," +
			" count(model.activityDocument.activityDocumentId) " +
			" from ActivityInfoDocument model,UserAddress UA" +
			" where model.isDeleted ='N'" +
			" and model.activityAddressId = UA.userAddressId ");
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
		sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
	if(districtId != null && districtId.longValue() > 0l)
		sb.append(" and model.userAddress.district.districtId = :districtId");
	if(startDate != null && endDate != null){
		sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
		}
		
	sb.append(" group by UA.constituency.constituencyId order by UA.district.orderNo asc");
	Query query =  getSession().createQuery(sb.toString());

		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("districtId", districtId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}

		return query.list();
}


public List<Object[]> getMandalNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long constituencyId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
	sb.append("select UA.tehsil.tehsilId,UA.tehsil.tehsilName," +
			" count(distinct model.activityLocationInfoId), count(distinct model.activityConductedInfoId) " +
			" from ActivityInfoDocument model,UserAddress UA" +
			" where model.isDeleted ='N'" +
			" and model.activityAddressId = UA.userAddressId ");
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
		sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
	if(constituencyId != null && constituencyId.longValue() > 0l)
		sb.append(" and UA.constituency.constituencyId = :constituencyId");
	if(startDate != null && endDate != null){
		sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
		}
		
	sb.append(" group by UA.tehsil.tehsilId order by UA.tehsil.tehsilName asc");
	Query query =  getSession().createQuery(sb.toString());

		query.setParameter("activityScopeId", activityScopeId);
		query.setParameter("constituencyId", constituencyId);
		if(startDate != null && endDate != null){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
		}
return query.list();
}


public List<Object[]> getMuncipalityNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long constituencyId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
	sb.append("select UA.localElectionBody.localElectionBodyId,UA.localElectionBody.name," +
			" count(distinct model.activityLocationInfoId), count(distinct model.activityConductedInfoId) " +
			" from ActivityInfoDocument model,UserAddress UA" +
			" where model.isDeleted ='N'" +
			" and model.activityAddressId = UA.userAddressId ");
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
		sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
	if(constituencyId != null && constituencyId.longValue() > 0l)
		sb.append(" and UA.constituency.constituencyId = :constituencyId");
	if(startDate != null && endDate != null){
		sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
		}
		
	sb.append(" group by UA.localElectionBody.localElectionBodyId order by UA.localElectionBody.name asc");
Query query =  getSession().createQuery(sb.toString());

 query.setParameter("activityScopeId", activityScopeId);
query.setParameter("constituencyId", constituencyId);
if(startDate != null && endDate != null){
	query.setDate("startDate", startDate);
	query.setDate("endDate", endDate);
}

return query.list();
}

public List<Object[]> getPanchaytNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId, Long mandalOrMuncId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
	sb.append("select UA.panchayat.panchayatId,UA.panchayat.panchayatName," +
			" count(distinct model.activityLocationInfoId), count(distinct model.activityConductedInfoId) " +
			" from ActivityInfoDocument model,UserAddress UA" +
			" where model.isDeleted ='N'" +
			" and model.activityAddressId = UA.userAddressId ");
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
		sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
	if(mandalOrMuncId != null && mandalOrMuncId.longValue() > 0l)
		sb.append(" and UA.tehsil.tehsilId = :mandalOrMuncId");
	if(startDate != null && endDate != null){
		sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");
		}
		
	sb.append(" group by UA.panchayat.panchayatId order by UA.panchayat.panchayatName asc");
Query query =  getSession().createQuery(sb.toString());

 query.setParameter("activityScopeId", activityScopeId);
query.setParameter("mandalOrMuncId", mandalOrMuncId);
if(startDate != null && endDate != null){
	query.setDate("startDate", startDate);
	query.setDate("endDate", endDate);
}
return query.list();
}

public List<Object[]> getWardNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId, Long mandalOrMuncId,Date startDate,Date endDate){
	StringBuilder sb = new StringBuilder();
	sb.append("select UA.ward.constituencyId,UA.ward.name," +
			" count(distinct model.activityLocationInfoId), count(distinct model.activityConductedInfoId) " +
			" from ActivityInfoDocument model,UserAddress UA" +
			" where model.isDeleted ='N'" +
			" and model.activityAddressId = UA.userAddressId ");
	if(activityScopeId != null && activityScopeId.longValue() > 0l)
		sb.append(" and model.activityDocument.activityScope.activityScopeId = :activityScopeId");
	if(mandalOrMuncId != null && mandalOrMuncId.longValue() > 0l)
		sb.append(" and UA.localElectionBody.localElectionBodyId = :mandalOrMuncId");
	if(startDate != null && endDate != null)
		sb.append(" and date(model.activityDocument.activityDate) between :startDate and :endDate");

		
		sb.append(" group by UA.ward.constituencyId order by UA.ward.name asc");
	Query query =  getSession().createQuery(sb.toString());

	 query.setParameter("activityScopeId", activityScopeId);
	query.setParameter("mandalOrMuncId", mandalOrMuncId);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	return query.list();
}
		public List<Object[]> getDocumentsCuntForScopeId(Long activityScopeId,List<Long> mandalIdList,List<Long> muncipIdsList){
			StringBuilder sb = new StringBuilder();
			sb.append("select locationInfo.activityLocationInfoId,count(distinct model.activityDocument.activityDocumentId)," +
					" conductedInfo.activityConductedInfoId " +
					" from ActivityInfoDocument model " +
					" left join model.activityLocationInfo locationInfo " +
					" left join model.activityConductedInfo conductedInfo " +
					" where model.activityDocument.activityScope.activityScopeId = :activityScopeId "+
					" and model.isDeleted = 'N' and model.activityDocument.activityScope.activity.isActive = 'Y'" +
					" and model.activityDocument.activityScope.isDeleted = 'N' " );
			
			if(mandalIdList != null && mandalIdList.size() > 0) {
				if(muncipIdsList.isEmpty()){
					sb.append(" and model.userAddress.tehsil.tehsilId in (:mandalIdList) ");
				}else{
					sb.append(" and model.userAddress.tehsil.tehsilId in (:mandalIdList) ");
					if(muncipIdsList != null && muncipIdsList.size() > 0)
						sb.append(" or  model.userAddress.localElectionBody.localElectionBodyId in (:muncipIdsList) ");
				}
					
			} else if(muncipIdsList != null && muncipIdsList.size() > 0){
				if(muncipIdsList != null && muncipIdsList.size() > 0){
					sb.append(" and  model.userAddress.localElectionBody.localElectionBodyId in (:muncipIdsList) ");
				}else{
					sb.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:muncipIdsList) ");
				}
			}
			
			sb.append(" group by model.activityLocationInfo.activityLocationInfoId ");
			if(mandalIdList != null && mandalIdList.size() > 0){
				sb.append(", model.userAddress.tehsil.tehsilId ");
			}else if(muncipIdsList != null && muncipIdsList.size() > 0){
				sb.append(", model.userAddress.localElectionBody.localElectionBodyId ");
			}
			
			Query query = getSession().createQuery(sb.toString());
				query.setParameter("activityScopeId", activityScopeId);
			if(mandalIdList != null && mandalIdList.size() > 0) {
				query.setParameterList("mandalIdList", mandalIdList);
			}if(muncipIdsList != null && muncipIdsList.size() > 0){
				query.setParameterList("muncipIdsList", muncipIdsList);
			} 
			return query.list();
		}
	public List<Object[]> getDocuemntDtlsByLocation(Long activityScopeId,Long activityLocationInfoId) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select " +
		 		" model.activityDocument.activityDocumentId,model.activityDocument.path  " +
		 		" from ActivityInfoDocument model where model.isDeleted='N' " +
		 		" and model.activityLocationInfoId=:activityLocationInfoId " +
		 		" and model.activityDocument.activityScopeId=:activityScopeId");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setParameter("activityLocationInfoId", activityLocationInfoId);
		 query.setParameter("activityScopeId", activityScopeId);
		 return query.list();
	}
}

