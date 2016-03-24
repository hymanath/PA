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

	public List<Object[]> getLableDetailsWithStatusWiseCounts(Date labelDate,Long userId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.appointmentLabel.appointmentLabelId,model.appointmentLabel.labelName," +
				" model.appointmentLabel.appointmentLabelStatus.appointmentLabelStatusId, " +
				" model.appointmentLabel.appointmentLabelStatus.status, " +
				" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status,count(model.appointment.appointmentStatusId) " +
				" from LabelAppointment model " +
				" where model.appointmentLabel.isDeleted='N' and model.appointment.isDeleted='N' " );
		if(labelDate != null)
				sb.append("and date(model.appointmentLabel.updatedTime)=:labelDate ");
		sb.append(" and model.appointmentLabel.insertedBy=:userId ");
		sb.append(" group by model.appointment.appointmentStatusId,model.appointmentLabel.appointmentLabelId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("userId", userId);
		
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
	
	public List<Object[]> getAppointmentsOfALableForUpdate(Long lableId){
		Query query = getSession().createQuery(" select model.appointment.appointmentId," +
				"model.appointment.appointmentPriority.appointmentPriorityId,model.appointment.appointmentPriority.priority," +
				"model.appointment.reason," +
				"model.appointment.appointmentStatus.appointmentStatusId,model.appointment.appointmentStatus.status,model1.userId,model1.firstName " +
				" from LabelAppointment model,User model1 " +
				" where model.appointmentLabel.appointmentLabelId=:lableId and model.appointmentLabel.isDeleted='N' and model.appointment.isDeleted='N' " +
				" and model.createdBy=model1.userId ");
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
	
public List<Object[]> getLabelAppointmentsForFixedSatus(Date toDayDate,String searchType,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select " +
				" model.appointment.appointmentStatusId,model.appointment.appointmentStatus.status, " +
				" count(distinct model.appointment.appointmentId)" +
				" from LabelAppointment model,AppointmentTimeSlot ATS" +
				" where model.appointment.isDeleted = 'N'" +
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
		
		str.append(" group by model.appointment.appointmentStatusId ");
		
		
		Query query = getSession().createQuery(str.toString());
		
		if(toDayDate !=null){
			query.setDate("toDayDate", toDayDate);
		}
		
		return query.list();
	}

public List<Object[]> getLabelAppointmentsStatus(Date toDayDate,String type){
	
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
			" and model.appointmentLabel.isDeleted = 'N'" +
			" and model.isDeleted = 'N' ");
	
	
	if(type !=null && type.trim().equalsIgnoreCase("toDay")){
			if(toDayDate !=null){
				str.append(" and date(model.appointment.updatedTime) = :toDayDate ");
			}
	}
	
	str.append(" and model.appointment.appointmentStatusId in (:statusIds) ");		
	str.append(" group by model.appointment.appointmentStatusId ");
	
	Query query = getSession().createQuery(str.toString());
	
	if(type !=null && type.trim().equalsIgnoreCase("toDay")){
		if(toDayDate !=null){
			query.setDate("toDayDate", toDayDate);
		}
	}
	query.setParameterList("statusIds",IConstants.APPOINTMENT_STATUS_IDS);
	return query.list();
}
}
