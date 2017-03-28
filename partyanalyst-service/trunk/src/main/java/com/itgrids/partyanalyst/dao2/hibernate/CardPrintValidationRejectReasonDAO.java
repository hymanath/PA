package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICardPrintValidationRejectReasonDAO;
import com.itgrids.partyanalyst.model.CardPrintValidationRejectReason;

public class CardPrintValidationRejectReasonDAO extends GenericDaoHibernate<CardPrintValidationRejectReason, Long> implements ICardPrintValidationRejectReasonDAO{

	public CardPrintValidationRejectReasonDAO() {
		super(CardPrintValidationRejectReason.class);
	}
	
	public List<Long> checkRejectReasonsByCardPrintValidationId(Long cardPrintValidationId){
		
		Query query = getSession().createQuery("" +
		"select model.cardPrintValidationRejectReasonId " +
		"from   CardPrintValidationRejectReason model where model.cardPrintValidationId =:cardPrintValidationId and model.isDeleted = 'N' ");
		query.setParameter("cardPrintValidationId", cardPrintValidationId);
		return query.list();
	}
	
	public Integer deleteCardPrintValidationRejectReasonIds(List<Long> ids)
	{
		Query query = getSession().createQuery("update CardPrintValidationRejectReason model set model.isDeleted = 'Y'   " +
				" where model.cardPrintValidationRejectReasonId in (:ids) ");
		query.setParameterList("ids", ids);
		return query.executeUpdate();
	}
	
}