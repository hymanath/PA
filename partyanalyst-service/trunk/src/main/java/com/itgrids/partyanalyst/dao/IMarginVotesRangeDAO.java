package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MarginVotesRange;

public interface IMarginVotesRangeDAO extends GenericDao<MarginVotesRange, Long> {
	public List<Object[]> getMarginVotesAgeRangeDetails();

}
