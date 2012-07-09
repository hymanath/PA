package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IClassifiedProblemsDAO;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class ClassifiedProblemsDAOHibernateTest extends BaseDaoTestCase{

	private IClassifiedProblemsDAO classifiedProblemsDAO;
	
	public void setClassifiedProblemsDAO(
			IClassifiedProblemsDAO classifiedProblemsDAO) {
		this.classifiedProblemsDAO = classifiedProblemsDAO;
	}
	
	public void test()
	{
		classifiedProblemsDAO.getAll();
	}
/*	public void testCheckIfProblemAlreadyClassified(){
		List<Long> clsfdPrblmIdLst=classifiedProblemsDAO.checkIfProblemAlreadyClassified(2L);
		if(clsfdPrblmIdLst!=null && clsfdPrblmIdLst.size()>0)
			System.out.println(clsfdPrblmIdLst.get(0));	
		else
			System.out.println("not available"); 
	}*/	
}
