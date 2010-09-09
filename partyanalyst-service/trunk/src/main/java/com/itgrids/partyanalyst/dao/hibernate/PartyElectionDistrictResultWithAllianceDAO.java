package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResultWithAlliance;

public class PartyElectionDistrictResultWithAllianceDAO extends
		GenericDaoHibernate<PartyElectionDistrictResultWithAlliance, Long>
		implements IPartyElectionDistrictResultWithAllianceDAO {

	public PartyElectionDistrictResultWithAllianceDAO() {
		super(PartyElectionDistrictResultWithAlliance.class);
	}

	
}
