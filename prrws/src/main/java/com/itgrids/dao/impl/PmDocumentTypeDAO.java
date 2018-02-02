package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDocumentTypeDAO;
import com.itgrids.model.PmDocumentType;

@Repository
public class PmDocumentTypeDAO extends GenericDaoHibernate<PmDocumentType, Long> implements IPmDocumentTypeDAO {

	@Autowired
	SessionFactory sessionFactory; 
	public PmDocumentTypeDAO() {
		super(PmDocumentType.class);
		
	}

	

}
