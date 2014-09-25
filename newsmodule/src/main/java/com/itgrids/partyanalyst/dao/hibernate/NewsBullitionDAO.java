package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsBullitionDAO;
import com.itgrids.partyanalyst.model.NewsBullition;

public class NewsBullitionDAO extends GenericDaoHibernate<NewsBullition,Long> implements INewsBullitionDAO {

	public NewsBullitionDAO() {
		super(NewsBullition.class);
	}

}
