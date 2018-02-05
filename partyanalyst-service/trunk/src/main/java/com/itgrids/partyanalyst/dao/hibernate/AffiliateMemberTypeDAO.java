package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAffiliateMemberTypeDAO;
import com.itgrids.partyanalyst.model.AffiliateMemberType;

public class AffiliateMemberTypeDAO extends GenericDaoHibernate<AffiliateMemberType, Long> implements IAffiliateMemberTypeDAO{

	public AffiliateMemberTypeDAO() {
		super(AffiliateMemberType.class);
		// TODO Auto-generated constructor stub
	}

}
