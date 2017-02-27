package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtOrderDocuments;

public interface IGovtOrderDocumentsDAO extends GenericDao<GovtOrderDocuments, Long>{
	public List<Object[]> getGovtOrderDocumentsPath(Long govtOrderId);

}
