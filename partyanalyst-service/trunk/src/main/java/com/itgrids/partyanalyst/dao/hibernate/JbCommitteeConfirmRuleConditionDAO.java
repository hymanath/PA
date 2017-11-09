package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeConfirmRuleConditionDAO;
import com.itgrids.partyanalyst.model.JbCommitteeConfirmRuleCondition;

public class JbCommitteeConfirmRuleConditionDAO extends GenericDaoHibernate<JbCommitteeConfirmRuleCondition, Long> implements
		IJbCommitteeConfirmRuleConditionDAO {

	public JbCommitteeConfirmRuleConditionDAO() {
		super(JbCommitteeConfirmRuleCondition.class);
		
	}

	

}
