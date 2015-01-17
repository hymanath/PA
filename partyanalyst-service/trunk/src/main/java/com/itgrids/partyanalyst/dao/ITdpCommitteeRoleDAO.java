package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeRole;

public interface ITdpCommitteeRoleDAO  extends GenericDao<TdpCommitteeRole, Long>{
	public List<Object[]> getAllCommitteeRoles(Long committeeId);
}
