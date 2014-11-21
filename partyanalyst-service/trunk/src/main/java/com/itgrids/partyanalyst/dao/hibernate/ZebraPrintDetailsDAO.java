package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;
import com.itgrids.partyanalyst.model.ZebraPrintDetails;

public class ZebraPrintDetailsDAO extends GenericDaoHibernate<ZebraPrintDetails, Long> implements IZebraPrintDetailsDAO {

	public ZebraPrintDetailsDAO() {
		super(ZebraPrintDetails.class);		
	}
	
	public List<Object[]> getMemberShipCardPrintStatusByDate(List<Long> constituencyIds,Date fromDate, Date toDate, String type)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select UA.constituency.constituencyId,UA.constituency.name, UA.constituency.district.districtName, date(ZPD.insertedTime) , count(distinct ZPD.zebraPrintDetailsId) from ZebraPrintDetails ZPD, TdpCadre TD, UserAddress UA where ");
		queryStr.append(" TD.tdpCadreId = ZPD.tdpCadreId and TD.userAddress.userAddressId = UA.userAddressId and  ");
		queryStr.append("  TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and UA.constituency.constituencyId in (:constituencyIds)  ");
		
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and ( date(ZPD.insertedTime) >= :fromDate and date(ZPD.insertedTime) <= :toDate ) ");
		}
		
		if(type != null && type.equalsIgnoreCase("printStatus"))
		{
			queryStr.append(" and ( ZPD.printStatus like '%Y%' OR ZPD.printStatus like '%y%' ) ");
		}		
		else if(type != null && type.equalsIgnoreCase("errorStatus"))
		{
			queryStr.append(" and ( ZPD.errorStatus like '%Y%' OR ZPD.errorStatus like '%y%' ) ");
		}
		else
		{
			queryStr.append(" and ZPD.printStatus is not null  ");
		}
		queryStr.append(" group by UA.constituency.constituencyId, date(ZPD.insertedTime) order by date(ZPD.insertedTime) desc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("constituencyIds", constituencyIds);
		if(fromDate != null && toDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			
		}
		return query.list();
		
	}
}
