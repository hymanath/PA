package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertCadreTypeRelationDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.model.AlertCadreTypeRelation;

public class AlertCadreTypeRelationDAO extends GenericDaoHibernate<AlertCadreTypeRelation, Long> 
implements IAlertCadreTypeRelationDAO{

	public AlertCadreTypeRelationDAO() {
		super(AlertCadreTypeRelation.class);
	}

}
