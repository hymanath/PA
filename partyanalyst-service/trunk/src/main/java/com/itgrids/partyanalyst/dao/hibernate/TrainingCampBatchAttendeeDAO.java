package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.model.TrainingCampBatchAttendee;

public class TrainingCampBatchAttendeeDAO extends GenericDaoHibernate<TrainingCampBatchAttendee, Long> implements ITrainingCampBatchAttendeeDAO{

	public TrainingCampBatchAttendeeDAO() {
		super(TrainingCampBatchAttendee.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getBatchWiseProgramWiseAcceptedMemeberDetails(String searchType, Date startDate, Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("select model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, model.trainingCampBatchId, date(model.trainingCampBatch.fromDate), date(model.trainingCampBatch.toDate), ");
		queryStr.append(" model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, count(distinct model.trainingCampBatchAttendeeId) ");
		
		queryStr.append(" from TrainingCampBatchAttendee model where model.isDeleted = 'false' ");
		
		if(startDate != null && endDate != null)
		{
			queryStr.append(" and (date(model.attendedTime) >=:startDate and date(model.attendedTime) <=:endDate) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("notStarted"))
		{
			queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.status = 'Not Started' ");
		}
		
		queryStr.append(" group by model.trainingCampBatchId ");
	
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
	}
	
	/*public List<Object[]> getTdpCadreDetailsforASchedule(Long scheduleId){
		
		String query="" +
		" select tcs.training_camp_schedule_id,tcb.training_camp_batch_id,tcb.training_camp_batch_code," +
		"        tc.tdp_cadre_id,tc.first_name,tc.mobile_no,tc.image,ua.constituency_id,c.name, "+
	    "        tccf.cadre_leadership_level_id,tccf.cadre_comminication_skills_status_id,tccf.cadre_leadership_skills_status_id,tccf.cadre_health_status_id "+
			       
		" from training_camp_batch_attendee tcba join training_camp_schedule_invitee tcsi on tcba.training_camp_schedule_invitee_id=tcsi.training_camp_schedule_invitee_id "+ 
		"      join training_camp_batch tcb on tcba.training_camp_batch_id=tcb.training_camp_batch_id "+
	    "      join tdp_cadre tc on tcba.tdp_cadre_id=tc.tdp_cadre_id "+ 
	    "      join user_address ua on  tc.address_id=ua.user_address_id "+
	    "      join constituency c on ua.constituency_id=c.constituency_id "+
		"      join training_camp_schedule tcs on tcsi.training_camp_schedule_id=tcs.training_camp_schedule_id "+
	    "      left join training_camp_cadre_feedback_details tccf on tccf.tdp_cadre_id=tc.tdp_cadre_id and tccf.training_camp_batch_id=tcba.training_camp_batch_id " +
	   
		" where  tcs.training_camp_schedule_id=:scheduleId and tc.is_deleted='N' and tc.enrollment_year=2014 " +
		" order by tcb.training_camp_batch_id asc,tc.first_name asc";
		
		Query sqlQuery=getSession().createSQLQuery(query);
		sqlQuery.setParameter("scheduleId",scheduleId);
		return sqlQuery.list();
    }*/
	
    public List<Object[]> getTdpCadreDetailsforASchedule(List<Long> schedulesList,Long batchId){
		
		String query="" +
		/*" select tcs.training_camp_schedule_id,tcb.training_camp_batch_id,tcb.training_camp_batch_code," +
		"        tc.tdp_cadre_id,tc.first_name,tc.mobile_no,tc.image,ua.constituency_id,c.name, "+
	    "        tccf.cadre_leadership_level_id,tccf.cadre_comminication_skills_status_id,tccf.cadre_leadership_skills_status_id,tccf.cadre_health_status_id," +
	    "        tcb.is_feedback_updatable  "+
			       
		" from training_camp_batch_attendee tcba  join training_camp_batch tcb on tcba.training_camp_batch_id=tcb.training_camp_batch_id "+
		"      join training_camp_schedule tcs on tcs.training_camp_schedule_id=tcb.training_camp_schedule_id "+
	    "      join tdp_cadre tc on tcba.tdp_cadre_id=tc.tdp_cadre_id "+ 
	    "      join user_address ua on  tc.address_id=ua.user_address_id "+
	    "      join constituency c on ua.constituency_id=c.constituency_id "+
	    "      left join training_camp_cadre_feedback_details tccf on tccf.tdp_cadre_id=tc.tdp_cadre_id and tccf.training_camp_batch_id=tcba.training_camp_batch_id " +
	   
		" where  tcs.training_camp_schedule_id in (:schedulesList) and tc.is_deleted='N' and tc.enrollment_year=2014 " +
		" order by tcb.training_camp_batch_id asc,tc.first_name asc";*/
    	
   " select tcs.training_camp_schedule_id,tcb.training_camp_batch_id,tcb.training_camp_batch_code, " +
	    " tc.tdp_cadre_id,tc.first_name,tc.mobile_no,tc.image,ua.constituency_id,c.name as name, " +
	    " tccf.cadre_leadership_level_id,tccf.cadre_comminication_skills_status_id,tccf.cadre_leadership_skills_status_id,tccf.cadre_health_status_id, " +
	    " tcb.is_feedback_updatable,tcl.tdp_committee_level,tr.role,tbc.name," +
	    " tccf.smart_phone_exist,tccf.watsapp_using,tccf.watsapp_share,tccf.facebook_using," +
	    " tce.tdp_committee_level_id,tce.tdp_committee_level_value,tc.membership_id,tccf.health_card_attachment " +
    				       
   " from training_camp_batch_attendee tcba  join training_camp_batch tcb on tcba.training_camp_batch_id=tcb.training_camp_batch_id " +
        " join training_camp_schedule tcs on tcs.training_camp_schedule_id=tcb.training_camp_schedule_id " +
        " join tdp_cadre tc on tcba.tdp_cadre_id=tc.tdp_cadre_id " +
        " join user_address ua on  tc.address_id=ua.user_address_id " +
        " join constituency c on ua.constituency_id=c.constituency_id " +
        " left join training_camp_cadre_feedback_details tccf on tccf.tdp_cadre_id=tc.tdp_cadre_id and tccf.training_camp_batch_id=tcba.training_camp_batch_id " +
        " left join  tdp_committee_member tcm on tcba.tdp_cadre_id=tcm.tdp_cadre_id and tcm.is_active='Y' " +
        " left join tdp_committee_role tcr on tcm.tdp_committee_role_id=tcr.tdp_committee_role_id " +
        " left join tdp_committee tce on tcr.tdp_committee_id=tce.tdp_committee_id " +
        " left join tdp_committee_level tcl on tce.tdp_committee_level_id=tcl.tdp_committee_level_id " +
        " left join tdp_roles tr on tcr.tdp_roles_id=tr.tdp_roles_id " +
        " left join tdp_basic_committee tbc on tce.tdp_basic_committee_id=tbc.tdp_basic_committee_id " +

   " where  tcs.training_camp_schedule_id in (:schedulesList) and tc.is_deleted='N' and tc.enrollment_year=2014  and tcba.is_deleted = 'false'" +
   " and tcba.training_camp_batch_id =:batchId " +
   " order by tcb.training_camp_batch_id asc,tc.first_name asc";
   
  		
		Query sqlQuery=getSession().createSQLQuery(query);
		sqlQuery.setParameterList("schedulesList", schedulesList);
		sqlQuery.setParameter("batchId", batchId);
		//sqlQuery.setParameter("scheduleId",scheduleId);
		return sqlQuery.list();
    }
    
	public List<Object[]> getAchievementsForCadreBySchedule(List<Long> schedulesList,Long batchId){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.achievement " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreAchievement model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId=model1.trainingCampBatchId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainingCampBatch.trainingCampSchedule.trainingCampScheduleId in (:schedulesList) and model.isDeleted = 'false' " +
		" and model.trainingCampBatchId = :batchId ");
		query.setParameterList("schedulesList", schedulesList);
		query.setParameter("batchId", batchId);
		return query.list();
	}
   public List<Object[]> getGoalsForCadreBySchedule(List<Long> schedulesList,Long batchId){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.goal " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreGoal model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId=model1.trainingCampBatchId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainingCampBatch.trainingCampSchedule.trainingCampScheduleId in (:schedulesList) and model.isDeleted = 'false'" +
		" and model.trainingCampBatchId = :batchId ");
		query.setParameterList("schedulesList", schedulesList);
		query.setParameter("batchId", batchId);
		return query.list();
	}
   
   public Object[] getCadreDetailsByCadreIdAndBatchId(Long tdpCadreId,Long batchId){
	   
	   Query query=getSession().createQuery("" +
	   " select model1.tdpCadreId,model1.firstname,model1.mobileNo,model1.userAddress.constituency.name," +
	   " model1.userAddress.district.districtName,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName,model1.image " +
	   " from TrainingCampBatchAttendee model,TdpCadre model1 " +
	   " where model.tdpCadreId=model1.tdpCadreId and " +
	   " model.tdpCadreId=:tdpCadreId and model.trainingCampBatch.trainingCampBatchId=:batchId " +
	   " and model1.isDeleted='N' and model1.enrollmentYear=2014 and model.isDeleted = 'false' ");
	   query.setParameter("tdpCadreId",tdpCadreId);
	   query.setParameter("batchId",batchId);
	   return (Object[])query.uniqueResult();
   }
   
   @SuppressWarnings("unchecked")
   public List<String> getAttendeesForATrainingCampBatch(Long trainingCampBatchId)
   {
	   Query query = getSession().createQuery("SELECT model.tdpCadre.memberShipNo FROM TrainingCampBatchAttendee model where model.trainingCampBatch.trainingCampBatchId = :trainingCampBatchId and model.isDeleted = 'false' ");
	   query.setParameter("trainingCampBatchId",trainingCampBatchId);
	   return query.list();
   }
	
   public List<Object[]> getRunningUpcomingCounts(List<Long> batchIds){
	   Query query = getSession().createQuery(" select model.trainingCampBatchId,count(distinct model.tdpCadreId) " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId in (:batchIds) and model.isDeleted = 'false' " +
	   		" and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false'  " +
	   		" group by model.trainingCampBatchId ");
	   query.setParameterList("batchIds", batchIds);
	   return query.list();
   }
   
   public List<Object[]> getRunningUpcomingCountDetails(List<Long> batchIds){
	   Query query = getSession().createQuery(" select model.trainingCampBatchId, model.tdpCadreId " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId in (:batchIds) and model.isDeleted = 'false' and model.trainingCampBatch.isCancelled='false' and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' ");
	   query.setParameterList("batchIds", batchIds);
	   return (List<Object[]>)query.list();
   }
   
   public Long getConfirmedCountsByBatch(Long batchId,Date fromDate,Date toDate){
	   
	   StringBuilder sb=new StringBuilder();
	   
	   sb.append(" select count(distinct model.tdpCadreId) from TrainingCampBatchAttendee model where model.trainingCampBatchId=:trainingCampBatchId " );
	   
	   if(fromDate!=null && toDate!=null){
		   sb.append(" and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate ");
	   }
	   	
	   sb.append(" and model.isDeleted = 'false' ");
	   
	   Query query=getSession().createQuery(sb.toString());
	   if(fromDate!=null && toDate!=null){
		   query.setParameter("fromDate", fromDate);
		   query.setParameter("toDate", toDate);
	   }
	   query.setParameter("trainingCampBatchId",batchId);
	   return (Long)query.uniqueResult();
   }
   public List<Object[]> getConfirmedCadreByBatch(Long batchId){
	   Query query=getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId,model.tdpCadre.memberShipNo," +
	   		" model.tdpCadre.firstname,model.tdpCadre.mobileNo,model.tdpCadre.image,model.tdpCadre.userAddress.constituency.constituencyId," +
	   		" model.tdpCadre.userAddress.constituency.name  " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where" +
	   		" model.trainingCampBatch.trainingCampBatchId=:batchId and model.isDeleted = 'false'");
	   query.setParameter("batchId",batchId);
	   
	   return query.list();
   }
   
   public List<TrainingCampBatchAttendee> getAttendeeDetailsByInviteeId(Long inviteeId, Long batchId,Long scheduleId){
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select distinct model  from TrainingCampBatchAttendee model where ");
	   queryStr.append(" model.trainingCampScheduleInviteeId=:inviteeId and model.isDeleted = 'false' ");
	   if(batchId != null && batchId.longValue()>0L)
		   queryStr.append(" and model.trainingCampBatchId=:batchId ");
	   if(scheduleId != null && scheduleId.longValue()>0L)
		   queryStr.append(" and model.trainningCampScheduleInvitee.trainingcampScheduleId=:scheduleId ");
	 
	   Query query=getSession().createQuery(queryStr.toString());
	   query.setParameter("inviteeId",inviteeId);
	   
	   if(batchId != null && batchId.longValue()>0L)
		   query.setParameter("batchId",batchId);
	   if(scheduleId != null && scheduleId.longValue()>0L)
		   query.setParameter("scheduleId",scheduleId);	   
	 
	   return query.list();
   }
   
   public List<Long> getRunningUpcomingAttendeeCounts(Long batchId){
	   Query query = getSession().createQuery(" select distinct model.tdpCadreId " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId=:batchId and model.isDeleted = 'false' ");
	   query.setParameter("batchId", batchId);
	   return (List<Long>)query.list();
   }
   
  /* public List<Object[]> getInvitedCounts(List<Long> batchIds){
	   Query query = getSession().createQuery(" select model.trainingCampBatchId,count(distinct model.tdpCadre.tdpCadreId) " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId in (:batchIds) and model.isDeleted='false' " +
	   		" group by model.trainingCampBatchId ");
	   query.setParameterList("batchIds", batchIds);
	   return query.list();
   }*/
   
   /*public List<Object[]> getInvitedDetails(List<Long> batchIds){
	   Query query = getSession().createQuery(" select distinct model.trainingCampBatchId, model.tdpCadre.tdpCadreId " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId in (:batchIds) and model.isDeleted='false' ");
	   query.setParameterList("batchIds", batchIds);
	   return query.list();
   }*/
   
   public List<Object[]> getInvitedCountsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> cadreIdsLsit){
	   StringBuilder sb = new StringBuilder();
	   sb.append(" select model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId,count(distinct model.tdpCadre.tdpCadreId) " +
		   		" from TrainingCampBatchAttendee model " +
		   		" where model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId=:centerId " +
		   		" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId=:programId " +
		   		" and model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false' " +
		   		"and model.isDeleted='false' ");
	   if(fromDate!=null && toDate!=null){
		   sb.append(" and ( date(model.attendedTime) between :fromDate and :toDate) ");
	   }
	   if(cadreIdsLsit != null && cadreIdsLsit.size()>0)
	   {
		   sb.append(" and model.tdpCadre.tdpCadreId not in (:cadreIdsLsit) ");		  
	   }
	   Query query = getSession().createQuery(sb.toString());
	   if(fromDate!=null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
	   }
	   if(cadreIdsLsit!=null && cadreIdsLsit!=null){
		   query.setParameterList("cadreIdsLsit", cadreIdsLsit);
	   }
	   query.setParameter("centerId", centerId);
	   query.setParameter("programId", programId);
	   return query.list();
   }
   
   public List<Object[]> getInvitedCountsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList){
	   
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
	   			" model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
	   			" count(distinct model.tdpCadre.tdpCadreId) from TrainingCampBatchAttendee model where model.trainingCampBatch.attendeeTypeId=2 " +
	   			" and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false' and model.isDeleted='false' ");
	   
	   if(fromDate!=null && toDate!=null){
		   sb.append(" and ( date(model.attendedTime) between :fromDate and :toDate) ");
	   }
	   if(cadreIdsList != null && cadreIdsList.size()>0)
	   {
		   sb.append(" and model.tdpCadre.tdpCadreId not in (:cadreIdsList) ");		  
	   }
	   
	   sb.append(" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   if(fromDate!=null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
	   }
	   if(cadreIdsList!=null && cadreIdsList!=null){
		   query.setParameterList("cadreIdsList", cadreIdsList);
	   }
	   
	   return query.list();
   }
   
public List<Object[]> getInvitedDetailsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList){
	   
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select distinct model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
	   			" model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
	   			"  model.tdpCadre.tdpCadreId from TrainingCampBatchAttendee model where model.trainingCampBatch.attendeeTypeId=2 " +
	   			" and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false' and model.isDeleted='false' ");
	   
	   if(fromDate!=null && toDate!=null){
		   sb.append(" and ( date(model.attendedTime) between :fromDate and :toDate) ");
	   }
	   if(cadreIdsList != null && cadreIdsList.size()>0)
	   {
		   sb.append(" and model.tdpCadre.tdpCadreId not in (:cadreIdsList) ");		  
	   }
	   
	   //sb.append(" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   if(fromDate!=null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
	   }
	   if(cadreIdsList!=null && cadreIdsList!=null){
		   query.setParameterList("cadreIdsList", cadreIdsList);
	   }
	   
	   return query.list();
   }

   public List<Long> getInvitedDetailsForCenter(Long centerId,Long programId,Date fromDate,Date toDate,List<Long> cadreIdsLsit){
	   StringBuilder sb = new StringBuilder();
	   sb.append(" select distinct model.tdpCadre.tdpCadreId " +
		   		" from TrainingCampBatchAttendee model " +
		   		" where model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId=:centerId " +
		   		" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId=:programId " +
		   		" and model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' " +
		   		"and model.isDeleted='false' ");
	   if(fromDate!=null && toDate!=null){
		   sb.append(" and ( date(model.attendedTime) between :fromDate and :toDate) ");
	   }
	   if(cadreIdsLsit != null && cadreIdsLsit.size()>0)
	   {
		   sb.append(" and model.tdpCadre.tdpCadreId not in (:cadreIdsLsit) ");		  
	   }
	   Query query = getSession().createQuery(sb.toString());
	   if(fromDate!=null && toDate!=null){
		   query.setParameter("fromDate", fromDate);
		   query.setParameter("toDate", toDate);
	   }
	   if(cadreIdsLsit!=null && cadreIdsLsit!=null){
		   query.setParameterList("cadreIdsLsit", cadreIdsLsit);
	   }
	   query.setParameter("centerId", centerId);
	   query.setParameter("programId", programId);
	   return (List<Long>)query.list();
   }
   
   
   public List<Object[]> getSpeakersDetails(Date fromdate, Date toDate)
   {
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append("select distinct model.tdpCadreId, model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo,model.tdpCadre.mobileNo," +
	   		" count(model.tdpCadreId) " +
	   		"  from TrainingCampBatchAttendee model where model.isDeleted='false' and model.trainingCampBatch.attendeeTypeId =2 and " +
	   		" model.trainingCampBatch.attendeeType.isDeleted ='false' " );
	   
	   if(fromdate != null && toDate != null)
		   queryStr.append(" and ( date(model.insertedTime) >= :fromdate and date(model.insertedTime) <= :toDate ) ");
	   
	   queryStr.append(" group by model.tdpCadreId order by model.trainingCampBatchId ");
	   
	   Query query = getSession().createQuery(queryStr.toString());
	   if(fromdate != null && toDate != null){
		   query.setParameter("fromdate", fromdate);
		   query.setParameter("toDate", toDate);
	   }
	   
	   return query.list();
   }
   public List<Object[]> getProgramCampBatchDetailsForAMemberBasedOnCadreId(List<Long> cadreIdList,Date fromDate,Date toDate){
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select model.tdpCadreId,model.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchName, " +
	   		"model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId, " +
	   		"model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName, " +
	   		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
	   		"model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
	   		"model.attendedTime " +
	   		" from TrainingCampBatchAttendee model " +
	   		"where model.isDeleted='false' and model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' " +
	   		"and model.tdpCadreId in (:cadreIdList) ");
	   if(fromDate != null && toDate != null){
		   sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	   }
	   
	   sb.append(" group by date(model.attendedTime), model.trainingCampBatchId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   query.setParameterList("cadreIdList", cadreIdList);
	   if(fromDate != null && toDate != null){
		   query.setParameter("fromDate", fromDate);
		   query.setParameter("toDate", toDate);
	   }
	   
	   return query.list();
	   
   }
}
