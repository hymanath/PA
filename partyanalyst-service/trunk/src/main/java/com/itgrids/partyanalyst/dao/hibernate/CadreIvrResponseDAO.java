package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.model.CadreIvrResponse;

public class CadreIvrResponseDAO extends GenericDaoHibernate<CadreIvrResponse, Long> implements ICadreIvrResponseDAO{

	public CadreIvrResponseDAO() {
		super(CadreIvrResponse.class);
		// TODO Auto-generated constructor stub
	}

}
