package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipantExpectedRole;

public interface IDebateParticipantExceptedRoleDAO extends GenericDao<DebateParticipantExpectedRole, Long>{

	public List<Object[]> getPaticepentExpRoles(Long debateId);
}
