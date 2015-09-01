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
		" select tcs.training_camp_schedule_id,tcb.training_camp_batch_id,tcb.training_camp_batch_code," +
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
	
}
