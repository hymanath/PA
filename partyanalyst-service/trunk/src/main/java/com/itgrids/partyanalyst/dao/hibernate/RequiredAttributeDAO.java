package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.RequiredAttribute;

import com.itgrids.partyanalyst.dao.IRequiredAttributeDAO;

public class RequiredAttributeDAO extends GenericDaoHibernate<RequiredAttribute, Long> implements IRequiredAttributeDAO{

	public RequiredAttributeDAO() {
		super(RequiredAttribute.class);
		
	}

}
