package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionLeadDAO;
import com.itgrids.model.PetitionLead;

@Repository
public class PetitionLeadDAO extends GenericDaoHibernate<PetitionLead, Long> implements IPetitionLeadDAO{
	
@Autowired
SessionFactory sessionFactory;

	PetitionLeadDAO(){
		super(PetitionLead.class);
	}
	
	public List<Object[]> getPetitionLeadDetailsList(){
		StringBuffer qryString = new StringBuffer();
		qryString.append("select model.petitionLeadId,model.lead from  PetitionLead model ");
		Query query = getSession().createQuery(qryString.toString());
		return query.list();
	}
}
