package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDocumentDAO;
import com.itgrids.model.PmDocument;

@Repository
public class PmDocumentDAO extends GenericDaoHibernate<PmDocument, Long> implements IPmDocumentDAO {

	@Autowired
	SessionFactory sessionFactory; 
	
	PmDocumentDAO(){
		super(PmDocument.class);
	}
}
