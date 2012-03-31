package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.CandidatePageCustomPages;

public interface ICandidatePageCustomPagesDAO extends GenericDao<CandidatePageCustomPages,Long>{
	
	public List<Object[]> getCustomPagesOfACandidatePage(Long candidateId);

}
