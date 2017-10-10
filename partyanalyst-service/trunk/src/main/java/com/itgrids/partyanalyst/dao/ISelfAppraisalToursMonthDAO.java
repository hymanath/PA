package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalToursMonth;

public interface ISelfAppraisalToursMonthDAO extends GenericDao<SelfAppraisalToursMonth, Long> {
  
	public List<String> getMonthAndYear(Date fromDate,Date toDate);
	public List<Long> getMonthYearByTourMonths(List<String> monthYearList);
	public List<Long> getSelfAppraisalToursMonth(String toursMonth);
	public List<String> getSelfAppraisalMonthById(Long selfAppraisalToursMonthId);
	public List<Long> getLatestMonthYearId();
	public List<Object[]> getMonthAndYearDtls(List<String> monthYearList);
	public List<Object[]> getMonthDtls(List<String> monthYearList);
}
