package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeConfirmRuleDAO;
import com.itgrids.partyanalyst.model.CommitteeConfirmRule;

public class CommitteeConfirmRuleDAO extends GenericDaoHibernate<CommitteeConfirmRule, Long> implements ICommitteeConfirmRuleDAO {

	public CommitteeConfirmRuleDAO() {
		super(CommitteeConfirmRule.class);
	}
    
}
