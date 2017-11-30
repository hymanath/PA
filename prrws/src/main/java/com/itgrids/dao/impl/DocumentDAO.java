package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDocumentDAO;
import com.itgrids.model.Document;

@Repository
public class DocumentDAO extends GenericDaoHibernate<Document, Long> implements IDocumentDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	public DocumentDAO() {
		super(Document.class);
	}
}
