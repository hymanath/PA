package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardPrintValidationUserDAO;
import com.itgrids.partyanalyst.model.CardPrintValidationUser;

public class CardPrintValidationUserDAO extends GenericDaoHibernate<CardPrintValidationUser, Long> implements ICardPrintValidationUserDAO{

	public CardPrintValidationUserDAO() {
		super(CardPrintValidationUser.class);
	}
 }

