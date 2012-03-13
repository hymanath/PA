package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.MessageToParty;

public interface IMessageToPartyDAO extends GenericDao<MessageToParty, Long>{
	
	public List<Object[]> getMessageToParty(Long partyId, int startIndex,int resultsCount);
	
	public List<Long> getPartyMessageCount(Long partyId);
	
	public List getAllPartyMessages(Date firstDate, Date secondDate,String selectstatus);
	
    public Integer adminModifiedMessages(Long id,String message, String isApproved);

}
