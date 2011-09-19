package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICallTrackingProblemDAO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;

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
    public void testSearchCallTrackingProblem(){
    	
    	CallTrackingVO callTrackingVO = new CallTrackingVO();
    	callTrackingVO.setProblemPurpose("other new");
    	List results = callTrackingProblemDAO.searchCallTrackingProblem(callTrackingVO,null);
    	System.out.println(results.size());
    	
    }
}
