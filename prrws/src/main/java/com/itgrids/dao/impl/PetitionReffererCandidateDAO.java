package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionReffererCandidateDAO;
import com.itgrids.model.PetitionReffererCandidate;

@Repository
public class PetitionReffererCandidateDAO extends GenericDaoHibernate<PetitionReffererCandidate, Long> implements IPetitionReffererCandidateDAO {

	public PetitionReffererCandidateDAO(){
		super(PetitionReffererCandidate.class);
	}
	
	@Autowired
	SessionFactory sessionFactory;
}
