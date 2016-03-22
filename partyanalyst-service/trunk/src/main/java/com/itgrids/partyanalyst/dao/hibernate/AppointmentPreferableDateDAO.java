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
				" select  model.appointment.appointmentId , date(model.appointmentDate) " +
				" from   AppointmentPreferableDate model where model.appointment.appointmentPreferableTimeId = 1 and model.appointment.appointmentId in (:appointmentIds)");
		query.setParameterList("appointmentIds",appointmentIds);
		return query.list();
				
	}
	
}
