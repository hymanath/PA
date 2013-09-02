package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SmsResponseDetails;

public interface ISmsResponseDetailsDAO extends GenericDao<SmsResponseDetails,Long>{
	
	public List<SmsResponseDetails> getVoiceSmsHistoryForAuser(Long userId);
	public List<Object[]> getVoiceSmsSentUserDetails(Date fromDate,Date toDate);
	public List<String> getResponseCodesForAnUser(Long userId);
	public List<SmsResponseDetails> getVoiceSmsHistoryForAllSubUsers(List<Long> userIds , Integer startIndex , Integer maxResults);
	public List<Long> getVoiceSmsHistoryCountForAllSubUsers(List<Long> userIds);






}
