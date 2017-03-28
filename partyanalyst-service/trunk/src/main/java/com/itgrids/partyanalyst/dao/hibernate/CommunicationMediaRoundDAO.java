package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommunicationMediaRoundDAO;
import com.itgrids.partyanalyst.model.CommunicationMediaRound;

public class CommunicationMediaRoundDAO extends GenericDaoHibernate<CommunicationMediaRound,Long> implements ICommunicationMediaRoundDAO{

	public CommunicationMediaRoundDAO()
	{
		super(CommunicationMediaRound.class);
	}

}
