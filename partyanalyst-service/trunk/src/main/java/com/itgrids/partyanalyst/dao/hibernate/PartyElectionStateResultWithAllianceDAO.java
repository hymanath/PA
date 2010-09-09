package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionStateResultWithAllianceDAO;
import com.itgrids.partyanalyst.model.PartyElectionStateResultWithAlliance;

public class PartyElectionStateResultWithAllianceDAO extends
		GenericDaoHibernate<PartyElectionStateResultWithAlliance, Long> implements
		IPartyElectionStateResultWithAllianceDAO {

	public PartyElectionStateResultWithAllianceDAO() {
		super(PartyElectionStateResultWithAlliance.class);
	}

}
