package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertVerificationUserDAO;
import com.itgrids.partyanalyst.model.AlertVerificationUser;

public class AlertVerificationUserDAO extends GenericDaoHibernate<AlertVerificationUser, Long> implements
		IAlertVerificationUserDAO {
	
	public AlertVerificationUserDAO() {
		super (AlertVerificationUser.class);
	}

}
