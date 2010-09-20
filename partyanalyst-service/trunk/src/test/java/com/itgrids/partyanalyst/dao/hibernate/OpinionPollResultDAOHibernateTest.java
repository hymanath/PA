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
		Long totalPolledVotes=0l;
		Double totalVotesPercentage = new Double(0);
		QuestionsOptionsVO question = new QuestionsOptionsVO();
		List<OptionVO> opinionPollQuestionAndPercentages = new ArrayList<OptionVO>(0);
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			totalPolledVotes+=new Long(parms[0].toString());
			question.setQuestion(parms[2].toString());			
		}		
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[])result.get(i);
			OptionVO optionVO = new OptionVO();
			optionVO.setOption(parms[1].toString());			
			optionVO.setPercentage(new BigDecimal((new Long(parms[0].toString())*100)/totalPolledVotes).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			opinionPollQuestionAndPercentages.add(optionVO);		
			System.out.println(optionVO.getPercentage()+"\t"+((new Long(parms[0].toString())*100)/totalPolledVotes));
		}		
		question.setOptions(opinionPollQuestionAndPercentages);				
	}
}
