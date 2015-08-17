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
	
	public List<Object[]> getTdpCadreDetailsforASchedule(Long scheduleId){
		
		
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
	    "      left join training_camp_cadre_feedback_details tccf on tccf.tdp_cadre_id=tc.tdp_cadre_id " +
	   
		" where  tcs.training_camp_schedule_id=:scheduleId and tc.is_deleted='N' and tc.enrollment_year=2014 " +
		" order by tcb.training_camp_batch_id asc,tc.first_name asc";
		
		Query sqlQuery=getSession().createSQLQuery(query);
		sqlQuery.setParameter("scheduleId",scheduleId);
		return sqlQuery.list();
    }
	public List<Object[]> getAchievementsForCadreBySchedule(Long scheduleId){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.achievement " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreAchievement model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainningCampScheduleInvitee.trainingcampScheduleId =:scheduleId ");
		query.setParameter("scheduleId",scheduleId);
		return query.list();
	}
   public List<Object[]> getGoalsForCadreBySchedule(Long scheduleId){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampBatch.trainingCampBatchId,model.trainingCampBatch.trainingCampBatchCode,model.tdpCadreId," +
		" model1.goal " +
		" from TrainingCampBatchAttendee model,TrainingCampCadreGoal model1" +
		" where model.tdpCadreId=model1.tdpCadreId and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 " +
		" and model.trainningCampScheduleInvitee.trainingcampScheduleId =:scheduleId ");
		query.setParameter("scheduleId",scheduleId);
		return query.list();
	}
	
	
	
	
}
