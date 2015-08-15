package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHSurveyAnswersDAO;
import com.itgrids.partyanalyst.model.HHSurveyAnswers;

public class HHSurveyAnswerDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHSurveyAnswersDAO hhSurveyAnswersDAO;
	
	public IHHSurveyAnswersDAO getHhSurveyAnswersDAO() {
		return hhSurveyAnswersDAO;
	}

	public void setHhSurveyAnswersDAO(IHHSurveyAnswersDAO hhSurveyAnswersDAO) {
		this.hhSurveyAnswersDAO = hhSurveyAnswersDAO;
	}


	/*public void test(){
		List<HHSurveyAnswers> list=hhSurveyAnswersDAO.getSurveyAnswersByHouseHoldId(21l);
		System.out.println(list.size());
	}*/



	/*public void test(){
		List<Object[]> list = hhSurveyAnswersDAO.getQuestionWiseSummary(2l, 282l);
		System.out.println(list.size());
	}*/
	
	/*public void test(){
		List<Object[]> list = hhSurveyAnswersDAO.getQuestionWiseSummaryCount(2l, 282l);
		System.out.println(list.size());
	}*/
	
	/*public void test(){
		List<Object[]> list = hhSurveyAnswersDAO.getVoterAndNonVotersUnderOption(5l, 3349l);
		System.out.println(list.size());
	}*/
	
	/*public void test(){
		List<Object[]> list = hhSurveyAnswersDAO.getQuestionWiseSummaryCountByPanchayat(2l, 282l);
		System.out.println(list.size());
	}*/
	
	/*public void test(){
		List<Object[]> list = hhSurveyAnswersDAO.getHouseHoldsOfPanchayatWithOption(5l, 3303l);
		System.out.println(list.size());
	}*/
	
	public void test(){
		
		ArrayList<Long> List = new ArrayList<Long>();
		List.add(3280l);
		List.add(3281l);
		List.add(3283l);
		List.add(3284l);
		List.add(3285l);
		List.add(3286l);
		List.add(3287l);
		List.add(3288l);
		List.add(3289l);
		List.add(3290l);
		List.add(3291l);
		List.add(3292l);
		List.add(3293l);
		List.add(3294l);
		List.add(3295l);
		List.add(3296l);
		List.add(3297l);
		List.add(3298l);
		List.add(3299l);
		List.add(3300l);
		List.add(3301l);
		List.add(3302l);
		List.add(3303l);
		List.add(3304l);
		List.add(3305l);
		List.add(3306l);
		List.add(3307l);
		List.add(3308l);
		List.add(3309l);
		List.add(3310l);
		List.add(3311l);
		List.add(3312l);
		List.add(3313l);
		List.add(3314l);
		List.add(3315l);
		List.add(3316l);
		List.add(3317l);
		List.add(3318l);
		List.add(3319l);
		List.add(3320l);
		List.add(3321l);
		List.add(3322l);
		List.add(3323l);
		List.add(3324l);
		List.add(3325l);
		List.add(3326l);
		List.add(3327l);
		List.add(3328l);
		List.add(3329l);
		List.add(3330l);
		List.add(3331l);
		List.add(3332l);
		List.add(3334l);
		List.add(3335l);
		List.add(3336l);
		List.add(3337l);
		List.add(3338l);
		List.add(3339l);
		List.add(3340l);
		List.add(3341l);
		List.add(3342l);
		List.add(3343l);
		List.add(3344l);
		List.add(3345l);
		List.add(3346l);
		List.add(3347l);
		List.add(3348l);
		List.add(3349l);
		List.add(3350l);
		List.add(3351l);
		List.add(3352l);
		List.add(3353l);
		List.add(3354l);
		List.add(3355l);
		List.add(3356l);
		List.add(3357l);
		List.add(3358l);
		List.add(3359l);
		List.add(3360l);
		List.add(3361l);
		List.add(3362l);
		List.add(3363l);
		List.add(3364l);
		List.add(3365l);
		List.add(3366l);
		List.add(3367l);
		List.add(5433l);
		List.add(18925l);
		List.add(21015l);
		
		List<Object[]> list = hhSurveyAnswersDAO.getHouseHoldsOfPanchayatsWithOption(44l, List);
		if(list!=null && list.size()>0){
			System.out.println("VOTER ID\t CARD NO\t NAME \t PANCHAYATID \t PANCHAYAT NAME \t HOUSENO \t VOTERID \t MOBILE \t  RELATIVE \t  RELATION \t  TEHSILID \t  TEHSILNAME \t");
			for(Object[] obj:list){
				System.out.println(obj[0].toString()+"\t"+obj[4].toString()+"\t"+obj[3].toString()+"\t"
			+obj[5].toString()+"\t"+obj[6].toString()+"\t"+"#"+obj[2].toString()+"\t"+obj[8].toString()+
			"\t"+obj[9].toString()+"\t"+obj[10].toString()+"\t"+obj[11].toString()+"\t"
			+obj[12].toString()+"\t"+obj[13].toString());
			}
		}
		//System.out.println(list.size());


	}

}
