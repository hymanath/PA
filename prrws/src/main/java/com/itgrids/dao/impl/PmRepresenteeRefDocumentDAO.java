package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeRefDocumentDAO;
import com.itgrids.model.PmRepresenteeRefDocument;

@Repository
public class PmRepresenteeRefDocumentDAO extends GenericDaoHibernate<PmRepresenteeRefDocument, Long> implements IPmRepresenteeRefDocumentDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeRefDocumentDAO() {
		super(PmRepresenteeRefDocument.class);
		
	}

	
}
