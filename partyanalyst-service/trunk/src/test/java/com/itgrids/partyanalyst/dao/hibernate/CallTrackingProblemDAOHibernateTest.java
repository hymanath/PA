package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICallTrackingProblemDAO;

public class CallTrackingProblemDAOHibernateTest extends BaseDaoTestCase {
	
	private ICallTrackingProblemDAO callTrackingProblemDAO ;
	
    public void setCallTrackingProblemDAO(ICallTrackingProblemDAO callTrackingProblemDAO) {
		this.callTrackingProblemDAO = callTrackingProblemDAO;
	}
    /*public void test()
	{
    	callTrackingProblemDAO.getAll();
	}*/
   /* public void testGetProblemDetailbyMobileNo()
    {
    	List results = callTrackingProblemDAO.getProblemDetailbyMobileNo("1234567890");
    	System.out.println(results.size());
	}*/
   /* public Date getCurrentDateAndTime(){
		try {
		java.util.Date now = new java.util.Date();
        String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String strDateNew = sdf.format(now);        
			now = sdf.parse(strDateNew);
			System.out.println(now);
			return now;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
    public void testGetProblemDetailbyTodayDate()
    {
    	List results = callTrackingProblemDAO.getProblemDetailbyTodayDate(getCurrentDateAndTime());
    	System.out.println(results.size());
	}*/
    
   /* public void testDeleteProblemByProblemId(){
    	callTrackingProblemDAO.deleteProblemByProblemId(3L);
    }*/
    /*public void testSearchCallTrackingProblem(){
    	
    	CallTrackingVO callTrackingVO = new CallTrackingVO();
    	callTrackingVO.setProblemPurpose("other new");
    	List results = callTrackingProblemDAO.searchCallTrackingProblem(callTrackingVO,null);
    	System.out.println(results.size());
    	
    }*/
    
/*public void testGetProblemCount(){
	String DATE_FORMAT = "dd/MM/yyyy";
	String date1="18/09/2011";
	String date2="20/09/2011";
	Date fromDate = null;
	Date toDate = null;
	try{
	 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	  fromDate = (Date)sdf.parse(date1);
	  toDate = (Date)sdf.parse(date2);
	}catch(Exception e){}
    Long result	= callTrackingProblemDAO.getProblemCount(fromDate,toDate).get(0);
    	
    	System.out.println(result);
    }*/
    
   /* public void testGetProblemTypes(){
    	
        List<String> result	= callTrackingProblemDAO.getProblemTypes();
        	for(String res: result)
        	System.out.println(res);
        }*/
 /*public void testGetProblemByProblemPurpose(){
	 String DATE_FORMAT = "dd/MM/yyyy";
		String date1="18/09/2011";
		String date2="20/09/2011";
		Date fromDate = null;
		Date toDate = null;
		try{
		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		  fromDate = (Date)sdf.parse(date1);
		  toDate = (Date)sdf.parse(date2);
		}catch(Exception e){}
    	
        List<Object[]> result	= callTrackingProblemDAO.getProblemByProblemPurpose("Appointment",fromDate,toDate);
       
        	System.out.println(result.size());
        }*/
 
    public void testGetProblemCountDateByProblem(){
   	 String DATE_FORMAT = "dd/MM/yyyy";
   		String date1="18/09/2011";
   		String date2="20/09/2011";
   		Date fromDate = null;
   		Date toDate = null;
   		try{
   		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
   		  fromDate = (Date)sdf.parse(date1);
   		  toDate = (Date)sdf.parse(date2);
   		}catch(Exception e){}
       	
           List<Object[]> result	= callTrackingProblemDAO.getProblemCountDateByProblem("Appointment",fromDate,toDate);
          for(Object[] o: result){
           	System.out.println((Long)o[0]);
           	System.out.println(o[1]);
        	System.out.println(o[2]);
          }
           }
}
