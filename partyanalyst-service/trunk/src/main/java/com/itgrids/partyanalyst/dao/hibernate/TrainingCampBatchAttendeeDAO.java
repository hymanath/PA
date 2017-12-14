package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.model.TrainingCampBatchAttendee;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
    public List<Object[]> getTdpCadreDetailsforASchedule(List<Long> schedulesList,Long batchId,Long enrollmentYearId){
		
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
   " and tcba.training_camp_batch_id =:batchId and tcb.attendee_type_id = 1 and tcb.is_cancelled='false'  ";
   if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
	   query=query+ " and tcs.enrollment_year_id =:enrollmentYearId ";
   
   query = query+ " order by tcb.training_camp_batch_id asc,tc.first_name asc";
		Query sqlQuery=getSession().createSQLQuery(query);
		sqlQuery.setParameterList("schedulesList", schedulesList);
		sqlQuery.setParameter("batchId", batchId);
		//sqlQuery.setParameter("scheduleId",scheduleId);
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 sqlQuery.setParameter("enrollmentYearId",enrollmentYearId);
		 
		return sqlQuery.list();
    }
    
	public List<Object[]> getAchievementsForCadreBySchedule(List<Long> schedulesList,Long batchId,Long enrollmentYearId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
		 
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.achievement " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreAchievement model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId=model1.trainingCampBatchId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainingCampBatch.trainingCampSchedule.trainingCampScheduleId in (:schedulesList) and model.isDeleted = 'false' " +
		" and model.trainingCampBatchId = :batchId and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' "+queryStr.toString()+"");
		query.setParameterList("schedulesList", schedulesList);
		query.setParameter("batchId", batchId);
		if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			   query.setParameter("enrollmentYearId",enrollmentYearId);
		return query.list();
	}
   public List<Object[]> getGoalsForCadreBySchedule(List<Long> schedulesList,Long batchId,Long enrollmentYearId){
	   StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
		 
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.goal " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreGoal model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId=model1.trainingCampBatchId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainingCampBatch.trainingCampSchedule.trainingCampScheduleId in (:schedulesList) and model.isDeleted = 'false'" +
		" and model.trainingCampBatchId = :batchId and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' "+queryStr.toString()+" ");
		query.setParameterList("schedulesList", schedulesList);
		query.setParameter("batchId", batchId);
		  if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			   query.setParameter("enrollmentYearId",enrollmentYearId);
		  
		return query.list();
	}
   
   public List<Object[]> getCadreDetailsByCadreIdAndBatchId(Long tdpCadreId,Long batchId,Long enrollmentYearId){
	   StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
		 
	   Query query=getSession().createQuery("" +
	   " select model1.tdpCadreId,model1.firstname,model1.mobileNo,model1.userAddress.constituency.name," +
	   " model1.userAddress.district.districtName,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName,model1.image " +
	   " from TrainingCampBatchAttendee model,TdpCadre model1 " +
	   " where model.tdpCadreId=model1.tdpCadreId and " +
	   " model.tdpCadreId=:tdpCadreId and model.trainingCampBatch.trainingCampBatchId=:batchId " +
	   " and model1.isDeleted='N' and model1.enrollmentYear=2014 and model.isDeleted = 'false' " +
	   " and model.trainingCampBatch.attendeeTypeId=1 and model.trainingCampBatch.isCancelled ='false' "+queryStr.toString()+"");
	   query.setParameter("tdpCadreId",tdpCadreId);
	   query.setParameter("batchId",batchId);
	   if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
		   query.setParameter("enrollmentYearId",enrollmentYearId);
	   return query.list();
   }
   
   @SuppressWarnings("unchecked")
   public List<String> getAttendeesForATrainingCampBatch(Long trainingCampBatchId)
   {
	   Query query = getSession().createQuery("SELECT model.tdpCadre.memberShipNo FROM TrainingCampBatchAttendee model where model.trainingCampBatch.trainingCampBatchId = :trainingCampBatchId and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 ");
	   query.setParameter("trainingCampBatchId",trainingCampBatchId);
	   return query.list();
   }
	
   public List<Object[]> getRunningUpcomingCounts(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   StringBuilder sb=new StringBuilder();
	   sb.append(" select model.trainingCampBatchId,count(distinct model.tdpCadreId) " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where" );
	   if(batchIds != null && batchIds.size()>0){
	   	 sb.append(" model.trainingCampBatchId in (:batchIds) and " );
	    }
	   	sb.append("  model.isDeleted = 'false' " +
	   		" and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' and model.trainingCampBatch.isCancelled='false'  " );
	   	if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
	   	 sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in(:enrollmentYearIds)");
	   	}
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and  model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	   	}
		sb.append(" group by model.trainingCampBatchId ");
	   Query query =getSession().createQuery(sb.toString());
	   if(batchIds != null && batchIds.size()>0)
	      query.setParameterList("batchIds", batchIds);
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   	}
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds);
	   	}
	   return query.list();
   }
   
   public List<Object[]> getRunningUpcomingCountDetails(List<Long> batchIds,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   StringBuilder sb=new StringBuilder();
	   sb.append(" select distinct model.trainingCampBatchId, model.tdpCadreId " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId in (:batchIds) and model.isDeleted = 'false' and model.trainingCampBatch.isCancelled='false' " +
	   		" and model.trainingCampBatch.attendeeType.attendeeTypeId=1 and model.trainingCampBatch.attendeeType.isDeleted='false' ");
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
 	        }
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds) ");
	   }
	   Query query =getSession().createQuery(sb.toString());
	   query.setParameterList("batchIds", batchIds);
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   }
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds);
	   }
	   return (List<Object[]>)query.list();
   }
   
   public List<Long> getConfirmedCountsByBatch(Long batchId,Date fromDate,Date toDate,String searchTypeStr,List<Long> staffCadreIdsList,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   
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
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId in(:enrollmentYearIds) ");
	   
	   sb.append(" and model.isDeleted = 'false' ");
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	   }
	   Query query=getSession().createQuery(sb.toString());
	   if(fromDate!=null && toDate!=null){
		   query.setParameter("fromDate", fromDate);
		   query.setParameter("toDate", toDate);
	   }
	   if(batchId != null && batchId.longValue()>0L)
		   query.setParameter("trainingCampBatchId",batchId);
	   if(staffCadreIdsList != null && staffCadreIdsList.size()>0)
		   query.setParameterList("staffCadreIdsList",staffCadreIdsList);
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		   query.setParameterList("enrollmentYearIds",enrollmentYearIds);
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds",programYearIds);
	   }
	   return query.list();
   }
   public List<Object[]> getConfirmedCadreByBatch(Long batchId,Long enrollmentYearId){
	   StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
		 
	   Query query=getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId,model.tdpCadre.memberShipNo," +
	   		" model.tdpCadre.firstname,model.tdpCadre.mobileNo,model.tdpCadre.image,model.tdpCadre.userAddress.constituency.constituencyId," +
	   		" model.tdpCadre.userAddress.constituency.name  " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where" +
	   		" model.trainingCampBatch.trainingCampBatchId=:batchId and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 "+queryStr.toString()+" ");
	   query.setParameter("batchId",batchId);
	   if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
		   query.setParameter("enrollmentYearId",enrollmentYearId);
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
   
   public List<Long> getRunningUpcomingAttendeeCounts(Long batchId,List<Long> enrollmentYearIds,List<Long> programYearIds){ 
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select distinct model.tdpCadreId " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId=:batchId and model.isDeleted = 'false' and model.trainingCampBatch.attendeeTypeId=1 ");
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0) 
		   queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId in(:enrollmentYearIds) ");
	   if(programYearIds != null && programYearIds.size()>0){
		   queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	   }
	   Query query=getSession().createQuery(queryStr.toString());
	   query.setParameter("batchId", batchId);
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds);
 	        }
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0)
		   query.setParameterList("enrollmentYearIds",enrollmentYearIds);	   
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
   
   public List<Object[]> getInvitedCountsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   
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
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
 	        }
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
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
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   }
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds);
	   }
	   return query.list();
   }
   
