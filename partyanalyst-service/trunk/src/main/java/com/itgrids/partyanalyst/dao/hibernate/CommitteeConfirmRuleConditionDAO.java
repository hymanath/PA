package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICommitteeConfirmRuleConditionDAO;
import com.itgrids.partyanalyst.model.CommitteeConfirmRuleCondition;

public class CommitteeConfirmRuleConditionDAO extends GenericDaoHibernate<CommitteeConfirmRuleCondition, Long> implements ICommitteeConfirmRuleConditionDAO {

	public CommitteeConfirmRuleConditionDAO() {
	  super(CommitteeConfirmRuleCondition.class);
	}
    
    	public List<Object[]> getRolesMinPositionsByRule(Long committeeConfirmRuleId){
		
		Query query = getSession().createQuery("" +
		" select  model.tdpRolesId , model.minPositions " +
		" from    CommitteeConfirmRuleCondition model " +
		" where   model.committeeConfirmRuleId = :committeeConfirmRuleId ");
		query.setParameter("committeeConfirmRuleId", committeeConfirmRuleId);
		return query.list();
	}
}
