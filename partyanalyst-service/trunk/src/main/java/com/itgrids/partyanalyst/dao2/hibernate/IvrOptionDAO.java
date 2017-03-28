package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IIvrOptionDAO;
import com.itgrids.partyanalyst.model.IvrOption;

public class IvrOptionDAO extends GenericDaoHibernate<IvrOption,Long> implements IIvrOptionDAO{

	public IvrOptionDAO() {
		super(IvrOption.class);
	}

	
}
