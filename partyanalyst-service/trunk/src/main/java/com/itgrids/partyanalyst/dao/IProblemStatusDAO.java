package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemStatus;

public interface IProblemStatusDAO extends GenericDao<ProblemStatus, Long>{

	@SuppressWarnings("unchecked")
	public List getDefaultProblemStatus(String statusValues);
	
	public List<ProblemStatus> getByStatus(String status);
}
