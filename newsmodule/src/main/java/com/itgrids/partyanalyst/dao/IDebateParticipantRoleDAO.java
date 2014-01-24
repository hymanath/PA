package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipantRole;

public interface IDebateParticipantRoleDAO extends GenericDao<DebateParticipantRole, Long>{

	public List<Object[]> getParticepentRoles(Long debateId);
}
