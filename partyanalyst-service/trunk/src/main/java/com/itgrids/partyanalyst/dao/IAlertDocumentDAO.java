package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertDocument;

public interface IAlertDocumentDAO extends GenericDao<AlertDocument, Long> {
	
	public List<Object[]> getDocumentsForAlert(Long alertId);
	public int deleteDocument(Long docId);
}