public List<Object[]> getInvitedDetailsForCenterAndProgram(Date fromDate,Date toDate,List<Long> cadreIdsList,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   
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
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
 	        }
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
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
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   }
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds);
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
   
   
   public List<Object[]> getSpeakersDetails(Date fromdate, Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds)
   {
	   StringBuilder queryStr = new StringBuilder();
	   queryStr.append("select distinct model.tdpCadreId, model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo,model.tdpCadre.mobileNo," +
	   		" count(model.tdpCadreId) " +
	   		"  from TrainingCampBatchAttendee model where model.isDeleted='false' and model.trainingCampBatch.attendeeTypeId =2 and " +
	   		" model.trainingCampBatch.attendeeType.isDeleted ='false' and  model.trainingCampBatch.isCancelled='false' " );
	   
	   if(fromdate != null && toDate != null)
		   queryStr.append(" and ( date(model.insertedTime) >= :fromdate and date(model.insertedTime) <= :toDate ) ");
	   
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
 		  queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
 	        }
	   if(programYearIds!= null && programYearIds.size()>0){
		   queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programYearIds)");
	   }
	   queryStr.append(" group by model.tdpCadreId order by model.trainingCampBatchId ");
	   
	   Query query = getSession().createQuery(queryStr.toString());
	   if(fromdate != null && toDate != null){
		   query.setParameter("fromdate", fromdate);
		   query.setParameter("toDate", toDate);
	   }
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   }
	   if(programYearIds!= null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds);
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
   
   public List<Long> getTotalSpeakersCountDetails(List<Long> cadreIdList,Date fromDate,Date toDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select count(distinct model.tdpCadreId) from TrainingCampBatchAttendee model " +
	   		" where model.isDeleted='false' and model.trainingCampBatch.attendeeTypeId=2 and model.trainingCampBatch.attendeeType.isDeleted='false' " +
	   		" and model.tdpCadreId not in (:cadreIdList) and model.trainingCampBatch.isCancelled = 'false' ");
	   if(fromDate != null && toDate != null){
		   sb.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	   }
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
 	        }
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
	   }
	   Query query = getSession().createQuery(sb.toString());
	   
	   query.setParameterList("cadreIdList", cadreIdList);
	   if(fromDate != null && toDate != null){
		   query.setParameter("fromDate", fromDate);
		   query.setParameter("toDate", toDate);
	   }
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
		   query.setParameterList("enrollmentYearIds", enrollmentYearIds);
	   }
       if(programYearIds != null && programYearIds.size()>0){
    	   query.setParameterList("programYearIds", programYearIds); 
	   }
	   return query.list();
	   
   }
   
   public List<Long> getTodaySpeakersDetails(Date todayDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
	   StringBuilder sb = new StringBuilder();
	   sb.append("select tcba.tdpCadreId from TrainingCampBatchAttendee tcba, TrainingCampBatch tcb " +
	   		" where tcba.trainingCampBatchId=tcb.trainingCampBatchId and tcb.attendeeTypeId=2 and date(tcba.attendedTime)=:todayDate " +
	   		" and tcb.isCancelled = 'false'  " );
	   if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
 		  sb.append(" and tcb.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
 	        }
	   if(programYearIds != null && programYearIds.size()>0){
		   sb.append(" and tcb.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)"); 
	   }
	   sb.append("order by tcba.trainingCampBatchAttendeeId desc");
	   Query query=getSession().createQuery(sb.toString());
	   query.setParameter("todayDate", todayDate);
	   if(programYearIds != null && programYearIds.size()>0){
		   query.setParameterList("programYearIds", programYearIds);
	   }
	   return query.list();
   }
   
   public List<Long> getInvitedCountByLocation(Long id,String searchType){
	   
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
	   
	   return query.list();
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
   public List<Object[]> getTotalInvitedForTrainingCampStateLevel(List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds){  
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
						   " count(distinct TCBA.tdpCadreId) from TrainingCampBatchAttendee TCBA, TdpCadre TC " +  
						   " where TCBA.trainingCampBatch.attendeeTypeId = 1 and " +
						   " date(TCBA.trainingCampBatch.fromDate) <= (:toDate) and" );    
		if(stateId.longValue() == 1L){
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		if(programIdList != null && programIdList.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIdList) and " );
		}
		if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollYrIds)  and " );
		}
		queryString.append(" TCBA.tdpCadre.tdpCadreId = TC.tdpCadreId and TCBA.isDeleted = 'false' and TCBA.trainingCampBatch.isCancelled='false' " +
						   " group by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId " +
						   " order by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId ");
		Query query = getSession().createQuery(queryString.toString());
		if(programIdList != null && programIdList.size() >0){
			query.setParameterList("programIdList", programIdList);
		}
		query.setDate("toDate", toDate); 
		
		if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
		}
		return query.list();       
		
	}
   public List<Object[]> getStateDistrictTrainingProgramInvitedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select " +
	   					  " TCL.tdp_committee_level_id as id, " +
	   					  " TCL.tdp_committee_level as level, " +
	   					  " count(distinct TCM.tdp_cadre_id) as total " +
	   					  " from "+
			   			  " tdp_committee_member TCM, " + 
			   			  " tdp_committee_role TCR, " +
			   			  " tdp_committee TC, " +
			   			  " tdp_committee_level TCL, " +
			   			  " tdp_cadre TDP, "+
			   			  " training_camp_batch_attendee TCBA, " +
			   			  " training_camp_batch TCB, " +
			   			  " training_camp CAMP, " +
			   			  " training_camp_program TCP, " +
			   			  " training_camp_schedule TCS, " +
			   			  " user_address UA, district D "+
			   			  " where  "+
			   			  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and  "+
			   			  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and  "+
			   			  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and  " +
			   			  " TDP.address_id = UA.user_address_id and " +  
			   			  " UA.district_id = D.district_id and " +
			   			  " date(TCB.from_date) <= (:toDate) and ");
	   if(programIdList != null && programIdList.size() > 0){
		   queryString.append(" TCS.training_camp_program_id in (:programIdList) and  ");
	   }
	   if(stateId == 1l){
		   queryString.append(" (D.district_id between 1 and 23) and ");
	   }else{  
		   queryString.append(" (D.district_id between 1 and 10) and ");
	   }
	   
	   if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" TCS.enrollment_year_id in (:enrollYrIds)  and " );
		}
	   queryString.append(" TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and  "+
			   			  " TCR.tdp_committee_id = TC.tdp_committee_id and  "+
			   			  " TC.tdp_committee_level_id = TCL.tdp_committee_level_id and  "+
			   			  " TCM.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014  "+
			   			  " group by  "+
			   			  " TCL.tdp_committee_level_id ");
	  SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("level", Hibernate.STRING).addScalar("total", Hibernate.LONG);
	  //query.setParameter("campId", campId);
	  if(programIdList != null && programIdList.size() > 0){
		  query.setParameterList("programIdList", programIdList);
	  }
	  query.setDate("toDate", toDate);
	  if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
		}
	  return query.list();
   }
   public List<Object[]> getMlaMpInchargeTrainingProgramInvitedDetails(Long campId, List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select " +
	   					  " PRT.public_representative_type_id as id, " +
	   					  " PRT.position as position , " +
	   					  " count(distinct TDP.tdp_cadre_id) as total " +
	   					  " from "+
	   					  " candidate CND, tdp_cadre_candidate TCC, " +
	   					  " public_representative PR, " +
	   					  " public_representative_type PRT, " +
	   					  " tdp_cadre TDP, "+
	   					  " training_camp_batch_attendee TCBA, " +
	   					  " training_camp_batch TCB, " +
	   					  " training_camp TC, " +
	   					  " training_camp_program TCP, " +
	   					  " training_camp_schedule TCS, " +
	   					  " user_address UA, " +
	   					  " district D "+
	   					  " where "+
	   					  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
	   					  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
	   					  //" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+ 
	   					  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
	   					  " TCC.candidate_id = CND.candidate_id and "+
	   					  " PR.candidate_id = CND.candidate_id and "+
	   					  " PR.public_representative_type_id = PRT.public_representative_type_id and "+
	   					  " TCC.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 and " +
	   					  " TDP.address_id = UA.user_address_id and " +  
			   			  " UA.district_id = D.district_id and " +
			   			  " date(TCB.from_date) <= (:toDate) and ");
	   if(programIdList != null && programIdList.size() >0){
		   queryString.append(" TCS.training_camp_program_id in (:programIdList) and ");
	   }
	   if(stateId == 1l){
		   queryString.append(" (D.district_id between 1 and 23) ");
	   }else{  
		   queryString.append(" (D.district_id between 1 and 10) ");
	   }
	   if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" and TCS.enrollment_year_id in (:enrollYrIds)   " );
		}
	   queryString.append(" group by PRT.public_representative_type_id" );
	   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("position", Hibernate.STRING).addScalar("total", Hibernate.LONG);
	   //query.setParameter("campId", campId);
	   if(programIdList != null && programIdList.size() >0){
		   query.setParameterList("programIdList", programIdList); 
	   }
	   query.setDate("toDate", toDate); 
	   if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
		}
	   return query.list(); 
   }
   public List<Object[]> getDistWiseInvitedMembers(List<Long> programIdList,Long stateId,Date toDate,List<Long> enrollYrIds){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select " +
	   					  " TCP.training_camp_program_id as programId,TCP.program_name as programName,D.district_id as id, D.district_name as name, " +
	   					  " count(distinct TDP.tdp_cadre_id) as total " +
	   					  " from "+
			   			  " training_camp_batch_attendee TCBA, training_camp_batch TCB, " +
			   			  " training_camp TC, training_camp_program TCP, training_camp_schedule TCS, tdp_cadre TDP, user_address UA, district D "+
			   			  " where "+
			   			  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
			   			  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and " +
			   			  " TCS.training_camp_program_id = TCP.training_camp_program_id and "+
			   			  //" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+
			   			  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
			   			  " TDP.address_id = UA.user_address_id and " +
			   			  " UA.district_id = D.district_id and " +
			   			  " date(TCB.from_date) <= (:toDate) and ");  
	   if(programIdList != null && programIdList.size() >0){
		   queryString.append(" TCP.training_camp_program_id in (:programIdList) and ");
	   }
	   if(stateId == 1l){
		   queryString.append(" (D.district_id BETWEEN 1 and 23) ");
	   }else{
		   queryString.append(" (D.district_id BETWEEN 1 and 10) ");
	   }
	   if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" and TCS.enrollment_year_id in (:enrollYrIds)   " );
		}
			   			
	   queryString.append(" group by TCP.training_camp_program_id,D.district_id order by TCP.training_camp_program_id,D.district_id ");
	   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("programId", Hibernate.LONG).addScalar("programName", Hibernate.STRING).addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING).addScalar("total", Hibernate.LONG);
	   //query.setParameter("campId", campId);
	   if(programIdList != null && programIdList.size() >0){
		   query.setParameterList("programIdList", programIdList); 
	   }
	   query.setDate("toDate", toDate);  
	   if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
		}
	  
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
   public List<Object[]> getStDistTrainingPrgInvitedDtlsCmtLvL(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select distinct " +
	   					  " TCL.tdp_committee_level_id as id, " +
	   					  " TCL.tdp_committee_level as level," +
	   					  " TCM.tdp_cadre_id as cadreId " +
	   					  " from "+  
			   			  " tdp_committee_member TCM, " +
			   			  " tdp_committee_role TCR, " +
			   			  " tdp_committee TC, " +
			   			  " tdp_committee_level TCL, " +
			   			  " tdp_cadre TDP, "+
			   			  " training_camp_batch_attendee TCBA, " +
			   			  " training_camp_batch TCB, training_camp CAMP, " +
			   			  " training_camp_program TCP, " +
			   			  " training_camp_schedule TCS, " +
			   			  " user_address UA, district D "+
			   			  " where  "+
			   			  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and  "+
			   			  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and  "+
			   			  " TCS.training_camp_program_id in (:programIdList) and  "+
			   			  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and  " +
			   			  " TDP.address_id = UA.user_address_id and " +  
			   			  " UA.district_id = D.district_id and " +
			   			  " date(TCB.from_date) <= (:toDate) and ");  
	   if(stateId == 1l){
		   queryString.append(" (D.district_id between 1 and 23) and ");
	   }else{  
		   queryString.append(" (D.district_id between 1 and 10) and ");
	   }
	   queryString.append(" TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and  "+
			   			  " TCR.tdp_committee_id = TC.tdp_committee_id and  "+
			   			  " TC.tdp_committee_level_id = TCL.tdp_committee_level_id and  " +
			   			  " TCL.tdp_committee_level_id in (:designationIdList) and "+
			   			  " TCM.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 ");
	  SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("level", Hibernate.STRING).addScalar("cadreId", Hibernate.LONG);
	  //query.setParameter("campId", campId);
	  query.setParameterList("programIdList", programIdList);
	  query.setDate("toDate", toDate);
	  query.setParameterList("designationIdList", designationIdList);
	  return query.list();
   }
   public List<Object[]> getMlaMpInchargeTrngPrgInvitedDtlsPubRep(Long campId, List<Long> programIdList, Long stateId, Date toDate, List<Long> designationIdList){
	   StringBuilder queryString = new StringBuilder();
	   queryString.append(" select distinct " +
	   					  " PRT.public_representative_type_id as id, " +
	   					  " PRT.position as position , " +
	   					  " TDP.tdp_cadre_id as tdpCadre " +
	   					  " from "+
	   					  " candidate CND, tdp_cadre_candidate TCC, " +
	   					  " public_representative PR, " +
	   					  " public_representative_type PRT, " +
	   					  " tdp_cadre TDP, "+
	   					  " training_camp_batch_attendee TCBA, " +
	   					  " training_camp_batch TCB, " +
	   					  " training_camp TC, " +
	   					  " training_camp_program TCP, " +
	   					  " training_camp_schedule TCS, " +
	   					  " user_address UA, " +
	   					  " district D "+
	   					  " where "+
	   					  " TCBA.training_camp_batch_id = TCB.training_camp_batch_id and "+
	   					  " TCB.training_camp_schedule_id = TCS.training_camp_schedule_id and "+
	   					  //" TCS.training_camp_id = (:campId) and TCS.training_camp_program_id = (:programId) and "+ 
	   					  " TCS.training_camp_program_id in (:programIdList) and "+ 
	   					  " TCBA.tdp_cadre_id = TDP.tdp_cadre_id and "+
	   					  " TCC.candidate_id = CND.candidate_id and "+
	   					  " PR.candidate_id = CND.candidate_id and "+
	   					  " PR.public_representative_type_id = PRT.public_representative_type_id and " +
	   					  " PRT.public_representative_type_id in (:designationIdList) and "+
	   					  " TCC.tdp_cadre_id = TDP.tdp_cadre_id and TDP.enrollment_year = 2014 and " +
	   					  " TDP.address_id = UA.user_address_id and " +  
			   			  " UA.district_id = D.district_id and " +
			   			  " date(TCB.from_date) <= (:toDate) and ");
	   if(stateId == 1l){
		   queryString.append(" (D.district_id between 1 and 23) ");
	   }else{  
		   queryString.append(" (D.district_id between 1 and 10) ");
	   }
	   SQLQuery query = getSession().createSQLQuery(queryString.toString()).addScalar("id", Hibernate.LONG).addScalar("position", Hibernate.STRING).addScalar("tdpCadre", Hibernate.LONG);
	   //query.setParameter("campId", campId);
	   query.setParameterList("programIdList", programIdList);
	   query.setParameterList("designationIdList", designationIdList);
	   query.setDate("toDate", toDate);  
	   return query.list(); 
   }
   public List<Object[]> getTotalInvitedCadreIdForTrainingCampStateLevel(List<Long> programIdList, Long stateId, Date toDate){  
		StringBuilder queryString = new StringBuilder();
		queryString.append(" select distinct TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
						   " TC.tdpCadreId from TrainingCampBatchAttendee TCBA, TdpCadre TC " +  
						   " where " +  
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIdList) and " +
						   " date(TCBA.trainingCampBatch.fromDate) <= (:toDate) and" );    
		if(stateId.longValue() == 1){
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId between 1 and 23 and ");
		}else{
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId between 1 and 10 and ");
		}
		queryString.append(" TCBA.tdpCadre.tdpCadreId = TC.tdpCadreId and TCBA.isDeleted = 'false' ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("programIdList", programIdList);
		query.setDate("toDate", toDate); 
		return query.list();       
		
	}
   
   public List<Object[]> getTrainingCampInviteeSummary(List<Long> cadreIds){
	   Query query = getSession().createQuery(" select model.tdpCadreId,count(distinct trainingCampBatchId) " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.isDeleted='false' " +
	   		" and model.tdpCadreId in (:cadreIds) " +
	   		" group by model.tdpCadreId ");
	   
	   query.setParameterList("cadreIds", cadreIds);
	   
	   return query.list();
   } 
   
   public List<Object[]> getInviteeAttndAndNonInviteeAttnded(List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds){  
		StringBuilder queryString = new StringBuilder();
		Long tdpCommitteeEnrlYrId = null;
		if(enrollYrIds != null && enrollYrIds.size() > 0){
			if(enrollYrIds.get(0) == 4)
				tdpCommitteeEnrlYrId = 2l;
			else if(enrollYrIds.get(0) == 3)
				tdpCommitteeEnrlYrId = 1l;
		}
		queryString.append(" select TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
						   " count(distinct TCBA.tdpCadreId),TCM.tdpCommitteeRole.tdpRoles.tdpRolesId from TrainingCampBatchAttendee TCBA, TdpCommitteeMember TCM " +  
						   " where  TCBA.trainingCampBatch.attendeeTypeId = 1 and " +
						   " date(TCBA.trainingCampBatch.fromDate) <= (:toDate) and " );    
		if(stateId.longValue() == 1L){
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		if(programIdList != null && programIdList.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIdList) and " );
		}
		if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollYrIds)  and " +
					" TCM.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId = :tdpCommitteeEnrlYrId and " );
			
		}
		queryString.append(" TCBA.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId and TCBA.isDeleted = 'false' and TCBA.trainingCampBatch.isCancelled='false' " +
				" and TCM.isActive = 'Y'  " +
						   " group by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId " +
						   " order by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId ,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId ");
		Query query = getSession().createQuery(queryString.toString());
		if(programIdList != null && programIdList.size() >0){
			query.setParameterList("programIdList", programIdList);
		}
		query.setDate("toDate", toDate); 
		
		if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
			query.setParameter("tdpCommitteeEnrlYrId", tdpCommitteeEnrlYrId);
		}
		return query.list();       
		
	}
   
   public List<Object[]> getTotalAttendeeCount(List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds,Long userAccessLvlId,
		   List<Long> userAccessLvlVals,List<Long> tdpCommitteeLvlIds){  
		StringBuilder queryString = new StringBuilder();
		Long tdpCommitteeEnrlYrId = null;
		if(enrollYrIds != null && enrollYrIds.size() > 0){
			if(enrollYrIds.get(0) == 4)
				tdpCommitteeEnrlYrId = 2l;
			else if(enrollYrIds.get(0) == 3)
				tdpCommitteeEnrlYrId = 1l;
		}
		queryString.append(" select TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
						   " TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName, " +
						   " count(distinct TCBA.tdpCadreId),TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId," +
						   "  TCM.tdpCommitteeRole.tdpRolesId from TrainingCampBatchAttendee TCBA, TdpCommitteeMember TCM " +  
						   " where TCBA.trainingCampBatch.attendeeTypeId = 1 and " +
						   " date(TCBA.trainingCampBatch.fromDate) <= (:toDate) and " );    
		if(stateId.longValue() == 1L){
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		if(programIdList != null && programIdList.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIdList) and " );
		}
		if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollYrIds)  and " +
					" TCM.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId = :tdpCommitteeEnrlYrId and " );
		}
		
		if(tdpCommitteeLvlIds != null && tdpCommitteeLvlIds.size()>0){
			queryString.append(" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:tdpCommitteeLvlIds) and  ");
		}
		if(userAccessLvlId != null && userAccessLvlId.longValue() == 2l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 3l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLvlVals) and  ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 4l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 5l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 6l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 7l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 8l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 8l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLvlVals) and ");
		}
		queryString.append(" TCBA.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId and TCBA.isDeleted = 'false' and TCBA.trainingCampBatch.isCancelled='false' " +
				" and TCM.isActive = 'Y'  " +
						   " group by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRolesId " +
						   " order by TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRolesId  ");
		Query query = getSession().createQuery(queryString.toString());
		if(programIdList != null && programIdList.size() >0){
			query.setParameterList("programIdList", programIdList);
		}
		query.setDate("toDate", toDate); 
		
		if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
			query.setParameter("tdpCommitteeEnrlYrId", tdpCommitteeEnrlYrId);
		}
		
		if(userAccessLvlId != null && userAccessLvlVals != null && userAccessLvlVals.size() >0)
			query.setParameterList("userAccessLvlVals", userAccessLvlVals);
		
		if(tdpCommitteeLvlIds != null && tdpCommitteeLvlIds.size()>0){
			query.setParameterList("tdpCommitteeLvlIds", tdpCommitteeLvlIds);
		}
		return query.list();       
		
	}
   
   public List<Object[]> getTotalLocationWiseAttendeeCount(List<Long> programIdList, Long stateId, Date toDate,List<Long> enrollYrIds,Long userAccessLvlId,
		   List<Long> userAccessLvlVals){  
		StringBuilder queryString = new StringBuilder();
		Long tdpCommitteeEnrlYrId = null;
		if(enrollYrIds != null && enrollYrIds.size() > 0){
			if(enrollYrIds.get(0) == 4)
				tdpCommitteeEnrlYrId = 2l;
			else if(enrollYrIds.get(0) == 3)
				tdpCommitteeEnrlYrId = 1l;
		}
		
		queryString.append(" select ");
		if(userAccessLvlId != null && userAccessLvlId.longValue() == 2l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId , ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 3l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId , ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 5l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId , ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 4l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId , ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 6l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId , ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 7l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId , ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 8l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId , ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 9l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId , ");
		}
		queryString.append(" count(distinct TCBA.tdpCadreId),TCM.tdpCommitteeRole.tdpRolesId from TrainingCampBatchAttendee TCBA, TdpCommitteeMember TCM " +  
						   " where  TCBA.trainingCampBatch.attendeeTypeId = 1 and " +
						   " date(TCBA.trainingCampBatch.fromDate) <= (:toDate) and " );    
		if(stateId.longValue() == 1L){
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			queryString.append(" TCBA.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		if(programIdList != null && programIdList.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in (:programIdList) and " );
		}
		if(enrollYrIds != null && enrollYrIds.size() >0){
			queryString.append(" TCBA.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollYrIds)  and " +
					" TCM.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId = :tdpCommitteeEnrlYrId and " );
		}
		
		if(userAccessLvlId != null && userAccessLvlId.longValue() == 2l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 3l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLvlVals) and  ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 5l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 4l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 6l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLvlVals) and  ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 7l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 8l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLvlVals) and ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 9l){
			queryString.append("  TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLvlVals) and ");
		}
		queryString.append(" TCBA.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId and TCBA.isDeleted = 'false' and TCBA.trainingCampBatch.isCancelled='false' " +
				" and TCM.isActive = 'Y'  " );
		if(userAccessLvlId != null && userAccessLvlId.longValue() == 2l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId,TCM.tdpCommitteeRole.tdpRolesId  ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 3l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId,TCM.tdpCommitteeRole.tdpRolesId  ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 5l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId,TCM.tdpCommitteeRole.tdpRolesId ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 4l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,TCM.tdpCommitteeRole.tdpRolesId  ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 6l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,TCM.tdpCommitteeRole.tdpRolesId   ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 7l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,TCM.tdpCommitteeRole.tdpRolesId  ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 8l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId,TCM.tdpCommitteeRole.tdpRolesId ");
		}else if(userAccessLvlId != null && userAccessLvlId.longValue() == 9l){
			queryString.append(" group by  TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId,TCM.tdpCommitteeRole.tdpRolesId  ");
		}
						   
		Query query = getSession().createQuery(queryString.toString());
		if(programIdList != null && programIdList.size() >0){
			query.setParameterList("programIdList", programIdList);
		}
		query.setDate("toDate", toDate); 
		
		if(enrollYrIds != null && enrollYrIds.size() >0){
			query.setParameterList("enrollYrIds", enrollYrIds);
			query.setParameter("tdpCommitteeEnrlYrId", tdpCommitteeEnrlYrId);
		}
		
		if(userAccessLvlId != null && userAccessLvlVals != null && userAccessLvlVals.size() >0)
			query.setParameterList("userAccessLvlVals", userAccessLvlVals);
		
		return query.list();       
		
	}
   
   public List<Object[]> getDayWiseTrainingCampDetailsCount(String programId,String startDate,String endDate,String enrollemntYrId,Long basicCommitteeId,String committeeLvlIds,Long locationScopeId,String loctionVals)
   
   {
	   
	   Long committeeEnrollmetYrId = null;
	   if(enrollemntYrId.equalsIgnoreCase("4")){
		   committeeEnrollmetYrId=2l;
	   }else if(enrollemntYrId.equalsIgnoreCase("3")){
		   committeeEnrollmetYrId=1l;
	   }
	   
	Query query = getSession().createSQLQuery("CALL get_training_camp_attendance_details(:programId,:startDate,:endDate,:enrollemntYrId,:basicCommitteeId,:committeeLvlIds,:locationScopeId,:levelVals)")
			.addScalar("tdp_cadre_id", Hibernate.LONG)
			.addScalar("date(A.attended_time)", Hibernate.STRING)
			.addScalar("training_camp_batch_id", Hibernate.LONG)
			.addScalar("tdp_roles_id", Hibernate.LONG)
			.addScalar("tdp_committee_level_id", Hibernate.LONG)
			.addScalar("state_id", Hibernate.LONG)
			.addScalar("scope_value", Hibernate.LONG)
			.addScalar("Attended_Status", Hibernate.STRING)
			.addScalar("training_camp_program_id", Hibernate.LONG)
			.addScalar("state_id", Hibernate.LONG)
			.addScalar("state_name", Hibernate.STRING)
			.addScalar("district_id", Hibernate.LONG)
			.addScalar("district_name", Hibernate.STRING)
			.addScalar("parliament_constituency_id", Hibernate.LONG)
			.addScalar("parliament_constituency_name", Hibernate.STRING)
			.addScalar("constituency_id", Hibernate.LONG)
			.addScalar("constituency", Hibernate.STRING)
			.addScalar("tehsil_id", Hibernate.LONG)
			.addScalar("tehsil_name", Hibernate.STRING)
			.addScalar("local_election_body", Hibernate.LONG)
			.addScalar("town", Hibernate.STRING)
			.addScalar("panchayat_id", Hibernate.LONG)
			.addScalar("panchayat_name", Hibernate.STRING)
			.addScalar("ward_id", Hibernate.LONG)
			.addScalar("ward_name", Hibernate.STRING);
	
			query.setParameter("programId", programId).setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("enrollemntYrId", committeeEnrollmetYrId).setParameter("basicCommitteeId", basicCommitteeId)
   			.setParameter("committeeLvlIds", committeeLvlIds).setParameter("locationScopeId", locationScopeId).setParameter("levelVals", loctionVals);
   	
   	return query.list();
   }

