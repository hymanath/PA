package com.itgrids.partyanalyst.dao.hibernate;


import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IJbCommitteeConfirmRuleConditionDAO;
import com.itgrids.partyanalyst.model.JbCommitteeConfirmRuleCondition;

public class JbCommitteeConfirmRuleConditionDAO extends GenericDaoHibernate<JbCommitteeConfirmRuleCondition, Long> implements
		IJbCommitteeConfirmRuleConditionDAO {

	public JbCommitteeConfirmRuleConditionDAO() {
		super(JbCommitteeConfirmRuleCondition.class);
		
	}

	public List<Object[]> getCasteCategoryMinMembersForCommittee(Long jbCommitteeConfirmRuleId){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select model.casteCategory.casteCategoryId,model.casteCategory.categoryName,model.minMember  from JbCommitteeConfirmRuleCondition model   ");
		
		if(jbCommitteeConfirmRuleId != null && jbCommitteeConfirmRuleId.longValue() >0l){
			sb.append(" where model.jbCommitteeConfirmRule.jbCommitteeConfirmRuleId = :jbCommitteeConfirmRuleId ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(jbCommitteeConfirmRuleId != null && jbCommitteeConfirmRuleId.longValue() >0l){
			query.setParameter("jbCommitteeConfirmRuleId", jbCommitteeConfirmRuleId);
		}
		
		return query.list();
		
	}

}
