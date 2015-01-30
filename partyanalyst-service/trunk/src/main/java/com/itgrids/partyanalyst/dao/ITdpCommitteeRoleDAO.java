package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeRole;

public interface ITdpCommitteeRoleDAO  extends GenericDao<TdpCommitteeRole, Long>{
	public List<Object[]> getAllCommitteeRoles(Long committeeId);
	public String gettingConfirmedCommittee(Long tdpCommitteeId,Long tdpCommitteeRoleId);
	public List<Object[]> getDetailsForTdpCommitteRoleId(Long roleId);
	public int updateMaxPosForCommitteeRoleId(Long roleId, Long maxPos, Date updatedTime);
	public String getCommitteeStatus(Long tdpCommitteeRole);
	public List<Object[]> getMaxCountsForACommittee(Long committeeId);
}
