package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.SpecialPageUpdatesEmail;

public interface ISpecialPageUpdatesEmailDAO extends GenericDao<SpecialPageUpdatesEmail, Long>{
	
	public List<Object> getSpecialPageUpdatesEmail(String emailId ,Long specialPageId);
	
	public List<SpecialPageUpdatesEmail> getAllSubscriberDetails();

}
