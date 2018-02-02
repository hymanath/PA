package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkDocumentDAO;
import com.itgrids.model.GovtWorkDocument;

@Repository
public class GovtWorkDocumentDAO extends GenericDaoHibernate<GovtWorkDocument, Long> implements IGovtWorkDocumentDAO{

	public GovtWorkDocumentDAO() {
		super(GovtWorkDocument.class);
	}

}
