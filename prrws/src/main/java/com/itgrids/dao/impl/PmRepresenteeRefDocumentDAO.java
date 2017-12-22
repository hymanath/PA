package com.itgrids.dao.impl;

import java.util.Date;
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
		str.append("select distinct model.pmRepresenteeRefDetailsId, model.document.path from PmRepresenteeRefDocument model where model.pmRepresenteeRefDetails.petitionId =:petitionId");
		Query query =getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	public List<Long> getPmRepresenteeRefDocumentIds(List<Long> representeeRefDetailsIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeRefDocumentId from PmRepresenteeRefDocument model where "
				+ " model.isDeleted ='N' and model.pmRepresenteeRefDetailsId in (:representeeRefDetailsIds) ");
		Query query =getSession().createQuery(sb.toString());
		query.setParameterList("representeeRefDetailsIds", representeeRefDetailsIds);
		return query.list();
	}
	public int updatePmPmRepresenteeRefDocumens(List<Long> representeeRefDocIds,Date updatedTime,Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" update PmRepresenteeRefDocument model set model.isDeleted ='Y',model.updatedTime =:updatedTime, model.updatedUserId =:userId  " +
				" where model.pmRepresenteeRefDocumentId in (:representeeRefDocIds) ");
		Query query =getSession().createQuery(sb.toString());
		query.setParameterList("representeeRefDocIds", representeeRefDocIds);
		query.setParameter("updatedTime", updatedTime);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}
}
