package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertVerificationUserTypeUserDAO;
import com.itgrids.partyanalyst.model.AlertVerificationUserTypeUser;

public class AlertVerificationUserTypeUserDAO extends GenericDaoHibernate<AlertVerificationUserTypeUser, Long> implements IAlertVerificationUserTypeUserDAO {

	public AlertVerificationUserTypeUserDAO(){
		super(AlertVerificationUserTypeUser.class);
	}

}
