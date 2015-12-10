package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;

public interface IActivityInfoDocumentDAO extends GenericDao<ActivityInfoDocument, Long>{
	public List<Object[]> getEventDocuments(EventDocumentVO inputVO);
}
