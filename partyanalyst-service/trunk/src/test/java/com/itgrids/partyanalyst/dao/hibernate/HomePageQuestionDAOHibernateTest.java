package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;


import com.itgrids.partyanalyst.dao.IHomePageQuestionDAO;
import com.itgrids.partyanalyst.model.HomePageQuestion;
import com.itgrids.partyanalyst.model.HomePageQuestionAnswers;

public class HomePageQuestionDAOHibernateTest extends BaseDaoTestCase{
	
	private IHomePageQuestionDAO homePageQuestionDAO;

	private HomePageQuestion homePageQuestion=new HomePageQuestion();
	public void setHomePageQuestionDAO(
			IHomePageQuestionDAO homePageQuestionDAO) {
		this.homePageQuestionDAO = homePageQuestionDAO;
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
	public void test(){
		
		
		List l=homePageQuestionDAO.getQuestionsForThePresentDay(getCurrentDateAndTime(),"TRUE");
		Object o[] = (Object[])l.get(0);
		System.out.println(o[0]);
		System.out.println(o[1]);
		
		
}
	
}