package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingSessionDAO;
import com.itgrids.partyanalyst.model.PartyMeetingSession;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingSessionDAO extends GenericDaoHibernate<PartyMeetingSession, Long> implements IPartyMeetingSessionDAO{

	public PartyMeetingSessionDAO() {
		super(PartyMeetingSession.class);
			
	}

	public List<Object[]> getSessionDetailsForPartyMeetings(Set<Long> partyMeetingsIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  distinct model.partyMeetingId, " +//0
		        " model.partyMeetingSessionId, " +//1
		        " model.sessionType.type, '' , " +//2,3
		        " model.startTime, " +//4
		        " model.endTime," +//5
		        " model.lateTime," +//6
		        " model.sessionType.startTime," +//7
		        " model.sessionType.endTime," +//8
		        " model.sessionType.lateTime, "+//9
		        " model.sessionType.sessionTypeId," +//10
		        " model.attendanceStartTime   " +//11
		        " from PartyMeetingSession model where model.isDeleted='N' and " +
				" model.partyMeetingId in (:partyMeetingsIds)  order by model.orderNo asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("partyMeetingsIds", partyMeetingsIds);		
		return query.list();
	}
	
	public List<Object[]> getSessionDetailsForPartiMeetings(Set<Long> partyMeetingsTypeIds,List<Long> partyMeetingsIds){
		StringBuilder queryStr = new StringBuilder();
		if(partyMeetingsTypeIds != null && partyMeetingsTypeIds.size()>0){
			queryStr.append(" select  distinct pm.partyMeetingId, model.partyMeetingSessionId, model.sessionType.type, '' , " +
					" model.startTime, model.endTime,model.lateTime,model.sessionType.startTime,model.sessionType.endTime,model.sessionType.lateTime," +
					" pm.partyMeetingTypeId, pm.meetingName " +
					"  from PartyMeetingSession model " +
					" left join model.partyMeeting pm " +
					" where model.isDeleted='N' and " +
					" pm.partyMeetingTypeId in (:partyMeetingsTypeIds)  ");
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				queryStr.append(" and pm.partyMeetingId in (:partyMeetingIds) ");
			}
			queryStr.append("  order by  model.orderNo asc ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("partyMeetingsTypeIds", partyMeetingsTypeIds);
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				query.setParameterList("partyMeetingIds",partyMeetingsIds);
			}
			return query.list();
		}
		return null;
	}
	
	public List<Object[]> getSessionDetailsForPartiMeetings(Set<Long> partyMeetingsTypeIds,List<Long> partyMeetingsIds,Date startDate,Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		if(partyMeetingsTypeIds != null && partyMeetingsTypeIds.size()>0){
			queryStr.append(" select  distinct pm.partyMeetingId, model.partyMeetingSessionId, model.sessionType.type, '' , " +
					" model.startTime, model.endTime,model.lateTime,model.sessionType.startTime,model.sessionType.endTime,model.sessionType.lateTime," +
					" pm.partyMeetingTypeId, pm.meetingName " +
					"  from PartyMeetingSession model " +
					" left join model.partyMeeting pm " +
					" where model.isDeleted='N' and " +
					" pm.partyMeetingTypeId in (:partyMeetingsTypeIds)  ");
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				queryStr.append(" and pm.partyMeetingId in (:partyMeetingIds) ");
			}
			if(startDate != null && endDate != null)
			{
				queryStr.append(" and date(pm.startDate) between :startDate and :endDate ");
			}
			queryStr.append("  order by  model.orderNo asc ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("partyMeetingsTypeIds", partyMeetingsTypeIds);
			if(partyMeetingsIds != null && partyMeetingsIds.size()>0){
				query.setParameterList("partyMeetingIds",partyMeetingsIds);
			}
			if(startDate != null && endDate != null)
			{
				query.setDate("startDate",startDate);
				query.setDate("endDate",endDate);
			}
			return query.list();
		}
		return null;
	}
	
	public List<Object[]> getPartyMeetingSession(Long partyMeetingId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  distinct model.partyMeeting.partyMeetingId, " +//0
				" model.sessionType.sessionTypeId," +//1
				" model.sessionType.type " +//2
		        " from PartyMeetingSession model where model.isDeleted='N' and " +
				" model.partyMeeting.partyMeetingId = :partyMeetingId  order by model.orderNo asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingId", partyMeetingId);		
		return query.list();
	}
	public List<Object[]> getLateTimeList(Set<Long> meetingIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct" +
				" model.partyMeeting.partyMeetingId, " +
				" model.partyMeetingSessionId," +
				" model.lateTime " +
				" from PartyMeetingSession model " +
				" where " +
				" model.isDeleted = 'N' " +
				" and model.partyMeeting.partyMeetingId in (:meetingIds) " +
				" order by model.partyMeeting.partyMeetingId,model.orderNo ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("meetingIds", meetingIds);
		return query.list();  
	}
	public List<Object[]> getLateTimeListForMultiMeetings(Set<Long> meetingIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct" +
				" model.sessionType.sessionTypeId ," +
				" model.lateTime, " +
				" model.sessionType.lateTime, " +
				" model.sessionType.type" +
				" from PartyMeetingSession model " +
				" where " +
				" model.isDeleted = 'N' " +
				" and model.partyMeeting.partyMeetingId in (:meetingIds) " +
				" order by model.orderNo ");  
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("meetingIds", meetingIds);
		return query.list();  
	}
	
	public List<Object[]> getLateTimeDetails(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,List<Long> levelIdsList,Long partyMeetngGrpId,Long locationValId){
		StringBuilder queryStr = new StringBuilder();
		
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			queryStr.append(" select  distinct model.sessionType.sessionTypeId,model.sessionType.type,model.lateTime, model.partyMeeting.partyMeetingType.partyMeetingTypeId " +
					" from PartyMeetingSession model,PartyMeetingGroupsMappingInfo model1 where model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetnMainTypId " +
					" and  model.partyMeeting.partyMeetingType.isActive = 'Y'  and " +
					" model1.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId  ");
			queryStr.append(" and model1.partyMeetingGroup.partyMeetingGroupId = :partyMeetngGrpId ");
		}
		else{
			queryStr.append(" select  distinct model.sessionType.sessionTypeId,model.sessionType.type,model.lateTime, model.partyMeeting.partyMeetingType.partyMeetingTypeId " +
					" from PartyMeetingSession model where model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetnMainTypId " +
					" and  model.partyMeeting.partyMeetingType.isActive = 'Y'    ");
			
		}
		if(levelIdsList != null && levelIdsList.size() > 0l){
			queryStr.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (:levelIdsList) ");
		}
		
		if(locationValId != null && locationValId.longValue() > 0l){
			queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId = :locationValId "); 
		}
		if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId");
	  }
	  if(fromDate!= null && toDate!=null){
		  queryStr.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");	 
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
		
		
		queryStr.append(" group by  model.sessionType.sessionTypeId  ");
		Query query = getSession().createQuery(queryStr.toString());
		 if(locationValuesSet != null && locationValuesSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValuesSet);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			 query.setParameter("partyMeetngGrpId", partyMeetngGrpId); 
		 }
		 if(locationValId != null && locationValId.longValue() > 0l){
			 query.setParameter("locationValId", locationValId);
		 }
		 if(levelIdsList != null && levelIdsList.size() > 0l){
			 query.setParameterList("levelIdsList", levelIdsList); 
		 }
		query.setParameter("partyMeetnMainTypId", partyMeetnMainTypId);
		return query.list();
	}
	public List<Object[]> getSessionsDetailsByMeetingId(Long meetingId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.partyMeetingId,");//0
		queryStr.append("model.partyMeetingSessionId,");//1
		queryStr.append(" model.sessionType.sessionTypeId,");//2
		queryStr.append("model.startTime,");//3
		queryStr.append("model.endTime,");//4
		queryStr.append("model.lateTime,");	//5
		queryStr.append("model.sessionType.type ");	//6
		queryStr.append("from PartyMeetingSession model " );		
		queryStr.append("where model.isDeleted = 'N' ");
		if(meetingId !=null && meetingId.longValue() >0L){
			queryStr.append(" and model.partyMeetingId=:meetingId ");
		}
		queryStr.append(" order by model.orderNo ");  
		Query query = getSession().createQuery(queryStr.toString());
		if(meetingId !=null && meetingId.longValue() >0L){
			query.setParameter("meetingId", meetingId);
		}
		return query.list();  
	}
	 public Integer updatePartyMeetingSession(Long sessionId){
			Query query=getSession().createQuery(" update PartyMeetingSession model set model.isDeleted = 'Y' where model.partyMeetingSessionId =:sessionId ");		
			query.setParameter("sessionId",sessionId);
			return  query.executeUpdate();
		}
}//
