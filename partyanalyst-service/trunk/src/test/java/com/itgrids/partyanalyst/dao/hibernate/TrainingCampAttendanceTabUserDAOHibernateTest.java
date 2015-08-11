package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceTabUserDAO;

public class TrainingCampAttendanceTabUserDAOHibernateTest extends BaseDaoTestCase{

	private ITrainingCampAttendanceTabUserDAO trainingCampAttendanceTabUserDAO;

	public void setTrainingCampAttendanceTabUserDAO(
			ITrainingCampAttendanceTabUserDAO trainingCampAttendanceTabUserDAO) {
		this.trainingCampAttendanceTabUserDAO = trainingCampAttendanceTabUserDAO;
	}
	
	public void test()
	{
		trainingCampAttendanceTabUserDAO.getAll();
	}
}
