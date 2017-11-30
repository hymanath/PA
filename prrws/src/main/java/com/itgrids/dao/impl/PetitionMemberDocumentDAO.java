package com.itgrids.dao.impl;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionMemberDocumentDAO;
import com.itgrids.model.PetitionMemberDocument;

@Repository
public class PetitionMemberDocumentDAO extends GenericDaoHibernate<PetitionMemberDocument, Long> implements IPetitionMemberDocumentDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PetitionMemberDocumentDAO() {
		super(PetitionMemberDocument.class);
		
	}

	

}
