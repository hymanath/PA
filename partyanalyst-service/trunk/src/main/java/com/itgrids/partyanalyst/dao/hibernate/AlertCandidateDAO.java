package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertCandidate;

public class AlertCandidateDAO extends
		GenericDaoHibernate<AlertCandidate, Long> implements IAlertCandidateDAO {
	public AlertCandidateDAO() {
		super(AlertCandidate.class);
	}

}
