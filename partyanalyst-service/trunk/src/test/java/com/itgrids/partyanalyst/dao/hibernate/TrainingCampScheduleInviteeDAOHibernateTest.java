package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeDAO;

	public class TrainingCampScheduleInviteeDAOHibernateTest  extends BaseDaoTestCase{
	private ITrainingCampScheduleInviteeDAO trainingCampScheduleInviteeDAO;
	
	public ITrainingCampScheduleInviteeDAO getTrainingCampScheduleInviteeDAO() {
		return trainingCampScheduleInviteeDAO;
	}
	
	public void setTrainingCampScheduleInviteeDAO(
			ITrainingCampScheduleInviteeDAO trainingCampScheduleInviteeDAO) {
		this.trainingCampScheduleInviteeDAO = trainingCampScheduleInviteeDAO;
	}
	
	public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		try {
			
			Date startDate= format.parse("09/01/2015");
			Date endDate= format.parse("09/15/2015");
			List<Object[]> list = trainingCampScheduleInviteeDAO.getCampusWiseBatchWiseMembersDetails("interested", "NotStarted", startDate, endDate);
			if(list != null && list.size()>0)
			{
				System.out.println(list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
