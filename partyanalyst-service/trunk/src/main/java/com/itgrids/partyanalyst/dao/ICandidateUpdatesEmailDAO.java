package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.CandidateUpdatesEmail;

public interface ICandidateUpdatesEmailDAO extends GenericDao<CandidateUpdatesEmail,Long>{
	
	public List<Object> getCandidateUpdatesEmail(String emailId ,Long candidateId);
	
	public List<CandidateUpdatesEmail> getAllSubscriberDetails();
	
}
