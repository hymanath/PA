package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
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
		queryStr.append(" select model.partyMeetingMinuteId,model.partyMeeting.partyMeetingId,model.minutePoint,model.insertedBy.userId,model.insertedBy.firstName," +
				"model.updatedBy.userId,model.updatedBy.firstName,model.insertedTime," +
				"model.updatedTime,model.partyMeeting.meetingName,momAtrSourceType.sourceType " +
				" from PartyMeetingMinute model " +
				" left join model.momAtrSourceType momAtrSourceType " +
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
		queryObject.setParameter("isGovtParty", isGovtParty);
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
				" model.userAddressId,model.partyMeetingId,model.locationLevel,model.momAtrSourceTypeId " +
				" FROM PartyMeetingMinute model " +
				" WHERE model.isDeleted = 'N'" +
				" and model.partyMeetingMinuteId =:minuteId ");
		
		Query query = getSession().createQuery(str.toString());		
		query.setParameter("minuteId", minuteId);
		
		return query.list();
		
	} 
	
	public List<Object[]> getMinuteDetailsForMeetingsList(List<Long> partymeetingIdsList,String accessType,List<Long> accessValues){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingMinuteId,model.partyMeeting.partyMeetingId,model.minutePoint,model.insertedBy.userId,model.insertedBy.firstName," +
				"model.updatedBy.userId,model.updatedBy.firstName,model.insertedTime," +
				"model.updatedTime,model.partyMeeting.meetingName,momAtrSourceType.sourceType " +
				" from PartyMeetingMinute model " +
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
	  	    				" model.partyMeeting.isActive='Y' ");
  	
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
			queryStr.append(" and model.locationAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
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
	    				" model.partyMeeting.isActive='Y' ");
	
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
		queryStr.append(" and model.locationAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
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
    				" model.partyMeeting.isActive='Y' ");

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
	queryStr.append(" and model.createdAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
	queryStr.append(" and model.createdAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
	queryStr.append(" and model.createdAddress.ward.constituencyId in (:userAccessLevelValues)"); 
  }  
  //getting meeting levelId 
	List<Long> partyMeetinLevelIds = getPartyMeetingLevelByRegionScope(userAccessLevelId);
	if (partyMeetinLevelIds != null && partyMeetinLevelIds.size() > 0) {
		 queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetinLevelIds)");
	}
  if (type.equalsIgnoreCase("atYourLocationOnly")) {
	 queryStr.append(" and model.assignedAddressId is null ");
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
    				" model.partyMeeting.isActive='Y' ");

  if(monthId != null && monthId > 0){
		  queryStr.append(" and month(model.insertedTime)=:monthId ");	 
  }
  if(yearId != null && yearId > 0){
	 	queryStr.append(" and year(model.insertedTime)=:yearId ");		 
  }
  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
   queryStr.append(" and model.assignedAddress.state.stateId in (:userAccessLevelValues)");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
   queryStr.append(" and model.assignedAddress.district.districtId in (:userAccessLevelValues)");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
    queryStr.append(" and model.assignedAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
    queryStr.append(" and model.assignedAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
    queryStr.append(" and model.assignedAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
	queryStr.append(" and model.assignedAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
	queryStr.append(" and model.assignedAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
	queryStr.append(" and model.assignedAddress.ward.constituencyId in (:userAccessLevelValues)"); 
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
return (Long)query.uniqueResult();	
}
public List<Object[]> getTotalMomDetailsByLocation(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId){
	
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
    		" model.assignedLocationValue ");
    queryStr.append(" from PartyMeetingMinute model " +
    				" where " +
    				" model.partyMeeting.isActive='Y' ");

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
	queryStr.append(" and model.locationAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
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
	  		          " model.partyMeeting.isActive='Y' ");
	  
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

public List<Object[]> getMomDetailsByType(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type,String momType){
	
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
    		" model.assignedLocationValue ");
    queryStr.append(" from PartyMeetingMinute model " +
    				" where " +
    				" model.partyMeeting.isActive='Y' ");

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
 return query.list();	
 }
  public String getDynamicQuery(Long userAccessLevelId,String type) {
	   StringBuilder queryStr = new StringBuilder();
	   if (type.equalsIgnoreCase("createdMom")) {
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
				queryStr.append(" and model.createdAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
				queryStr.append(" and model.createdAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
				queryStr.append(" and model.createdAddress.ward.constituencyId in (:userAccessLevelValues)"); 
			  }   
	   } else if (type.equalsIgnoreCase("assignedMom")) {
		     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.assignedAddress.state.stateId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.assignedAddress.district.districtId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID){
			    queryStr.append(" and model.assignedAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID){
			    queryStr.append(" and model.assignedAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MANDAL_LEVEl_ID){
			    queryStr.append(" and model.assignedAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID){ //  town/division
				queryStr.append(" and model.assignedAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID){ 
				queryStr.append(" and model.assignedAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.REGIONSCOPE_WARD_LEVEl_ID){ 
				queryStr.append(" and model.assignedAddress.ward.constituencyId in (:userAccessLevelValues)"); 
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
}
