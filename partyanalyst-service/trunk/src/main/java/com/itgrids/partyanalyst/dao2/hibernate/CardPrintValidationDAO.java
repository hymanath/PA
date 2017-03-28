package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
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
	
	//
	public List<Object[]> constWiseBoxesCountAndValidatedCardsCount(Long userId , Date fromDate , Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select TC.userAddress.constituency.constituencyId ,TC.userAddress.constituency.name,count(distinct CPV.boxNo),count(distinct CPV.tdpCadreId) " +
				  " from  CardPrintValidation CPV , TdpCadre TC " +
				  " where CPV.tdpCadreId = TC.tdpCadreId and " +
				  "       CPV.cardPrintValidationUserId = :userId ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(CPV.insertedTime) between :fromDate and :toDate ");
		}
		sb.append(" group by TC.userAddress.constituency.constituencyId " +
				"   order by TC.userAddress.constituency.name ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		return query.list();
	}
	
	public List<Object[]> constWiseAcceptedAndRejectedCards(Long userId , Date fromDate , Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select TC.userAddress.constituency.constituencyId ,CPV.printStatus,count(distinct CPV.tdpCadreId) " +
				  " from  CardPrintValidation CPV , TdpCadre TC " +
				  " where CPV.tdpCadreId = TC.tdpCadreId and " +
				  "       CPV.cardPrintValidationUserId = :userId ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(CPV.insertedTime) between :fromDate and :toDate ");
		}
		sb.append(" group by TC.userAddress.constituency.constituencyId,CPV.printStatus ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		return query.list();
	}
	public List<Object[]> getConstWiseBoxWiseValidatedCardsCount(Long userId , Date fromDate , Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select TC.userAddress.constituency.constituencyId ,CPV.boxNo,count(distinct CPV.tdpCadreId) " +//2
				  " from  CardPrintValidation CPV , TdpCadre TC " +
				  " where CPV.tdpCadreId = TC.tdpCadreId and " +
				  "       CPV.cardPrintValidationUserId = :userId ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(CPV.insertedTime) between :fromDate and :toDate ");
		}
		sb.append(" group by TC.userAddress.constituency.constituencyId,CPV.boxNo ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		return query.list();
	}
	
	public List<Object[]> getConstWiseBoxWiseStatusWiseCounts(Long userId , Date fromDate , Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select TC.userAddress.constituency.constituencyId ,CPV.boxNo,CPV.printStatus , count(distinct CPV.tdpCadreId) " +//3
				  " from  CardPrintValidation CPV , TdpCadre TC " +
				  " where CPV.tdpCadreId = TC.tdpCadreId and " +
				  "       CPV.cardPrintValidationUserId = :userId ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(CPV.insertedTime) between :fromDate and :toDate ");
		}
		sb.append(" group by TC.userAddress.constituency.constituencyId,CPV.boxNo,CPV.printStatus ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		return query.list();
	}
	
	public List<Object[]> getBoxWiseValidatedCardsCountByUser(Long userId , Date fromDate , Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select CPV.boxNo,count(distinct CPV.tdpCadreId),TC.userAddress.constituency.name  " +//2
				  " from  CardPrintValidation CPV , TdpCadre TC " +
				  " where CPV.tdpCadreId = TC.tdpCadreId and " +
				  "       CPV.cardPrintValidationUserId = :userId ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(CPV.insertedTime) between :fromDate and :toDate ");
		}
		sb.append(" group by CPV.boxNo " +
				  " order by TC.userAddress.constituency.name ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		return query.list();
	}
	public List<Object[]> getBoxWiseStatusWiseCountsByUser(Long userId , Date fromDate , Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select CPV.boxNo , CPV.printStatus , count(distinct CPV.tdpCadreId) " +//2
				  " from  CardPrintValidation CPV , TdpCadre TC " +
				  " where CPV.tdpCadreId = TC.tdpCadreId and " +
				  "       CPV.cardPrintValidationUserId = :userId ");
		if(fromDate != null && toDate != null){
			sb.append(" and date(CPV.insertedTime) between :fromDate and :toDate ");
		}
		sb.append(" group by CPV.boxNo , CPV.printStatus ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		
		return query.list();
	}
	
	public Long getCardPrintValidationIdByTdpCadreId(Long tdpCadreId){
		Query query = getSession().createQuery(" select model.cardPrintValidationId from  CardPrintValidation model where model.tdpCadreId =:tdpCadreId" );
		query.setParameter("tdpCadreId", tdpCadreId);
		return (Long)query.uniqueResult();
	}
 }
