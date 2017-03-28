package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertUserTypeDAO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertUserType;

public class AlertUserTypeDAO extends GenericDaoHibernate<AlertUserType, Long>
		implements IAlertUserTypeDAO {
	public AlertUserTypeDAO() {
		super(AlertUserType.class);
	}
	
	
	

}
