package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IQuestionOptionsDAO;

public class QuestionOptionsDAOHibernateTest extends BaseDaoTestCase  {
	private IQuestionOptionsDAO questionOptionsDAO;

	public void setQuestionOptionsDAO(IQuestionOptionsDAO questionOptionsDAO) {
		this.questionOptionsDAO = questionOptionsDAO;
	}
	
	/*public void testGetAll(){
		questionOptionsDAO.getAll();
	}
	
	public void testGetOptionsForQuestions(){
		List<Long> questionIds = new ArrayList<Long>();
		questionIds.add(34l);
		List<Object[]>  valuesList = null;//questionOptionsDAO.getOptionsForQuestions(questionIds);
		for(Object[] value : valuesList ){
			for(Object val:value){
				System.out.print(val.toString()+" : ");
			}
			System.out.println("");
		}
	}*/
}
