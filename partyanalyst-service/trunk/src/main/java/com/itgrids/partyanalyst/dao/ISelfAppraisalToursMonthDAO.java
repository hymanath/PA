package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalToursMonth;

public interface ISelfAppraisalToursMonthDAO extends GenericDao<SelfAppraisalToursMonth, Long> {

	public List<Long> getSelfAppraisalToursMonth(String toursMonth);
	
}
