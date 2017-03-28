package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardPrintValidationTrackDAO;
import com.itgrids.partyanalyst.model.CardPrintValidationTrack;

public class CardPrintValidationTrackDAO extends GenericDaoHibernate<CardPrintValidationTrack, Long> implements ICardPrintValidationTrackDAO{

	public CardPrintValidationTrackDAO() {
		super(CardPrintValidationTrack.class);
	}
	

}
