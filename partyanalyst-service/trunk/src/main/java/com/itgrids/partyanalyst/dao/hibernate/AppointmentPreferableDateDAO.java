package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentPreferableDateDAO;
import com.itgrids.partyanalyst.model.AppointmentPreferableDate;

public class AppointmentPreferableDateDAO extends GenericDaoHibernate<AppointmentPreferableDate, Long> implements  IAppointmentPreferableDateDAO {

	public AppointmentPreferableDateDAO( ) {
		super(AppointmentPreferableDate.class);
	}
   
	public List<Object[]> getMultipleDatesforAppointments(List<Long> appointmentIds){
		Query query = getSession().createQuery(" " +
				" select  model.appointment.appointmentId , date(model.appointmentDate),model.appointment.appointmentPreferableTimeId,model.appointment.appointmentPreferableTime.preferabelTimeTxt " +
				" from   AppointmentPreferableDate model where  model.appointment.appointmentId in (:appointmentIds)");
		query.setParameterList("appointmentIds",appointmentIds);
		return query.list();
				
	}
	
	public List<Object[]> getPreferableDatesforAppointments(List<Long> appointmentIds){
		Query query = getSession().createQuery(" " +
				" select  model.appointment.appointmentId , min(date(model.appointmentDate)),max(date(model.appointmentDate)) " +
				" from AppointmentPreferableDate model where  model.appointment.appointmentId in (:appointmentIds) " +
				" group by model.appointment.appointmentId ");
		query.setParameterList("appointmentIds",appointmentIds);
		return query.list();
				
	}
	
}
