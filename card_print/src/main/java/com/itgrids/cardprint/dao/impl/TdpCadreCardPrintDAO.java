package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.ITdpCadreCardPrintDAO;
import com.itgrids.cardprint.model.TdpCadreCardPrint;

public class TdpCadreCardPrintDAO extends GenericDaoHibernate<TdpCadreCardPrint, Long> implements ITdpCadreCardPrintDAO {

	public TdpCadreCardPrintDAO(){
		super(TdpCadreCardPrint.class);
	}
    
	public Long getCardPrintVendorIdByConstituencyId(Long constituencyId){
		
		Query query = getSession().createQuery("" +
		"select distinct model.cardPrintVendorId  " +
		"from   TdpCadreCardPrint model " +
		"where  model.constituencyId = :constituencyId ");
		query.setParameter("constituencyId",constituencyId );
		return (Long)query.uniqueResult();
	}
	
	public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
}
