package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.TrainingCampSchedule;

public class TrainingCampAttendanceTabUserDAOHibernateTest extends BaseDaoTestCase{

	private ITrainingCampAttendanceTabUserDAO trainingCampAttendanceTabUserDAO;

	public void setTrainingCampAttendanceTabUserDAO(
			ITrainingCampAttendanceTabUserDAO trainingCampAttendanceTabUserDAO) {
		this.trainingCampAttendanceTabUserDAO = trainingCampAttendanceTabUserDAO;
	}
	
	/*public void test()
	{
		trainingCampAttendanceTabUserDAO.getAll();
	}*/
	
	/*public void testGetTrainingCampSchedulesOfAttendanceTabUser()
	{
		List<TrainingCampSchedule> list = trainingCampAttendanceTabUserDAO.getTrainingCampSchedulesOfAttendanceTabUser(1l);
		System.out.println(list.size());
	
	}*/
	
	public void testGetTrainingCampBatchesOfScheduleForTabUser()
	{
		List<Object[]> list = trainingCampAttendanceTabUserDAO.getTrainingCampBatchesOfScheduleForTabUser(1l,1l);
		System.out.println(list.size());
	}
}
