/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 13, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.PartyImportantDates;

public interface IPartyImportantDatesDAO extends GenericDao<PartyImportantDates, Long> {

	public List<PartyImportantDates> findByPartyId(Long partyId);
	public List<PartyImportantDates> findTodaysPartyImportantDates(Long partyId);

}
