package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterStatus;

public interface IVoterStatusDAO extends GenericDao<VoterStatus, Long>{

	public Long getVoterStatusIdByStatus(String status);
	
}
