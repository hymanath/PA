package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.utils.IConstants;
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
	
	/*public void testFindProblemHistoryByProblemLocationAndStatusId(){
		List list = problemHistoryDAO.findProblemHistoryByProblemLocation(2l);
		System.out.println(list.size());
	}*/

	/*public void testGetAllDates(){		
		Date currentDate = getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,0,IConstants.PRESENT_DAY);
		List list = null;
		try{
			list = problemHistoryDAO.getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(currentDate,IConstants.NEW,IConstants.FALSE);
			for(int i=0;i<list.size();i++){
				Object[] parms = (Object[])list.get(i);
				System.out.println(parms[0]+"\t\t"+parms[1]);
			}
			System.out.println("List size is:-"+list.size());
		}catch(Exception e){
		}	
	}
	
	public Date  getPresentPreviousAndCurrentDayDate(String dateFormat,int noOfDays,String choice) {
		int MILLIS_IN_DAY = 1000 * 60 * 60 * 24*noOfDays;
		String strDateNew="";
		Date currentDate = new Date();
		String DATE_FORMAT = dateFormat;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);		
		if(choice.equalsIgnoreCase(IConstants.FUTURE_DAY)){
			strDateNew = sdf.format(currentDate.getTime() + MILLIS_IN_DAY) ;
		}else{
			strDateNew = sdf.format(currentDate.getTime() - MILLIS_IN_DAY) ;
		}		
		try {
			currentDate = sdf.parse(strDateNew);
			return currentDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
*/
	/*public Date getDateBeforeDays(int days) {  
		long backDateMS = System.currentTimeMillis() - ((long)days) *24*60*60*1000;  
		Date backDate = new Date();  
		backDate.setTime(backDateMS);  		
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		 String strDateNew = sdf.format(backDate) ;
		 try {
			backDate = sdf.parse(strDateNew);
		} catch (ParseException e) {
			e.printStackTrace();
		}
					
		return backDate;  
	} 
	
	public void testGetProblemsCountInAWeek(){
		List<SelectOptionVO> result = null;
		try {
			System.out.println("0");
			List list = problemHistoryDAO.getAllNonApprovedProblemsBetweenDates(getDateBeforeDays(7),getDateBeforeDays(0),"NEW","false");
			System.out.println("Size--->"+list.size());
			System.out.println("1");
			 result = new ArrayList<SelectOptionVO>(0);
			System.out.println("2");
			for(int i=0;i<list.size();i++){
				Object[] parms = (Object[])list.get(i);
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)parms[0]);
				selectOptionVO.setName(parms[1].toString());
				result.add(selectOptionVO);
				System.out.println(parms[0]+"\t"+parms[1]);
			}		
			//return result;
		} catch(Exception e) {
			e.printStackTrace();
			//return null;
		}
		
	}*/
	
	/*public void testGet(){
		 int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
		    Date date = new Date();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		    String prevDate = dateFormat.format(date.getTime() - MILLIS_IN_DAY);
		    String currDate = dateFormat.format(date.getTime());
		    String nextDate = dateFormat.format(date.getTime() + MILLIS_IN_DAY);
		    System.out.println("Previous date: " + prevDate);
		    System.out.println("Currnent date: " + currDate);
		    System.out.println("Next date: " + nextDate);
		   
		  
	}*/
	
	/*public void testGet(){
		Date date1 = convertStringToDate("1/1/2010",IConstants.DATE_PATTERN);
		Date date2 = convertStringToDate("1/10/2010",IConstants.DATE_PATTERN);
		System.out.println(date1);
		System.out.println(date2);
		List list = problemHistoryDAO.getAllNonApprovedProblemsBetweenDatesWithCompleteData(date1,date2,IConstants.NEW,IConstants.FALSE);
		System.out.println(list.size());
	}
	public Date convertStringToDate(String givenDate,String dateFormat){
		 DateFormat formatter ; 
	     Date date = null; 
	     try{
              formatter = new SimpleDateFormat(dateFormat);
              date = (Date)formatter.parse(givenDate);
              return date;
	    }catch(Exception e){	    	
	    	  return date;
	    } 	    
	}*/
	
	public void testGet(){
		Integer[] xx = new Integer[2];
		xx[0] = 1;
		xx[1] = 2;
		acceptSelectedProblemsByAdmin(xx);
	}
	public void acceptSelectedProblemsByAdmin(Integer[] problemHistoryIds){
		for(int i=0;i<problemHistoryIds.length;i++){			
			ProblemHistory problemHistory = problemHistoryDAO.get(problemHistoryIds[i].longValue());
			//System.out.println(problemHistory.getProblemLocation().getProblemLocationId());
			problemHistory.setIsApproved("true");
			problemHistoryDAO.save(problemHistory);
			setComplete();
		}	
	}
}
