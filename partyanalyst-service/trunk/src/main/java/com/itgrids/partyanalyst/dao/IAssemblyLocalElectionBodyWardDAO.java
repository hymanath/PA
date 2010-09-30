package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssemblyLocalElectionBodyWard;

public interface IAssemblyLocalElectionBodyWardDAO extends GenericDao<AssemblyLocalElectionBodyWard, Long> {
	@SuppressWarnings("unchecked")
	public List findByLocalElectionBody(Long localElectionBodyId, String year);
}
