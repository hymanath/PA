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
	
	public List<Object[]> getPrintStatusWiseRecordsCount(){
		
		Query query = getSession().createQuery("" +
		" select    model.printStatus , count(maxPrintDetailsId) " +
		" from      MaxPrintDetails model " +
		" group by  model.printStatus");
		
		return query.list();
	}
	
   public List<Object[]> getConstWisePrintStatusWiseRecordsCount(){
		
		Query query = getSession().createQuery("" +
		" select    model.constituency.constituencyId,model.constituency.name,model.district.districtId ,model.district.districtName," +//3
		"           model.printStatus , count(maxPrintDetailsId) " +//5
		" from      MaxPrintDetails model " +
		" group by  model.constituency.constituencyId , model.printStatus " +
		" order by  model.district.districtName , model.constituency.name ");
		
		return query.list();
	}
   
   
}

