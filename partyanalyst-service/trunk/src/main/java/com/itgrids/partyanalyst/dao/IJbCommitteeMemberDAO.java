package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.JbCommitteeMember;

  public interface IJbCommitteeMemberDAO extends GenericDao<JbCommitteeMember, Long> {
	  public List<Object[]> getCommitteMemebersByRoleIds(List<Long> roleIdsList,Date fromDate,Date toDate);
	  public List<Object[]> getCommitteeWiseTotalMemberAddedCount(String type,Set<Long> userAccessVals);
	  public List<Object[]> getCommitteeWiseTotalMemberAddedCount(Date fromDate,Date endDate,Long levelId,Long levelVal,Long committeeLvlId);
	  public Long getMemberApprovedCountByCommitteId(Long committeeId);
}
