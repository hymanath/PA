package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDocumentTypeDAO;
import com.itgrids.partyanalyst.model.DocumentType;

public class DocumentTypeDAO extends GenericDaoHibernate<DocumentType, Long> implements IDocumentTypeDAO{

	public DocumentTypeDAO() {
		super(DocumentType.class);
		
	}

}
