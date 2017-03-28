package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalRuleDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalRule;

public class SelfAppraisalRuleDAO extends GenericDaoHibernate<SelfAppraisalRule, Long> implements ISelfAppraisalRuleDAO {
      public SelfAppraisalRuleDAO(){
    	  super(SelfAppraisalRule.class);
      }
}
