package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoiceSmsResponseDetails;

public interface IVoiceSmsResponseDetailsDAO extends GenericDao<VoiceSmsResponseDetails,Long>{
	
	public List<VoiceSmsResponseDetails> getVoiceSmsHistoryForAuser(Long userId);
	public List<Object[]> getVoiceSmsSentUserDetails(Date fromDate,Date toDate);
	public List<String> getResponseCodesForAnUser(Long userId);
	public List<VoiceSmsResponseDetails> getVoiceSmsHistoryForAllSubUsers(List<Long> userIds);





}
