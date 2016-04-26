package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentTrackingDAO;
import com.itgrids.partyanalyst.model.AppointmentTracking;

public class AppointmentTrackingDAO extends GenericDaoHibernate<AppointmentTracking, Long> implements IAppointmentTrackingDAO {

	public AppointmentTrackingDAO() {
		super(AppointmentTracking.class);
	}
	public List<Object[]> getAppointmentTrackingDetails(Long appointmentId)
	{
		Query query = getSession().createQuery("select model.appointmentStatus.appointmentStatusId,"
				+ "model.appointmentStatus.status,model.user.userId,model.user.firstName,model.user.lastName,model.remarks,model.actionTime,"
				+ "appointmentComment.appointmentCommentId,appointmentComment.comment,fromAppointmentStatus.appointmentStatusId,fromAppointmentStatus.status"
				+ " from AppointmentTracking model left join model.appointmentComment appointmentComment " +
				" left join model.fromAppointmentStatus fromAppointmentStatus"
				+ " where model.appointment.appointmentId = :appointmentId"
				+ " order by model.actionTime desc");
		query.setParameter("appointmentId", appointmentId);
		return query.list();
	}
}
