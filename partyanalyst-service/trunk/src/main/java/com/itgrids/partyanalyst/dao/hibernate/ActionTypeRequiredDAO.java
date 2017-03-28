package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActionTypeRequiredDAO;
import com.itgrids.partyanalyst.model.ActionTypeRequired;

public class ActionTypeRequiredDAO extends GenericDaoHibernate<ActionTypeRequired, Long> implements IActionTypeRequiredDAO {

	public ActionTypeRequiredDAO(){
		super(ActionTypeRequired.class);
	}

}
