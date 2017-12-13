package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDesignationDAO;
import com.itgrids.model.PmDesignation;

@Repository
public class PmDesignationDAO extends GenericDaoHibernate<PmDesignation, Long> implements IPmDesignationDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PmDesignationDAO() {
		super(PmDesignation.class);
		
	}


	public List<Object[]> getAllpetitionDesignationList(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select model.pmDesignationId,model.designation from PmDesignation model where model.isDeleted='N'   order by model.designation asc ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}

	public List<Object[]> getAllReferredCandidateDesignationList(){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select distinct  model.pmDesignation.pmDesignationId, " +
					" model.pmDesignation.designation from PmRefCandidateDesignation model where model.pmDesignation.isDeleted='N' " +
					" order by model.pmDesignation.orderNo asc ");
			Query qry = getSession().createQuery(sb.toString());
			return qry.list();
		}
	
	public List<Object[]> getGivenPetitionCandidateDesignationList(){
		return null;
	}
	
	public List<Object[]> getGivenpetitionReprDesignationsList(){
		return null;
	}

}
