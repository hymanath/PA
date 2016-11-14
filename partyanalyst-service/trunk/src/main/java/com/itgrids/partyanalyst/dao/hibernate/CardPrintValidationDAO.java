package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardPrintValidationDAO;
import com.itgrids.partyanalyst.model.CardPrintValidation;

public class CardPrintValidationDAO extends GenericDaoHibernate<CardPrintValidation, Long> implements ICardPrintValidationDAO{

	public CardPrintValidationDAO() {
		super(CardPrintValidation.class);
	}
 }
