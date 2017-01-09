package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertClarificationStatusDAO;
import com.itgrids.partyanalyst.model.AlertClarificationStatus;

public class AlertClarificationStatusDAO extends GenericDaoHibernate<AlertClarificationStatus, Long> implements IAlertClarificationStatusDAO{

	public AlertClarificationStatusDAO() {
		super(AlertClarificationStatus.class);
	}

}
