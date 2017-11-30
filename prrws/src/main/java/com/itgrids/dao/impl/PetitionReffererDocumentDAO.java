package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionReffererDocumentDAO;
import com.itgrids.model.PetitionReffererDocument;

@Repository
public class PetitionReffererDocumentDAO extends GenericDaoHibernate<PetitionReffererDocument, Long> implements IPetitionReffererDocumentDAO {

	@Autowired
	SessionFactory sessionFactory;

	public PetitionReffererDocumentDAO() {
		super(PetitionReffererDocument.class);
	}
}
