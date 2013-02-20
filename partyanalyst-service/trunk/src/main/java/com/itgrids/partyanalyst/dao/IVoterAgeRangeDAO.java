package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterAgeRange;

public interface IVoterAgeRangeDAO extends GenericDao<VoterAgeRange, Long>{

	public Long getVoterAgeRangeIdByType(String ageRange);
	
	public List<String> getAllVoterAgeRanges();
	
}
