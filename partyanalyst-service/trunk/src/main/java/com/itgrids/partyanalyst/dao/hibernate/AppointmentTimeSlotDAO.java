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
	
}
