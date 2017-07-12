package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionMappingDAO;
import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;

public class BoothInchargeRoleConditionMappingDAO extends GenericDaoHibernate<BoothInchargeRoleConditionMapping, Long> implements IBoothInchargeRoleConditionMappingDAO{

	public BoothInchargeRoleConditionMappingDAO() {
		super(BoothInchargeRoleConditionMapping.class);
	}
}
