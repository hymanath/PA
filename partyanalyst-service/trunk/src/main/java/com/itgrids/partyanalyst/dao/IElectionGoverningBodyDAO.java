package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ElectionGoverningBody;

public interface IElectionGoverningBodyDAO extends GenericDao<ElectionGoverningBody, Long>{

	public List<Object[]> getAllMinistersIdsAndMinistry(Long electionId);
}
