package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertCadreTypeDAO;
import com.itgrids.partyanalyst.model.AlertCadreType;

public class AlertCadreTypeDAO extends GenericDaoHibernate<AlertCadreType, Long> 
 implements IAlertCadreTypeDAO {

	public AlertCadreTypeDAO() {
		super(AlertCadreType.class);
	}

}
