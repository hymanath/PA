package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SuggestiveRange;

public interface ISuggestiveRangeDAO extends GenericDao<SuggestiveRange, Long>{
	
	public List<SuggestiveRange> getSuggestiveRangeList();

}
