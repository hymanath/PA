package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CardPrintValidationUser;

public interface ICardPrintValidationUserDAO extends GenericDao<CardPrintValidationUser,Long>{
	
	public  Object[] getCardPrinterUserDetails(String username,String password);
}
