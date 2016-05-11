package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;

public class AppointmentStatusDAOHibernateTest extends BaseDaoTestCase {
	
	private IAppointmentStatusDAO appointmentStatusDAO;

	public IAppointmentStatusDAO getAppointmentStatusDAO() {
		return appointmentStatusDAO;
	}

	public void setAppointmentStatusDAO(IAppointmentStatusDAO appointmentStatusDAO) {
		this.appointmentStatusDAO = appointmentStatusDAO;
	}

	public void test(){
		System.out.println("hi");
		List<Object[]> list = appointmentStatusDAO.getAppointmentStatusList();
		System.out.println(list);
	}
}
