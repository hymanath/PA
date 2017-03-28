package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyTrainingCamps;

public interface IPartyTrainingCampsDAO extends GenericDao<PartyTrainingCamps, Long> {
	@SuppressWarnings("unchecked")
	public List<PartyTrainingCamps> getTrainingCampsPartywise(Long partyId);
}
