package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ApplicationDocument;

public interface IApplicationDocumentDAO extends GenericDao<ApplicationDocument, Long>{

	public Integer deleteNominatedUploadedFile(List<Long> applicatnDocIdList);
	public List<Object[]> getNominatedPostReport(Long tdpCadreId);
	public List<Object[]> getNominatedPostDocumentDetails(Set<Long> candidateIds);
	public List<Object[]> getApplicationDocuments(Long tdpCadreId,String searchType,Long nominateCandId,Long applicationId);
}
