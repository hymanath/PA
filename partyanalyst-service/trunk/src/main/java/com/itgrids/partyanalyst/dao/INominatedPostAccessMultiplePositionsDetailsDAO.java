package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostAccessMultiplePositionsDetails;

public interface INominatedPostAccessMultiplePositionsDetailsDAO extends GenericDao<NominatedPostAccessMultiplePositionsDetails, Long> {
	public List<Object[]> getDepartMentIdsByBoardIdsList();

}
