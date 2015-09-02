package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IReferalDesignationDAO;
import com.itgrids.partyanalyst.model.ReferalDesignation;

public class ReferalDesignationDAO extends GenericDaoHibernate<ReferalDesignation, Long> implements IReferalDesignationDAO{

	public ReferalDesignationDAO() {
		super(ReferalDesignation.class);
		
	}

}
