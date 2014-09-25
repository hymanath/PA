package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsBullitionValuesDAO;
import com.itgrids.partyanalyst.model.NewsBullitionValues;

public class NewsBullitionValuesDAO extends GenericDaoHibernate<NewsBullitionValues, Long> implements INewsBullitionValuesDAO{

	public NewsBullitionValuesDAO() {
		super(NewsBullitionValues.class);
	}

}
