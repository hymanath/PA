package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaEvents;
import com.itgrids.partyanalyst.model.KaizalaGroupDocument;
import com.itgrids.partyanalyst.model.KaizalaGroupDocumentType;

public interface IKaizalaGroupDocumentTypeDAO extends GenericDao<KaizalaGroupDocumentType, Long> {
	public Long getGroupDocumentTypeId(String documentType);
}
