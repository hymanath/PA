package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetails;

public interface ISelfAppraisalCandidateDetailsDAO extends GenericDao<SelfAppraisalCandidateDetails, Long> {
	public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate);
}
