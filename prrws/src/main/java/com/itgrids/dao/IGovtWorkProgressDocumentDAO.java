package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWorkProgressDocument;

public interface IGovtWorkProgressDocumentDAO extends GenericDao<GovtWorkProgressDocument, Long>{
	public List<Object[]> getStatusDocumentsOfGovtWork(Long workId); 
}
