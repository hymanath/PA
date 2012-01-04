package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;
import com.itgrids.partyanalyst.model.MessageToCandidate;

public class MessageToCandidateDAO extends GenericDaoHibernate<MessageToCandidate, Long> implements	IMessageToCandidateDAO {
	public MessageToCandidateDAO() {
		super(MessageToCandidate.class);
	}

}
