package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
import com.itgrids.partyanalyst.model.LabelAppointment;
import com.itgrids.partyanalyst.utils.IConstants;

public class LabelAppointmentDAO extends GenericDaoHibernate<LabelAppointment, Long> implements	ILabelAppointmentDAO {

	public LabelAppointmentDAO(){
		super(LabelAppointment.class);
	}

	public List<Object[]> getLableDetailsWithStatusWiseCounts(Date labelDate,Long userId,Long statusId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.appointmentLabel.appointmentLabelId,model.appointmentLabel.labelName," +
				" model.appointmentLabel.appointmentLabelStatus.appointmentLabelStatusId, " +
				" model.appointmentLabel.appointmentLabelStatus.status, " +
				" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status,count(model.appointment.appointmentStatusId),model.appointmentLabel.updatedTime " +
				" from LabelAppointment model " +
				" where model.appointmentLabel.isDeleted='N' and model.appointment.isDeleted='N' and model.isDeleted='N' " );
		if(labelDate != null)
				sb.append("and date(model.appointmentLabel.updatedTime)=:labelDate ");
		sb.append(" and model.appointmentLabel.appointmentUserId=:userId ");
		if(statusId !=null && statusId>0){
			sb.append(" and model.appointmentLabel.appointmentLabelStatusId=:statusId ");
		}
		sb.append(" group by model.appointment.appointmentStatusId,model.appointmentLabel.appointmentLabelId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("userId", userId);
		if(statusId !=null && statusId>0){
			query.setParameter("statusId", statusId);
		}
		
		if(labelDate != null)
			query.setParameter("labelDate", labelDate);
		
		return query.list();
	}
	

	public  List<Object[]>  checkLabelWithAppointment(Long appointmentLabelId,List<Long> appointmentIds){
		
		Query query = getSession().createQuery(" select model.labelAppointmentId ,model.appointmentLabelId,model.appointmentId " +
				" from    LabelAppointment model " +
				" where   model.appointmentLabelId = :appointmentLabelId and model.appointmentId in (:appointmentIds) and model.isDeleted='N' ");
		query.setParameter("appointmentLabelId", appointmentLabelId);
		query.setParameterList("appointmentIds", appointmentIds);
		return query.list();
	}
	
	public List<Long> getAppointmentsForALabel(Long appointmentLabelId){
		Query query = getSession().createQuery(" select model.appointmentId " +
				" from    LabelAppointment model " +
				" where   model.appointmentLabelId = :appointmentLabelId  and model.isDeleted='N' ");
		query.setParameter("appointmentLabelId", appointmentLabelId);
		return query.list();
	}
	 public Integer updateLabeledAppointments(Long appointmentLabelId,List<Long> appointmentIds,Long userId,Date date){
			
		Query query=getSession().createQuery("update LabelAppointment model set model.updatedBy=:userId ,model.updatedTime= :date " +
				" where " +
				" model.appointmentLabelId = :appointmentLabelId and  model.appointmentId  in (:appointmentIds) ");
		
		query.setParameter("appointmentLabelId",appointmentLabelId);
		query.setParameterList("appointmentIds",appointmentIds);
		query.setParameter("userId",userId);
		query.setTimestamp("date",date);
		return query.executeUpdate();
	}
	 public Integer deleteLabeledAppointments(Long appointmentLabelId ,List<Long> appointmentIds){
			
		Query query=getSession().createQuery("update LabelAppointment model set model.isDeleted='Y' " +
				" where " +
				" model.appointmentLabelId = :appointmentLabelId and model.appointmentId  in (:appointmentIds) ");
		
		query.setParameter("appointmentLabelId",appointmentLabelId);
		query.setParameterList("appointmentIds",appointmentIds);
		return query.executeUpdate();
	 }
	 
	 public List<LabelAppointment> getDetailsOfLabelledAppointments(Long appointmentLabelId , List<Long> appointmentIds){
		 
		 Query query = getSession().createQuery(" select model from  LabelAppointment model where model.appointmentLabelId = :appointmentLabelId and model.appointmentId in (:appointmentIds)");
		 query.setParameter("appointmentLabelId",appointmentLabelId);
		 query.setParameterList("appointmentIds", appointmentIds);
	     return query.list();
	 }
	
