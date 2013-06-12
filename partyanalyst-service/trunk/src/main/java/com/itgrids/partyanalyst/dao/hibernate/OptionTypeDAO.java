package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOptionTypeDAO;
import com.itgrids.partyanalyst.model.OptionType;

public class OptionTypeDAO extends GenericDaoHibernate<OptionType, Long> implements IOptionTypeDAO{

	public OptionTypeDAO() {
		super(OptionType.class);
		// TODO Auto-generated constructor stub
	}

}
