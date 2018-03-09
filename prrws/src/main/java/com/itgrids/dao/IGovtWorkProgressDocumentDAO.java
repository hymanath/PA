package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWorkProgressDocument;

public interface IGovtWorkProgressDocumentDAO extends GenericDao<GovtWorkProgressDocument, Long>{
	public List<Object[]> getStatusDocumentsOfGovtWork(Long workId); 
	public List<Object[]> getStatusWiseDocs(List<Long> workZoneIds,Long locationScopeId,List<Long> locationValues,Long statusId,Date startDate,Date endDate);
	public List<Object[]> getRecentWorkDocuments(Long workTypeId);
}
