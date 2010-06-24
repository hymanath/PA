package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SmsHistory;

public interface ISmsHistoryDAO extends GenericDao<SmsHistory, Long> {

	List<SmsHistory> findByHistoryId(Long historyId);
	
	List<SmsHistory> findBySentDate(String sentDate);
	
	List<SmsHistory> findByMobileNumber(String mobileNumber);
	
	List<SmsHistory> findByLocationType(String locationType);
	
	List<SmsHistory> findByLocationId(Long locationId);
	
}