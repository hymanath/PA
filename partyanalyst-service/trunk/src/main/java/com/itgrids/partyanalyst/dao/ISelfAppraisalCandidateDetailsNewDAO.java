package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsNew;

public interface ISelfAppraisalCandidateDetailsNewDAO extends GenericDao<SelfAppraisalCandidateDetailsNew, Long> {

	public List<String> getMonthAndYearOfBetweenDates(Date fromDate,Date toDate);
	
}
