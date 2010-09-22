package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
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
	
	 public void testGetAllPollsForTheCurrentDay(){
		 
		 List<SelectOptionVO> allPolls = new ArrayList<SelectOptionVO>(0);		 
		 List result  = opinionPollQuestionsDAO.getAllOpinionPolls(IConstants.TRUE);
		 for(int i=0;i<result.size();i++){
			 Object[] parms = (Object[])result.get(i);
			 SelectOptionVO resultVo = new SelectOptionVO(); 
			 resultVo.setId(new Long(parms[0].toString()));
			 resultVo.setName(parms[1].toString());
			 allPolls.add(resultVo);
			 System.out.println(resultVo.getId()+"\t"+resultVo.getName());
		 }
		 System.out.println(result.size());
		 		 		
	 }
	 
}


