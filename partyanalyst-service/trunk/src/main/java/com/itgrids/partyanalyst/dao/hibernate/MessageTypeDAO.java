package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMessageTypeDAO;
import com.itgrids.partyanalyst.model.MessageType;

public class MessageTypeDAO extends GenericDaoHibernate<MessageType, Long>
		implements IMessageTypeDAO {

	public MessageTypeDAO() {
		super(MessageType.class);
	}


}
