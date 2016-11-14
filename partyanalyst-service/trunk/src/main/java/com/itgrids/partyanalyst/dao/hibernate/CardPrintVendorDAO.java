package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICardPrintVendorDAO;
import com.itgrids.partyanalyst.model.CardPrintVendor;

public class CardPrintVendorDAO extends GenericDaoHibernate<CardPrintVendor, Long> implements ICardPrintVendorDAO{

	public CardPrintVendorDAO() {
		super(CardPrintVendor.class);
		
	}
 }

