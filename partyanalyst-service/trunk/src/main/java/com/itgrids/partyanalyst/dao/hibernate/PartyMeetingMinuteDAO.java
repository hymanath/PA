package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.MomDetailsVO;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingMinuteDAO extends GenericDaoHibernate<PartyMeetingMinute,Long> implements IPartyMeetingMinuteDAO{

	public PartyMeetingMinuteDAO()
	{
		super(PartyMeetingMinute.class);
	}
	
	public List<Object[]> getPartyMeetingsMinutsDetlsByCadreIds(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId,PMI.partyMeeting.meetingName, count(distinct PMI.partyMeetingMinuteId)  from PartyMeetingMinute PMI where " +
				"  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		return query.list();
	}
	
	public List<Object[]> getMinuteDetailsForAMeeting(Long partyMeetingId,String accessType,List<Long> accessValues)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingMinuteId,model.partyMeeting.partyMeetingId,model.minutePoint,insertedBy.userId,insertedBy.firstName," +
				"updatedBy.userId,updatedBy.firstName,model.insertedTime," +
				"model.updatedTime,model.partyMeeting.meetingName,momAtrSourceType.sourceType,model.createdLocationScopeId," +
				" model.createdAddressId,model.assignedLocationScopeId,address.userAddressId,model.statusId,model.partyMeetingMinuteStatus.status " +
				" from PartyMeetingMinute model " +
				" left join model.momAtrSourceType momAtrSourceType left join model.assignedAddress address " +
				" left join model.insertedBy insertedBy left join model.updatedBy updatedBy " +
				" where " +
				"  model.partyMeeting.partyMeetingId=:partyMeetingId and model.isDeleted='N' ");
		
		if(accessType !=null && accessType.equalsIgnoreCase("MP") && accessValues.size()>0){
			queryStr.append(" and  model.partyMeeting.meetingAddress.constituency.constituencyId in (:accessValues) ");
		}else if(accessType !=null && accessType.equalsIgnoreCase("DISTRICT") && accessValues.size()>0){
			queryStr.append(" and  model.partyMeeting.meetingAddress.district.districtId in (:accessValues) ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingId", partyMeetingId);
		
		if(accessType !=null && ( accessType.equalsIgnoreCase("MP") || accessType.equalsIgnoreCase("DISTRICT") ) &&  accessValues.size()>0){
			query.setParameterList("accessValues", accessValues);
		}
		
		return query.list();
	}
	
	public Integer updateMeetingPoint(Long minuteId,String minuteText,Long updatedBy,Date updateTime,Long levelId,Long levelValue,String isActionable,Long statusId,
			UserAddress userAddress,Long isGovtParty){
		
		StringBuilder query = new StringBuilder();
		query.append("update PartyMeetingMinute model set model.minutePoint = :minuteText,model.updatedBy.userId=:updatedBy,model.updatedTime=:updateTime" +
				",model.isActionable =:isActionable,model.locationLevel=:levelId,model.locationValue=:levelValue,model.statusId=:statusId," +
				"model.userAddressId=:userAddressId");
		if(isGovtParty != null && isGovtParty > 0l){
			query.append(",model.momAtrSourceTypeId=:isGovtParty ");
		}
		
		query.append(" where model.partyMeetingMinuteId = :minuteId");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter("minuteText", minuteText);
		queryObject.setParameter("updatedBy", updatedBy);
		queryObject.setParameter("updateTime", updateTime);
		queryObject.setParameter("minuteId", minuteId);
		if(isGovtParty != null && isGovtParty > 0l){
		queryObject.setParameter("isGovtParty", isGovtParty);
		}
		queryObject.setParameter("isActionable", isActionable);
		if(levelId !=null && levelId>0l){
			queryObject.setParameter("levelId", levelId);
		}else{
			queryObject.setParameter("levelId", null);
		}
		
		if(levelValue !=null && levelValue>0l){
			queryObject.setParameter("levelValue", levelValue);
		}else{
			queryObject.setParameter("levelValue", null);
		}
		
		queryObject.setParameter("statusId", statusId);
		if(userAddress !=null){
			queryObject.setParameter("userAddressId", userAddress.getUserAddressId());
		}else{
			queryObject.setParameter("userAddressId", null);
		}
		
		
		return queryObject.executeUpdate();	
	}
	
	public Integer deleteMeetingMinutePoint(Long minuteId,Long updatedBy,Date updateTime){
		StringBuilder query = new StringBuilder();
		query.append("update PartyMeetingMinute model set model.isDeleted = 'Y',model.updatedBy.userId=?,model.updatedTime=? where model.partyMeetingMinuteId = ?");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter(0, updatedBy);
		queryObject.setParameter(1, updateTime);
		queryObject.setParameter(2, minuteId);
		return queryObject.executeUpdate();	
		
	}
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Minutes Of Meeting
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, Count of Minutes
	 */
	public List<Object[]> getMinuteDetailsForMeetings(List<Long> partyMeetingIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeeting.partyMeetingId," +
				" count(model.partyMeetingMinuteId)" +
				" from PartyMeetingMinute model where " +
				" model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted='N'" +
				" group by model.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		
		return query.list();
	}
	
	public List<Long> getMOMHavingMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId from PartyMeetingMinute model " +
				" where model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted = 'N' ");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyMeetingMinuteRetrieveDetails(Long minuteId){
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT model.partyMeetingMinuteId,model.minutePoint,model.isActionable,model.statusId," +
				" model.userAddressId,model.partyMeetingId,model.locationLevel,model.momAtrSourceTypeId,model.momPriorityId,model.assignedAddressId " +
				" FROM PartyMeetingMinute model " +
				" WHERE model.isDeleted = 'N'" +
				" and model.partyMeetingMinuteId =:minuteId ");
		
		Query query = getSession().createQuery(str.toString());		
		query.setParameter("minuteId", minuteId);
		
		return query.list();
		
	} 
	
	public List<Object[]> getMinuteDetailsForMeetingsList(List<Long> partymeetingIdsList,String accessType,List<Long> accessValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingMinuteId,model.partyMeeting.partyMeetingId,model.minutePoint,insertedBy.userId,insertedBy.firstName," +
				"updatedBy.userId,updatedBy.firstName,model.insertedTime," +
				"model.updatedTime,model.partyMeeting.meetingName,momAtrSourceType.sourceType " +
				" from PartyMeetingMinute model left join model.insertedBy insertedBy left join model.updatedBy updatedBy " +
				" left join model.momAtrSourceType momAtrSourceType " +
				" where " +
				"  model.partyMeeting.partyMeetingId in (:partymeetingIdsList) and model.isDeleted='N' ");
		
		if(accessType !=null && accessType.equalsIgnoreCase("MP") && accessValues.size()>0){
			queryStr.append(" and  model.partyMeeting.meetingAddress.constituency.constituencyId in (:accessValues) ");
		}else if(accessType !=null && accessType.equalsIgnoreCase("DISTRICT") && accessValues.size()>0){
			queryStr.append(" and  model.partyMeeting.meetingAddress.district.districtId in (:accessValues) ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partymeetingIdsList", partymeetingIdsList);
		
		if(accessType !=null && ( accessType.equalsIgnoreCase("MP") || accessType.equalsIgnoreCase("DISTRICT") ) &&  accessValues.size()>0){
			query.setParameterList("accessValues", accessValues);
		}
		
		return query.list();
	}
