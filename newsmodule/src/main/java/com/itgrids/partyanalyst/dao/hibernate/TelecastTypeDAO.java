package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITelecastTypeDAO;
import com.itgrids.partyanalyst.model.TelecastType;

public class TelecastTypeDAO extends GenericDaoHibernate<TelecastType, Long> implements ITelecastTypeDAO{

	public TelecastTypeDAO() {
		super(TelecastType.class);
		// TODO Auto-generated constructor stub
	}

}
