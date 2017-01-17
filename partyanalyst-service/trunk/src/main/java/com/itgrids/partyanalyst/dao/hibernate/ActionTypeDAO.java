package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActionTypeDAO;
import com.itgrids.partyanalyst.model.ActionType;

public class ActionTypeDAO extends GenericDaoHibernate<ActionType, Long> implements IActionTypeDAO {

	public ActionTypeDAO(){
		super(ActionType.class);
	}

	
}
