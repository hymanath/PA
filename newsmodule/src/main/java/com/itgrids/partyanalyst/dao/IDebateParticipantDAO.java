package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipant;

public interface IDebateParticipantDAO extends GenericDao<DebateParticipant, Long>{

	public List<Object[]> getDebatePaticepantsAndSummeryDetails(Long debateId);
}
