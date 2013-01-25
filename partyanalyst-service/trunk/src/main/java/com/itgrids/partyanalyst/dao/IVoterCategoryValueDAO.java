package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterCategoryValue;

public interface IVoterCategoryValueDAO extends GenericDao<VoterCategoryValue, Long>{
	
	//public void flushAndclearSession();
	public List<VoterCategoryValue> getVoterCategoryValues1();
	
	public List<Long> getVoterCategoryValue(Long userId,Long voterId,Long categoryId);
	
	public List<VoterCategoryValue> getVoterCategoryValues(Long userId,Long voterId,Long categoryId);
	
	public List<Object[]> getVoterCategoryValuesForVoters(Long userId,List<Long> voterIds);
}
