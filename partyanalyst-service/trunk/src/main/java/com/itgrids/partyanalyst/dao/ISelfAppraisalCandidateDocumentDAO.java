package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDocument;

public interface ISelfAppraisalCandidateDocumentDAO extends GenericDao<SelfAppraisalCandidateDocument, Long> {

	public List<Object[]> getSelfAppraisalDocumentDetails(Long candidateId,Long toursMonthId);
	public List<Object[]> getDocumentsOfCandidates(Date fromDate,Date toDate,Set<Long> candidateIds,List<Long> monthyearIds);
	public int deleteDocumentByDocument(List<Long> documents);
	public List<Object[]> getCandiateDocument(List<Long> monthYearIds,Long candiateId);
	public List<Object[]> getSelfAppraisalDocuments(Long cadreId,Long toursMonthId);
}
