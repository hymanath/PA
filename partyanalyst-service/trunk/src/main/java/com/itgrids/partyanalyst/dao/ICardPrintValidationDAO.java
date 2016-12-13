package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CardPrintValidation;

public interface ICardPrintValidationDAO extends GenericDao<CardPrintValidation,Long>{

	public List<Object[]> getValidatedCardsCountsForBoxNos(List<String> boxNos);
	public List<Object[]> getErrorCardsCountsForBoxNos(List<String> boxNos);
}
