package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollResultDAO;
import com.itgrids.partyanalyst.model.OpinionPollResult;

public class OpinionPollResultDAOHibernateTest extends BaseDaoTestCase{

	private IOpinionPollResultDAO opinionPollResultDAO;

	public IOpinionPollResultDAO getOpinionPollResultDAO() {
		return opinionPollResultDAO;
	}

	public void setOpinionPollResultDAO(IOpinionPollResultDAO opinionPollResultDAO) {
		this.opinionPollResultDAO = opinionPollResultDAO;
	}
	
	public void testGet(){
		List st = opinionPollResultDAO.getOpinionPollResultByQuestionAndOptionId(1l,3l);
		Long count = 0l;
		Long opinionPollResultId =0l;
		
		for(int i=0;i<st.size();i++){
			Object[] parms = (Object[])st.get(i);
			count = new Long(parms[0].toString());
			opinionPollResultId = new Long(parms[1].toString());			
		}
		System.out.println(count+"\t"+opinionPollResultId);
		//assertEquals(st.size(),1);
	}
}
