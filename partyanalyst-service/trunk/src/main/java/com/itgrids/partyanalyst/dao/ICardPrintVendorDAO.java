package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CardPrintVendor;

public interface ICardPrintVendorDAO extends GenericDao<CardPrintVendor,Long>{
	public List<Object[]> getVendorNames();
}
