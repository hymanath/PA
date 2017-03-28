package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotDAO;
import com.itgrids.partyanalyst.model.AppointmentTimeSlot;

public class AppointmentTimeSlotDAO extends GenericDaoHibernate<AppointmentTimeSlot,Long> implements IAppointmentTimeSlotDAO{
	
	public AppointmentTimeSlotDAO() {
		super(AppointmentTimeSlot.class);
	}
	public List<Object[]> getAppointmentConfirmDates(List<Long> appointmentIds)
	{
		StringBuffer str = new StringBuffer();
	    str.append("select model.appointment.appointmentId,date(model.fromDate)"+
	        " from AppointmentTimeSlot model " +
	        " where model.appointment.isDeleted='N' "
	        + " and model.appointment.appointmentId in(:appointmentIds) ");
	    Query query = getSession().createQuery(str.toString());
	    query.setParameterList("appointmentIds", appointmentIds);
	    return query.list();
	}
	public List<Object[]> getAppointmentConfirmDates(Date date,Long apptUserId,Long apptStatusId)
	{	
		
		Query query = getSession().createQuery(" select model.date,model.fromDate,model.toDate " +
				" from   AppointmentTimeSlot model " +
				" where  model.appointment.isDeleted='N' and model.date =:date and model.appointment.appointmentUserId =:apptUserId " +
				"        and model.appointment.appointmentStatusId = :apptStatusId ");
	    query.setParameter("apptUserId", apptUserId);
	    query.setDate("date", date);
	    query.setParameter("apptStatusId", apptStatusId);
	    return query.list();
	}
	
	public AppointmentTimeSlot getAppointmentTimeSlotByAppointmentId(Long appointmentId)
	{
		Query query = getSession().createQuery("Select model from AppointmentTimeSlot model where model.appointment.appointmentId = :appointmentId and model.isDeleted = 'N'");
		query.setParameter("appointmentId",appointmentId);
		return (AppointmentTimeSlot)query.uniqueResult();
	}
	public List<Object[]> getAppointmentDetails(Date fromDate,Date toDate,Long tdpCadreId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.appointment.appointmentStatus.appointmentStatusId," +
				" model.appointment.appointmentId,model.appointment.reason," +
				" model.date,model1.appointmentCandidate.name," +
				" model1.appointmentCandidate.membershipId," +
				" model1.appointmentCandidate.mobileNo," +
				" model1.appointmentCandidate.imageURL," +
				" model.appointment.appointmentStatus.status" +
				" from AppointmentTimeSlot model,AppointmentCandidateRelation model1" +
				" where model.appointment.appointmentId = model1.appointment.appointmentId " +
				" and model1.appointmentCandidate.tdpCadreId = :tdpCadreId" +
				" and model.isDeleted = 'N' and model.appointment.isDeleted = 'N'");
		/*sb.append("select a.appointment_status_id,a.appointment_id,a.reason,ats.date,ac.name" +
				" from appointment_candidate ac,appointment_time_slot ats,appointment_candidate_relation acr,appointment a " +
				" where ac.membership_id = :membershipId and acr.appointment_id = a.appointment_id" +
				" and acr.appointment_candidate_id = ac.appointment_candidate_id and acr.appointment_id = ats.appointment_id" +
				//"and date(ats.from_date) = '2016-06-14' and date(ats.to_date) = '2016-06-14'" +
				" and ats.is_deleted = 'N' and a.is_deleted = 'N'");*/
		if(fromDate != null && toDate != null){
			sb.append(" and model.date between :fromDate and :toDate");
			//sb.append(" and date(ats.from_date) = :fromDate and date(ats.to_date) = :toDate");
		}
		Query query = getSession().createQuery(sb.toString());
			query.setParameter("tdpCadreId", tdpCadreId);
		if(fromDate != null && toDate  != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		return query.list();
	}
	public List<Object[]> getAppointmentList(List<Long> appointmentIds){
		Query query = getSession().createQuery("select distinct model.appointment.appointmentId,model.appointmentCandidate.name," +
				" model.appointmentCandidate.mobileNo,model.appointmentCandidate.membershipId,model.appointmentCandidate.imageURL" +
				" from AppointmentCandidateRelation model" +
				" where model.appointment.appointmentId in (:appointmentIds)" +
				" and model.appointment.isDeleted = 'N'");
		query.setParameterList("appointmentIds", appointmentIds);
		return query.list();
	}
	
	public List<Object[]> getLeaderAppointmentDetails(Date fromDate,Date toDate,Long tdpCadreId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.appointment.appointmentStatus.appointmentStatusId," +
				" model.appointment.appointmentId,model.appointment.reason," +
				" model.date,model1.appointmentCandidate.name," +
				" model1.appointmentCandidate.membershipId," +
				" model1.appointmentCandidate.mobileNo," +
				" model1.appointmentCandidate.imageURL," +
				" model.appointment.appointmentStatus.status," +
				" model.appointment.appointmentUser.appointmenUserId," +
				" model.appointment.appointmentUser.tdpCadre.firstname," +
				" model.appointment.appointmentUser.tdpCadre.mobileNo," +
				" model.appointment.appointmentUser.tdpCadre.image" +
				" from AppointmentTimeSlot model,AppointmentCandidateRelation model1" +
				" where model.appointment.appointmentId = model1.appointment.appointmentId " +
				" and model.appointment.appointmentUser.tdpCadreId = :tdpCadreId" +
				" and model.isDeleted = 'N' and model.appointment.isDeleted = 'N'" +
				" and model.appointment.appointmentUser.tdpCadre.isDeleted = 'N'");
		if(fromDate != null && toDate != null)
			sb.append(" and model.date between :fromDate and :toDate");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("tdpCadreId", tdpCadreId);
		if(fromDate != null && toDate  != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
}
