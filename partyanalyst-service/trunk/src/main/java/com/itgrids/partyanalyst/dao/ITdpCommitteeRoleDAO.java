package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeRole;

public interface ITdpCommitteeRoleDAO  extends GenericDao<TdpCommitteeRole, Long>{
	
	public List<Object[]> getAllCommitteeRoles(Long committeeId);
	public List<Object[]> getAllCommitteeRoles(Long committeeId,List<Long> enrollmentIds,Date startDate,Date endDate);
	public List<Object[]> getDetailsForTdpCommitteRoleId(Long roleId);
	public int updateMaxPosForCommitteeRoleId(Long roleId, Long maxPos, Date updatedTime);
	public String getCommitteeStatus(Long tdpCommitteeRole);
	public List<Object[]> getMaxCountsForACommittee(Long committeeId);
	public List<Object[]> getAllAffiliCommitteeRoles(List<Long> affiliIds);
	public String getTdpCommitteeRoleType(Long tdpCommitteeRoleId);
	public List<Object[]> getTdpRoles(List<Long> committeeEnrollmntIds,List<Long> committeeLevlIdsList);
	public List<Object[]> getAllRolesInACommittee(Long tdpCommitteeId);
	public List<Object[]> getTotalCommitteesByRole(Long constituencyId,List<Long> levelIds,List<Long> lvelValues,List<Long> basicCommiteIds,List<Long> cmiteEnrlmntYearIds);
}
