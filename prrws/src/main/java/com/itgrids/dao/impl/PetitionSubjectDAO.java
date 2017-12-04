package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionSubjectDAO;
import com.itgrids.model.PetitionSubject;


@Repository
public class PetitionSubjectDAO extends GenericDaoHibernate<PetitionSubject, Long> implements IPetitionSubjectDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public PetitionSubjectDAO() {
		super(PetitionSubject.class);
	}
	
	public List<Object[]> getPetitionSubjectList(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.petitionSubjectId,model.subject "+
				  " from PetitionSubject model " +
				  " where model.parentPetitionSubjectId is null and model.isDeleted = 'N' ");
		Query qry =getSession().createQuery(sb.toString());
		return qry.list();
	}

	public List<Object[]> getPetitionSubSubjectList(Long subjectId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.petitionSubjectId,model.subject "+
				  " from PetitionSubject model " +
				  " where model.parentPetitionSubjectId= :subjectId and model.isDeleted = 'N' ");
		Query qry =getSession().createQuery(sb.toString());
		qry.setParameter("subjectId", subjectId);
		return qry.list();
	}
	
}
