package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IZebraPrintDetailsDAO;
import com.itgrids.cardprint.model.ZebraPrintDetails;

public class ZebraPrintDetailsDAO  extends GenericDaoHibernate<ZebraPrintDetails, Long> implements IZebraPrintDetailsDAO {

	public ZebraPrintDetailsDAO(){
		super(ZebraPrintDetails.class);
	}

	public List<Object[]>  getPrintDetailsByConstituencyId(Long constituencyId){
		Query query = getSession().createQuery("" +
		" select  model.tdpCadreCardPrintId , model.printTime , model.serialNumber , model.printStatus , " +//3
		"         model.printCode , model.printDesc , model.printerSerialNumber , model.boxNo , model.pcNo, model.outerBoxNo " +//9
		" from   ZebraPrintDetails model " +
		" where  model.constituencyId = :constituencyId");
		query.setParameter("constituencyId",constituencyId );
		return query.list();
	}
	
	public List<Object[]> getPrintStatusWiseRecordsCount(){
		
		Query query = getSession().createQuery("" +
		" select    model.printStatus , count(zebraPrintDetailsId) " +
		" from      ZebraPrintDetails model " +
		" group by  model.printStatus");
		
		return query.list();
	}
	
	 public List<Object[]> getConstWisePrintStatusWiseRecordsCount(){
			
			Query query = getSession().createQuery("" +
			" select    model.constituency.constituencyId,model.constituency.name,model.district.districtId ,model.district.districtName," +
			"           model.printStatus , count(zebraPrintDetailsId) " +
			" from      ZebraPrintDetails model " +
			" group by  model.constituency.constituencyId , model.printStatus " +
			" order by  model.district.districtName , model.constituency.name ");
			
			return query.list();
		}
}
