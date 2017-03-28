package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalRuleProgramDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalRuleProgram;

public class SelfAppraisalRuleProgramDAO extends GenericDaoHibernate<SelfAppraisalRuleProgram, Long> implements
		ISelfAppraisalRuleProgramDAO {
        public SelfAppraisalRuleProgramDAO(){
        	super(SelfAppraisalRuleProgram.class);
        }
}
