package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingUpdationDetailsDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUpdationDetails;

public class PartyMeetingUpdationDetailsDAO extends GenericDaoHibernate<PartyMeetingUpdationDetails, Long> implements IPartyMeetingUpdationDetailsDAO{

	public PartyMeetingUpdationDetailsDAO() {
		super(PartyMeetingUpdationDetails.class);
	}
	  @SuppressWarnings("unchecked")
	public List<Object[]> getCommentsDetailsByPartyMeetingId(Long partyMeetingId){
		  
	      	Query query = getSession().createQuery(" select model.partyMeetingUpdationDetailsId,model.comment,model.name,model.insertedTime," +
	      			" model.mobileNo ,tdpCadre.tdpCadreId,tdpCadre.memberShipNo,tdpCadre.image " +
	      			" from PartyMeetingUpdationDetails model " +
	      			" left join model.tdpCadre tdpCadre where " +
	      			" model.partyMeetingId = :partyMeetingId and model.isDeleted= 'false' order by model.insertedTime desc");
	      	
	      	query.setParameter("partyMeetingId",partyMeetingId);
	      	return query.list();
	      }
	public List<Object[]> getCommentsAvailableByPartyMeetingId(List<Long> partyMeetingIds){
		  
      	Query query = getSession().createQuery(" select model.partyMeetingId,model.comment " +
      			" from PartyMeetingUpdationDetails model where " +
      			" model.partyMeetingId in(:partyMeetingIds) and model.isDeleted= 'false' ");
      	
      	query.setParameterList("partyMeetingIds",partyMeetingIds);
      	return query.list();
      }
	public List<Object[]> getUpdatedDetails(Long locationLevelId,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		    sb.append("select distinct model.partyMeeting.partyMeetingId," +
		    		" model.partyMeeting.meetingName," +
		    		" model.user.userName" +
		    		" from PartyMeetingUpdationDetails model " +
		    		" where model.isDeleted= 'false'");
		    if(locationLevelId != null && locationLevelId.longValue() == 7l)
		    	sb.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8)");
		    else if(locationLevelId != null && locationLevelId.longValue() == 4l)
		    	sb.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (4,5,6)");
		    else if(locationLevelId != null && (locationLevelId.longValue() == 3l || locationLevelId.longValue() == 2l))
		    	sb.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId = :locationLevelId");
		    if(startDate != null && endDate != null){
	            sb.append(" and  ( (date(model.partyMeeting.startDate)>=:startDate and date(model.partyMeeting.startDate)<=:endDate) or (date(model.partyMeeting.endDate)>=:startDate and date(model.partyMeeting.endDate)<=:endDate) ) ");
	        }
		
		 Query query = getSession().createQuery(sb.toString());
		 	if(locationLevelId != null && (locationLevelId.longValue() == 3l || locationLevelId.longValue() == 2l))
		 		query.setParameter("locationLevelId", locationLevelId);
		 	if(startDate != null && endDate != null){
		 		query.setDate("startDate", startDate);
		 		query.setDate("endDate", endDate);
		 	}
		 	return query.list();
	}
	public List<Object[]> getDocumentList(Long partyMeetingId){
		Query query = getSession().createQuery("select model.partyMeetingUpdationDetailsId," +
		    	" model.name," +
		    	" model.mobileNo," +
		    	" model.comment," +
		    	" model.insertedTime," +
		    	" tdpCadre.memberShipNo " +
				" from PartyMeetingUpdationDetails model" +
				" left join model.tdpCadre tdpCadre" +
				" where model.partyMeeting.partyMeetingId = :partyMeetingId" +
				" and model.isDeleted= 'false'");
		query.setParameter("partyMeetingId", partyMeetingId);
		
		return query.list();
	}
	public String getMemberShipNo(Long partyMeetingId){
		Query query = getSession().createQuery("select model.tdpCadre.memberShipNo " +
				" from  PartyMeetingUpdationDetails model" +
				" where model.partyMeeting.partyMeetingId = :partyMeetingId" +
				" and model.isDeleted= 'false'");
		query.setParameter("partyMeetingId", partyMeetingId);
		return  (String) query.uniqueResult();
	}
	public List<Object[]> getUpdationDetailsCount(Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder(); 
		sb.append("select count(distinct model.partyMeeting.partyMeetingId)," +
				" model.partyMeeting.partyMeetingLevel.level," +
				" model.partyMeeting.partyMeetingLevelId" +
				" from PartyMeetingUpdationDetails model " +
				" where model.isDeleted= 'false'" +
				" and model.partyMeeting.isActive= 'Y'");
		if(startDate != null && endDate !=  null)
			sb.append(" and  ( (date(model.partyMeeting.startDate)>=:startDate and date(model.partyMeeting.startDate)<=:endDate) or (date(model.partyMeeting.endDate)>=:startDate and date(model.partyMeeting.endDate)<=:endDate) ) ");
			sb.append(" group by model.partyMeeting.partyMeetingLevelId");
			
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate !=  null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
}
