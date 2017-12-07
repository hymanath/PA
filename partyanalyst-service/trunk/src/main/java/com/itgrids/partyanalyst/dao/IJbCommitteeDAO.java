package com.itgrids.partyanalyst.dao;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.JbCommittee;

   public interface IJbCommitteeDAO extends GenericDao<JbCommittee, Long> {
   public List<Object[]> getDistrictWiseCommitteeDetails(Date fromDate,Date endDate,String type,Set<Long> userAccessVals,Set<Long> ids);//ids for constituency ids in case of prliament
   public List<Object[]> getJbCommitteeStatusCount(String type,Set<Long> locationIdsList);
   public List<Object[]> getLocationWiseCommitteeDetailsForCommitteeLvl(Date fromDate,Date endDate,Long levelId,List<Long> levelVal,Long committeeLvlId,Long status,List<Long> matchedConstIdsForUserId);
   public List<Object[]> getJbCommitteeStatusByCommiteeId(Long committeeId);
}
