package com.itgrids.partyanalyst.dao.hibernate;



import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVerificationConversationDAO;
import com.itgrids.partyanalyst.model.VerificationConversation;

public class VerificationConversationDAO extends GenericDaoHibernate<VerificationConversation, Long> implements IVerificationConversationDAO {

	public VerificationConversationDAO(){
		super(VerificationConversation.class);
	}

}
