package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyAnswerDAO;

public class SurveyAnswerDAOHibernateTest extends BaseDaoTestCase {

	private ISurveyAnswerDAO surveyAnswerDAO;

	public void setSurveyAnswerDAO(ISurveyAnswerDAO surveyAnswerDAO) {
		this.surveyAnswerDAO = surveyAnswerDAO;
	}
	/*public void testGetAll(){
		surveyAnswerDAO.getAll();
	}*/
	
	public void testGetCasteWiseSurveyInfo(){
		List<Long> questionIds = new ArrayList<Long>();
		questionIds.add(34l);
		List<Object[]>  valuesList = surveyAnswerDAO.getCasteWiseSurveyInfo(questionIds);
		for(Object[] value : valuesList ){
			for(Object val:value){
				System.out.print(val.toString()+" : ");
			}
			System.out.println("");
		}
	}
}
