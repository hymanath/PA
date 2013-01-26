package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterAgeRange;

public interface IVoterAgeRangeDAO extends GenericDao<VoterAgeRange, Long>{

	public Long getVoterAgeRangeIdByType(String ageRange);
	
}
