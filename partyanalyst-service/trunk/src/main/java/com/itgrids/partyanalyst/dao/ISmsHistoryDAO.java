package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SmsHistory;

public interface ISmsHistoryDAO extends GenericDao<SmsHistory, Long> {

	public List<SmsHistory> findByHistoryId(Long historyId);
	
	public List<SmsHistory> findBySentDate(String sentDate);
	
	public List<SmsHistory> findByMobileNumber(String mobileNumber);
	
	public List<SmsHistory> findByLocationType(String locationType);
	
	public List<SmsHistory> findByLocationId(Long locationId);
	
	public List<Long> getTotalSmsSentByUserAfterRenewal(Long trackId);
}