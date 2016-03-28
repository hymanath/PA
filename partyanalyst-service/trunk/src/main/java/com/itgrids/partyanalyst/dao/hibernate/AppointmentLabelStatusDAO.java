package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentLabelStatusDAO;
import com.itgrids.partyanalyst.model.AppointmentLabelStatus;

public class AppointmentLabelStatusDAO extends GenericDaoHibernate<AppointmentLabelStatus, Long> implements IAppointmentLabelStatusDAO {
	
	public AppointmentLabelStatusDAO() {
		super(AppointmentLabelStatus.class);
	}
	public List<Object[]> getAppmntLblStatusList(){
		Query query = getSession().createQuery("select model.appointmentLabelStatusId, model.status from AppointmentLabelStatus model");
		return query.list();
	}

}
