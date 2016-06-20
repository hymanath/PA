package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.service.INotificationService;

public class AppointmentDAOHibernateTest extends BaseDaoTestCase {
	private INotificationService notificationService;
	private IAppointmentDAO appointmentDAO;

	public IAppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}

	public void setAppointmentDAO(IAppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}
	
	public INotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void test(){
		Long notificationType = 1l;
		String notification = "good news for you";
		String status = notificationService.saveNotification(notificationType,notification );
	}
}
