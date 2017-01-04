package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISchemephaseDAO;
import com.itgrids.partyanalyst.model.GovtSchemeBenefitType;
import com.itgrids.partyanalyst.model.Schemephase;

public class SchemephaseDAO extends GenericDaoHibernate<Schemephase, Long> implements ISchemephaseDAO {

	public SchemephaseDAO() {
		super(Schemephase.class);
	}

}
