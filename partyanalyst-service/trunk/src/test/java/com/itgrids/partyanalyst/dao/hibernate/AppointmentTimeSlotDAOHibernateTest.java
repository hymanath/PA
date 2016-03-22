package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotDAO;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.service.impl.DateService;

public class AppointmentTimeSlotDAOHibernateTest extends BaseDaoTestCase{
	
	private IAppointmentTimeSlotDAO appointmentTimeSlotDAO;

	
	public IAppointmentTimeSlotDAO getAppointmentTimeSlotDAO() {
		return appointmentTimeSlotDAO;
	}

	public void setAppointmentTimeSlotDAO(
			IAppointmentTimeSlotDAO appointmentTimeSlotDAO) {
		this.appointmentTimeSlotDAO = appointmentTimeSlotDAO;
	}
	
	public void test()
	{
		
	}

}
