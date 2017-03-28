package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtSchemePhaseDAO;
import com.itgrids.partyanalyst.model.GovtSchemePhase;
import com.itgrids.partyanalyst.model.Schemephase;

public class GovtSchemePhaseDAO extends GenericDaoHibernate<GovtSchemePhase, Long> implements IGovtSchemePhaseDAO{

	public GovtSchemePhaseDAO() {
		super(GovtSchemePhase.class);
	}

}
