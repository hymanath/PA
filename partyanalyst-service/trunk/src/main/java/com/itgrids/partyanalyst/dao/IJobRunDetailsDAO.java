package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Job;
import com.itgrids.partyanalyst.model.JobRunDetails;

public interface IJobRunDetailsDAO extends GenericDao<JobRunDetails, Long>{
	public List<Date> getStartTime();
}
