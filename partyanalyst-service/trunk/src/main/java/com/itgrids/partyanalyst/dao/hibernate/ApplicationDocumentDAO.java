package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IApplicationDocumentDAO;
import com.itgrids.partyanalyst.model.ApplicationDocument;

public class ApplicationDocumentDAO extends GenericDaoHibernate<ApplicationDocument, Long> implements IApplicationDocumentDAO{

	public ApplicationDocumentDAO() {
		super(ApplicationDocument.class);
		
	}

}
