package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertActionTypeDAO;
import com.itgrids.partyanalyst.model.AlertActionType;

public class AlertActionTypeDAO extends GenericDaoHibernate<AlertActionType, Long> implements IAlertActionTypeDAO {

	public AlertActionTypeDAO(){
		super(AlertActionType.class);
	}
	
}
