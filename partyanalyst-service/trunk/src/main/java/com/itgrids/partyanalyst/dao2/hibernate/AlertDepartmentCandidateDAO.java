package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDepartmentCandidateDAO;
import com.itgrids.partyanalyst.model.AlertDepartmentCandidate;

public class AlertDepartmentCandidateDAO extends GenericDaoHibernate<AlertDepartmentCandidate, Long> implements IAlertDepartmentCandidateDAO{

	public AlertDepartmentCandidateDAO() {
		super(AlertDepartmentCandidate.class);
		
	}

}
