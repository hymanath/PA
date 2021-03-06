/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;

/**
 * Interface for ConstituencyElectionDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IConstituencyElectionDAO extends GenericDao<ConstituencyElection, Long>{

	/**
	 * Find all ConstituencyElection entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ConstituencyElection property to query
	 * @param value
	 *            the property value to match
	 * @return List<ConstituencyElection> found by query
	 */
	public List<ConstituencyElection> findByProperty(String propertyName,
			Object value);
	public List<ConstituencyElection> findByElection(Long electionID);
	public List<ConstituencyElection> findByElectionAndDistrict(Long electionID, Long districtID);
	public List<ConstituencyElection> findByElectionAndConstituency(Long electionID, Long constituencyID);
	public List<ConstituencyElection> findByConstituency(Constituency constituency);
	public List<ConstituencyElection> findByConstituencyElectionAndDistrict(String electionYear, String constituencyName, Long electionScopeId, Long districtId);
	public List<ConstituencyElection> findByConstituencyElectionAndState(String electionYear, String parliamentConstituencyName, Long electionScopeId, Long stateId);
	
	public List<ConstituencyElection> findByElectionScopeAndYear(Long electionScopeId,String year);
	
	public List<ConstituencyElection> findByElectionAndState(Long electionID,Long stateId);
	
	public List<ConstituencyElection> findByElectionAndStateAndDistrict(Long electionID,Long stateId,Long districtID);
	

}