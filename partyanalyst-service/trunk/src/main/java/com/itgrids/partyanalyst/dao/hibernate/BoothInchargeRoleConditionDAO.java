package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionDAO;
import com.itgrids.partyanalyst.model.BoothInchargeRoleCondition;

public class BoothInchargeRoleConditionDAO extends GenericDaoHibernate<BoothInchargeRoleCondition, Long> implements IBoothInchargeRoleConditionDAO{

	public BoothInchargeRoleConditionDAO() {
		super(BoothInchargeRoleCondition.class);
	}
}
