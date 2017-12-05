package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionDesignationDAO;
import com.itgrids.model.PetitionDesignation;

@Repository
public class PetitionDesignationDAO extends GenericDaoHibernate<PetitionDesignation, Long> implements IPetitionDesignationDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public PetitionDesignationDAO() {
		super(PetitionDesignation.class);
		
	}
	
	public List<Object[]> getAllpetitionDesignationList(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select model.petitionDesignationId,model.designationName from PetitionDesignation model where model.isDeleted='N'   order by model.designationName asc ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}

	public List<Object[]> getAllReferredCandidateDesignationList(){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select distinct  model.petitionDesignation.petitionDesignationId, " +
					" model.petitionDesignation.designationName from PetitionReffererCandidate model where model.petitionDesignation.isDeleted='N' " +
					" order by model.petitionDesignation.designationName asc ");
			Query qry = getSession().createQuery(sb.toString());
			return qry.list();
		}
	
	public List<Object[]> getGivenPetitionCandidateDesignationList(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  model.petitionReffererCandidate.petitionDesignation.petitionDesignationId, " +
				" model.petitionReffererCandidate.petitionDesignation.designationName from PetitionRefferer model where model.petitionReffererCandidate.petitionDesignation.isDeleted='N' " +
				" order by model.petitionReffererCandidate.petitionDesignation.designationName asc ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}
	
	public List<Object[]> getGivenpetitionReprDesignationsList(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  model.petitionDesignation.petitionDesignationId, " +
				" model.petitionDesignation.designationName from PetitionMember model where model.petitionDesignation.isDeleted='N' " +
				" order by model.petitionDesignation.designationName asc ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}

}
