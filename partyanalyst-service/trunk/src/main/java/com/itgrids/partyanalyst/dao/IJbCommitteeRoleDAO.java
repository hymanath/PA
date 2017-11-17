package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.JbCommitteeRole;

   public interface IJbCommitteeRoleDAO extends GenericDao<JbCommitteeRole, Long> {
	   public List<Object[]> getDesignationsIdsByCommitteeId(Long committeeId);
	   public  List<Object[]> getCommitteeWiseTotalMemberCount(String type,Set<Long> userAccessVals);
	   public  List<Object[]> getCommitteeLvlWiseTotalMemberCountInLocation(Date fromDate,Date endDate  ,Long levelId,Long levelVal, Long committeeLvlId);
	   public Long getTotalRoleMemberCountByCommitteId(Long committeeId);
}
