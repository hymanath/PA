package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
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
	/*
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
		List<ProblemHistory> list = problemHistoryDAO.findProblemLocationsByUserId(new Long(3), new Long(1));
		assertEquals(1,list.size());
	}
	
	public void testByHamletLocationId(){			
		List result = problemHistoryDAO.findProblemsForALocationsByHamletId(836l);
		assertEquals(1, result.size());				
	}
	
	public void testByLocationId(){
		List result = problemHistoryDAO.findCompleteProblems(153l);
		assertEquals(1, result.size());	
	}
	
	public void testByStatus(){
		List result = problemHistoryDAO.findProblemsByStatusForALocationsByConstituencyId("836","new");
		assertEquals(1, result.size());
	}
	public void testByLocation(){
		List result = problemHistoryDAO.findProblemsForALocationsByHamletId(66l);
		assertEquals(1, result.size());
	}*/
	/*public void testGetProblemsCountInAllStatusByLocationForAUser(){
		List result = problemHistoryDAO.getProblemsCountInAllStatusByLocation("231,232");
		System.out.println(result.size());
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(Long.parseLong(parms[0].toString())+"\t");
			System.out.print(parms[1].toString()+"\t");
			System.out.println(Long.parseLong(parms[2].toString()));
		}
	}*/
	
	/*public void testFindProblemsForALocationsByConstituencyId(){
		List list = problemHistoryDAO.findLatestProblemsByMandals("835,836,843,844", 2l);
		System.out.println(list.size());
		for(Object[] values:(List<Object[]>)list)
			System.out.println(values[2]+" "+values[7]);
	}*/
	
	/*public void testFindProblemsByDateAndLocation()throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
		System.out.println(sdf.parse("2010/15/02"));
		System.out.println(sdf.parse("2010/15/02"));
		List list = problemHistoryDAO.findProblemsByDateAndLocation("836,843,844", sdf.parse("1/12/2010"), sdf.parse("1/12/2010"));
		System.out.println(list.size());
	}*/
	
	/*public void testFindLatestProblemsGroupByDatePostedByMandalsAndStatus(){
		List list = problemHistoryDAO.findLatestProblemsGroupByDatePostedByMandalsAndStatus("835,836,843,844", "1,2,6");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			Object[] parms = (Object[])list.get(i);
			System.out.println(parms[0]+"\t"+parms[1]+"\t"+parms[2]);
		}
	}*/
	
	/*public void testDate(){
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat sdfOutput =  
	        new SimpleDateFormat  ("dd/MM/yyyy");
		for(int i=0; i<10; i++){
			calendar.setTime(new Date());
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -i);
			System.out.println("i::"+i+"::"+sdfOutput.format((Date) calendar.getTime()));
		}	  
	  
	}*/
	
	/*public void testGetAssignedProblemsProgress(){
		List list = problemHistoryDAO.getAssignedProblemsProgress(3l);
		System.out.println(list.size());
	}
	*/
	
	public void testFindProblemHistoryByProblemLocationAndStatusId(){
		List list = problemHistoryDAO.findProblemHistoryByProblemLocation(2l);
		System.out.println(list.size());
	}
}
