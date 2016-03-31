package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.model.Appointment;
import com.itgrids.partyanalyst.model.Cadre;

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

}
