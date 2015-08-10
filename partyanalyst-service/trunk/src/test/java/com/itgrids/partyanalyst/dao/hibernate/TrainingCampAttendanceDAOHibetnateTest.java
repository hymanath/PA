package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;

public class TrainingCampAttendanceDAOHibetnateTest extends BaseDaoTestCase{

	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;

	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}
	
	public void test()
	{
		trainingCampAttendanceDAO.getAll();
	}
}
