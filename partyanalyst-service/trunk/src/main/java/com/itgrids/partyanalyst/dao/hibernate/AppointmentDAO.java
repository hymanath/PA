package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.model.Appointment;
import com.itgrids.partyanalyst.utils.IConstants;

public class AppointmentDAO extends GenericDaoHibernate<Appointment, Long>
		implements IAppointmentDAO {

	public AppointmentDAO() {
		super(Appointment.class);
	}

	public List<Object[]> getTotalAppointmentStatus() {
		Query query = getSession()
				.createQuery(
						"select model.appointmentStatusId, count(model.appointmentStatusId) from Appointment model where model.isDeleted='N' group by model.appointmentStatusId order by model.appointmentStatusId");
		return query.list();
	}

	public List<Object[]> getTotalAppointmentStatusForToday(Date today) {
		Query query = getSession()
				.createQuery(
						"select model.appointmentStatusId, count(model.appointmentStatusId) from Appointment model "
								+ "where model.isDeleted='N' and (model.insertedTime =:today or model.updatedTime =:today) "
								+ "group by model.appointmentStatusId order by model.appointmentStatusId");
		query.setDate("today", today);
		return query.list();
	}

	public List<Object[]> getAppointmentCreatedUsers() {
		Query query = getSession()
				.createQuery(
						"select distinct model.createdUser.userId, model.createdUser.firstName,model.createdUser.lastName from Appointment model");
		return query.list();
	}

	public Integer updateUniquesIdForAppointment(String uniqueCode,
			Long appointmentId) {
		Query query = getSession()
				.createQuery(
						" update Appointment model set model.appointmentUniqueId=:uniqueCode where model.appointmentId=:appointmentId ");
		query.setParameter("appointmentId", appointmentId);
		query.setParameter("uniqueCode", uniqueCode);
		return query.executeUpdate();
}
	
	public Integer updateAppntmntStatusById(Long appointmentId, Date updatedTime) {
		Query query = getSession()
				.createQuery(
						" update Appointment model set model.appointmentStatusId= :statusId, model.updatedTime = :updatedTime  where model.appointmentId=:appointmentId ");
		query.setParameter("appointmentId", appointmentId);
		query.setTimestamp("updatedTime", updatedTime);
		query.setParameter("statusId", IConstants.APPOINTMENT_STATUS_FIXED);
		return query.executeUpdate();
	}
	public Integer updateLabelingStatusToAppts(List<Long> appointmentIds,String labelStatus){
		
		Query query=getSession().createQuery("update Appointment model set model.isLabelled=:labelStatus" +
				" where " +
				" model.appointmentId  in (:appointmentIds) ");
		
		query.setParameterList("appointmentIds",appointmentIds);
		query.setParameter("labelStatus",labelStatus);
		return query.executeUpdate();
	}
public Long getAppointmentStatusId(Long appointmentId){
		
		Query query=getSession().createQuery("select model.appointmentStatus.appointmentStatusId from Appointment model where model.appointmentId = :appointmentId and model.isDeleted = 'N' ");
		
		query.setParameter("appointmentId",appointmentId);
		return (Long)query.uniqueResult();
	}

	public Integer updateApptStatusbyApptIds(List<Long> appointmemtIds, Date updatedTime,Long statusId,Long userID) {
		
		Query query = getSession().createQuery(
		 " update Appointment model set model.appointmentStatusId= :statusId, model.updatedTime = :updatedTime,model.updatedBy=:userID " +
		 " where model.appointmentId in (:appointmemtIds) ");
		query.setParameterList("appointmemtIds", appointmemtIds);
		query.setTimestamp("updatedTime", updatedTime);
		query.setParameter("statusId",statusId);
		query.setParameter("userID", userID);
		return query.executeUpdate();
	}
	
	public Integer updatedAppointmentStatus(List<Long> appointmentIds,Long apptStatusId,Long userId,Date date){
		Query query=getSession().createQuery("update Appointment model set model.appointmentStatusId=:appointmentStatusId," +
				" model.updatedTime=:date, model.updatedBy=:userId " +
				" where " +
				" model.appointmentId  in (:appointmentIds) ");
		query.setParameterList("appointmentIds",appointmentIds);
		query.setParameter("appointmentStatusId", apptStatusId);
		query.setParameter("userId", userId);
		query.setTimestamp("date", date);
		return query.executeUpdate();
	}
	
	public List<Long> getAppointmentIdsByDateByStatus(Date date,Long apptStatusId) {
		
		Query query = getSession().createQuery("select  distinct model.appointmentId " +
			"from    Appointment model "+
		    "where   model.isDeleted='N' and  date(model.updatedTime) =:date and model.appointmentStatusId = :apptStatusId");
					
		query.setDate("date", date);
		query.setParameter("apptStatusId", apptStatusId);
		return query.list();
	}
	public Long getCurrentAppointmentStatus(Long appointmentId)
	{
		Query query = getSession().createQuery("select model.appointmentStatusId from Appointment model where model.appointmentId = :appointmentId");
		query.setParameter("appointmentId", appointmentId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> eachStatusApptCountByDateAndApptUser(Long apptUserId,List<Long> statusIds,Date date){
		
		StringBuilder sb= new StringBuilder();
		sb.append(" select model.appointmentStatusId,model.appointmentStatus.status,count(distinct model.appointmentId)" +
				"   from   Appointment model where model.isDeleted='N' ");
		if(date!=null){
			sb.append(" and date(model.insertedTime) = :date ");
		}
		if(apptUserId!=null && apptUserId>0l){
			sb.append(" and model.appointmentUserId = :apptUserId ");
		}
		if(statusIds!=null && statusIds.size()>0){
			sb.append(" and model.appointmentStatusId in (:statusIds)  ");
		}
		sb.append(" group by model.appointmentStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(date!=null){
			query.setDate("date",date);
		}
		if(apptUserId!=null && apptUserId>0l){
			query.setParameter("apptUserId",apptUserId);
		}
		if(statusIds!=null && statusIds.size()>0){
			query.setParameterList("statusIds",statusIds);
		}
		return query.list();
		
	}
	
	public Integer updateApptStatusbyApptId(Long appointmentId, Date updatedTime,Long statusId,Long userID) {
		
		Query query = getSession().createQuery(
		 " update Appointment model set model.appointmentStatusId= :statusId, model.updatedTime = :updatedTime,model.updatedBy=:userID " +
		 " where model.appointmentId = :appointmentId ");
		query.setParameter("appointmentId", appointmentId);
		query.setTimestamp("updatedTime", updatedTime);
		query.setParameter("statusId",statusId);
		query.setParameter("userID", userID);
		return query.executeUpdate();
	}
	
	public Integer updateAppointmentReason(Long appointmentId,String reason,Date presentDate,Long userId){		
		
		Query query = getSession().createQuery(" update Appointment model set model.reason = :reason,model.updatedTime = :updatedTime," +
				"model.updatedBy=:userId " +
				" where model.appointmentId = :appointmentId  ");
		
		query.setParameter("appointmentId", appointmentId);
		query.setTimestamp("updatedTime", presentDate);
		query.setParameter("userId", userId);
		query.setParameter("reason", reason);
		
		return query.executeUpdate();
	}
	
	public List<Object[]> eachStatusApptCountByDateAndApptUserNew(Long apptUserId,List<Long> statusIds,Date date){
		StringBuilder sb= new StringBuilder();
		sb.append(" select model.appointmentStatusId,model.appointmentStatus.status,count(distinct model.appointmentId)" +
				"   from   Appointment model,AppointmentTimeSlot ATS where model.isDeleted='N'" +
				" and model.appointmentId = ATS.appointmentId ");
		if(date!=null){
			sb.append(" and ATS.date = :date ");
		}
		if(apptUserId!=null && apptUserId>0l){
			sb.append(" and model.appointmentUserId = :apptUserId ");
		}
		if(statusIds!=null && statusIds.size()>0){
			sb.append(" and model.appointmentStatusId in (:statusIds)  ");
		}
		sb.append(" group by model.appointmentStatusId");
		
		Query query = getSession().createQuery(sb.toString());
		if(date!=null){
			query.setDate("date",date);
		}
		if(apptUserId!=null && apptUserId>0l){
			query.setParameter("apptUserId",apptUserId);
		}
		if(statusIds!=null && statusIds.size()>0){
			query.setParameterList("statusIds",statusIds);
		}
		return query.list();
	}
}
	


