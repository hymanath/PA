package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.dao.IElectionDAO;

public class ElectionService {
	
	private IElectionDAO electionDAO;

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	

}
