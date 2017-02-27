package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;

public interface INominatedPostGovtOrderDAO extends GenericDao<NominatedPostGovtOrder, Long>{
	public List<Object[]> gettingGoPassedDates(Long nominatedPostId);

}
