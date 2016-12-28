package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDocument;

public interface ISelfAppraisalCandidateDocumentDAO extends GenericDao<SelfAppraisalCandidateDocument, Long> {

	public List<Object[]> getSelfAppraisalDocumentDetails(Long candidateId,Long year,Long month);
	
}
