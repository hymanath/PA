package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CardPrintValidation;

public interface ICardPrintValidationDAO extends GenericDao<CardPrintValidation,Long>{

	public List<Object[]> getValidatedCardsCountsForBoxNos(List<String> boxNos);
	public List<Object[]> getErrorCardsCountsForBoxNos(List<String> boxNos);
	
	public List<Object[]> constWiseBoxesCountAndValidatedCardsCount(Long userId , Date fromDate , Date toDate);
	public List<Object[]> constWiseAcceptedAndRejectedCards(Long userId , Date fromDate , Date toDate);
	public List<Object[]> getConstWiseBoxWiseValidatedCardsCount(Long userId , Date fromDate , Date toDate);
	public List<Object[]> getConstWiseBoxWiseStatusWiseCounts(Long userId , Date fromDate , Date toDate);
	
	public List<Object[]> getBoxWiseValidatedCardsCountByUser(Long userId , Date fromDate , Date toDate);
	public List<Object[]> getBoxWiseStatusWiseCountsByUser(Long userId , Date fromDate , Date toDate);
	
	public Long getCardPrintValidationIdByTdpCadreId(Long tdpCadreId);
}
