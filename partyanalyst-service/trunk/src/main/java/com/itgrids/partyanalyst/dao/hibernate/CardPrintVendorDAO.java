package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICardPrintVendorDAO;
import com.itgrids.partyanalyst.model.CardPrintVendor;

public class CardPrintVendorDAO extends GenericDaoHibernate<CardPrintVendor, Long> implements ICardPrintVendorDAO{

	public CardPrintVendorDAO() {
		super(CardPrintVendor.class);
		
	}
	public List<Object[]> getVendorNames(){
	 Query query = getSession().createQuery("select model.cardPrintVendorId," +
	 		" model.vendorName" +
	 		" from CardPrintVendor model");
	 return query.list();
	 
	}
 }

