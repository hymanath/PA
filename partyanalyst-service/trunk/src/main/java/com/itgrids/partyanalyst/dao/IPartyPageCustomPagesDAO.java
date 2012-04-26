package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyPageCustomPages;

public interface IPartyPageCustomPagesDAO extends GenericDao<PartyPageCustomPages,Long>{
	
	public List<Object[]> getCustomPagesOfAPartyPage(Long partyId);
	
	public List<PartyPageCustomPages> partyExistsOrNot(Long pageId,String customPageName);
	
	public List<Object[]> getPartyCustomPage(Long pageId);
	
	public List<Object> getPartyPageId(Long pageId);

}
