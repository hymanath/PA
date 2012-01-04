package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMessageToPartyDAO;
import com.itgrids.partyanalyst.model.MessageToParty;

public class MessageToPartyDAO extends GenericDaoHibernate<MessageToParty,Long> implements IMessageToPartyDAO{
	public MessageToPartyDAO()
	{
		super(MessageToParty.class);
	} 
}
