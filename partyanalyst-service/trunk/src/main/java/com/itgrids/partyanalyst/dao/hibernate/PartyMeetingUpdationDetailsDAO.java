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
	public List<Object[]> getUpdatedDetails(Long locationLevelId,Date startDate,Date endDate,String status,List<Long> distIds,List<Long> constIds,String locationType,String thridPartyStatus){
		StringBuilder sb = new StringBuilder();
		    sb.append("select distinct model.partyMeeting.partyMeetingId," +
		    		" model.partyMeeting.meetingName," +
		    		" model.user.userName,d.districtName," +
		    		" c.name,model.partyMeetingUpdationDetailsId " +
		    		" from PartyMeetingUpdationDetails model " +
		    		" left join model.partyMeeting.meetingAddress.district d " +
		    		" left join model.partyMeeting.meetingAddress.constituency c " +
		    		" where model.isDeleted= 'false' and model.partyMeeting.thirdPartyStatus is not null and  model.partyMeeting.thirdPartyStatus != ''  and  model.partyMeeting.isActive ='Y' ");
		    if(locationLevelId != null && locationLevelId.longValue() == 7l)
		    	sb.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8)");
		    else if(locationLevelId != null && locationLevelId.longValue() == 4l)
		    	sb.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (4,5,6)");
		    else if(locationLevelId != null && (locationLevelId.longValue() == 3l || locationLevelId.longValue() == 2l))
		    	sb.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId = :locationLevelId");
		    if(startDate != null && endDate != null){
	            sb.append(" and  ( (date(model.partyMeeting.startDate)>=:startDate and date(model.partyMeeting.startDate)<=:endDate) or (date(model.partyMeeting.endDate)>=:startDate and date(model.partyMeeting.endDate)<=:endDate) ) ");
	        }
		    
		    if(status != null && status.trim().equalsIgnoreCase("maybe")){
	        	sb.append(" and model.partyMeeting.isConducted is not null and model.partyMeeting.isConductedByIvr is not null" +
	        				" and ((model.partyMeeting.isConducted = 'Y' and model.partyMeeting.isConductedByIvr = 'N')" +
	        				" or (model.partyMeeting.isConducted = 'N' and model.partyMeeting.isConductedByIvr = 'Y'))");
	        }
	        /*else if(status != null && status.trim().equalsIgnoreCase("no")){
	        	sb.append(" and ((model.partyMeeting.isConducted = 'N' and model.partyMeeting.isConductedByIvr = 'N')" +
	        				" or (model.partyMeeting.isConducted is null and model.partyMeeting.isConductedByIvr = 'N')" +
	        				" or (model.partyMeeting.isConducted = 'N' and model.partyMeeting.isConductedByIvr is null))");
	        }*/
		    if(distIds != null && distIds.size()>0){
		    	sb.append(" and d.districtId in(:distIds)");
		    }
		    if(constIds != null && constIds.size()>0){
		    		sb.append(" and c.constituencyId in(:constIds) " );
		    	}
		   if(thridPartyStatus != null && !thridPartyStatus.trim().equalsIgnoreCase("ALL")){
			   sb.append(" and model.partyMeeting.thirdPartyStatus = :thridPartyStatus");
		   }
		   else  if(thridPartyStatus != null && thridPartyStatus.trim().equalsIgnoreCase("ALL")){
			   sb.append(" and model.partyMeeting.thirdPartyStatus in ('Y','N')");
		   }
		   sb.append(" order by model.partyMeeting.partyMeetingLevelId,model.partyMeeting.thirdPartyStatus ");
		 Query query = getSession().createQuery(sb.toString());
		 	if(locationLevelId != null && (locationLevelId.longValue() == 3l || locationLevelId.longValue() == 2l))
		 		query.setParameter("locationLevelId", locationLevelId);
		 	if(startDate != null && endDate != null){
		 		query.setDate("startDate", startDate);
		 		query.setDate("endDate", endDate);
		 	 }
			if(distIds != null && distIds.size()>0){
			    	query.setParameterList("distIds", distIds);
			    }
			    
		    if(constIds != null && constIds.size()>0){
		    		query.setParameterList("constIds", constIds);
		    	}
		 
		 	if(thridPartyStatus != null && !thridPartyStatus.trim().equalsIgnoreCase("ALL")){
		 		query.setParameter("thridPartyStatus", thridPartyStatus);
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
				" model.partyMeeting.partyMeetingLevelId, model.partyMeeting.thirdPartyStatus " +
				" from PartyMeetingUpdationDetails model " +
				" where model.isDeleted= 'false'" +
				" and model.partyMeeting.isActive= 'Y' and model.partyMeeting.thirdPartyStatus is not null and model.partyMeeting.thirdPartyStatus != '' ");
		if(startDate != null && endDate !=  null)
			sb.append(" and  ( (date(model.partyMeeting.startDate)>=:startDate and date(model.partyMeeting.startDate)<=:endDate) or (date(model.partyMeeting.endDate)>=:startDate and date(model.partyMeeting.endDate)<=:endDate) ) ");
			sb.append(" group by model.partyMeeting.partyMeetingLevelId,model.partyMeeting.thirdPartyStatus ");
			
		Query query = getSession().createQuery(sb.toString());
		if(startDate != null && endDate !=  null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	public List<Object[]> getDocumentList1(List<Long> partyMeetingIds){
		Query query = getSession().createQuery("select model.partyMeetingUpdationDetailsId," +
		    	" model.name," +
		    	" model.mobileNo," +
		    	" model.comment," +
		    	" model.insertedTime," +
		    	" tdpCadre.memberShipNo " +
				" from PartyMeetingUpdationDetails model" +
				" left join model.tdpCadre tdpCadre" +
				" where model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted= 'false'");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		
		return query.list();
	}
	
}
