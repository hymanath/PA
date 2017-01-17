package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActionTypeStatusDAO;
import com.itgrids.partyanalyst.model.ActionTypeStatus;

public class ActionTypeStatusDAO extends GenericDaoHibernate<ActionTypeStatus, Long> implements IActionTypeStatusDAO {

	public ActionTypeStatusDAO(){
		super(ActionTypeStatus.class);
	}
}
