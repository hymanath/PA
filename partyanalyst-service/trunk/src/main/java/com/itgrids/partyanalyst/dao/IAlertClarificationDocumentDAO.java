package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertClarification;
import com.itgrids.partyanalyst.model.AlertClarificationDocument;

public interface IAlertClarificationDocumentDAO extends GenericDao<AlertClarificationDocument, Long>{
	
	 public List<Object[]> getAlertAttachments(Long alertId);
	 public Integer updateDocumentStatus(Long documentId);
}
