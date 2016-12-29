package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardPrintStatusDAO;
import com.itgrids.partyanalyst.model.CardPrintStatus;

public class CardPrintStatusDAO extends GenericDaoHibernate<CardPrintStatus, Long> implements ICardPrintStatusDAO {

	public CardPrintStatusDAO(){
		super(CardPrintStatus.class);
	}

}
