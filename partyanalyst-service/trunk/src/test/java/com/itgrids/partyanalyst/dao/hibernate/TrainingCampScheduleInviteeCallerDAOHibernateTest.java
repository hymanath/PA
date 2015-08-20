package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeCallerDAO;

public class TrainingCampScheduleInviteeCallerDAOHibernateTest extends BaseDaoTestCase{
	
	private ITrainingCampScheduleInviteeCallerDAO trainingCampScheduleInviteeCallerDAO;

	public ITrainingCampScheduleInviteeCallerDAO getTrainingCampScheduleInviteeCallerDAO() {
		return trainingCampScheduleInviteeCallerDAO;
	}

	public void setTrainingCampScheduleInviteeCallerDAO(
			ITrainingCampScheduleInviteeCallerDAO trainingCampScheduleInviteeCallerDAO) {
		this.trainingCampScheduleInviteeCallerDAO = trainingCampScheduleInviteeCallerDAO;
	}
	
	/*public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try{
			
			Date startDate= format.parse("09/01/2015");
			Date endDate= format.parse("09/15/2015");
			List<Object[]> allocatedCallsForBatchConfirmationDetails = trainingCampScheduleInviteeCallerDAO.getAllocatedCallsForBatchConfirmationDetails("Planned", startDate, endDate);
			if(allocatedCallsForBatchConfirmationDetails != null && allocatedCallsForBatchConfirmationDetails.size() > 0)
			{
				System.out.println(allocatedCallsForBatchConfirmationDetails.size());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/

}
