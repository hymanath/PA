package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyTrainingCampsDAO;
import com.itgrids.partyanalyst.model.PartyTrainingCamps;

public class PartyTrainingCampsDAO extends GenericDaoHibernate<PartyTrainingCamps, Long> implements IPartyTrainingCampsDAO {

	public PartyTrainingCampsDAO() {
		super(PartyTrainingCamps.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<PartyTrainingCamps> getTrainingCampsPartywise(Long partyId) {
		
		return getHibernateTemplate().find("from PartyTrainingCamps model where model.party.partyId = ?", partyId);
	}

}
