package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardSenderDAO;
import com.itgrids.partyanalyst.model.CardSender;

public class CardSenderDAO extends GenericDaoHibernate<CardSender, Long> implements ICardSenderDAO{
	
	public CardSenderDAO(){
		super(CardSender.class);
	}

}
