package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOptionDAO;
import com.itgrids.partyanalyst.model.Option;

public class OptionDAO extends GenericDaoHibernate<Option, Long> implements IOptionDAO{

	public OptionDAO() {
		super(Option.class);
		// TODO Auto-generated constructor stub
	}

}
