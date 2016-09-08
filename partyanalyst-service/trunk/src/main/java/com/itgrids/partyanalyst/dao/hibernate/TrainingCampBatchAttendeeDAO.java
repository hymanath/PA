package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

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
		
		queryStr.append(" from TrainingCampBatchAttendee model where model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 " +
				" and model.trainingCampBatch.isCancelled ='false' ");
		
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
   " and tcba.training_camp_batch_id =:batchId and tcb.attendee_type_id = 1 and tcb.is_cancelled='false'  " +
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
		" and model.trainingCampBatchId = :batchId and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
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
		" and model.trainingCampBatchId = :batchId and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
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
	   " and model1.isDeleted='N' and model1.enrollmentYear=2014 and model.isDeleted = 'false' " +
	   " and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' ");
	   query.setParameter("tdpCadreId",tdpCadreId);
	   query.setParameter("batchId",batchId);
	   return (Object[])query.uniqueResult();
   }
   
   @SuppressWarnings("unchecked")
   public List<String> getAttendeesForATrainingCampBatch(Long trainingCampBatchId)
   {
	   Query query = getSession().createQuery("SELECT model.tdpCadre.memberShipNo FROM TrainingCampBatchAttendee model where model.trainingCampBatch.trainingCampBatchId = :trainingCampBatchId and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 ");
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
	   Query query = getSession().createQuery(" select distinct model.trainingCampBatchId, model.tdpCadreId " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId in (:batchIds) and model.isDeleted = 'false' and model.trainingCampBatch.isCancelled='false' and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' ");
	   query.setParameterList("batchIds", batchIds);
	   return (List<Object[]>)query.list();
   }
   
   public Long getConfirmedCountsByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList){
	   
	   StringBuilder sb=new StringBuilder();
	   
	   sb.append(" select count(distinct model.tdpCadre.tdpCadreId) from TrainingCampBatchAttendee model where  " +
	   		" model.trainingCampBatch.attendeeTypeId=1 " );
	   if(batchId != null && batchId.longValue()>0L)
	   {
		   sb.append(" and model.trainingCampBatchId=:trainingCampBatchId ");
		   
		   if(fromDate!=null && toDate!=null){
			   sb.append(" and (date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate) ");
		   }
	   }
	   else if(searchTypeStr != null && fromDate!=null && toDate!=null){
		   if(searchTypeStr.trim().equalsIgnoreCase("upcoming"))
			   sb.append(" and ( date(model.trainingCampBatch.fromDate) > :fromDate and date(model.trainingCampBatch.toDate) > :toDate) ");
		   else  if(searchTypeStr.trim().equalsIgnoreCase("running"))
			   sb.append(" and ( (:fromDate between date(model.trainingCampBatch.fromDate)  and date(model.trainingCampBatch.toDate) ) or " +
			   		" (:toDate between date(model.trainingCampBatch.fromDate)  and date(model.trainingCampBatch.toDate) ) ) ");
		   else  if(searchTypeStr.trim().equalsIgnoreCase("completed"))
			   sb.append(" and (date(model.trainingCampBatch.fromDate) < :fromDate and date(model.trainingCampBatch.toDate) < :toDate ) ");
	   }
	   if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   sb.append(" and model.tdpCadre.tdpCadreId not in (:staffCadreIdsList) " );
	   
	   sb.append(" and model.isDeleted = 'false' ");
	   
	   Query query=getSession().createQuery(sb.toString());
	   if(fromDate!=null && toDate!=null){
		   query.setParameter("fromDate", fromDate);
		   query.setParameter("toDate", toDate);
	   }
	   if(batchId != null && batchId.longValue()>0L)
		   query.setParameter("trainingCampBatchId",batchId);
	   if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   query.setParameterList("staffCadreIdsList",staffCadreIdsList);
	   
	   return (Long)query.uniqueResult();
   }
   public List<Object[]> getConfirmedCadreByBatch(Long batchId){
	   Query query=getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId,model.tdpCadre.memberShipNo," +
	   		" model.tdpCadre.firstname,model.tdpCadre.mobileNo,model.tdpCadre.image,model.tdpCadre.userAddress.constituency.constituencyId," +
	   		" model.tdpCadre.userAddress.constituency.name  " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where" +
	   		" model.trainingCampBatch.trainingCampBatchId=:batchId and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 ");
	   query.setParameter("batchId",batchId);
	   
	   return query.list();
   }
   
   public List<TrainingCampBatchAttendee> getAttendeeDetailsByInviteeId(Long inviteeId, Long batchId,Long scheduleId){
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select distinct model  from TrainingCampBatchAttendee model where ");
	   queryStr.append(" model.trainingCampScheduleInviteeId=:inviteeId and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 ");
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
	   		" where model.trainingCampBatchId=:batchId and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 ");
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
		   		"and model.isDeleted='false' and model.trainingCampBatch.isCancelled='false' ");
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
	   		" model.trainingCampBatch.attendeeType.isDeleted ='false' and  model.trainingCampBatch.isCancelled='false' " );
	   
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
	   		"and model.tdpCadreId in (:cadreIdList) and model.trainingCampBatch.isCancelled = 'false' ");
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
   
   public Long getTotalSpeakersCountDetails(List<Long> cadreIdList,Date fromDate,Date toDate){
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select count(distinct model.tdpCadreId) from TrainingCampBatchAttendee model " +
	   		" where model.isDeleted='false' and model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' " +
	   		" and model.tdpCadreId not in (:cadreIdList) and model.trainingCampBatch.isCancelled = 'false' ");
	   if(fromDate != null && toDate != null){
		   sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	   }
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   query.setParameterList("cadreIdList", cadreIdList);
	   if(fromDate != null && toDate != null){
		   query.setParameter("fromDate", fromDate);
		   query.setParameter("toDate", toDate);
	   }
	   
	   return (Long) query.uniqueResult();
	   
   }
   
   public List<Long> getTodaySpeakersDetails(Date todayDate){
	   Query query = getSession().createQuery("select tcba.tdpCadreId from TrainingCampBatchAttendee tcba, TrainingCampBatch tcb " +
	   		" where tcba.trainingCampBatchId=tcb.trainingCampBatchId and tcb.attendeeTypeId=2 and date(tcba.attendedTime)=:todayDate " +
	   		" and tcb.isCancelled = 'false'  " +
	   		"order by tcba.trainingCampBatchAttendeeId desc");
	   query.setParameter("todayDate", todayDate);
	   return query.list();
   }
   
   public Long getInvitedCountByLocation(Long id,String searchType){
	   
	   StringBuilder str = new StringBuilder();
	   str.append("select count(distinct model.tdpCadreId)" +
	   					" from TrainingCampBatchAttendee model" +
	   					" where");
	   
	   if(searchType.equalsIgnoreCase("panchayat"))
		   str.append(" model.tdpCadre.userAddress.panchayat.panchayatId = :id and");
	   else if(searchType.equalsIgnoreCase("ward"))
		   str.append(" model.tdpCadre.userAddress.ward.constituencyId = :id and");
	   else if(searchType.equalsIgnoreCase("mandal"))
		   str.append(" model.tdpCadre.userAddress.tehsil.tehsilId = :id and");
	   else if(searchType.equalsIgnoreCase("leb"))
		   str.append(" model.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :id and");
	   else if(searchType.equalsIgnoreCase("constituency"))
		   str.append(" model.tdpCadre.userAddress.constituency.constituencyId = :id and");
	   else if(searchType.equalsIgnoreCase("parliament"))
		   str.append(" model.tdpCadre.userAddress.parliamentConstituency.constituencyId = :id and");
	   else if(searchType.equalsIgnoreCase("district"))
		   str.append(" model.tdpCadre.userAddress.district.districtId = :id " +
		   		" and model.tdpCadre.isDeleted = 'N'" +
		   		" and model.tdpCadre.enrollmentYear = '2014' and ");
	   
	   str.append(" model.isDeleted = 'false'");
	   Query query = getSession().createQuery(str.toString());
	   
	   if(searchType != null)
		   query.setParameter("id", id);
	   
	   return (Long) query.uniqueResult();
   }
   
   public List<Long> getInvitedCadreIdsByLocation(Long id,String searchType){
	   
	   StringBuilder str = new StringBuilder();
	   str.append("select distinct model.tdpCadreId" +
	   					" from TrainingCampBatchAttendee model" +
	   					" where");
	   
	   if(searchType.equalsIgnoreCase("panchayat"))
		   str.append(" model.tdpCadre.userAddress.panchayat.panchayatId = :id and");
	   else if(searchType.equalsIgnoreCase("ward"))
		   str.append(" model.tdpCadre.userAddress.ward.constituencyId = :id and");
	   else if(searchType.equalsIgnoreCase("mandal"))
		   str.append(" model.tdpCadre.userAddress.tehsil.tehsilId = :id and");
	   else if(searchType.equalsIgnoreCase("leb"))
		   str.append(" model.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :id and");
	   else if(searchType.equalsIgnoreCase("constituency"))
		   str.append(" model.tdpCadre.userAddress.constituency.constituencyId = :id and");
	   else if(searchType.equalsIgnoreCase("parliament"))
		   str.append(" model.tdpCadre.userAddress.parliamentConstituency.constituencyId = :id and");
	   else if(searchType.equalsIgnoreCase("district"))
		   str.append(" model.tdpCadre.userAddress.district.districtId = :id and");
	   
	   str.append(" model.isDeleted = 'false'");
	   
	   Query query = getSession().createQuery(str.toString());
	   
	   if(searchType != null)
		   query.setParameter("id", id);
	   
	   return query.list();
   }
   public Long getTotalInvitedForTrainingCampStateLevel(Long campId, Long programId){  
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select count(distinct TC.tdpCadreId) from TrainingCampBatchAttendee TCBA, TdpCadre TC " +  
						   " where " +
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId = (:campId) and TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId = (:programId) and " +
						   " TCBA.tdpCadre.tdpCadreId = TC.tdpCadreId and TCBA.isDeleted = 'false' ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("campId", campId);
		query.setParameter("programId", programId);
		return (Long) query.uniqueResult();   
		
	}
   public List<Object[]> getStateDistrictTrainingProgramInvitedDetails(Long campId, Long programId){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select TCL.tdp_committee_level_id as id, TCL.tdp_committee_level as level,count(distinct TCM.tdp_cadre_id) as total from "+
			   			  " tdp_committee_member TCM, tdp_committee_role TCR, tdp_committee TC, tdp_committee_level TCL, tdp_cadre TDP, "+
			   			  " training_camp_batch_attendee TCBA, training_camp_batch TCB, training_camp CAMP, training_camp_program TCP, training_camp_schedule TCS  "+
			   			  " where  "+
			   			  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and  "+
			   			  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and  "+
			   			  " TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and  "+
			   			  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and  "+
			   			  " TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and  "+
			   			  " TCR.tdp_committee_id = TC.tdp_committee_id and  "+
			   			  " TC.tdp_committee_level_id = TCL.tdp_committee_level_id and  "+
			   			  " TCM.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014  "+
			   			  " group by  "+
			   			  " TCL.tdp_committee_level_id ");
	   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("level", Hibernate.STRING).addScalar("total", Hibernate.LONG);
	   query.setParameter("campId", campId);
	   query.setParameter("programId", programId);
	   return query.list();
   }
   public List<Object[]> getMlaMpInchargeTrainingProgramInvitedDetails(Long campId, Long programId){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select PRT.public_representative_type_id as id,PRT.position as position ,count(distinct TDP.tdp_cadre_id) as total" +
	   					  " from "+
	   					  " candidate CND, tdp_cadre_candidate TCC, public_representative PR, public_representative_type PRT, tdp_cadre TDP, "+
	   					  " training_camp_batch_attendee TCBA, training_camp_batch TCB, training_camp TC, training_camp_program TCP, training_camp_schedule TCS "+
	   					  " where "+
	   					  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
	   					  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
	   					  " TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+ 
	   					  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
	   					  " TCC.candidate_id = CND.candidate_id and "+
	   					  " PR.candidate_id = CND.candidate_id and "+
	   					  " PR.public_representative_type_id = PRT.public_representative_type_id and "+
	   					  " TCC.tdp_cadre_id = TDP.tdp_cadre_id "+
	   					  " group by PRT.public_representative_type_id" );
	   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("position", Hibernate.STRING).addScalar("total", Hibernate.LONG);
	   query.setParameter("campId", campId);
	   query.setParameter("programId", programId);
	   return query.list(); 
   }
   public List<Object[]> getDistWiseInvitedMembers(Long stateId, Long campId, Long programId){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select D.district_id as id, D.district_name as name, count(distinct TDP.tdp_cadre_id) as total from "+
			   			  " training_camp_batch_attendee TCBA, training_camp_batch TCB, training_camp TC, training_camp_program TCP, training_camp_schedule TCS, tdp_cadre TDP, user_address UA, district D "+
			   			  " where "+
			   			  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
			   			  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
			   			  " TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
			   			  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
			   			  " TDP.address_id = UA.user_address_id and " +
			   			  " (D.district_id BETWEEN 11 and 23) and "+
			   			  " UA.district_id = D.district_id "+    
                          " group by D.district_id order by D.district_id ");
	   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING).addScalar("total", Hibernate.LONG);
	   query.setParameter("campId", campId);
	   query.setParameter("programId", programId);
	  
	   return query.list();  
   }
   
   public List<Object[]> getInvitedMemberCadreId(Long distId, Long programId){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select distinct D.district_id as id,D.district_name as name,  TDP.tdp_cadre_id as tid from "+  
			   			  " training_camp_batch_attendee TCBA, training_camp_batch TCB, training_camp TC, training_camp_program TCP, training_camp_schedule TCS, "+
			   			  " tdp_cadre TDP, user_address UA, district D "+
			   			  " where "+
			   			  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and  "+
			   			  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
			   			  " TCS.training_camp_program_id = (:programId) and "+
			   			  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
			   			  " TDP.address_id = UA.user_address_id and  "+
			   			  " D.district_id = (:distId) and "+
			   			  " UA.district_id = D.district_id "+
			   			  " order by D.district_id ");
	   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING).addScalar("tid", Hibernate.LONG);
	   query.setParameter("distId", distId);
	   query.setParameter("programId", programId);  
	  
	   return query.list();  
   }
}
