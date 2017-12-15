package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
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
	public List<Object[]> getPmPetitionDocumentByPetition(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmPetitionDocumentId, model.document.path from PmPetitionDocument model where model.petitionId =:petitionId");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	public List<Long> getPmPetitionDocumentIds(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append("select  model.pmPetitionDocumentId from PmPetitionDocument model where model.isDeleted='N' and  model.petitionId =:petitionId ");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	public Integer updatePmpetitionDocuments(List<Long> petitiionDocIds){
		StringBuilder str = new StringBuilder();
		str.append(" upadate PmPetitionDocument model set model.isDeleted ='Y' where model.pmPetitionDocumentId in (:petitiionDocIds) " );
		Query query =getSession().createQuery(str.toString());
		query.setParameter("petitiionDocIds", petitiionDocIds);
		return query.executeUpdate();
	}
}
