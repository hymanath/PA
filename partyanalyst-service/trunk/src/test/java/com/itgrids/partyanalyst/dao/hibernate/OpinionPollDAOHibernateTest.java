package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import org.appfuse.dao.BaseDaoTestCase;
import java.sql.SQLException;
import java.util.List;

import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.OpinionPollQuestions;
import com.itgrids.partyanalyst.model.OpinionPollQuestionOptions;
import com.itgrids.partyanalyst.model.OpinionPollResult;
import com.itgrids.partyanalyst.model.OpinionPoll;
import com.itgrids.partyanalyst.model.QuestionsRepository;

import com.itgrids.partyanalyst.dao.IOpinionPollDAO;


public class OpinionPollDAOHibernateTest extends BaseDaoTestCase {
	
	private IOpinionPollDAO opinionPollDAO;

	public void setOpinionPollDAO(IOpinionPollDAO opinionPollDAO) {
		this.opinionPollDAO = opinionPollDAO;
	}

	public void test(){
		opinionPollDAO.getAll();		
	}
}
