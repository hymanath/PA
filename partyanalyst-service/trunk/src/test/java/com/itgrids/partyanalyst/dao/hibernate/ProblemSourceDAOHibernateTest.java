package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IInformationSourceDAO;
import com.itgrids.partyanalyst.model.InformationSource;

public class ProblemSourceDAOHibernateTest extends BaseDaoTestCase{

	private IInformationSourceDAO problemSourceDAO;

	public IInformationSourceDAO getProblemSourceDAO() {
		return problemSourceDAO;
	}

	public void setProblemSourceDAO(IInformationSourceDAO problemSourceDAO) {
		this.problemSourceDAO = problemSourceDAO;
	}

	public void testGetAll(){
		List<InformationSource> list = problemSourceDAO.getAll();
		assertEquals(1, list.size());
	}
}