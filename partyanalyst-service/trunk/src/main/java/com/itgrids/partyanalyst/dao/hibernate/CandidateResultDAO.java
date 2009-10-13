/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.model.CandidateResult;


import com.itgrids.partyanalyst.dao.columns.enums.CandidateResultColumnNames;



public class CandidateResultDAO extends GenericDaoHibernate<CandidateResult, Long> implements
		ICandidateResultDAO {
  
	public CandidateResultDAO() {
		super(CandidateResult.class);
	}	
	

	public List<CandidateResult> findByRank(Object rank) {
		
		return findByProperty(CandidateResultColumnNames.RANK, rank);
	}

	public List<CandidateResult> findByVotesEarned(Object votesEarned) {
		
		return findByProperty(CandidateResultColumnNames.VOTES_EARNED, votesEarned);
	}
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findByProperty(CandidateResultColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from CandidateResult where " + propertyName.getValue() + "=?", value);		
		
	}
	
}
