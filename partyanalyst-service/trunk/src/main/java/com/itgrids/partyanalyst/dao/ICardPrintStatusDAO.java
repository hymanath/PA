package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CardPrintStatus;

public interface ICardPrintStatusDAO extends GenericDao<CardPrintStatus, Long> {
	
	public List<Object[]> getAllCardPrintStatus();
}
