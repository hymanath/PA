package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IConstituencyTehsilDAO;
import com.itgrids.partyanalyst.model.ConstituencyTehsil;

public class ConstituencyTehsilDAO extends GenericDaoHibernate<ConstituencyTehsil, Long> implements IConstituencyTehsilDAO{

	public ConstituencyTehsilDAO() {
		super(ConstituencyTehsil.class);
}

}
