package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardReceiverDAO;
import com.itgrids.partyanalyst.model.CardReceiver;

public class CardReceiverDAO extends GenericDaoHibernate<CardReceiver, Long> implements ICardReceiverDAO {

	public CardReceiverDAO() {
		super(CardReceiver.class);
	}

}
