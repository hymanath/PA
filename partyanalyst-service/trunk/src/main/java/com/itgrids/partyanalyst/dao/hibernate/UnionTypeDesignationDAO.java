package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.UnionTypeDesignation;

import com.itgrids.partyanalyst.dao.IUnionTypeDesignationDAO;

public class UnionTypeDesignationDAO extends GenericDaoHibernate<UnionTypeDesignation, Long> implements IUnionTypeDesignationDAO{

	public UnionTypeDesignationDAO() {
		super(UnionTypeDesignation.class);
		
	}

}
