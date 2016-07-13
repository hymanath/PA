package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.model.ApplicationStatus;

public class ApplicationStatusDAO extends GenericDaoHibernate<ApplicationStatus, Long> implements IApplicationStatusDAO{

	public ApplicationStatusDAO() {
		super(ApplicationStatus.class);
		
	}

}
