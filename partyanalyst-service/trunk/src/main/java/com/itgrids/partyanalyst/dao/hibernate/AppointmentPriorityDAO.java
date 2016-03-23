package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentPriorityDAO;
import com.itgrids.partyanalyst.model.AppointmentPriority;

public class AppointmentPriorityDAO extends GenericDaoHibernate<AppointmentPriority, Long> implements IAppointmentPriorityDAO {
	
	public AppointmentPriorityDAO() {
		super(AppointmentPriority.class);
	}
	public List<Object[]> getAppointmentPriorityList(){
		Query query = getSession().createQuery("select model.appointmentPriorityId, model.priority from AppointmentPriority model");
		return query.list();
	}

}
