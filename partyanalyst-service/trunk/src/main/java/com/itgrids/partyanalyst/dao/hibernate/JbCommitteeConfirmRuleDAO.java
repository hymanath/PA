package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeConfirmRuleDAO;
import com.itgrids.partyanalyst.model.JbCommitteeConfirmRule;

public class JbCommitteeConfirmRuleDAO extends GenericDaoHibernate<JbCommitteeConfirmRule, Long>
		implements IJbCommitteeConfirmRuleDAO {

	public JbCommitteeConfirmRuleDAO() {
		super(JbCommitteeConfirmRule.class);
	}

	
}
