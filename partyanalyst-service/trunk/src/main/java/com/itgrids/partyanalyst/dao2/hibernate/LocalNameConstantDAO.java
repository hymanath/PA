package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ILocalNameConstantDAO;
import com.itgrids.partyanalyst.model.LocalNameConstant;

public class LocalNameConstantDAO extends GenericDaoHibernate<LocalNameConstant,Long> implements ILocalNameConstantDAO{

	public LocalNameConstantDAO() {
		super(LocalNameConstant.class);
	}

	
}
