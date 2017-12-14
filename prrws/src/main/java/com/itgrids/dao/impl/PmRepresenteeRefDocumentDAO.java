package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
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

	public List<Object[]> getPmRepresenteeRefDocumentByPetition(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmRepresenteeRefDocumentId, model.document.path from PmRepresenteeRefDocument model where model.pmRepresenteeRefDetails.petitionId =:petitionId");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
}
