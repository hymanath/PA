package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISelfAppraisalProgramDesignationRuleDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalProgramDesignationRule;

public class SelfAppraisalProgramDesignationRuleDAO extends GenericDaoHibernate<SelfAppraisalProgramDesignationRule, Long>
		implements ISelfAppraisalProgramDesignationRuleDAO {

	   public SelfAppraisalProgramDesignationRuleDAO(){
		   super(SelfAppraisalProgramDesignationRule.class);
	   }
}
