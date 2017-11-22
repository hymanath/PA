package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.JbCommitteeConfirmRuleCondition;

      public interface IJbCommitteeConfirmRuleConditionDAO extends GenericDao<JbCommitteeConfirmRuleCondition, Long> {
    	  
    	  public List<Object[]> getCasteCategoryMinMembersForCommittee(Long jbCommitteeConfirmRuleId);

}
