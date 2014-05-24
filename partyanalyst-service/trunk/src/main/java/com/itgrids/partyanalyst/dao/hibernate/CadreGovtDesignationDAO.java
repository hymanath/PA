package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreGovtDesignationDAO;
import com.itgrids.partyanalyst.model.CadreGovtDesignation;

public class CadreGovtDesignationDAO extends GenericDaoHibernate<CadreGovtDesignation, Long> implements ICadreGovtDesignationDAO{

	public CadreGovtDesignationDAO() {
		super(CadreGovtDesignation.class);
	
	}

}
