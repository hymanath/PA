package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDocumentTypeDAO;
import com.itgrids.model.DocumentType;

@Repository
public class DocumentTypeDAO extends GenericDaoHibernate<DocumentType, Long> implements IDocumentTypeDAO{

	public DocumentTypeDAO() {
		super(DocumentType.class);
	}

}