	public List<Object[]> getAppointmentsOfALableForUpdate(Long lableId,String callFrom){
		StringBuilder str = new StringBuilder(); 
		str.append(" select model.appointment.appointmentId," +
				"model.appointment.appointmentPriority.appointmentPriorityId,model.appointment.appointmentPriority.priority," +
				"model.appointment.reason," +
				"model.appointment.appointmentStatus.appointmentStatusId,model.appointment.appointmentStatus.status,model1.userId,model1.firstName," +
				"model.appointment.insertedTime,model.appointment.appointmentUniqueId " +
				" from LabelAppointment model,User model1 " +
				" where model.appointmentLabel.appointmentLabelId=:lableId and model.isDeleted='N' and model.appointmentLabel.isDeleted='N' and model.appointment.isDeleted='N' " +
				" and model.createdBy=model1.userId ");
		if(callFrom != null && callFrom.equalsIgnoreCase("print"))
			str.append(" and model.appointment.appointmentStatus.appointmentStatusId !="+IConstants.APPOINTMENT_STATUS_FIXED+"");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("lableId", lableId);
		return query.list();
	}
	
	
	/*select al.appointment_label_id,a.appointment_id,a.appointment_status_id
	from label_appointment la,appointment_label al,appointment a,appointment_time_slot  ats
	where 
	la.appointment_label_id = al.appointment_label_id
	and a.appointment_id = la.appointment_id
	and ats.appointment_id = a.appointment_id
	and ats.from_date >= :fromDate 
	and ats.to_date <= :toDate
	group by al.appointment_label_id,a.appointment_id,a.appointment_status_id;*/
	
	public List<Object[]> getLabelAppointmentsForFixed(Date toDayDate,String searchType,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.appointmentLabel.appointmentLabelId,model.appointmentLabel.labelName," +
				" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status, " +
				" count(distinct model.appointment.appointmentId)" +
				" from LabelAppointment model,AppointmentTimeSlot ATS" +
				" where model.appointment.isDeleted = 'N'" +
				" and model.appointmentLabel.isDeleted = 'N'" +
				" and model.isDeleted = 'N' " +
				" and ATS.appointmentId = model.appointment.appointmentId ");
		
		if(type !=null && type.trim().equalsIgnoreCase("overall")){
			if(toDayDate !=null){
				if(searchType !=null && searchType.trim().equalsIgnoreCase("Inprogress")){
					str.append(" and ATS.fromDate  >= :toDayDate and ATS.toDate <= :toDayDate ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Upcoming")){
					str.append(" and ATS.fromDate > :toDayDate  ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Completed")){
					str.append(" and ATS.toDate <  :toDayDate  ");
				}			
			}
		}else{
			if(toDayDate !=null){
				if(searchType !=null && searchType.trim().equalsIgnoreCase("Inprogress")){
					str.append(" and date(ATS.fromDate)  >= :toDayDate and date(ATS.toDate) <= :toDayDate ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Upcoming")){
					str.append(" and date(ATS.fromDate) > :toDayDate  ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Completed")){
					str.append(" and date(ATS.toDate) <  :toDayDate  ");
				}
			}
		}
		
		str.append(" group by model.appointmentLabel.appointmentLabelId,model.appointment.appointmentStatusId ");
		
		
		Query query = getSession().createQuery(str.toString());
		
