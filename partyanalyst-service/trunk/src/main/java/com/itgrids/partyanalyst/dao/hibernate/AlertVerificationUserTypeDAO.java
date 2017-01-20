package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertVerificationUserTypeDAO;
import com.itgrids.partyanalyst.model.AlertVerificationUserType;

public class AlertVerificationUserTypeDAO extends GenericDaoHibernate<AlertVerificationUserType, Long> implements IAlertVerificationUserTypeDAO {

	public AlertVerificationUserTypeDAO(){
		super(AlertVerificationUserType.class);
	}

}
