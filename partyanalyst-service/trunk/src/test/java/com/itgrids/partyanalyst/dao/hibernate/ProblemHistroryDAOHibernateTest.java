package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.model.ProblemHistory;
import com.itgrids.partyanalyst.utils.IConstants;
public class ProblemHistroryDAOHibernateTest extends BaseDaoTestCase {

	private IProblemHistoryDAO problemHistoryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITownshipDAO townshipDAO;
	private IConstituencyDAO constituencyDAO;
	private IHamletDAO hamletDAO;
	private ITehsilDAO tehsilDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	
	
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
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

	
/*	@SuppressWarnings("unchecked")
	public void testGetProblemHistoryIdByHamletIds(){
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(1));
		list.add(new Long(2));
	
		List list1 = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(list,IConstants.HAMLET_LEVEL);
		System.out.println(list1.size());
	}*/
	
	/*public void testGetByLocations(){
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(232)); 
		List list1 = problemHistoryDAO.getAllProblemHistoryIdsForGivenLocationByTheirIds(list,IConstants.CONSTITUENCY_LEVEL,IConstants.TRUE);
		System.out.println(list1.size());
		if(list1.size()!=0){
			for(int i=0;i<list.size();i++){
				Object[] parms = (Object[])list1.get(i);
				
				System.out.println("prob"+parms[0].toString());
				System.out.println("desc"+parms[1].toString());
				System.out.println("scope"+parms[2].toString());
				System.out.println("ilv"+parms[3].toString());
				System.out.println("ion"+parms[4].toString());
				System.out.println("name"+parms[5].toString());
				System.out.println("pid"+parms[6].toString());
				System.out.println("phid"+parms[7].toString());
				
			}
		}
		
	}*/
	
