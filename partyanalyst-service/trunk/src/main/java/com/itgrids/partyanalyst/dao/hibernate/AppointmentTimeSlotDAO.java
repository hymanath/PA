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
}
