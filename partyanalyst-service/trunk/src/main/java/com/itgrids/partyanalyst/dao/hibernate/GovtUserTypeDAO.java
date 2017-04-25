package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGovtUserTypeDAO;
import com.itgrids.partyanalyst.model.GovtUserType;


public class GovtUserTypeDAO extends GenericDaoHibernate<GovtUserType, Long> implements
IGovtUserTypeDAO  {

	public GovtUserTypeDAO(){
		super(GovtUserType.class);
	}
}
