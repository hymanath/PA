package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothInchargeCommittee;

public interface IBoothInchargeCommitteeDAO extends GenericDao<BoothInchargeCommittee, Long>{
	
	public Long getElectionBoothDetails(Date startDate, Date endDate,Long locationTypeId, Long locationValue, String type,
			List<Long> committeeEnrollmentYearsIdsLst);

}
