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
import org.appfuse.dao.jpa.GenericDaoJpa;
import javax.persistence.Query;
import com.itgrids.partyanalyst.dao.columns.enums.ConstituencyElectionResultColumnNames;


import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;


/**
*@author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
*@author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
*/


public class ConstituencyElectionResultDAO extends GenericDaoHibernate<ConstituencyElectionResult, Long>
		implements IConstituencyElectionResultDAO {
    
	
	public ConstituencyElectionResultDAO() {
		super(ConstituencyElectionResult.class);
	}
	public List<ConstituencyElectionResult> findByMissingVotes(
			Object missingVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.MISSING_VOTES, missingVotes);
	}

	
	public List<ConstituencyElectionResult> findByRejectedVotes(
			Object rejectedVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.REJECTED_VOTES, rejectedVotes);
	}

	public List<ConstituencyElectionResult> findByTenderedVotes(
			Object tenderedVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.TENDERED_VOTES, tenderedVotes);
	}

	public List<ConstituencyElectionResult> findByTotalVotes(Object totalVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.TOTAL_VOTES, totalVotes);
	}

	public List<ConstituencyElectionResult> findByTotalVotesPolled(
			Object totalVotesPolled) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.TOTAL_VOTES_POLLED, totalVotesPolled);
	}

	public List<ConstituencyElectionResult> findByValidVotes(Object validVotes) {
		
		return findByProperty(ConstituencyElectionResultColumnNames.VALID_VOTES, validVotes);
	}

	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByProperty(ConstituencyElectionResultColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from ConstituencyElectionResult where " + propertyName.getValue() + "=?", value);		
		
	}
	
/**
 * @param constituencyElectionIDs should be list of constituencyElectionIDs with comma seperated string
 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyElectionResult> findByConstituencyElections(String constituencyElectionIDs){
		return getHibernateTemplate().find( "from ConstituencyElectionResult model where model.constituencyElection.constiElecId in ("+constituencyElectionIDs+")");
	}
	
}
