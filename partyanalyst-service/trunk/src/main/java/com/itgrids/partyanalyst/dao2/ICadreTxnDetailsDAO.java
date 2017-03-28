package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreTxnDetails;


public interface ICadreTxnDetailsDAO extends GenericDao<CadreTxnDetails, Long>{
	
	public List<Object[]> getTransactionDetailsByDates(Date today, Date yesterDay);
	
	public List<Long>  getTotalCadreSurveyTxnTeamSize(Date searchDate);
	

	public Integer updateCompleteStatus(String uniqueKey,Long constituencyId);
	public Long getPendingAmountForUser(String uniqueKey,Long constituencyId,Long userId);
	public Integer updatePendingAmount(Long pendingAmount,String uniqueKey,Long constituencyId,Long userId);

	public Long getUsersCount(Date searchDate,Date todayDate);
	public List<Object[]> getCompletedTransactionDetailsByDates(Date todayDate, Date searchDate);
	public Long getNotCompletedTransactionDetailsByDates(Date todayDate, Date searchDate);
	
	public List<Object[]> findLocationDetailsByAssemblyIds(List<Long> locationIds, String queryStr);
}
