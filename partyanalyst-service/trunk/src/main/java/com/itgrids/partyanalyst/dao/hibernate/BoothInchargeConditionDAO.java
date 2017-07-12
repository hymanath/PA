package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothInchargeConditionDAO;
import com.itgrids.partyanalyst.model.BoothInchargeCondition;

public class BoothInchargeConditionDAO extends GenericDaoHibernate<BoothInchargeCondition, Long> implements IBoothInchargeConditionDAO{

	public BoothInchargeConditionDAO() {
		super(BoothInchargeCondition.class);
	}
}
