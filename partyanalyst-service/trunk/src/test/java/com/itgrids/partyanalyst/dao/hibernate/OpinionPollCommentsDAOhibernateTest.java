package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOpinionPollCommentsDAO;

public class OpinionPollCommentsDAOhibernateTest extends BaseDaoTestCase{

	private IOpinionPollCommentsDAO opinionPollCommentsDAO;

	public void setOpinionPollCommentsDAO(
			IOpinionPollCommentsDAO opinionPollCommentsDAO) {
		this.opinionPollCommentsDAO = opinionPollCommentsDAO;
	}
}
