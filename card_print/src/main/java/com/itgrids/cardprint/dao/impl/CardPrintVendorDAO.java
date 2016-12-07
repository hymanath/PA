package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.ICardPrintVendorDAO;
import com.itgrids.cardprint.model.CardPrintVendor;

public class CardPrintVendorDAO extends GenericDaoHibernate<CardPrintVendor, Long> implements ICardPrintVendorDAO {

	public CardPrintVendorDAO(){
		super(CardPrintVendor.class);
	}
}