@Override
public List<Object[]> getFilteredListCount(List<Long> cadreIdList,List<Long> programIdList,List<Long> commiteeList,Long userAccessLevelId, List<Long> userAccessLevelValues,Long StringType) {
	StringBuffer sb = new StringBuffer();
		if(StringType!= null && StringType == 1L){
		sb.append(" select TCMP.training_camp_id as trainingCampId,TCMP.camp_name as campName,count(distinct TCBA.tdp_cadre_id) as count  " );
		}else if(StringType!= null && StringType == 2L){
			sb.append(" select D.district_id as trainingCampId,D.district_name as campName,count(distinct TCBA.tdp_cadre_id) as count  " );
		}else if(StringType!= null && StringType == 3L){
			sb.append(" select PCON.constituency_id as trainingCampId,PCON.name as campName,count(distinct TCBA.tdp_cadre_id) as count  " );
		}else if(StringType!= null && StringType ==4L){
			sb.append(" select CON.constituency_id as trainingCampId,CON.name as campName,count(distinct TCBA.tdp_cadre_id) as count  " );
		}
		
		sb.append("  from tdp_committee TC  " );
			 if(userAccessLevelValues!= null && userAccessLevelValues.size()>0L){
				 sb.append(" LEFT JOIN user_address UA ON TC.address_id = UA.user_address_id ");
				 if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 2l){
					   	sb.append(" LEFT JOIN district D ON UA.district_id = D.district_id ");
					}else if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 3l){
				   	sb.append(" LEFT JOIN district D ON UA.district_id = D.district_id ");
				   }else if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 4l){
				   	sb.append(" LEFT JOIN constituency CON ON UA.constituency_id = CON.constituency_id ");
				   }else if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l && userAccessLevelId.longValue() == 5l){
				   	sb.append(" LEFT JOIN constituency PCON ON UA.parliament_constituency_id = CON.constituency_id ");
				   	}
			   }
			 sb.append(" ,training_camp_batch_attendee TCBA, training_camp_batch TCB,training_camp_schedule TCS,training_camp TCMP," +
			   " tdp_committee_member TCM, tdp_committee_role TCR ");
			 sb.append( " where ");
			 if(userAccessLevelId != null && userAccessLevelId.longValue()  == 2l && userAccessLevelValues != null && userAccessLevelValues.size() >0l ){
				 sb.append("  D.district_id  in (11,12,13,14,15,16,17,18,19,20,21,22,23,517) and ");
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()  == 3l && userAccessLevelValues != null && userAccessLevelValues.size() >0l ){
				 sb.append("  D.district_id  in (:userAccessLevelValueList) and ");
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()  == 4l && userAccessLevelValues != null && userAccessLevelValues.size() >0l){
				 sb.append("  CON.constituency_id in (:userAccessLevelValueList) and ");
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()  == 5l && userAccessLevelValues != null && userAccessLevelValues.size() >0l){
				 sb.append(" PCON.constituency_id in (:userAccessLevelValueList) and  ");
			 }
			
			  sb.append(" TCBA.training_camp_batch_id = TCB.training_camp_batch_id and TCBA.tdp_cadre_id in(:cadreIdList) "+
			  " and TCB.training_camp_schedule_id = TCS.training_camp_schedule_id " +
			  " and TCS.training_camp_id = TCMP.training_camp_id and TCS.training_camp_program_id in(:programIdList) "+
			  " and TCBA.is_deleted='false' and TCBA.tdp_cadre_id = TCM.tdp_cadre_id "+
			  " and TCM.tdp_committee_role_id=TCR.tdp_committee_role_id and TCR.tdp_committee_id = TC.tdp_committee_id "+
			  " and TC.tdp_committee_level_id in (:commiteeList) ");
			  if(StringType != null && StringType.longValue() == 1l){
				  sb.append(" group by TCMP.training_camp_id ");
				}else if(StringType != null && StringType.longValue() == 2l){
					sb.append("  group by D.district_id ");
				}else if(StringType != null && StringType.longValue() == 4l){
					sb.append("  group by  PCON.constituency_id ");
				}else if(StringType != null && StringType.longValue() == 3l){
					sb.append(" group by  CON.constituency_id ");
				}
	Query query = getSession().createSQLQuery(sb.toString())
			.addScalar("trainingCampId", Hibernate.LONG)
			.addScalar("campName", Hibernate.STRING)
			.addScalar("count", Hibernate.LONG);
	
	if(programIdList != null && programIdList.size() >0){
		query.setParameterList("programIdList", programIdList);
	}
	if(cadreIdList != null && cadreIdList.size() >0){
		query.setParameterList("cadreIdList", cadreIdList);
	}
	if(userAccessLevelId != null && userAccessLevelId.longValue()  != 2l && userAccessLevelValues != null && userAccessLevelValues.size() >0l ){
		query.setParameterList("userAccessLevelValueList",userAccessLevelValues);
	}
	
	query.setParameterList("commiteeList",commiteeList);
	return query.list();
   }
}


