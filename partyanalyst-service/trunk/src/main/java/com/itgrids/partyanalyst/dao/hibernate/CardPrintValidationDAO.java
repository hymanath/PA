package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICardPrintValidationDAO;
import com.itgrids.partyanalyst.model.CardPrintValidation;

public class CardPrintValidationDAO extends GenericDaoHibernate<CardPrintValidation, Long> implements ICardPrintValidationDAO{

	public CardPrintValidationDAO() {
		super(CardPrintValidation.class);
	}
	
	public List<Object[]> getValidatedCardsCountsForBoxNos(List<String> boxNos){
		Query query = getSession().createQuery("select model.boxNo," +
								" count(model.cardPrintValidationId)" +
								" from CardPrintValidation model" +
								" where model.boxNo in (:boxNos)" +
								" group by model.boxNo");
		query.setParameterList("boxNos", boxNos);
		
		return query.list();
	}
	
	public List<Object[]> getErrorCardsCountsForBoxNos(List<String> boxNos){
		Query query = getSession().createQuery("select distinct model.boxNo," +
								" count(model.cardPrintValidationId)" +
								" from CardPrintValidation model" +
								" where model.boxNo in (:boxNos)" +
								" and model.printStatus = 'R'" +
								" group by model.boxNo");
		query.setParameterList("boxNos", boxNos);
		
		return query.list();
	}
 }
