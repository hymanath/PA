package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.UnionType;

import com.itgrids.partyanalyst.dao.IUnionTypeDAO;

public class UnionTypeDAO extends GenericDaoHibernate<UnionType, Long> implements IUnionTypeDAO{

	public UnionTypeDAO() {
		super(UnionType.class);
		
	}

}
