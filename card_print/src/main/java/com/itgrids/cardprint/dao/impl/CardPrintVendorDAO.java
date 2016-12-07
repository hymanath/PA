package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.ICardPrintVendorDAO;
import com.itgrids.cardprint.model.CardPrintVendor;

public class CardPrintVendorDAO extends GenericDaoHibernate<CardPrintVendor, Long> implements ICardPrintVendorDAO {

	public CardPrintVendorDAO(){
		super(CardPrintVendor.class);
	}
	
	public List<Object[]> getAllVendors(){
		Query query = getSession().createQuery(" select model.cardPrintVendorId , model.vendorName from CardPrintVendor model ");
		return query.list();
	}
}
