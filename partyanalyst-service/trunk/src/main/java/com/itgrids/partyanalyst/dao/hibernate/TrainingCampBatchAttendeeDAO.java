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
		
		queryStr.append(" from TrainingCampBatchAttendee model where ");
		
		if(startDate != null && endDate != null)
		{
			queryStr.append(" (date(model.attendedTime) >=:startDate and date(model.attendedTime) <=:endDate) ");
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
	
    public List<Object[]> getTdpCadreDetailsforASchedule(List<Long> schedulesList){
		
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
	    " tce.tdp_committee_level_id,tce.tdp_committee_level_value " +
    				       
   " from training_camp_batch_attendee tcba  join training_camp_batch tcb on tcba.training_camp_batch_id=tcb.training_camp_batch_id " +
        " join training_camp_schedule tcs on tcs.training_camp_schedule_id=tcb.training_camp_schedule_id " +
        " join tdp_cadre tc on tcba.tdp_cadre_id=tc.tdp_cadre_id " +
        " join user_address ua on  tc.address_id=ua.user_address_id " +
        " join constituency c on ua.constituency_id=c.constituency_id " +
        " left join training_camp_cadre_feedback_details tccf on tccf.tdp_cadre_id=tc.tdp_cadre_id and tccf.training_camp_batch_id=tcba.training_camp_batch_id " +
        " left join  tdp_committee_member tcm on tcba.tdp_cadre_id=tcm.tdp_cadre_id " +
        " left join tdp_committee_role tcr on tcm.tdp_committee_role_id=tcr.tdp_committee_role_id " +
        " left join tdp_committee tce on tcr.tdp_committee_id=tce.tdp_committee_id " +
        " left join tdp_committee_level tcl on tce.tdp_committee_level_id=tcl.tdp_committee_level_id " +
        " left join tdp_roles tr on tcr.tdp_roles_id=tr.tdp_roles_id " +
        " left join tdp_basic_committee tbc on tce.tdp_basic_committee_id=tbc.tdp_basic_committee_id " +

   " where  tcs.training_camp_schedule_id in (:schedulesList) and tc.is_deleted='N' and tc.enrollment_year=2014 and tcm.is_active='Y' " +
   " order by tcb.training_camp_batch_id asc,tc.first_name asc";
   
  		
		Query sqlQuery=getSession().createSQLQuery(query);
		sqlQuery.setParameterList("schedulesList", schedulesList);
		//sqlQuery.setParameter("scheduleId",scheduleId);
		return sqlQuery.list();
    }
    
	public List<Object[]> getAchievementsForCadreBySchedule(List<Long> schedulesList){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.achievement " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreAchievement model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId=model1.trainingCampBatchId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainingCampBatch.trainingCampSchedule.trainingCampScheduleId in (:schedulesList) ");
		query.setParameterList("schedulesList", schedulesList);
		return query.list();
	}
   public List<Object[]> getGoalsForCadreBySchedule(List<Long> schedulesList){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.goal " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreGoal model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.trainingCampBatchId=model1.trainingCampBatchId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainingCampBatch.trainingCampSchedule.trainingCampScheduleId in (:schedulesList) ");
		query.setParameterList("schedulesList", schedulesList);
		return query.list();
	}
   
   public Object[] getCadreDetailsByCadreIdAndBatchId(Long tdpCadreId,Long batchId){
	   
	   Query query=getSession().createQuery("" +
	   " select model1.tdpCadreId,model1.firstname,model1.mobileNo,model1.userAddress.constituency.name," +
	   " model1.userAddress.district.districtName,model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName,model1.image " +
	   " from TrainingCampBatchAttendee model,TdpCadre model1 " +
	   " where model.tdpCadreId=model1.tdpCadreId and " +
	   " model.tdpCadreId=:tdpCadreId and model.trainingCampBatch.trainingCampBatchId=:batchId " +
	   " and model1.isDeleted='N' and model1.enrollmentYear=2014");
	   query.setParameter("tdpCadreId",tdpCadreId);
	   query.setParameter("batchId",batchId);
	   return (Object[])query.uniqueResult();
   }
   
   @SuppressWarnings("unchecked")
   public List<String> getAttendeesForATrainingCampBatch(Long trainingCampBatchId)
   {
	   Query query = getSession().createQuery("SELECT model.tdpCadre.memberShipNo FROM TrainingCampBatchAttendee model where model.trainingCampBatch.trainingCampBatchId = :trainingCampBatchId");
	   query.setParameter("trainingCampBatchId",trainingCampBatchId);
	   return query.list();
   }
	
   public List<Object[]> getRunningUpcomingCounts(List<Long> batchIds){
	   Query query = getSession().createQuery(" select model.trainingCampBatchId,count(distinct model.tdpCadreId) " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where model.trainingCampBatchId in (:batchIds) " +
	   		" group by model.trainingCampBatchId ");
	   query.setParameterList("batchIds", batchIds);
	   return query.list();
   }
   public Long getConfirmedCountsByBatch(Long batchId,Date fromDate,Date toDate){
	   Query query=getSession().createQuery(" select count(distinct model.tdpCadreId) from TrainingCampBatchAttendee model where model.trainingCampBatchId=:trainingCampBatchId " +
	   		" and date(model.trainingCampBatch.fromDate) >= :fromDate and date(model.trainingCampBatch.toDate) <= :toDate ");
	   query.setParameter("fromDate", fromDate);
	   query.setParameter("toDate", toDate);
	   query.setParameter("trainingCampBatchId",batchId);
	   return (Long)query.uniqueResult();
   }
   public List<Object[]> getConfirmedCadreByBatch(Long batchId){
	   Query query=getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId,model.tdpCadre.memberShipNo," +
	   		" model.tdpCadre.firstname,model.tdpCadre.mobileNo,model.tdpCadre.image,model.tdpCadre.userAddress.constituency.constituencyId," +
	   		" model.tdpCadre.userAddress.constituency.name  " +
	   		" from TrainingCampBatchAttendee model " +
	   		" where" +
	   		" model.trainingCampBatch.trainingCampBatchId=:batchId");
	   query.setParameter("batchId",batchId);
	   
	   return query.list();
   }
   
}
