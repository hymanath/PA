package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;

public class CadreParticipatedElectionDAO extends GenericDaoHibernate<CadreParticipatedElection, Long> implements ICadreParticipatedElectionDAO{

	public CadreParticipatedElectionDAO() {
		super(CadreParticipatedElection.class);
	}

}
