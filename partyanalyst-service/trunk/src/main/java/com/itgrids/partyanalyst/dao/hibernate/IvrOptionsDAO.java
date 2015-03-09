package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IIvrOptionsDAO;
import com.itgrids.partyanalyst.model.IvrOptions;


public class IvrOptionsDAO extends GenericDaoHibernate<IvrOptions, Long> implements IIvrOptionsDAO{

	public IvrOptionsDAO() {
		super(IvrOptions.class);
	}

}
