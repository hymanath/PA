package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCommentDAO;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.service.impl.DateService;

public class AppointmentCommentDAOHibernateTest extends BaseDaoTestCase{
	
	private IAppointmentCommentDAO appointmentCommentDAO;

	public IAppointmentCommentDAO getAppointmentCommentDAO() {
		return appointmentCommentDAO;
	}

	public void setAppointmentCommentDAO(
			IAppointmentCommentDAO appointmentCommentDAO) {
		this.appointmentCommentDAO = appointmentCommentDAO;
	}
	
	public void test(){
		
	}
	
}
