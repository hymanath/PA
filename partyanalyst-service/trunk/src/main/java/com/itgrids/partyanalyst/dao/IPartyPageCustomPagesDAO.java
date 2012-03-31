package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyPageCustomPages;

public interface IPartyPageCustomPagesDAO extends GenericDao<PartyPageCustomPages,Long>{
	
	public List<Object[]> getCustomPagesOfAPartyPage(Long partyId);

}