	/*public void testFindProblemCompleteInfo(){
		
		List list1 = problemHistoryDAO.findProblemCompleteInfo(59l);
		System.out.println(list1.size());
		if(list1.size()!=0){
			for(int i=0;i<list1.size();i++){
				Object[] parms = (Object[])list1.get(i);
				
				System.out.println("prob"+parms[0].toString());
				System.out.println("desc"+parms[1].toString());
				System.out.println("scope"+parms[2].toString());
				System.out.println("ilv"+parms[3].toString());
				System.out.println("ion"+parms[4].toString());
				System.out.println("name"+parms[5].toString());
				System.out.println("pid"+parms[6].toString());
				System.out.println("phid"+parms[7].toString());
				System.out.println("pFrom"+parms[8].toString());
				System.out.println("from"+parms[9].toString());
				System.out.println("from"+parms[10].toString());
				
			}
		}
		
	}
	*/
	/*public void testgetAllPostedProblemsByAnanymousUserId(){
		
		List list1 = problemHistoryDAO.getAllPostedProblemsByAnanymousUserId(2L, 0, 5, "asc", "model.problemLocation.problemAndProblemSource.problem.problem", IConstants.TOTAL);
		System.out.println(list1.size());
	}*/
	/*
	public void testFindProblemsForSelectedSearchOptions(){
		List list1 = problemHistoryDAO.findProblemsForSelectedSearchOptions(1l, 0l, 1l, "state", "stateId", 0l,false,false);
		System.out.println(list1.size());
	}
	
	/*public void testGetAllpostedProblemCountOtherThanLoggedInUser()
	{
		List list = problemHistoryDAO.getAllPostedProblemCountOtherThanLoggedInUser(2L);
		System.out.println(list.get(0));
	}*/
	/*
	public void testGetAllRecordsCountForPostedProblemsByAnanymousUserId(){
		Long count = problemHistoryDAO.getAllRecordsCountForPostedProblemsByAnanymousUserId(2L, IConstants.TOTAL);
		System.out.println("Total "+count);
	}
	
	
	@Test
	public void  testFindProblemsGroupesByDepartment(){
		
		List<ProblemHistory> pblmHistory = problemHistoryDAO.findProblemsForSelectedSearchOptions(19L, 3L, 1L, "district", "districtId", 0L, false, true);
		
		System.out.println(" Total Problems " + pblmHistory.size());
	}*/
	/*
	@Test
	public void testLatestProblemsForUser(){
		
		List<ProblemHistory> problemsList = problemHistoryDAO.getRecentPostedProblemsForAUserByCount(1L, 1L,0, 5) ;
		
		for(ProblemHistory ph:problemsList){
			
			System.out.println(" Date :" + ph.getDateUpdated());
		}
		
		
	}
	
	@Test
	public void testProblemsForAUserCount(){
		
		Long count = problemHistoryDAO.getRecentPostedProblemsCountForAUserByProblemStatus(1L, 2L);
		
		System.out.println(" Total Problems :" + count);
	}*/
	
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetProblemsOfUserBetweenDates(){
		
		List countValues = problemHistoryDAO.getProblemsPostedForAUserInBetweenDates(9L, 0, 5);
		
		if(countValues != null && countValues.size() > 0){
			
			Iterator lstItr = countValues.listIterator();
			while(lstItr.hasNext()){
				
				Object[] value = (Object[])lstItr.next();
				
				Long problemsCount = (Long)value[0];
				Date postedDate    = (Date)value[1];
				//Integer year = (Integer)value[2];
				String Status = (String)value[2];
				
				//String month = new DateFormatSymbols().getMonths()[postedDate-1];

				
				System.out.println(" Month :" + postedDate + " In -- > " + Status +" -->"+ problemsCount);
				
			}
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Test
	public void testGetProblemsOfUserBetweenDates(){
		
		List countValues = problemHistoryDAO.getProblemsPostedForAUserInBetweenMonths(1L, 0, 10);
		
        if(countValues != null && countValues.size() > 0){
			
			Iterator lstItr = countValues.listIterator();
			while(lstItr.hasNext()){
				
				Object[] value = (Object[])lstItr.next();
				
				Long problemsCount = (Long)value[0];
				Integer postedDate    = (Integer)value[1];
				String status      = (String)value[2];
				Integer year = (Integer)value[3];
				
				String month = new DateFormatSymbols().getMonths()[postedDate-1];
					
				System.out.println(" Month :" + month + " -- > " + "Year -->"+ year.toString() + " -->" + problemsCount + " Status : " + status);
				
			}
		}
		
		List count = problemHistoryDAO.getProblemsCountPostedByAUserInBetweenMonths(1L);
		
		
		for(int i=0;i<count.size();i++){
		Object val = (Object)count.get(i);
		
		  System.out.println(" Date :" + (Integer) val);
		}
		System.out.println(" count :" + count.size());
		
		
	}


	@SuppressWarnings("unchecked")
	@Test
	public void testGetProblemsForAnUserByStatus() throws Exception{
		
		
		 DateFormat format = 
			 new SimpleDateFormat("yyyy-MM-dd");
		 Date startDate = 
			 format.parse("2011-08-17");
		 Date endDate = 
			format.parse("2011-08-18");
		 
		 List<ProblemHistory> problemHistoryLst = problemHistoryDAO.getDifferentLifeCycleProblemsOfAUserPostedBetweenDates(1L,0L,startDate,endDate, 0, 10);
		 
		if(problemHistoryLst != null){
			
			int i=1;
			for(ProblemHistory pbHistory:problemHistoryLst){
				
				Problem problem = pbHistory.getProblemLocation().getProblemAndProblemSource().getProblem();
				
				System.out.println( i++ + ".) Problem : " + problem.getProblem());
				System.out.println(" Updated date :" + pbHistory.getDateUpdated());
				System.out.println(" Problem Status : " + pbHistory.getProblemStatus().getStatus());
				System.out.println("...............................................................");
			}
		}
	    
	}
	
	/*public void testGetTotalProblemsCountForAnUserInARegion()
	{
		List list = problemHistoryDAO.getTotalProblemsCountForAnUserInARegion(1l,3l,19l);
		System.out.println(list.size());
	}*/
	
	
	/*public void testGetStatusWiseProblemsForAnUserInARegion()
	{
		//String status = " and model.problemStatus.status = 'FIXED' ";
		String status = "";
		List<ProblemHistory> list = problemHistoryDAO.getStatusWiseProblemsForAnUserInARegion(1l,3l,19l,status);
		
		for(ProblemHistory problemHistory : list)
		{
			System.out.println(problemHistory.getProblemStatus().getStatus());
			Set<AssignedProblemProgress> set = problemHistory.getAssignedProblemProgresses();
			AssignedProblemProgress asp = null;
			for(AssignedProblemProgress assignedProblemProgress : set)
			{
				if(asp == null)
				asp = assignedProblemProgress;
				else if(asp.getAssignedProblemProgressId() < assignedProblemProgress.getAssignedProblemProgressId())
				asp = assignedProblemProgress;
			}
			if(asp != null && asp.getCadre() != null)
				System.out.println("==========="+asp.getCadre().getFirstName()+"======="+asp.getAssignedProblemProgressId());
			
		}
		
		System.out.println(list.size());
	}*/
	
	/*public void testGetTotalProblemsStatusForAnUser()
	{
		List<Object> list = problemHistoryDAO.getTotalProblemsStatusForAnUser(1l);
		System.out.println(list.size());
		
		for(Object obj:list)
		{
			System.out.println(obj.toString());
		}
	}
	public void testGetStatusWiseProblemsForAnUser()
	{
		String status = "";
		List<ProblemHistory> list = problemHistoryDAO.getStatusWiseProblemsForAnUser(1l,status);
		System.out.println(list.size());
	}*/
	
	@SuppressWarnings("unchecked")
	public void testGetCompleteProblemDetailsByName(){
		String mobileNum ="9676696760";
		String name ="Suresh";
		String ref ="refid";
		String query ="";
		Date startDate=null;
		Date endDate=null;
		DateFormat format = 
			 new SimpleDateFormat("yyyy-MM-dd");
		 
		try {
			 startDate = (Date)format.parse("2011-08-17");
			
			//endDate = (Date)format.parse("2011-08-18");
			
			query+="date(model.dateUpdated) =?";
		} catch (ParseException e1) {
				e1.printStackTrace();
		}
		
		
		 //query+= "model.problemLocation.problemAndProblemSource.problemExternalSource.name like '%"+name+"%' or model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.firstName like '%"+name+"%' ) and model.problemLocation.problemAndProblemSource.user.registrationId in(1,285)";
		//query +="and model.problemLocation.problemAndProblemSource.user.mobile ="+mobileNum+"";
		
		//query+="model.dateUpdated >='"+startDate+"' and model.dateUpdated <='"+endDate+"'";
		List<Object[]> result = problemHistoryDAO.getCompleteProblemDetailsBySearchString(query,startDate,endDate);
		
		for(Object[] resObj:result){
			System.out.println(resObj[0].toString());
			
			
		}
		
	}
	/*public void testGetProblemDetailsByName(){
		
     List<ProblemHistory> result = problemHistoryDAO.getProblemHistoryBasedOnId(77l);
		
		for(ProblemHistory resObj:result){
			System.out.println(resObj.toString());
			
	}
		System.out.println(result.get(0).getProblemLocation().getProblemAndProblemSource().getProblem().getDescription());
	}
	@SuppressWarnings("unchecked")
	public void testFindProblemCompleteInfo(){
		
		List list=problemHistoryDAO.findProblemCompleteInfo(77l);
		
		System.out.println(list.size());
	}*/
	
	public void testCheckUserFileUploadRight()
	{
		List<Object[]> list = problemHistoryDAO.checkUserFileUploadRight(10l,20l);
		System.out.println(list.size());
		
		for(Object obj:list)
		{
			System.out.println(obj.toString());
		}
	}
}	

