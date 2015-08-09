package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;

public class TrainingCampBatchAttendeeDAOHibernateTest extends BaseDaoTestCase{
	
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;

	public ITrainingCampBatchAttendeeDAO getTrainingCampBatchAttendeeDAO() {
		return trainingCampBatchAttendeeDAO;
	}

	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}
	
	public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
		
		try{
			
			Date startDate= format.parse("09/01/2015");
			Date endDate= format.parse("09/15/2015");
			
			List<Object[]> batchAndProgramWiseAcceptedMembersDetails = trainingCampBatchAttendeeDAO.getBatchWiseProgramWiseAcceptedMemeberDetails("NotStarted", startDate, endDate);
			if(batchAndProgramWiseAcceptedMembersDetails != null && batchAndProgramWiseAcceptedMembersDetails.size() > 0)
			{
				System.out.println(batchAndProgramWiseAcceptedMembersDetails.size());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
