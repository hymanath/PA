package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoiceSmsResponseDetails;

public interface IVoiceSmsResponseDetailsDAO extends GenericDao<VoiceSmsResponseDetails,Long>{
	
	public List<VoiceSmsResponseDetails> getVoiceSmsHistoryForAuser(Long userId);
	public List<Object[]> getVoiceSmsSentUserDetails();
	public List<String> getResponseCodesForAnUser(Long userId);




}
