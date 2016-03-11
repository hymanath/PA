package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;
import com.itgrids.partyanalyst.model.AppointmentStatus;

public class AppointmentStatusDAO extends GenericDaoHibernate<AppointmentStatus, Long> implements IAppointmentStatusDAO {

	public AppointmentStatusDAO() {
		super(AppointmentStatus.class);
	}
	public List<Object[]> getAppointmentStatusList(){
		Query query=getSession().createQuery(" select model.appointmentStatusId,model.status from AppointmentStatus model ");
		return query.list();
		}
 }
