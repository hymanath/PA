package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionWorkDocumentDAO;
import com.itgrids.model.PetitionWorkDocument;

@Repository
public class PetitionWorkDocumentDAO extends GenericDaoHibernate<PetitionWorkDocument, Long> implements IPetitionWorkDocumentDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PetitionWorkDocumentDAO(){
		super(PetitionWorkDocument.class);
	}

}
