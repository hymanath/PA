package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostAgeRange;

public interface INominatedPostAgeRangeDAO extends GenericDao<NominatedPostAgeRange, Long>{

	public List<Object[]> getAllAgeRanges();
	public List<Object[]> getAllAgeRangesByOrder();
}
