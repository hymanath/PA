package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICardPrintStatusDAO;
import com.itgrids.partyanalyst.model.CardPrintStatus;

public class CardPrintStatusDAO extends GenericDaoHibernate<CardPrintStatus, Long> implements ICardPrintStatusDAO {

	public CardPrintStatusDAO(){
		super(CardPrintStatus.class);
	}
    
	public List<Object[]> getAllCardPrintStatus(){
		Query query = getSession().createQuery("select model.cardPrintStatusId , model.cardPrintStatus from CardPrintStatus model ");
		return query.list();
	}
}