		if(toDayDate !=null){
			query.setDate("toDayDate", toDayDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getStatusLabelAppointments(Date toDayDate,String type){
		
		/*select al.appointment_label_id,a.appointment_status_id,count(distinct a.appointment_id) 
		from label_appointment la,appointment_label al,appointment a
		where 
		la.appointment_label_id = al.appointment_label_id
		and la.appointment_id = a.appointment_id
		and a.appointment_status_id in (4,5,6)
		group by al.appointment_label_id,a.appointment_status_id;*/
		
		StringBuilder str = new StringBuilder();
		str.append(" select model.appointmentLabel.appointmentLabelId,model.appointmentLabel.labelName," +
				" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status," +
				"  count(distinct model.appointment.appointmentId) " +
				" from LabelAppointment model" +
				" where model.appointment.isDeleted = 'N'" +
				" and model.appointmentLabel.isDeleted = 'N'" +
				" and model.isDeleted = 'N' ");
		
		
		if(type !=null && type.trim().equalsIgnoreCase("toDay")){
				if(toDayDate !=null){
					str.append(" and date(model.appointment.updatedTime) = :toDayDate ");
				}
		}
		
		str.append(" and model.appointment.appointmentStatusId in (:statusIds) ");		
		str.append(" group by model.appointmentLabel.appointmentLabelId,model.appointment.appointmentStatusId ");
		
		Query query = getSession().createQuery(str.toString());
		
		if(type !=null && type.trim().equalsIgnoreCase("toDay")){
			if(toDayDate !=null){
				query.setDate("toDayDate", toDayDate);
			}
		}
		query.setParameterList("statusIds",IConstants.APPOINTMENT_STATUS_IDS);
		return query.list();
	}
	
public List<Object[]> getLabelAppointmentsForFixedSatus(Date toDayDate,String searchType,String type,Long aptUserId){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select " +
				" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status, " +
				" count(distinct model.appointment.appointmentId)" +
				" from LabelAppointment model,AppointmentTimeSlot ATS" +
				" where model.appointment.isDeleted = 'N'" +
				" and model.isDeleted = 'N' " +
				" and ATS.appointmentId = model.appointment.appointmentId ");
		
		if(type !=null && !type.isEmpty()){
			if(toDayDate !=null){
				if(type !=null && type.trim().equalsIgnoreCase("toDay")){
					str.append(" and date(ATS.date) = date(:toDayDate) ");
				}
				
				if(searchType !=null && searchType.trim().equalsIgnoreCase("Inprogress")){
					str.append(" and :toDayDate between ATS.fromDate and ATS.toDate ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Upcoming")){
					str.append(" and ATS.fromDate > :toDayDate  ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Completed")){
					str.append(" and ATS.toDate <  :toDayDate  ");
				}	
			}
			
		}
		
		if(aptUserId !=null && aptUserId>0){
			str.append(" and model.appointment.appointmentUserId =:aptUserId ");
		}
		
		
		/*if(type !=null && type.trim().equalsIgnoreCase("overall")){
			if(toDayDate !=null){
				if(searchType !=null && searchType.trim().equalsIgnoreCase("Inprogress")){
					str.append(" and :toDayDate between ATS.fromDate and ATS.toDate ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Upcoming")){
					str.append(" and ATS.fromDate > :toDayDate  ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Completed")){
					str.append(" and ATS.toDate <  :toDayDate  ");
				}			
			}
		}else{
			if(toDayDate !=null){
				if(searchType !=null && searchType.trim().equalsIgnoreCase("Inprogress")){
					str.append(" and :toDayDate between date(ATS.fromDate)  and date(ATS.toDate) ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Upcoming")){
					str.append(" and date(ATS.fromDate) > :toDayDate  ");
				}else if(searchType !=null && searchType.trim().equalsIgnoreCase("Completed")){
					str.append(" and date(ATS.toDate) <  :toDayDate  ");
				}
			}
		}*/
		
		str.append(" group by model.appointment.appointmentStatusId ");
		
		
		Query query = getSession().createQuery(str.toString());
		
		if(toDayDate !=null){
			query.setDate("toDayDate", toDayDate);
		}
		if(aptUserId !=null && aptUserId>0){
			query.setParameter("aptUserId",aptUserId);
		}
		
		return query.list();
	}

public List<Object[]> getLabelAppointmentsStatus(Date toDayDate,String type,Long aptUserId){
	
	/*select al.appointment_label_id,a.appointment_status_id,count(distinct a.appointment_id) 
	from label_appointment la,appointment_label al,appointment a
	where 
	la.appointment_label_id = al.appointment_label_id
	and la.appointment_id = a.appointment_id
	and a.appointment_status_id in (4,5,6)
	group by al.appointment_label_id,a.appointment_status_id;*/
	
	StringBuilder str = new StringBuilder();
	str.append(" select " +
			" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status," +
			"  count(distinct model.appointment.appointmentId) " +
			" from LabelAppointment model" +
			" where model.appointment.isDeleted = 'N'" +
			" " +
			" and model.isDeleted = 'N' ");
	
	
	if(type !=null && type.trim().equalsIgnoreCase("toDay")){
			if(toDayDate !=null){
				str.append(" and date(model.appointment.updatedTime) = :toDayDate ");
			}
	}
	
	if(aptUserId !=null && aptUserId>0){
		str.append(" and model.appointment.appointmentUserId =:aptUserId ");
	}
	
	str.append(" and model.appointment.appointmentStatusId in (:statusIds) ");		
	str.append(" group by model.appointment.appointmentStatusId ");
	
	Query query = getSession().createQuery(str.toString());
	
	if(type !=null && type.trim().equalsIgnoreCase("toDay")){
		if(toDayDate !=null){
			query.setDate("toDayDate", toDayDate);
		}
	}
	if(aptUserId !=null && aptUserId>0){
		query.setParameter("aptUserId", aptUserId);
	}
	query.setParameterList("statusIds",IConstants.APPOINTMENT_STATUS_IDS);
	return query.list();
	}
	public List<Object[]> getTimeSlotsDetails(Long appointmentLabelId){
		StringBuilder str = new StringBuilder();
		str.append("select model.appointmentLabelId, model.appointmentId, model1.date, model1.fromDate, model1.toDate "+
		" from LabelAppointment model,AppointmentTimeSlot model1 "+
		" where model.appointmentId = model1.appointmentId and model.appointmentLabelId=:appointmentLabelId "+
		"order by model1.date");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("appointmentLabelId", appointmentLabelId);
		return query.list();
		
	}
	
	public List<Object[]> getViewAppointmentsOfALable(Long lableId){
		Query query = getSession().createQuery(" select model.appointment.appointmentId," +
				"model.appointment.appointmentPriority.appointmentPriorityId,model.appointment.appointmentPriority.priority," +
				"model.appointment.reason," +
				"model.appointment.appointmentStatus.appointmentStatusId,model.appointment.appointmentStatus.status,model1.userId,model1.firstName," +
				" ATS.fromDate,ATS.toDate,model.appointment.createdUser.firstName,ATS.appointmentTimeSlotId " +
				" from LabelAppointment model,User model1,AppointmentTimeSlot ATS " +
				" where model.appointmentLabel.appointmentLabelId=:lableId and model.appointmentLabel.isDeleted='N' and model.appointment.isDeleted='N' " +
				" and model.createdBy=model1.userId " +
				" and model.appointment.appointmentId = ATS.appointmentId" +
				" and ATS.isDeleted = 'N'" +
				" and model.isDeleted = 'N' ");
		
		query.setParameter("lableId", lableId);
		return query.list();
	} 
	
	public List<LabelAppointment> getAppointmentsOfLabel(List<Long> appotIds,Long labelId){
		Query query = getSession().createQuery(" select model from LabelAppointment model where model.appointmentLabelId=:labelId " +
				"and model.appointmentId in (:appotIds) and model.isDeleted='N' ");
		query.setParameter("labelId", labelId);
		query.setParameterList("appotIds", appotIds);
		return query.list();
	}
	
	public Integer updateIsDeletedStatus(List<Long> apptIds,Long labelId,Long registrationId,Date date){
		Query query = getSession().createQuery("update LabelAppointment model set model.isDeleted='Y',model.updatedTime=:date,model.updatedBy=:registrationId " +
				" where model.appointmentId in (:apptIds) and model.appointmentLabelId=:labelId ");
		query.setParameter("labelId", labelId);
		query.setParameter("date", date);
		query.setParameter("registrationId", registrationId);
		query.setParameterList("apptIds", apptIds);
		return query.executeUpdate();
	}
}
