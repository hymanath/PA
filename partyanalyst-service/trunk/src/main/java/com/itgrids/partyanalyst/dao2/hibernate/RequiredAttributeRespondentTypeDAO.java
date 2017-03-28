package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRequiredAttributeRespondentTypeDAO;
import com.itgrids.partyanalyst.model.RequiredAttributeRespondentType;

public class RequiredAttributeRespondentTypeDAO extends GenericDaoHibernate<RequiredAttributeRespondentType, Long> implements IRequiredAttributeRespondentTypeDAO{

	public RequiredAttributeRespondentTypeDAO() {
		super(RequiredAttributeRespondentType.class);
		
	}
}
