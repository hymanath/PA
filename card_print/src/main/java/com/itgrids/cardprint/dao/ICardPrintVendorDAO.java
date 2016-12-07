package com.itgrids.cardprint.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.cardprint.model.CardPrintVendor;

public interface ICardPrintVendorDAO extends GenericDao<CardPrintVendor, Long> {
	
	public List<Object[]> getAllVendors();
}
