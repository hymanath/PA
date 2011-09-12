package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IOpinionPollDAO;
import com.itgrids.partyanalyst.model.OpinionPoll;

public class OpinionPollDAO extends GenericDaoHibernate<OpinionPoll, Long> implements IOpinionPollDAO {

	public OpinionPollDAO() {
		super(OpinionPoll.class);
	}
}
