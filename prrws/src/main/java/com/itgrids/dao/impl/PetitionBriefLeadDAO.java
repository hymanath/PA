package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionBriefLeadDAO;
import com.itgrids.model.PetitionBriefLead;

@Repository
public class PetitionBriefLeadDAO extends GenericDaoHibernate<PetitionBriefLead, Long> implements IPetitionBriefLeadDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PetitionBriefLeadDAO(){
		super(PetitionBriefLead.class);
	}
	public List<Object[]> gePetitionBriefLeadDetailsList(){
		
		Query qry = getSession().createQuery(" select model.petitionBriefLeadId,model.breifLead from PetitionBriefLead model ");
		return qry.list();
	}
}
