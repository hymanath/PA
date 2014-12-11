package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IZebraPrintOnlineShipDAO;
import com.itgrids.partyanalyst.model.ZebraPrintOnlineShip;
import com.itgrids.partyanalyst.utils.IConstants;



public class ZebraPrintOnlineShipDAO extends GenericDaoHibernate<ZebraPrintOnlineShip, Long> implements IZebraPrintOnlineShipDAO{

	public ZebraPrintOnlineShipDAO() {
		super(ZebraPrintOnlineShip.class);
	}
	
	
	public List<Object[]> getCadreShippingAddressDetials(String searchType,Long locationId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.tdpCadre.firstname, model.tdpCadre.relativename,model.memberShipMember, model.shipAddress , model.mobileNo, model.tdpCadre.userAddress.district.districtName, model.tdpCadre.userAddress.constituency.name " +
				" from ZebraPrintOnlineShip model  where model.tdpCadre.enrollmentYear = 2014 and model.tdpCadre.isDeleted = 'N' and model.shipAddress is not null ");
		
			
		if(searchType != null && searchType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			if(locationId.longValue() != 0L){
				str.append(" and model.tdpCadre.userAddress.district.districtId = :locationId ");
			}
		}
		
		Query query = getSession().createQuery(str.toString()); 
		
		if(locationId.longValue() != 0L){
			query.setParameter("locationId", locationId);
		}
		
	
		return query.list();
	}
}
