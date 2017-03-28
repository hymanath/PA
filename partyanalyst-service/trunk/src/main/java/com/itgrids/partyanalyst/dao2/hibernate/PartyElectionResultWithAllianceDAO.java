package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionResultWithAllianceDAO;
import com.itgrids.partyanalyst.model.PartyElectionResultWithAlliance;

public class PartyElectionResultWithAllianceDAO extends
		GenericDaoHibernate<PartyElectionResultWithAlliance, Long> implements
		IPartyElectionResultWithAllianceDAO {

	public PartyElectionResultWithAllianceDAO() {
		super(PartyElectionResultWithAlliance.class);
	}
	

}
