package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Problem;

public interface IProblemDAO extends GenericDao<Problem, Long>{
	
	public List<String> getProblemReferenceNo(String refNo);

}
