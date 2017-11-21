package com.itgrids.partyanalyst.dao;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.JbCommittee;

   public interface IJbCommitteeDAO extends GenericDao<JbCommittee, Long> {
   public List<Object[]> getDistrictWiseCommitteeDetails(Date fromDate,Date endDate,String type,Set<Long> userAccessVals);
   public List<Object[]> getJbCommitteeStatusCount();
   public List<Object[]> getLocationWiseCommitteeDetailsForCommitteeLvl(Date fromDate,Date endDate,Long levelId,Long levelVal,Long committeeLvlId,String status);
   public List<Object[]> getJbCommitteeStatusByCommiteeId(Long committeeId);
}
