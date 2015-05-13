package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICardPrintUserDAO;
import com.itgrids.partyanalyst.model.CardPrintUser;

public class CardPrintUserDAO extends GenericDaoHibernate<CardPrintUser, Long> implements ICardPrintUserDAO{

	public CardPrintUserDAO() {
		super(CardPrintUser.class);
		// TODO Auto-generated constructor stub
	}

	
	public List checkUserEixsts(Long userId)
	{
		Query query = getSession().createQuery("select model.cardPrintUserId from CardPrintUser model where model.cardPrintUserId = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}
}
