package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterFlag;

public interface IVoterFlagDAO extends GenericDao<VoterFlag, Long>{
	public Integer deleteVoterFlag(Long flagId);
}
