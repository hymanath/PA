package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterCategoryValues;

public interface IVoterCategoryValuesDAO extends GenericDao<VoterCategoryValues, Long>{
	
	//public void flushAndclearSession();
	public List<VoterCategoryValues> getVoterCategoryValues1();
}
