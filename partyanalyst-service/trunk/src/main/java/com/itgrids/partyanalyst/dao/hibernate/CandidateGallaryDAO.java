package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICandidateGallaryDAO;
import com.itgrids.partyanalyst.model.CandidateGallary;

public class CandidateGallaryDAO extends GenericDaoHibernate<CandidateGallary, Long> implements ICandidateGallaryDAO{
	
	public CandidateGallaryDAO(){
		super(CandidateGallary.class);
	}
}
