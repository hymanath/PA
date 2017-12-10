package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmPetitionDocumentDAO;
import com.itgrids.model.PmPetitionDocument;

@Repository
public class PmPetitionDocumentDAO extends GenericDaoHibernate<PmPetitionDocument, Long> implements IPmPetitionDocumentDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PmPetitionDocumentDAO() {
		super(PmPetitionDocument.class);
	}

}
