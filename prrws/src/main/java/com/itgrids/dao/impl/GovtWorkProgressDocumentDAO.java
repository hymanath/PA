package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressDocumentDAO;
import com.itgrids.model.GovtWorkProgressDocument;

@Repository
public class GovtWorkProgressDocumentDAO extends GenericDaoHibernate<GovtWorkProgressDocument, Long> implements IGovtWorkProgressDocumentDAO{

	public GovtWorkProgressDocumentDAO() {
		super(GovtWorkProgressDocument.class);
	}

}
