package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaTextMessageDAO;
import com.itgrids.partyanalyst.model.KaizalaTextMessage;

public class KaizalaTextMessageDAO extends GenericDaoHibernate<KaizalaTextMessage, Long> implements IKaizalaTextMessageDAO{
	public KaizalaTextMessageDAO() {
		super(KaizalaTextMessage.class); 
	}
	
}