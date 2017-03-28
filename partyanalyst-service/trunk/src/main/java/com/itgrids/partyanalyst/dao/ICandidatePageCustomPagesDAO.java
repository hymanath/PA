package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.CandidatePageCustomPages;

public interface ICandidatePageCustomPagesDAO extends GenericDao<CandidatePageCustomPages,Long>{
	
	public List<Object[]> getCustomPagesOfACandidatePage(Long candidateId);
	
	public List<Object[]> getCandidateCustomPage(Long pageId);
	
	public List<CandidatePageCustomPages> candidateExistsOrNot(Long pageId,String customPageName);
	
	public List<Object> getCustomPageId(Long pageId);
	
	

}
