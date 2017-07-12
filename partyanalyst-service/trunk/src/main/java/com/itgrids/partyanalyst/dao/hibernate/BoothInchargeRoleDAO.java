package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeRoleDAO;
import com.itgrids.partyanalyst.model.BoothInchargeRole;

public class BoothInchargeRoleDAO extends GenericDaoHibernate<BoothInchargeRole, Long> implements IBoothInchargeRoleDAO{

	public BoothInchargeRoleDAO() {
		super(BoothInchargeRole.class);
	}

}
