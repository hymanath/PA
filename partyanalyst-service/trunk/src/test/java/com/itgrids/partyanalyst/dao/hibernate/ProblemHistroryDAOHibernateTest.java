package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ProblemHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
public class ProblemHistroryDAOHibernateTest extends BaseDaoTestCase {

	private IProblemHistoryDAO problemHistoryDAO;
	private IHamletDAO hamletDAO;
	
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}
	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}

	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}

	public void testSave(){
		ProblemHistory obj = new ProblemHistory();
		problemHistoryDAO.save(obj);
		setComplete();
	}
	
	public void testGetAll(){
		List<ProblemHistory> list = problemHistoryDAO.getAll();
		assertEquals(1, list.size());
	}
	
	public void testGetByUserId(){
		List<ProblemHistory> list = problemHistoryDAO.findProblemLocationsByUserId(new Long(5), new Long(2));
		assertEquals(1, list.size());
	}
	
	public void testByHamletLocationId(){			
		List result = problemHistoryDAO.findProblemsForALocationsByHamletId(new Long(66));
		assertEquals(1, result.size());				
	}
}
