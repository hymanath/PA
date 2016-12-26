package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IMaxPrintDetailsDAO;
import com.itgrids.cardprint.model.MaxPrintDetails;

public class MaxPrintDetailsDAO extends GenericDaoHibernate<MaxPrintDetails, Long> implements IMaxPrintDetailsDAO {

	public MaxPrintDetailsDAO(){
		super(MaxPrintDetails.class);
	}
	
	public List<Object[]>  getPrintDetailsByConstituencyId(Long constituencyId){
		Query query = getSession().createQuery("" +
		" select  model.tdpCadreCardPrintId , model.printTime , model.serialNumber , model.printStatus , " +
		"         model.printCode , model.printDesc , model.printerSerialNumber , model.boxNo , model.pcNo " +
		" from   MaxPrintDetails model " +
		" where  model.constituencyId = :constituencyId");
		query.setParameter("constituencyId",constituencyId );
		return query.list();
	}
}

