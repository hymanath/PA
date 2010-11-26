package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollQuestionsDAO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.utils.IConstants;


public class OpinionPollQuestionsDAOHibernateTest extends BaseDaoTestCase {
	
	private IOpinionPollQuestionsDAO opinionPollQuestionsDAO;

	public IOpinionPollQuestionsDAO getOpinionPollQuestionsDAO() {
		return opinionPollQuestionsDAO;
	}

	public void setOpinionPollQuestionsDAO(
			IOpinionPollQuestionsDAO opinionPollQuestionsDAO) {
		this.opinionPollQuestionsDAO = opinionPollQuestionsDAO;
	}
	
	/* public void testGetAllPollsForTheCurrentDay(){ 
		 List result  = opinionPollQuestionsDAO.getAllOpinionPolls(IConstants.TRUE);		
		 System.out.println(result.size());		 		 		
	 }*/
	
	public void testgetAllPollsForThePresentDay(){
		 List result  = opinionPollQuestionsDAO.getAllPollsForThePresentDay(getCurrentDateAndTime(),IConstants.TRUE);
		 for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				System.out.println(parms[0].toString());
				System.out.println(parms[1].toString());
				System.out.println(parms[2].toString());
				System.out.println(parms[3].toString());
		 }
	}
	
	public Date getCurrentDateAndTime(){
		try {
				java.util.Date now = new java.util.Date();
		        String DATE_FORMAT = "dd/MM/yyyy";
		        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		        String strDateNew = sdf.format(now);        
				now = sdf.parse(strDateNew);
			return now;
	    } catch (ParseException e) {
	    		e.printStackTrace();
			return null;
		}
}
}


