package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJobDAO;
import com.itgrids.partyanalyst.model.Job;

public class JobDAO extends GenericDaoHibernate<Job, Long> implements IJobDAO{

	public JobDAO() {
		super(Job.class);
		
	}
	

}
