package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.ProblemType;

public interface IProblemTypeDAO extends GenericDao<ProblemType, Long>{

	public List<Object[]> getProblemTypes();
}
