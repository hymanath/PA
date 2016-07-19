package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ApplicationDocument;

public interface IApplicationDocumentDAO extends GenericDao<ApplicationDocument, Long>{

	public Integer deleteNominatedUploadedFile(List<Long> applicatnDocIdList);
}
