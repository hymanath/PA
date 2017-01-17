package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAlertActionType;
import com.itgrids.partyanalyst.model.AlertActionType;

public class AlertActionTypeDAO extends GenericDaoHibernate<AlertActionType, Long> implements IAlertActionType {

	public AlertActionTypeDAO(){
		super(AlertActionType.class);
	}
}
