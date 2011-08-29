package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.model.Problem;

public class ProblemDAO extends GenericDaoHibernate<Problem, Long> implements IProblemDAO{

	public ProblemDAO(){
		super(Problem.class);
	}
	@SuppressWarnings("unchecked")
	public List<String> getProblemReferenceNo(String refNo){
		return getHibernateTemplate().find("select model.referenceNo from Problem model where referenceNo=?", refNo);
		
		
	}
	
	
	
}