public List<Object[]> getPartyMeetingMomPointsByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type,Long partyMeetingId){
    	
        StringBuilder queryStr = new StringBuilder();
	  	    queryStr.append(" select " +
	  	    				" model.partyMeeting.partyMeetingId");
	  	    if (type.equalsIgnoreCase("summary")) {
	  	    	 queryStr.append(",count(distinct model.partyMeetingMinuteId)");
	  	    } else if (type.equalsIgnoreCase("details")) {
	  	    	queryStr.append(",model.partyMeetingMinuteId,model.minutePoint ");
	  	    }
	  	    queryStr.append(" from PartyMeetingMinute model " +
	  	    				" where " +
	  	    				" model.partyMeeting.isActive='Y'  and model.isDeleted='N'  ");
  	
	  	 if(monthId != null && monthId > 0){
			  queryStr.append(" and month(model.partyMeeting.startDate)=:monthId ");	 
		 }
  	 	 if(yearId != null && yearId > 0){
  	 		queryStr.append(" and year(model.partyMeeting.startDate)=:yearId ");		 
		 }
  	 	  if (partyMeetingId != null && partyMeetingId > 0) {
  	 		 queryStr.append(" and model.partyMeeting.partyMeetingId=:partyMeetingId"); 
  	 	  }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.locationAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.locationAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.locationAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.locationAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
		    queryStr.append(" and model.locationAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
			queryStr.append(" and model.locationAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
			queryStr.append(" and model.locationAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
			queryStr.append(" and model.locationAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }  
		 //getting meeting levelId
		 List<Long> partyMeetinLevelIds = getPartyMeetingLevelByRegionScope(userAccessLevelId);
		 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
			 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
		 }
		 if (type.equalsIgnoreCase("summary")) {
  	    	 queryStr.append(" group by model.partyMeeting.partyMeetingId ");
  	     }
		 Query query = getSession().createQuery(queryStr.toString());
		
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(monthId != null && monthId > 0){
			 query.setParameter("monthId", monthId);
		 }
  	 	 if(yearId != null && yearId > 0){
  	 		query.setParameter("yearId", yearId);		 
		 }
		 if (partyMeetingId != null && partyMeetingId > 0) {
			 query.setParameter("partyMeetingId", partyMeetingId);
  	 	 }
		 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
  	 		 query.setParameterList("partyMeetinLevelIds", partyMeetinLevelIds);
		 }
		
      return query.list(); 	
  }
public List<Object[]> getPartyMeetingMomDtls(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type){
	
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select ");
	    if (type.equalsIgnoreCase("momPriorityWise")) {
	    	 queryStr.append(" model.momPriority.momPriorityId,model.momPriority.priority ");
	    } else if (type.equalsIgnoreCase("statusWise")) {
	    	queryStr.append(" model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId,model.partyMeetingMinuteStatus.status ");
	    }
		queryStr.append(",count(distinct model.partyMeetingMinuteId)");
	    queryStr.append(" from PartyMeetingMinute model " +
	    				" where " +
	    				" model.partyMeeting.isActive='Y'  and model.isDeleted='N'  ");
	
 	 if(monthId != null && monthId > 0){
		  queryStr.append(" and month(model.insertedTime)=:monthId ");	 
	 }
	 if(yearId != null && yearId > 0){
	 	queryStr.append(" and year(model.insertedTime)=:yearId ");		 
	 }
 	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	   queryStr.append(" and model.locationAddress.state.stateId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	   queryStr.append(" and model.locationAddress.district.districtId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
        queryStr.append(" and model.locationAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
        queryStr.append(" and model.locationAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
	    queryStr.append(" and model.locationAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
		queryStr.append(" and model.locationAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
		queryStr.append(" and model.locationAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
		queryStr.append(" and model.locationAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	 }  
 	 //getting meeting levelId
	 List<Long> partyMeetinLevelIds = getPartyMeetingLevelByRegionScope(userAccessLevelId);
	 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
		 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
	 }
	 if (type.equalsIgnoreCase("momPriorityWise")) {
    	 queryStr.append(" group by model.momPriority.momPriorityId ");
    } else if (type.equalsIgnoreCase("statusWise")) {
    	queryStr.append(" group by model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId  ");
    }
	 Query query = getSession().createQuery(queryStr.toString());
	
	 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	 }
	 if(monthId != null && monthId > 0){
		 query.setParameter("monthId", monthId);
	 }
	 if(yearId != null && yearId > 0){
	 		query.setParameter("yearId", yearId);		 
	 }
	 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
	 		 query.setParameterList("partyMeetinLevelIds", partyMeetinLevelIds);
	 }
  return query.list(); 	
}
public Long getMomCreatedByYourLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type){
	
    StringBuilder queryStr = new StringBuilder();
    queryStr.append(" select ");
    queryStr.append(" count(distinct model.partyMeetingMinuteId)");
    queryStr.append(" from PartyMeetingMinute model " +
    				" where " +
    				" model.partyMeeting.isActive='Y' and model.isDeleted='N' ");

   if(monthId != null && monthId > 0){
		  queryStr.append(" and month(model.insertedTime)=:monthId ");	 
   }
   if(yearId != null && yearId > 0){
	 	queryStr.append(" and year(model.insertedTime)=:yearId ");		 
   }
  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
   queryStr.append(" and model.createdAddress.state.stateId in (:userAccessLevelValues)");  
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
   queryStr.append(" and model.createdAddress.district.districtId in (:userAccessLevelValues)");  
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
    queryStr.append(" and model.createdAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
    queryStr.append(" and model.createdAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
    queryStr.append(" and model.createdAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
	queryStr.append(" and model.createdAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
	queryStr.append(" and model.createdAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
	queryStr.append(" and model.createdAddress.ward.constituencyId in (:userAccessLevelValues)"); 
  }  
  //getting meeting levelId 
	List<Long> partyMeetinLevelIds = getPartyMeetingLevelByRegionScope(userAccessLevelId);
	if(type.equalsIgnoreCase("atYourLocationOnly"))
		partyMeetinLevelIds.clear();// we are considering  below locatioins and own location MOMs (except assignto others MOMs)
	
	if(type == null || type.trim().isEmpty()){
		partyMeetinLevelIds.clear();
		partyMeetinLevelIds.add(userAccessLevelId);
		if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
			 queryStr.append(" and model.createdLocationScopeId in (:partyMeetinLevelIds)");
		}
	}else{
		if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
			 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
		}
	}
	
  if (type.equalsIgnoreCase("atYourLocationOnly")) {
	 queryStr.append(" and model.assignedAddressId is null ");//(except assignto others MOMs)
  } else  if (type.equalsIgnoreCase("assignedToOther")) {
	 queryStr.append(" and model.assignedAddressId is not null ");
  }
 
 Query query = getSession().createQuery(queryStr.toString());

 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
 }
 if(monthId != null && monthId > 0){
	 query.setParameter("monthId", monthId);
 }
 if(yearId != null && yearId > 0){
 		query.setParameter("yearId", yearId);		 
 }
 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
	query.setParameterList("partyMeetinLevelIds", partyMeetinLevelIds);
 }
return (Long)query.uniqueResult(); 	
}
public Long getMomAssignedToYourLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId){
	
   StringBuilder queryStr = new StringBuilder();
   queryStr.append(" select ");
   queryStr.append(" count(distinct model.partyMeetingMinuteId)");
   queryStr.append(" from PartyMeetingMinute model " +
    				" where " +
    				" model.partyMeeting.isActive='Y'  and model.isDeleted='N'  ");

  if(monthId != null && monthId > 0){
		  queryStr.append(" and month(model.insertedTime)=:monthId ");	 
  }
  if(yearId != null && yearId > 0){
	 	queryStr.append(" and year(model.insertedTime)=:yearId ");		 
  }
  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
   queryStr.append(" and model.assignedAddress.state.stateId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
   queryStr.append(" and model.assignedAddress.district.districtId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
    queryStr.append(" and model.assignedAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
    queryStr.append(" and model.assignedAddress.constituency.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
    queryStr.append(" and model.assignedAddress.tehsil.tehsilId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
	queryStr.append(" and model.assignedAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId "); 
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
	queryStr.append(" and model.assignedAddress.panchayat.panchayatId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId "); 
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
	queryStr.append(" and model.assignedAddress.ward.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId "); 
 }  
	//getting meeting levelId , in mom point view no need to consider meeting level. -- sathosh , srishialam 
	List<Long> partyMeetinLevelIds = null;//getPartyMeetingLevelByRegionScope(userAccessLevelId);
	if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
		 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
	}
 Query query = getSession().createQuery(queryStr.toString());

 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
 }
 if(monthId != null && monthId > 0){
	 query.setParameter("monthId", monthId);
 }
 if(yearId != null && yearId > 0){
 		query.setParameter("yearId", yearId);		 
 }
 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
	query.setParameterList("partyMeetinLevelIds", partyMeetinLevelIds);
 }
 if(userAccessLevelId != null && userAccessLevelId.longValue()>0l){
	 query.setParameter("userAccessLevelId", userAccessLevelId);
 }
return (Long)query.uniqueResult();	
}
public List<Object[]> getTotalMomDetailsByLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,Set<Long> momIdSet){
	
    StringBuilder queryStr = new StringBuilder();
    queryStr.append(" select " +
    		" model.partyMeeting.partyMeetingId," +
    		" model.partyMeeting.meetingName," +
    		" model.momPriority.priority," +
    		" model.partyMeetingMinuteStatus.status," +
    		" date(model.insertedTime)," +
    		" model.minutePoint," +
    		" model.partyMeetingMinuteId," +
    		" model.createdLocationScopeId," +
    		" model.createdLocationValue," +
    		" model.assignedLocationScopeId," +
    		" model.assignedLocationValue,model.createdAddress.userAddressId ");
    queryStr.append(" from PartyMeetingMinute model " +
    				" where " +
    				" model.partyMeeting.isActive='Y'  and model.isDeleted='N' ");

  if(monthId != null && monthId > 0){
	queryStr.append(" and month(model.insertedTime)=:monthId ");	 
  }
  if(yearId != null && yearId > 0){
	queryStr.append(" and year(model.insertedTime)=:yearId ");		 
  }
 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
   queryStr.append(" and model.locationAddress.state.stateId in (:userAccessLevelValues)");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
   queryStr.append(" and model.locationAddress.district.districtId in (:userAccessLevelValues)");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
    queryStr.append(" and model.locationAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
    queryStr.append(" and model.locationAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
    queryStr.append(" and model.locationAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
	queryStr.append(" and model.locationAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
	queryStr.append(" and model.locationAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
	queryStr.append(" and model.locationAddress.ward.constituencyId in (:userAccessLevelValues)"); 
 }  
 //getting meeting levelId 
 List<Long> partyMeetinLevelIds = getPartyMeetingLevelByRegionScope(userAccessLevelId);
 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
	 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
 }
 if (momIdSet != null && momIdSet.size() > 0) {
	 queryStr.append(" and model.partyMeetingMinuteId in(:momIds)");
 }
 Query query = getSession().createQuery(queryStr.toString());

 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
 }
 if(monthId != null && monthId > 0){
	 query.setParameter("monthId", monthId);
 }
 if(yearId != null && yearId > 0){
 		query.setParameter("yearId", yearId);		 
 }
 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
		query.setParameterList("partyMeetinLevelIds", partyMeetinLevelIds);
 }
 if (momIdSet != null && momIdSet.size() > 0) {
	 query.setParameterList("momIds", momIdSet);
 }
return query.list(); 	
}
public List<Object[]> getMomCreationLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId){
		
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select " +
	    		" model.partyMeetingMinuteId," +//0
	    		" state.stateName," +//1
	    		" district.districtName," +//2
	    		" parliamentConstituency.name," +//3
	    		" constituency.name," +//4
	    		" tehsil.tehsilName," +//5
	    		" panc.panchayatName," +//6
	    		" localElectionBody.name," +//7
	    		" ward.name ");//8
	    
	  queryStr.append(" from PartyMeetingMinute model ");
	  queryStr.append(" left join model.createdAddress createdAddress ");
	  queryStr.append(" left join createdAddress.state state ");
	  queryStr.append(" left join createdAddress.district district ");
	  queryStr.append(" left join createdAddress.parliamentConstituency parliamentConstituency ");
	  queryStr.append(" left join createdAddress.constituency constituency ");
	  queryStr.append(" left join createdAddress.tehsil tehsil ");
	  queryStr.append(" left join createdAddress.panchayat panc ");
	  queryStr.append(" left join createdAddress.localElectionBody localElectionBody ");
	  queryStr.append(" left join createdAddress.ward ward " +
	  		          " where " +
	  		          " model.partyMeeting.isActive='Y'  and model.isDeleted='N'  ");
	  
	  if(monthId != null && monthId > 0){
			queryStr.append(" and month(model.insertedTime)=:monthId ");	 
	  }
	  if(yearId != null && yearId > 0){
			queryStr.append(" and year(model.insertedTime)=:yearId ");		 
	  }
	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	   queryStr.append(" and state.stateId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	   queryStr.append(" and district.districtId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
	    queryStr.append(" and parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
	    queryStr.append(" and constituency.constituencyId in (:userAccessLevelValues) ");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
	    queryStr.append(" and tehsil.tehsilId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
		queryStr.append(" and localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
		queryStr.append(" and panc.panchayatId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
		queryStr.append(" and ward.constituencyId in (:userAccessLevelValues)"); 
	 }  
	 //getting meeting levelId 
	 List<Long> partyMeetinLevelIds = getPartyMeetingLevelByRegionScope(userAccessLevelId);
	 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
		 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
	 }
	 Query query = getSession().createQuery(queryStr.toString());
	
	 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	 }
	 if(monthId != null && monthId > 0){
		 query.setParameter("monthId", monthId);
	 }
	 if(yearId != null && yearId > 0){
	 		query.setParameter("yearId", yearId);		 
	 }
	 if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
	 		query.setParameterList("partyMeetinLevelIds", partyMeetinLevelIds);
	 }
	return query.list(); 	
	}
public List<Object[]> getMomAssignedLocation(Set<Long> momIdSet,Integer monthId,Integer yearId){
		
	  StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select " +
	    		" model.partyMeetingMinuteId," +
	    		" state.stateName," +
	    		" district.districtName," +
	    		" parliamentConstituency.name," +
	    		" constituency.name," +
	    		" tehsil.tehsilName," +
	    		" panc.panchayatName," +
	    		" localElectionBody.name," +
	    		" ward.name ");
	    
	  queryStr.append(" from PartyMeetingMinute model ");
	  queryStr.append(" left join model.assignedAddress assignedAddress ");
	  queryStr.append(" left join assignedAddress.state state ");
	  queryStr.append(" left join assignedAddress.district district ");
	  queryStr.append(" left join assignedAddress.parliamentConstituency parliamentConstituency ");
	  queryStr.append(" left join assignedAddress.constituency constituency ");
	  queryStr.append(" left join assignedAddress.tehsil tehsil ");
	  queryStr.append(" left join assignedAddress.panchayat panc ");
	  queryStr.append(" left join assignedAddress.localElectionBody localElectionBody ");
	  queryStr.append(" left join assignedAddress.ward ward " +
	  		          " where " +
	  		          " model.partyMeeting.isActive='Y' ");
	  
	/* if(fromDate!= null && toDate!=null){
	     queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
	 }*/
	  if(monthId != null && monthId > 0){
			queryStr.append(" and month(model.insertedTime)=:monthId ");	 
	  }
	  if(yearId != null && yearId > 0){
			queryStr.append(" and year(model.insertedTime)=:yearId ");		 
	  }
	 if (momIdSet != null && momIdSet.size() > 0) {
		 queryStr.append(" and model.partyMeetingMinuteId in(:momIds)");
	 }
	 Query query = getSession().createQuery(queryStr.toString());
	
	 if (momIdSet != null && momIdSet.size() > 0) {
		 query.setParameterList("momIds", momIdSet);
	 }
	/* if(fromDate!= null && toDate!=null){
	   query.setDate("fromDate", fromDate);
	   query.setDate("toDate", toDate);
	 }*/
	 if(monthId != null && monthId > 0){
		 query.setParameter("monthId", monthId);
	 }
	 if(yearId != null && yearId > 0){
	 		query.setParameter("yearId", yearId);		 
	 }
	return query.list(); 	
	}

public List<Object[]> getMomDetailsByType(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type,String momType,Set<Long> momIdSet){
	
    StringBuilder queryStr = new StringBuilder();
    queryStr.append(" select " +
    		" model.partyMeeting.partyMeetingId," +
    		" model.partyMeeting.meetingName," +
    		" model.momPriority.priority," +
    		" model.partyMeetingMinuteStatus.status," +
    		" date(model.insertedTime)," +
    		" model.minutePoint," +
    		" model.partyMeetingMinuteId," +
    		" model.createdLocationScopeId," +
    		" model.createdLocationValue," +
    		" model.assignedLocationScopeId," +
    		" model.assignedLocationValue,model.createdAddress.userAddressId ");
    queryStr.append(" from PartyMeetingMinute model " +
    				" where " +
    				" model.partyMeeting.isActive='Y'  and model.isDeleted='N'  ");

   if(monthId != null && monthId > 0){
		  queryStr.append(" and month(model.insertedTime)=:monthId ");	 
   }
   if(yearId != null && yearId > 0){
	 	queryStr.append(" and year(model.insertedTime)=:yearId ");		 
   }
   
    String dynamicQuery = getDynamicQuery(userAccessLevelId, momType);
    if (dynamicQuery != null && dynamicQuery.trim().length() > 0) {
    	queryStr.append(dynamicQuery);
    }
    
   if (type.equalsIgnoreCase("atYourLocationOnly")) {
	 queryStr.append(" and model.assignedAddressId is null ");
   } else  if (type.equalsIgnoreCase("assignedToOther")) {
	 queryStr.append(" and model.assignedAddressId is not null ");
   }
   if (momIdSet != null && momIdSet.size() > 0) {
		 queryStr.append(" and model.partyMeetingMinuteId in(:momIds)");
	 }
 Query query = getSession().createQuery(queryStr.toString());

 if (dynamicQuery != null && dynamicQuery.trim().length() > 0) {
	   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
 }
 if(monthId != null && monthId > 0){
	 query.setParameter("monthId", monthId);
 }
 if(yearId != null && yearId > 0){
 		query.setParameter("yearId", yearId);		 
 }
 if (momIdSet != null && momIdSet.size() > 0) {
	 query.setParameterList("momIds", momIdSet);
 }
 if(userAccessLevelId != null && userAccessLevelId.longValue()>0l){
	 query.setParameter("userAccessLevelId", userAccessLevelId);
 }
 return query.list();	
 }
  public String getDynamicQuery(Long userAccessLevelId,String type) {
	   StringBuilder queryStr = new StringBuilder();
	   if (type.equalsIgnoreCase("createdMom")) {
		     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.createdAddress.state.stateId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.createdAddress.district.districtId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
			    queryStr.append(" and model.createdAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
			    queryStr.append(" and model.createdAddress.constituency.constituencyId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
			    queryStr.append(" and model.createdAddress.tehsil.tehsilId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
				queryStr.append(" and model.createdAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId "); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
				queryStr.append(" and model.createdAddress.panchayat.panchayatId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId "); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
				queryStr.append(" and model.createdAddress.ward.constituencyId in (:userAccessLevelValues) and model.createdLocationScopeId =:userAccessLevelId "); 
			  }   
	   } else if (type.equalsIgnoreCase("assignedMom")) {
		     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.assignedAddress.state.stateId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.assignedAddress.district.districtId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
			    queryStr.append(" and model.assignedAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
			    queryStr.append(" and model.assignedAddress.constituency.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
			    queryStr.append(" and model.assignedAddress.tehsil.tehsilId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
				queryStr.append(" and model.assignedAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId "); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
				queryStr.append(" and model.assignedAddress.panchayat.panchayatId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId "); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
				queryStr.append(" and model.assignedAddress.ward.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId "); 
			  }  
	   }
	  
	   return queryStr.toString();
  }
	  public List<Long> getPartyMeetingLevelByRegionScope(Long userAccessLevelId) {
		   List<Long> partyMeetingLevelIds = new ArrayList<Long>();
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    	partyMeetingLevelIds.add(1l);  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				 partyMeetingLevelIds.add(2l); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
				 partyMeetingLevelIds.add(9l);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
				 partyMeetingLevelIds.add(3l); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
				 partyMeetingLevelIds.add(4l);   
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
				 partyMeetingLevelIds.add(5l);
				 partyMeetingLevelIds.add(6l);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
				 partyMeetingLevelIds.add(7l);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
				 partyMeetingLevelIds.add(8l);
			 }  
		    return partyMeetingLevelIds;
	  }

	public List<Object[]> getPartyMeetingMomAssignDtls(Long userAccessLevelId,
			List<Long> userAccessLevelValues, Integer monthId, Integer yearId,
			String type) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select ");
		if (type.equalsIgnoreCase("momPriorityWise")) {
			queryStr.append(" model.momPriority.momPriorityId,model.momPriority.priority ");
		} else if (type.equalsIgnoreCase("statusWise")) {
			queryStr.append(" model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId,model.partyMeetingMinuteStatus.status ");
		}
		queryStr.append(",count(distinct model.partyMeetingMinuteId)");
		queryStr.append(" from PartyMeetingMinute model " + " where "
				+ " model.partyMeeting.isActive='Y' and model.isDeleted='N' ");

		if (monthId != null && monthId > 0) {
			queryStr.append(" and month(model.insertedTime)=:monthId ");
		}
		if (yearId != null && yearId > 0) {
			queryStr.append(" and year(model.insertedTime)=:yearId ");
		}
		if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.STATE_LEVEl_ACCESS_ID) {
			queryStr.append(" and model.assignedAddress.state.stateId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		} else if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID) {
			queryStr.append(" and model.assignedAddress.district.districtId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		} else if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID) {
			queryStr.append(" and model.assignedAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		} else if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID) {
			queryStr.append(" and model.assignedAddress.constituency.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		} else if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.REGIONSCOPE_MANDAL_LEVEl_ID) {
			queryStr.append(" and model.assignedAddress.tehsil.tehsilId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		} else if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID) { // town/division
			queryStr.append(" and model.assignedAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		} else if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID) {
			queryStr.append(" and model.assignedAddress.panchayat.panchayatId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		} else if (userAccessLevelId != null
				&& userAccessLevelId.longValue() == IConstants.REGIONSCOPE_WARD_LEVEl_ID) {
			queryStr.append(" and model.assignedAddress.ward.constituencyId in (:userAccessLevelValues) and model.assignedLocationScopeId =:userAccessLevelId ");
		}
		if (type.equalsIgnoreCase("momPriorityWise")) {
			queryStr.append(" group by model.momPriority.momPriorityId ");
		} else if (type.equalsIgnoreCase("statusWise")) {
			queryStr.append(" group by model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId  ");
		}
		Query query = getSession().createQuery(queryStr.toString());

		if (userAccessLevelValues != null && userAccessLevelValues.size() > 0) {
			query.setParameterList("userAccessLevelValues",
					userAccessLevelValues);
		}
		if (monthId != null && monthId > 0) {
			query.setParameter("monthId", monthId);
		}
		if (yearId != null && yearId > 0) {
			query.setParameter("yearId", yearId);
		}
		if(userAccessLevelId != null && userAccessLevelId.longValue()>0l){
			query.setParameter("userAccessLevelId", userAccessLevelId);
		}
		return query.list();
	}
	public Date getPartMeetingMonthYear(Long partyMeetingId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.insertedTime from PartyMeetingMinute model where "
				+ " model.partyMeeting.isActive='Y' and model.isDeleted='N' ");
		if(partyMeetingId != null && partyMeetingId.longValue()>0l){
			queryStr.append(" and model.partyMeeting.partyMeetingId =:partyMeetingId ");
		}
		queryStr.append(" group by month(model.insertedTime),year(model.insertedTime) ");
		Query query = getSession().createQuery(queryStr.toString());
		if(partyMeetingId != null && partyMeetingId.longValue()>0l){
			query.setParameter("partyMeetingId", partyMeetingId);
		}
		return (Date)query.uniqueResult();
	}
	
	public List<Object[]> getMOMTypesCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String momType){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId,count(model.partyMeeting.partyMeetingId)" +
				" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y' and model.partyMeeting.startDate is not null " 
	    	  	+" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = 1 and model.isDeleted='N'");
		  if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 if(momType != null && momType.trim().equalsIgnoreCase("Actionable")){
			 queryStr.append(" and model.isActionable = 'Y'");
		 }else if(momType != null && momType.trim().equalsIgnoreCase("General")){
			 queryStr.append(" and model.isActionable = 'N'");
		 }
		 
		 queryStr.append(" group by model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId " +
		         " order by " +
		         " model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId ");
			
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
		 return query.list(); 
	}
	public List<Object[]> getLocationWiseMOMTypesCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String momType,CommitteeInputVO committeeBO){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select 0,count(distinct model.partyMeeting.partyMeetingId)");
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
  		  queryStr.append(",model.partyMeeting.meetingAddress.state.stateId ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			queryStr.append(",model.partyMeeting.meetingAddress.district.districtId ");
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			queryStr.append(",model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId ");
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			queryStr.append(",model.partyMeeting.meetingAddress.constituency.constituencyId");
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			queryStr.append(",model.partyMeeting.meetingAddress.tehsil.tehsilId ");
		}
		
		queryStr.append(" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y' and model.partyMeeting.startDate is not null " 
	    	  	+" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = 1 and model.isDeleted='N'");
		  if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 if(momType != null && momType.trim().equalsIgnoreCase("Actionable")){
			 queryStr.append(" and model.isActionable = 'Y'");
		 }else if(momType != null && momType.trim().equalsIgnoreCase("General")){
			 queryStr.append(" and model.isActionable = 'N'");
		 }
		 
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			 queryStr.append(" group by  model.partyMeeting.meetingAddress.state.stateId ");
  		}
  		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
  			queryStr.append(" group by  model.partyMeeting.meetingAddress.district.districtId ");
  		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
  			queryStr.append(" group by  model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId ");
  		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
  			queryStr.append(" group by  model.partyMeeting.meetingAddress.constituency.constituencyId");
  		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
  			queryStr.append(" group by  model.partyMeeting.meetingAddress.tehsil.tehsilId ");
  		}
		 
		 //queryStr.append(" group by model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId " +
		 //queryStr.append(" order by  model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId ");
			
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
		 return query.list(); 
	}
	public List<Object[]> getMOMActionTypeCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId,model.momAtrSourceType.momAtrSourceTypeId,model.momAtrSourceType.sourceType,count(distinct model.partyMeeting.partyMeetingId)" +
				" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y'" 
	    	  	+" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = 1 and model.isDeleted='N'");
		  if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 queryStr.append(" group by model.momAtrSourceType.momAtrSourceTypeId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId " +
		         " order by " +
		         " model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId ");
			
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
		 return query.list(); 
	}
	
	public List<Object[]> getMOMTypesByLevelTypeDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String momType,String levelType,List<Long> partyMeetingTypeValues){
		StringBuilder queryStr = new StringBuilder();
		 if(levelType != null && levelType.trim().equalsIgnoreCase("District"))
	    	  queryStr.append(" select model.partyMeeting.meetingAddress.district.districtId");
	     else  if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency"))
	    	  queryStr.append(" select model.partyMeeting.meetingAddress.constituency.constituencyId");
	     else  if(levelType != null && levelType.trim().equalsIgnoreCase("Mandal"))
	    	   queryStr.append(" select model.partyMeeting.meetingAddress.tehsil.tehsilId");
	     else  if(levelType != null && levelType.trim().equalsIgnoreCase("Parliament"))
	    	   queryStr.append(" select model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId");
		 
		 queryStr.append(",model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId,count(distinct model.partyMeeting.partyMeetingId)" +
				" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y' and model.partyMeeting.startDate is not null " 
	    	  	+" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = 1 and model.isDeleted='N'");
		  if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		  if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
				 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
			 }
		  
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 if(momType != null && momType.trim().equalsIgnoreCase("Actionable")){
			 queryStr.append(" and model.isActionable = 'Y'");
		 }else if(momType != null && momType.trim().equalsIgnoreCase("General")){
			 queryStr.append(" and model.isActionable = 'N'");
		 }
		 
		 if(levelType != null && levelType.trim().equalsIgnoreCase("District"))
			 queryStr.append(" group by model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId   order by  model.partyMeeting.meetingAddress.district.districtId asc");
		 else if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency"))
			 queryStr.append(" group by model.partyMeeting.meetingAddress.constituency.constituencyId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId   order by  model.partyMeeting.meetingAddress.constituency.constituencyId asc");
		 else if(levelType != null && levelType.trim().equalsIgnoreCase("Mandal"))
			 queryStr.append(" group by model.partyMeeting.meetingAddress.tehsil.tehsilId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId  order by  model.partyMeeting.meetingAddress.tehsil.tehsilId asc");
		 else if(levelType != null && levelType.trim().equalsIgnoreCase("Parliament"))
			 queryStr.append(" group by model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId  order by  model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId asc");	
		 
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
		 return query.list(); 
	}
	
	public List<Object[]> getMOMActionTypeDetailsByLevelType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String levelType,List<Long> partyMeetingTypeValues){
		StringBuilder queryStr = new StringBuilder();
		if(levelType != null && levelType.trim().equalsIgnoreCase("District"))
	    	  queryStr.append(" select model.partyMeeting.meetingAddress.district.districtId");
	    else  if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency"))
	    	  queryStr.append(" select model.partyMeeting.meetingAddress.constituency.constituencyId");
	    else  if(levelType != null && levelType.trim().equalsIgnoreCase("Mandal"))
	    	   queryStr.append(" select model.partyMeeting.meetingAddress.tehsil.tehsilId");
	     else  if(levelType != null && levelType.trim().equalsIgnoreCase("Parliament"))
	    	   queryStr.append(" select model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId");
		
		queryStr.append(",model.momAtrSourceType.momAtrSourceTypeId,model.momAtrSourceType.sourceType,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId,count(distinct model.partyMeeting.partyMeetingId)" +
				" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y'" 
	    	  	+" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = 1 and model.isDeleted='N'");
		  if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		  if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
				 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
			}
		 if(levelType != null && levelType.trim().equalsIgnoreCase("District")){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (3)");
		 }else if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency")){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (15)");
		 }
		 
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 if(levelType != null && levelType.trim().equalsIgnoreCase("District"))
			 queryStr.append(" group by model.momAtrSourceType.momAtrSourceTypeId,model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId  order by  model.partyMeeting.meetingAddress.district.districtId asc");
		 else if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency"))
			 queryStr.append(" group by model.momAtrSourceType.momAtrSourceTypeId,model.partyMeeting.meetingAddress.constituency.constituencyId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId  order by  model.partyMeeting.meetingAddress.constituency.constituencyId asc");
		 else if(levelType != null && levelType.trim().equalsIgnoreCase("Mandal"))
			 queryStr.append(" group by model.partyMeeting.meetingAddress.tehsil.tehsilId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId  order by  model.partyMeeting.meetingAddress.tehsil.tehsilId asc");
		 else if(levelType != null && levelType.trim().equalsIgnoreCase("Parliament"))
			 queryStr.append(" group by model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.partyMeeting.partyMeetingType.partyMeetingLevel.partyMeetingLevelId  order by  model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId asc");	
		 	
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
		 return query.list(); 
	}
	
	public List<Object[]> getMOMStatusDetailsByLevelType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String levelType){
		StringBuilder queryStr = new StringBuilder();
		if(levelType != null && levelType.trim().equalsIgnoreCase("District"))
	    	  queryStr.append(" select model.partyMeeting.meetingAddress.district.districtId");
	    else  if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency"))
	    	  queryStr.append(" select model.partyMeeting.meetingAddress.constituency.constituencyId");
		
		queryStr.append(",model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId,model.partyMeetingMinuteStatus.status,count(model.partyMeetingMinuteId)" +
				" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y'" 
	    	  	+" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = 1 and model.isDeleted='N'");
		  if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		  
		 if(levelType != null && levelType.trim().equalsIgnoreCase("District")){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (3)");
		 }else if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency")){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (15)");
		 }
		 
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 if(levelType != null && levelType.trim().equalsIgnoreCase("District"))
			 queryStr.append(" group by model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId,model.partyMeeting.meetingAddress.district.districtId  order by  model.partyMeeting.meetingAddress.district.districtId asc");
		 else if(levelType != null && levelType.trim().equalsIgnoreCase("Constituency"))
			 queryStr.append(" group by model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId,model.partyMeeting.meetingAddress.constituency.constituencyId  order by  model.partyMeeting.meetingAddress.constituency.constituencyId asc");
		 
		 	
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 
		 return query.list(); 
	}
	
	public List<Object[]> getMOMTypesCountDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingTypeId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select");
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID)
			queryStr.append(" model.partyMeeting.meetingAddress.state.stateId,model.partyMeeting.meetingAddress.state.stateName");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.meetingAddress.district.districtName");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.partyMeeting.meetingAddress.parliamentConstituency.name");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.constituency.constituencyId,model.partyMeeting.meetingAddress.constituency.constituencyId.name");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.tehsil.tehsilId,model.partyMeeting.meetingAddress.tehsil.tehsilId.tehsilName");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID) //  town/division
			 queryStr.append(" model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId,model.partyMeeting.meetingAddress.userAddress.localElectionBody.name"); 
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.panchayat.panchayatId,model.partyMeeting.meetingAddress.panchayat.panchayatName"); 
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.ward.constituencyId,model.partyMeeting.meetingAddress.ward.name");
		queryStr.append(",model.isActionable,count(distinct model.partyMeeting.partyMeetingId) ");
				
		queryStr.append(" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y' and model.partyMeeting.startDate is not null " 
	    	  	+" and model.isDeleted='N'");
		if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() > 0l)
			queryStr.append(" model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId");
		
		
		 if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		 if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeId)");
		 }
		 if(stateId != null && stateId.longValue() > 0){
		   if(stateId.longValue()==1l){
			   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ua.state_id = 1");
			}else if(stateId.longValue()==36l){
				queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
			}
		}
		 
		 
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 queryStr.append(" group by model.isActionable");
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID)
			 queryStr.append(" ,model.partyMeeting.meetingAddress.state.stateId");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID)
			 queryStr.append(" ,model.partyMeeting.meetingAddress.district.districtId");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID)
			 queryStr.append(" ,model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID)
			 queryStr.append(" ,model.partyMeeting.meetingAddress.constituency.constituencyId");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID)
			 queryStr.append(" ,model.partyMeeting.meetingAddress.tehsil.tehsilId");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID) //  town/division
			 queryStr.append(" ,model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId"); 
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID)
			 queryStr.append(" ,model.partyMeeting.meetingAddress.panchayat.panchayatId"); 
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID)
			 queryStr.append(" ,model.partyMeeting.meetingAddress.ward.constituencyId"); 
		 
			
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() > 0){
			 query.setParameter("partyMeetingMainTypeId", partyMeetingMainTypeId); 
		 }
		 if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() > 0){
			 query.setParameter("partyMeetingTypeId", partyMeetingTypeId); 
		 }
		 return query.list(); 
	}
	
	public List<Object[]> getMOMStatusCntDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long partyMeetingMainTypeId,Long partyMeetingTypeId,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select");
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID)
			queryStr.append(" model.partyMeeting.meetingAddress.state.stateId,model.partyMeeting.meetingAddress.state.stateName");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.meetingAddress.district.districtName");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.partyMeeting.meetingAddress.parliamentConstituency.name");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.constituency.constituencyId,model.partyMeeting.meetingAddress.constituency.constituencyId.name");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.tehsil.tehsilId,model.partyMeeting.meetingAddress.tehsil.tehsilId.tehsilName");  
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID) //  town/division
			 queryStr.append(" model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId,model.partyMeeting.meetingAddress.userAddress.localElectionBody.name"); 
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.panchayat.panchayatId,model.partyMeeting.meetingAddress.panchayat.panchayatName"); 
		 else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID)
			 queryStr.append(" model.partyMeeting.meetingAddress.ward.constituencyId,model.partyMeeting.meetingAddress.ward.name");
		queryStr.append(",model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId,count(pm.partyMeetingMinuteId) ");
				
		queryStr.append(" from PartyMeetingMinute model where "
				+" model.partyMeeting.isActive='Y' and model.partyMeeting.startDate is not null " 
	    	  	+" and model.isDeleted='N'");
		if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() > 0l)
			queryStr.append(" model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId");
		
		
		 if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");	 
		 }
		 if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeId)");
		 }
		 if(stateId != null && stateId.longValue() > 0){
		   if(stateId.longValue()==1l){
			   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ua.state_id = 1");
			}else if(stateId.longValue()==36l){
				queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
			}
		}
		 
		 
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	       queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 
		 queryStr.append(" group by model.partyMeetingMinuteStatus.partyMeetingMinuteStatusId");
		
			
		 Query query = getSession().createQuery(queryStr.toString());
			
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() > 0){
			 query.setParameter("partyMeetingMainTypeId", partyMeetingMainTypeId); 
		 }
		 if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() > 0){
			 query.setParameter("partyMeetingTypeId", partyMeetingTypeId); 
		 }
		 return query.list(); 
	}
	
	public List<Object[]> getPartyMeetingDetails(MomDetailsVO momDetailsVO,Long userAccessLevelId,List<Long> userAccessLevelValues){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" pm.party_meeting_level_id,pml.level, pm.party_meeting_id,pm.meeting_name,date(pm.conducted_date), ");//4
		sb.append(" s.state_id,s.state_name ,d.district_id,d.district_name,0,'', c.constituency_id,c.name, ");//12
		sb.append(" t.tehsil_id, t.tehsil_name, l.local_election_body_id, l.name,0,'',0,''");// p.panchayat_id, p.panchayat_name, w.constituency_id, w.name");//20
		sb.append(" from   ");
		sb.append(" party_meeting_level pml ,  ");
		sb.append(" party_meeting_type pmt  ,  ");
		sb.append(" party_meeting_main_type pmmt ,  ");
		sb.append(" party_meeting pm   ");
		sb.append(" LEFT JOIN user_address ua on pm.meeting_address_id = ua.user_address_id  ");
		sb.append(" LEFT JOIN state s on ua.state_id = s.state_id ");
		sb.append(" LEFT JOIN district d on ua.district_id = d.district_id  ");
		//sb.append(" LEFT JOIN constituency pa on ua.parliament_constituency_id = ua.parliament_constituency_id ");
		sb.append(" LEFT JOIN constituency c on ua.constituency_id = c.constituency_id  ");
		sb.append(" LEFT JOIN tehsil t on ua.tehsil_id = t.tehsil_id  ");
		sb.append(" LEFT JOIN local_election_body l on ua.local_election_body = l.local_election_body_id  ");
		//sb.append(" LEFT JOIN panchayat p on ua.panchayat_id = p.panchayat_id  ");
		//sb.append(" LEFT JOIN constituency w on ua.ward = w.constituency_id  ");
		sb.append(" where  ");
		sb.append(" pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id and pmt.is_active='Y'  and  ");
		sb.append(" pm.party_meeting_level_id = pml.party_meeting_level_id and pm.is_active='Y'  and  ");
		sb.append(" pm.party_meeting_type_id = pmt.party_meeting_type_id and pmt.is_active='Y' and  ");
		sb.append(" pm.is_active='Y' and pm.conducted_date is not null and ");
		sb.append(" pmmt.party_meeting_main_type_id = 1   ");
		
		if(momDetailsVO.getStartDate() != null && momDetailsVO.getEndDate() != null){
			sb.append(" and  (date(pm.conducted_date) BETWEEN :startDate and :endDate)    ");
		}
		if(momDetailsVO.getPartyMeetingLevelIdsList() != null && momDetailsVO.getPartyMeetingLevelIdsList().size()>0){
			sb.append(" and pm.party_meeting_type_id in (:partyMeetingLevelIdsList)   ");
		}
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   sb.append(" and ua.state_id in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 sb.append(" and ua.district_id in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 sb.append(" and ua.parliament_constituency_id in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 sb.append(" and ua.constituency_id in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			 sb.append(" and ua.tehsil_id in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			 sb.append(" and ua.local_election_body in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			 sb.append(" and ua.panchayat_id in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			 sb.append(" and ua.ward in (:userAccessLevelValues)"); 
		 }
		
		//sb.append(" and s.state_id = :stateId  ");
		sb.append(" group by pm.party_meeting_id  ");
		Query query = getSession().createSQLQuery(sb.toString());
		//query.setParameter("stateId", momDetailsVO.getStateId());
		if(momDetailsVO.getStartDate() != null && momDetailsVO.getEndDate() != null){
			query.setDate("startDate", momDetailsVO.getStartDate());
			query.setDate("endDate", momDetailsVO.getEndDate());
		}
		if(momDetailsVO.getPartyMeetingLevelIdsList() != null && momDetailsVO.getPartyMeetingLevelIdsList().size()>0){
			query.setParameterList("partyMeetingLevelIdsList", momDetailsVO.getPartyMeetingLevelIdsList());
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		return query.list();
	}
	
	public List<Object[]> getPartyMeetingMOMDetails(MomDetailsVO momDetailsVO,Long userAccessLevelId,List<Long> userAccessLevelValues){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" pm.party_meeting_level_id,pml.level, pm.party_meeting_id,pm.meeting_name,date(pm.conducted_date), ");//4
		sb.append(" pmm.party_meeting_minute_id,'',date(pmm.inserted_time), ");//7
		//sb.append(" pmm.party_meeting_minute_id,pmm.minute_point,date(pmm.inserted_time), ");//7
		sb.append(" st.mom_atr_source_type_id,st.source_type, ");//9
		sb.append(" pmm.is_actionable, ");//10
		sb.append(" s.state_id,s.state_name , d.district_id,d.district_name,0,'', c.constituency_id,c.name, ");//18
		sb.append(" t.tehsil_id, t.tehsil_name, l.local_election_body_id, l.name,0,'',0,'' ");// p.panchayat_id, p.panchayat_name, w.constituency_id, w.name");//26
		sb.append(" from   ");
		sb.append(" party_meeting_level pml ,  ");
		sb.append(" party_meeting_type pmt  ,  ");
		sb.append(" party_meeting_main_type pmmt ,  ");
		sb.append(" party_meeting_minute pmm ");
		sb.append(" LEFT JOIN mom_atr_source_type st on pmm.mom_atr_source_type_id = st.mom_atr_source_type_id and pmm.is_deleted='N' ");
		sb.append(" LEFT JOIN party_meeting pm on pmm.party_meeting_id = pm.party_meeting_id and pmm.is_deleted='N' and pm.is_active='Y' and pm.conducted_date is not null  ");
		//sb.append(" LEFT JOIN user_address ua on pmm.user_address_id = ua.user_address_id  ");
		sb.append(" LEFT JOIN user_address ua on pm.meeting_address_id = ua.user_address_id  ");
		sb.append(" LEFT JOIN state s on ua.state_id = s.state_id ");
		sb.append(" LEFT JOIN district d on ua.district_id = d.district_id  ");
		//sb.append(" LEFT JOIN constituency pa on ua.parliament_constituency_id = ua.parliament_constituency_id ");
		sb.append(" LEFT JOIN constituency c on ua.constituency_id = c.constituency_id  ");
		sb.append(" LEFT JOIN tehsil t on ua.tehsil_id = t.tehsil_id  ");
		sb.append(" LEFT JOIN local_election_body l on ua.local_election_body = l.local_election_body_id  ");
		//sb.append(" LEFT JOIN panchayat p on ua.panchayat_id = p.panchayat_id  ");
		//sb.append(" LEFT JOIN constituency w on ua.ward = w.constituency_id  ");
		sb.append(" where  ");
		sb.append(" pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id and pmt.is_active='Y'  and  ");
		sb.append(" pm.party_meeting_level_id = pml.party_meeting_level_id and pm.is_active='Y'  and  ");
		sb.append(" pm.party_meeting_type_id = pmt.party_meeting_type_id and pmt.is_active='Y' and  ");
		sb.append(" pmmt.party_meeting_main_type_id = 1   ");
		if(momDetailsVO.getStartDate() != null && momDetailsVO.getEndDate() != null){
			sb.append(" and (date(pm.conducted_date) BETWEEN :startDate and :endDate)    ");
		}
		if(momDetailsVO.getPartyMeetingLevelIdsList() != null && momDetailsVO.getPartyMeetingLevelIdsList().size()>0){
			sb.append(" and pm.party_meeting_type_id in (:partyMeetingLevelIdsList)   ");
		}
		
		//sb.append(" s.state_id = :stateId  ");
		if(momDetailsVO.getSourceTypeId() != null && momDetailsVO.getSourceTypeId().longValue()>0L ){
			sb.append(" and st.mom_atr_source_type_id =:sourceTypeId  ");
		}
		if(momDetailsVO.getMomType() != null && !momDetailsVO.getMomType().isEmpty() && momDetailsVO.getMomType().length()>0){
			sb.append(" and pmm.is_actionable in (:momType) ");
		}
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   sb.append(" and ua.state_id in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 sb.append(" and ua.district_id in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 sb.append(" and ua.parliament_constituency_id in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 sb.append(" and ua.constituency_id in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			 sb.append(" and ua.tehsil_id in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			 sb.append(" and ua.local_election_body in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			 sb.append(" and ua.panchayat_id in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			 sb.append(" and ua.ward in (:userAccessLevelValues)"); 
		 }
		
		sb.append(" group by pmm.party_meeting_minute_id  ");
		
		Query query = getSession().createSQLQuery(sb.toString());
		//query.setParameter("stateId", momDetailsVO.getStateId());
		if(momDetailsVO.getSourceTypeId() != null && momDetailsVO.getSourceTypeId().longValue()>0L ){
			query.setParameter("sourceTypeId", momDetailsVO.getSourceTypeId());
		}
		if(momDetailsVO.getMomType() != null && !momDetailsVO.getMomType().isEmpty() && momDetailsVO.getMomType().length()>0){
			query.setParameter("momType", momDetailsVO.getMomType());
		}
		if(momDetailsVO.getStartDate() != null && momDetailsVO.getEndDate() != null){
			query.setDate("startDate", momDetailsVO.getStartDate());
			query.setDate("endDate", momDetailsVO.getEndDate());
		}
		if(momDetailsVO.getPartyMeetingLevelIdsList() != null && momDetailsVO.getPartyMeetingLevelIdsList().size()>0){
			query.setParameterList("partyMeetingLevelIdsList", momDetailsVO.getPartyMeetingLevelIdsList());
		}
		
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		return query.list();
	}
	
	public List<Object[]> getPartyMeetingMOMDocumentsDetails(MomDetailsVO momDetailsVO,Long userAccessLevelId,List<Long> userAccessLevelValues){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" pm.party_meeting_id,pmm.party_meeting_minute_id,pmd.document_type,pmd.document_name, pmd.path,pmd.document_format,pmd.updated_time ");
		sb.append(" from  ");
		sb.append(" party_meeting_document pmd "); 
		sb.append(" LEFT JOIN party_meeting_minute pmm on pmd.party_meeting_minute_id = pmm.party_meeting_minute_id and pmm.is_deleted='N' "); 
		sb.append(" LEFT JOIN party_meeting pm on  pmd.party_meeting_id = pm.party_meeting_id and pm.is_active='Y' ");
		sb.append(" where    ");
		sb.append(" (date(pm.conducted_date) BETWEEN '2018-01-01' and '2018-05-01')  and "); 
		sb.append("  pm.party_meeting_level_id in (1,2,3,4,5,6) ");
		Query query= getSession().createSQLQuery(sb.toString());
		return query.list();
	}
}