/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 12, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Election;

public interface IElectionObjectsDAO extends GenericDao<Election, Long> {

    public List<Election> findElections(Long electionType_id,Long country_id,Long state_id);
	
	public List<Election> findElections(Long electionType_id,Long country_id);
	
	public List<Election> findElections(Long state_id);
	
	public List findLatestParliamentaryElectionYear(Long state_id);
}
