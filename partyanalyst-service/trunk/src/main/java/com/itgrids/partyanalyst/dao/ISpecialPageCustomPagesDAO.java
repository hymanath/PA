package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.SpecialPageCustomPages;

public interface ISpecialPageCustomPagesDAO extends GenericDao<SpecialPageCustomPages,Long>{
	
	public List<Object[]> getCustomPagesOfASpecialPage(Long specialPageId);
	
	public List<SpecialPageCustomPages> specialIdExistsOrNot(Long pageId,String customPageName);
	
	public List<Object[]> getSpecialCustomPage(Long pageId);
	
	public List<Object> getSpecialPageId(Long pageId);

}
