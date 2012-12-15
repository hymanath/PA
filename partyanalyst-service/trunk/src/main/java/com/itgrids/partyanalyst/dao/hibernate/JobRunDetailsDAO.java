package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJobRunDetailsDAO;
import com.itgrids.partyanalyst.model.JobRunDetails;

public class JobRunDetailsDAO extends GenericDaoHibernate<JobRunDetails, Long> implements IJobRunDetailsDAO{

	public JobRunDetailsDAO() {
		super(JobRunDetails.class);
			
	}
	
	

}
