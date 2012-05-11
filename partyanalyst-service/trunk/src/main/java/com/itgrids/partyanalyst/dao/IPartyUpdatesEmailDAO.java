package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;

public interface IPartyUpdatesEmailDAO extends GenericDao<PartyUpdatesEmail,Long>{
	
	public List<Object> getPartyUpdatesEmail(String emailId ,Long candidateId);
	
	public List<PartyUpdatesEmail> getAllSubscriberDetails();

}


