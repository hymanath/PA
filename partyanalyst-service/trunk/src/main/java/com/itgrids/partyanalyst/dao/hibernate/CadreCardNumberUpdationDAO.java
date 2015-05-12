package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreCardNumberUpdationDAO;
import com.itgrids.partyanalyst.model.CadreCardNumberUpdation;

public class CadreCardNumberUpdationDAO extends GenericDaoHibernate<CadreCardNumberUpdation, Long> implements ICadreCardNumberUpdationDAO{

	public CadreCardNumberUpdationDAO() {
		super(CadreCardNumberUpdation.class);
		// TODO Auto-generated constructor stub
	}

}
