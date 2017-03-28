package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJobRunDetailsDAO;
import com.itgrids.partyanalyst.model.JobRunDetails;

public class JobRunDetailsDAO extends GenericDaoHibernate<JobRunDetails, Long> implements IJobRunDetailsDAO{

	public JobRunDetailsDAO() {
		super(JobRunDetails.class);
			
	}
	
	public List<Date> getStartTime(){
		return getHibernateTemplate().find("select max(model.startTime) from JobRunDetails model");
	}
	

}
