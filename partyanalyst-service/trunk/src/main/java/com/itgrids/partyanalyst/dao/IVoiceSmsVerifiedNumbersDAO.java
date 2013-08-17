package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoiceSmsVerifiedNumbers;

public interface IVoiceSmsVerifiedNumbersDAO extends GenericDao<VoiceSmsVerifiedNumbers,Long>{
	
	public List<Long> getVerifiedNumbersOfUser(Long userId);


}
