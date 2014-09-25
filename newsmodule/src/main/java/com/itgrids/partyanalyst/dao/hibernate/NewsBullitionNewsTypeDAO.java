package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsBullitionNewsTypeDAO;
import com.itgrids.partyanalyst.model.NewsBullitionNewsType;

public class NewsBullitionNewsTypeDAO extends GenericDaoHibernate<NewsBullitionNewsType, Long> implements INewsBullitionNewsTypeDAO{

	public NewsBullitionNewsTypeDAO() {
		super(NewsBullitionNewsType.class);
	}

}
