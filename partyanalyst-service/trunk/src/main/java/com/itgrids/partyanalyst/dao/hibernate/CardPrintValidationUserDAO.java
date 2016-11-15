package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICardPrintValidationUserDAO;
import com.itgrids.partyanalyst.model.CardPrintValidationUser;

public class CardPrintValidationUserDAO extends GenericDaoHibernate<CardPrintValidationUser, Long> implements ICardPrintValidationUserDAO{

	public CardPrintValidationUserDAO() {
		super(CardPrintValidationUser.class);
	}
	
	public  Object[] getCardPrinterUserDetails(String username,String password){
		
		Query query = getSession().createQuery("" +
		"select model.cardPrintValidationUserId, model.name,model.username , model.password , model.mobileno " +
		"from   CardPrintValidationUser model " +
		"where  model.username = :username and model.password = :password and model.isActive = 'Y' and model.isDeleted = 'N' ");
		
		query.setParameter("username",username);
		query.setParameter("password",password);
		
		return (Object[])query.uniqueResult();
	}
	
 }

