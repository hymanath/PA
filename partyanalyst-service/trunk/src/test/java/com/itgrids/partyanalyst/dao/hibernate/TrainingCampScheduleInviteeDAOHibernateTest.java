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
	
	/*public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		try {
			
			Date startDate= format.parse("09/01/2015");
			Date endDate= format.parse("09/15/2015");
			List<Object[]> campAndSchedulewiseResultsList = trainingCampScheduleInviteeDAO.getCampusWiseBatchWiseMembersDetails("interested", "NotStarted", startDate, endDate);
			if(campAndSchedulewiseResultsList != null && campAndSchedulewiseResultsList.size()>0)
			{
				System.out.println(campAndSchedulewiseResultsList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		try{
			
			Date startDate= format.parse("09/01/2015");
			Date endDate= format.parse("09/15/2015");
			List<Object[]> batchAndProgramWiseInterestedMembersDetails = trainingCampScheduleInviteeDAO.getBatchWiseProgramWiseInterestedMembersDetails("interested", "NotStarted", startDate, endDate);
			if(batchAndProgramWiseInterestedMembersDetails != null && batchAndProgramWiseInterestedMembersDetails.size() > 0)
			{
				System.out.println(batchAndProgramWiseInterestedMembersDetails.size());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
