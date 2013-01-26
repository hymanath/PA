package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterFamilyRange;

public interface IVoterFamilyRangeDAO extends GenericDao<VoterFamilyRange, Long>{
	
	public Long getVoterFamilyRangeIdByFamilyRange(String familyRange);

}
