package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;

public class OpinionPollResultDAOHibernateTest extends BaseDaoTestCase{

	private IOpinionPollResultDAO opinionPollResultDAO;

	public IOpinionPollResultDAO getOpinionPollResultDAO() {
		return opinionPollResultDAO;
	}

	public void setOpinionPollResultDAO(IOpinionPollResultDAO opinionPollResultDAO) {
		this.opinionPollResultDAO = opinionPollResultDAO;
	}
	
	public void testGet(){
		List result = opinionPollResultDAO.getOpinionPollAnswersForAQuestionByQuestionId(1l);		
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			System.out.print(parms[0].toString());
			System.out.print(parms[1].toString());
			System.out.print(parms[2].toString());
			System.out.println(new Long(parms[3].toString()));
		}			
	}
}
