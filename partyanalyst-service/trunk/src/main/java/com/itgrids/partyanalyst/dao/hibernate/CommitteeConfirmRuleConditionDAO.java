package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeConfirmRuleConditionDAO;
import com.itgrids.partyanalyst.model.CommitteeConfirmRuleCondition;

public class CommitteeConfirmRuleConditionDAO extends GenericDaoHibernate<CommitteeConfirmRuleCondition, Long> implements ICommitteeConfirmRuleConditionDAO {

	public CommitteeConfirmRuleConditionDAO() {
	  super(CommitteeConfirmRuleCondition.class);
	}

}
