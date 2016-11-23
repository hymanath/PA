package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardPrintValidationRejectReasonDAO;
import com.itgrids.partyanalyst.model.CardPrintValidationRejectReason;

public class CardPrintValidationRejectReasonDAO extends GenericDaoHibernate<CardPrintValidationRejectReason, Long> implements ICardPrintValidationRejectReasonDAO{

	public CardPrintValidationRejectReasonDAO() {
		super(CardPrintValidationRejectReason.class);
	}

}