package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterNames;

public interface IVoterNamesDAO extends GenericDao<VoterNames, Long>{

	public VoterNames gerVoterNamesObjByVoterId(Long voterId);
}
