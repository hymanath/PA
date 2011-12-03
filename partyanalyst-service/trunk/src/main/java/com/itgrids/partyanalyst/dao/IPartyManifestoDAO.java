package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyManifesto;

public interface IPartyManifestoDAO extends GenericDao<PartyManifesto, Long> {
	public List<Object[]> getPartyManifestoInfo(Long partyId);
}
