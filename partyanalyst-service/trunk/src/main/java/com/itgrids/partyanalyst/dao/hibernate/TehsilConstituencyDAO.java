package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITehsilConstituencyDAO;
import com.itgrids.partyanalyst.model.TehsilConstituency;

public class TehsilConstituencyDAO extends GenericDaoHibernate<TehsilConstituency,Long> implements ITehsilConstituencyDAO {
	
	public TehsilConstituencyDAO() {
		super(TehsilConstituency.class);
	}

	
}
