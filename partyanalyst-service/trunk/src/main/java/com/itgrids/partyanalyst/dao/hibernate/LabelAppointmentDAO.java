package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
import com.itgrids.partyanalyst.model.LabelAppointment;

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
	 public Integer updateLabeledAppointments(List<Long> appointmentIds,Long userId,Date date){
			
		Query query=getSession().createQuery("update LabelAppointment model set model.updatedBy=:userId ,model.updatedTime= :date " +
				" where " +
				" model.appointmentId  in (:appointmentIds) ");
		
		query.setParameterList("appointmentIds",appointmentIds);
		query.setParameter("userId",userId);
		query.setTimestamp("date",date);
		return query.executeUpdate();
	}
	 public Integer deleteLabeledAppointments(List<Long> appointmentIds){
			
		Query query=getSession().createQuery("update LabelAppointment model set model.isDeleted='N' " +
				" where " +
				" model.appointmentId  in (:appointmentIds) ");
		
		query.setParameterList("appointmentIds",appointmentIds);
		return query.executeUpdate();
	 }
	 
	 public List<LabelAppointment> getDetailsOfLabelledAppointments(Long appointmentLabelId , List<Long> appointmentIds){
		 
		 Query query = getSession().createQuery(" select model from  LabelAppointment where model.appointmentLabelId = :appointmentLabelId and model.appointmentId in (:appointmentIds)");
		 query.setParameter("appointmentLabelId",appointmentLabelId);
		 query.setParameterList("appointmentIds", appointmentIds);
	     return query.list();
	 }
	
	public List<Object[]> getAppointmentsOfALableForUpdate(Long lableId){
		Query query = getSession().createQuery(" select model.appointment.appointmentId," +
				"model.appointment.appointmentPriority.appointmentPriorityId,model.appointment.appointmentPriority.priority," +
				"model.appointment.reason," +
				"model.appointment.appointmentStatus.appointmentStatusId,model.appointment.appointmentStatus.status " +
				" from LabelAppointment model " +
				" where model.appointmentLabel.appointmentLabelId=:lableId and model.appointmentLabel.isDeleted='N' and model.appointment.isDeleted='N' ");
		query.setParameter("lableId", lableId);
		return query.list();
	}
}
