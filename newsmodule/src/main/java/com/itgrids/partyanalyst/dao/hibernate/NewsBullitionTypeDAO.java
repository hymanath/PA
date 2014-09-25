package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsBullitionTypeDAO;
import com.itgrids.partyanalyst.model.NewsBullitionType;

public class NewsBullitionTypeDAO  extends GenericDaoHibernate<NewsBullitionType, Long> implements INewsBullitionTypeDAO{

	public NewsBullitionTypeDAO() {
		super(NewsBullitionType.class);
	}

}
