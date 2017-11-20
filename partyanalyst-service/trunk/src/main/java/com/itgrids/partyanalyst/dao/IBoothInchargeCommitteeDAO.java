package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothInchargeCommittee;

public interface IBoothInchargeCommitteeDAO extends GenericDao<BoothInchargeCommittee, Long>{
	
	public Long getElectionBoothDetails(Date startDate, Date endDate,Long locationTypeId, Long locationValue, String type,
			List<Long> committeeEnrollmentYearsIdsLst);

	public List<Object[]> getBoothInchargeCountDetails(Long locationId,Long locationValue, List<Long> committeeEnrlmntYrIds,
			Date startDate, Date endDate);
	public int unLockTheBoothInchargeCommittee(Long userId, List<Long> boothCommitteesIdsList);
	public List<Object[]> getCommitteeFinalizedBoothsListforUnlock(List<Long> assemblyIdsList);
}
