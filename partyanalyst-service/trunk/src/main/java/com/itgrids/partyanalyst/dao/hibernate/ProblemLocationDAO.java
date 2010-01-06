package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.model.ProblemLocation;

public class ProblemLocationDAO extends GenericDaoHibernate<ProblemLocation, Long> implements IProblemLocationDAO{

	public ProblemLocationDAO(){
		super(ProblemLocation.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProblemLocation> findByHamletId(Long hamletId){
		return getHibernateTemplate().find("from ProblemLocation model where model.hamlet.hamletId = ?", hamletId);
	}
	
}
