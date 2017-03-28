package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VerificationDocuments;

public interface IVerificationDocumentsDAO extends GenericDao<VerificationDocuments, Long> {

	public List<Object[]> getAlertDocumentByAlertId(Long alertId);
}
