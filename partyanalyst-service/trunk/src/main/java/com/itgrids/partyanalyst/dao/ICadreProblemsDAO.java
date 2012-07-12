package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.CadreProblems;

public interface ICadreProblemsDAO extends GenericDao<CadreProblems,Long>{
	
	public List<Object[]> getCadreDetailsAndMobileNoByProblemId(Long problemId);

}
