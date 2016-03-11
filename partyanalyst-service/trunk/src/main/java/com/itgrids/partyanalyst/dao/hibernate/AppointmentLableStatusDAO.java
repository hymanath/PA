package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentLableStatusDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.model.AppointmentLableStatus;

public class AppointmentLableStatusDAO extends GenericDaoHibernate<AppointmentLableStatus, Long> implements IAppointmentLableStatusDAO {
	
	public AppointmentLableStatusDAO() {
		super(AppointmentLableStatus.class);
	}
	public List<Object[]> getAppmntLblStatusList(){
		Query query = getSession().createQuery("select model.appointmentLableStatusId, model.status from AppointmentLableStatus model");
		return query.list();
	}

}
