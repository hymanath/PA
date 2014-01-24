package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipantCharcs;

public interface IDebateParticipantCharcsDAO extends GenericDao<DebateParticipantCharcs, Long>{

	public List<Object[]> getDebateCharcsDetails(Long debateId);
}
