package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOpinionPollCommentsDAO;
import com.itgrids.partyanalyst.model.OpinionPollComments;

public class OpinionPollCommentsDAO extends GenericDaoHibernate<OpinionPollComments,Long>  implements IOpinionPollCommentsDAO {	
	
	public OpinionPollCommentsDAO() {
		super(OpinionPollComments.class);
	}
}
