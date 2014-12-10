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
	
	
	public List<Object[]> getCadreShippingAddressDetials(Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.zebraPrintOnlineShipId,model.tdpCadreId, model.voterName, model.image, model.shipAddress,model.memberShipMember  " +
				" from ZebraPrintOnlineShip model  where model.tdpCadre.enrollmentYear = 2014 and model.tdpCadre.isDeleted = 'N' ");		
		if(constituencyId.longValue() != 0L){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId = :constituencyId ");
		}
		Query query = getSession().createQuery(str.toString()); 
		
		if(constituencyId.longValue() != 0L){
			query.setParameter("constituencyId", constituencyId);
		}
		
	
		return query.list();
	}
}
