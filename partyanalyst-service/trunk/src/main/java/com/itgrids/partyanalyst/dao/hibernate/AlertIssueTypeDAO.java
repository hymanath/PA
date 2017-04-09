package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.AlertIssueType;

import com.itgrids.partyanalyst.dao.IAlertIssueTypeDAO;

public class AlertIssueTypeDAO extends GenericDaoHibernate<AlertIssueType, Long> implements IAlertIssueTypeDAO{

	public AlertIssueTypeDAO() {
		super(AlertIssueType.class);
		
	}

}
