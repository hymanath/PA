package com.itgrids.partyanalyst.dao;

import java.util.List;

public interface ICommitteeConfirmRuleConditionDAO {
	
	public List<Object[]> getRolesMinPositionsByRule(Long committeeConfirmRuleId);
}
