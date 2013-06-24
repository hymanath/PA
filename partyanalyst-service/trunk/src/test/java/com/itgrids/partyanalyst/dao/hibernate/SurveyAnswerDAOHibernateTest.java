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
	
	/*public void testGetCasteWiseSurveyInfo(){
		List<Long> questionIds = new ArrayList<Long>();
		questionIds.add(34l);
		List<Object[]>  valuesList = surveyAnswerDAO.getCasteWiseSurveyInfo(questionIds);
		for(Object[] value : valuesList ){
			for(Object val:value){
				System.out.print(val.toString()+" : ");
			}
			System.out.println("");
		}
	}*/
	
	/*public void testgetsurveyDetailsBasedOnGivenAgeRange()
	{
		List<Long> questionIds = new ArrayList<Long>();
		questionIds.add(9l);
		questionIds.add(10l);
		questionIds.add(11l);
		questionIds.add(12l);
		questionIds.add(13l);
		List<Object[]> values = surveyAnswerDAO.getsurveyDetailsForAbove60Years(questionIds,"61");
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2] +":"+ parms[3] +":"+ parms[4]);
		}
	}*/
	/*public void testgetsurveyDetailsBasedOnGivenAgeRange()
	{
		List<Long> questionIds = new ArrayList<Long>();
		questionIds.add(9l);
		questionIds.add(10l);
		questionIds.add(11l);
		questionIds.add(12l);
		List<Object[]> values = surveyAnswerDAO.getGenderWiseSurveyAnalysis(questionIds,"male");
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2] +":"+ parms[3] +":"+ parms[4]);
		}
	}*/
	public void testgetsurveyDetailsBasedOnGivenAgeRange()
	{
		List<Long> questionIds = new ArrayList<Long>();
		questionIds.add(9l);
		questionIds.add(10l);
		questionIds.add(11l);
		questionIds.add(12l);
		List<Object[]> values = surveyAnswerDAO.getOptionWiseSurveyAnalysis(questionIds);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2] +":"+ parms[3] +":"+ parms[4]);
		}
	}
}
